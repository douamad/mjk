package com.pirtol.mjk.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.pirtol.mjk.MjkApp;
import com.pirtol.mjk.domain.Creance;
import com.pirtol.mjk.repository.CreanceRepository;
import com.pirtol.mjk.service.CreanceService;
import com.pirtol.mjk.service.dto.CreanceDTO;
import com.pirtol.mjk.service.mapper.CreanceMapper;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link CreanceResource} REST controller.
 */
@SpringBootTest(classes = MjkApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class CreanceResourceIT {
    private static final String DEFAULT_REF = "AAAAAAAAAA";
    private static final String UPDATED_REF = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_PV_REC = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_PV_REC = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_NATURE_LITIGE = "AAAAAAAAAA";
    private static final String UPDATED_NATURE_LITIGE = "BBBBBBBBBB";

    private static final Float DEFAULT_MONTANT = 1F;
    private static final Float UPDATED_MONTANT = 2F;

    private static final Integer DEFAULT_NOMBRE_ECHEANCE = 1;
    private static final Integer UPDATED_NOMBRE_ECHEANCE = 2;

    private static final Float DEFAULT_TOTAL_RECOUVRE = 1F;
    private static final Float UPDATED_TOTAL_RECOUVRE = 2F;

    private static final Float DEFAULT_SOLDE_A_RECOUVRER = 1F;
    private static final Float UPDATED_SOLDE_A_RECOUVRER = 2F;

    @Autowired
    private CreanceRepository creanceRepository;

    @Autowired
    private CreanceMapper creanceMapper;

    @Autowired
    private CreanceService creanceService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCreanceMockMvc;

    private Creance creance;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Creance createEntity(EntityManager em) {
        Creance creance = new Creance()
            .ref(DEFAULT_REF)
            .date(DEFAULT_DATE)
            .datePVRec(DEFAULT_DATE_PV_REC)
            .natureLitige(DEFAULT_NATURE_LITIGE)
            .montant(DEFAULT_MONTANT)
            .nombreEcheance(DEFAULT_NOMBRE_ECHEANCE)
            .totalRecouvre(DEFAULT_TOTAL_RECOUVRE)
            .soldeARecouvrer(DEFAULT_SOLDE_A_RECOUVRER);
        return creance;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Creance createUpdatedEntity(EntityManager em) {
        Creance creance = new Creance()
            .ref(UPDATED_REF)
            .date(UPDATED_DATE)
            .datePVRec(UPDATED_DATE_PV_REC)
            .natureLitige(UPDATED_NATURE_LITIGE)
            .montant(UPDATED_MONTANT)
            .nombreEcheance(UPDATED_NOMBRE_ECHEANCE)
            .totalRecouvre(UPDATED_TOTAL_RECOUVRE)
            .soldeARecouvrer(UPDATED_SOLDE_A_RECOUVRER);
        return creance;
    }

    @BeforeEach
    public void initTest() {
        creance = createEntity(em);
    }

    @Test
    @Transactional
    public void createCreance() throws Exception {
        int databaseSizeBeforeCreate = creanceRepository.findAll().size();
        // Create the Creance
        CreanceDTO creanceDTO = creanceMapper.toDto(creance);
        restCreanceMockMvc
            .perform(post("/api/creances").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(creanceDTO)))
            .andExpect(status().isCreated());

        // Validate the Creance in the database
        List<Creance> creanceList = creanceRepository.findAll();
        assertThat(creanceList).hasSize(databaseSizeBeforeCreate + 1);
        Creance testCreance = creanceList.get(creanceList.size() - 1);
        assertThat(testCreance.getRef()).isEqualTo(DEFAULT_REF);
        assertThat(testCreance.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testCreance.getDatePVRec()).isEqualTo(DEFAULT_DATE_PV_REC);
        assertThat(testCreance.getNatureLitige()).isEqualTo(DEFAULT_NATURE_LITIGE);
        assertThat(testCreance.getMontant()).isEqualTo(DEFAULT_MONTANT);
        assertThat(testCreance.getNombreEcheance()).isEqualTo(DEFAULT_NOMBRE_ECHEANCE);
        assertThat(testCreance.getTotalRecouvre()).isEqualTo(DEFAULT_TOTAL_RECOUVRE);
        assertThat(testCreance.getSoldeARecouvrer()).isEqualTo(DEFAULT_SOLDE_A_RECOUVRER);
    }

    @Test
    @Transactional
    public void createCreanceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = creanceRepository.findAll().size();

        // Create the Creance with an existing ID
        creance.setId(1L);
        CreanceDTO creanceDTO = creanceMapper.toDto(creance);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCreanceMockMvc
            .perform(post("/api/creances").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(creanceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Creance in the database
        List<Creance> creanceList = creanceRepository.findAll();
        assertThat(creanceList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllCreances() throws Exception {
        // Initialize the database
        creanceRepository.saveAndFlush(creance);

        // Get all the creanceList
        restCreanceMockMvc
            .perform(get("/api/creances?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(creance.getId().intValue())))
            .andExpect(jsonPath("$.[*].ref").value(hasItem(DEFAULT_REF)))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].datePVRec").value(hasItem(DEFAULT_DATE_PV_REC.toString())))
            .andExpect(jsonPath("$.[*].natureLitige").value(hasItem(DEFAULT_NATURE_LITIGE)))
            .andExpect(jsonPath("$.[*].montant").value(hasItem(DEFAULT_MONTANT.doubleValue())))
            .andExpect(jsonPath("$.[*].nombreEcheance").value(hasItem(DEFAULT_NOMBRE_ECHEANCE)))
            .andExpect(jsonPath("$.[*].totalRecouvre").value(hasItem(DEFAULT_TOTAL_RECOUVRE.doubleValue())))
            .andExpect(jsonPath("$.[*].soldeARecouvrer").value(hasItem(DEFAULT_SOLDE_A_RECOUVRER.doubleValue())));
    }

    @Test
    @Transactional
    public void getCreance() throws Exception {
        // Initialize the database
        creanceRepository.saveAndFlush(creance);

        // Get the creance
        restCreanceMockMvc
            .perform(get("/api/creances/{id}", creance.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(creance.getId().intValue()))
            .andExpect(jsonPath("$.ref").value(DEFAULT_REF))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()))
            .andExpect(jsonPath("$.datePVRec").value(DEFAULT_DATE_PV_REC.toString()))
            .andExpect(jsonPath("$.natureLitige").value(DEFAULT_NATURE_LITIGE))
            .andExpect(jsonPath("$.montant").value(DEFAULT_MONTANT.doubleValue()))
            .andExpect(jsonPath("$.nombreEcheance").value(DEFAULT_NOMBRE_ECHEANCE))
            .andExpect(jsonPath("$.totalRecouvre").value(DEFAULT_TOTAL_RECOUVRE.doubleValue()))
            .andExpect(jsonPath("$.soldeARecouvrer").value(DEFAULT_SOLDE_A_RECOUVRER.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingCreance() throws Exception {
        // Get the creance
        restCreanceMockMvc.perform(get("/api/creances/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCreance() throws Exception {
        // Initialize the database
        creanceRepository.saveAndFlush(creance);

        int databaseSizeBeforeUpdate = creanceRepository.findAll().size();

        // Update the creance
        Creance updatedCreance = creanceRepository.findById(creance.getId()).get();
        // Disconnect from session so that the updates on updatedCreance are not directly saved in db
        em.detach(updatedCreance);
        updatedCreance
            .ref(UPDATED_REF)
            .date(UPDATED_DATE)
            .datePVRec(UPDATED_DATE_PV_REC)
            .natureLitige(UPDATED_NATURE_LITIGE)
            .montant(UPDATED_MONTANT)
            .nombreEcheance(UPDATED_NOMBRE_ECHEANCE)
            .totalRecouvre(UPDATED_TOTAL_RECOUVRE)
            .soldeARecouvrer(UPDATED_SOLDE_A_RECOUVRER);
        CreanceDTO creanceDTO = creanceMapper.toDto(updatedCreance);

        restCreanceMockMvc
            .perform(put("/api/creances").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(creanceDTO)))
            .andExpect(status().isOk());

        // Validate the Creance in the database
        List<Creance> creanceList = creanceRepository.findAll();
        assertThat(creanceList).hasSize(databaseSizeBeforeUpdate);
        Creance testCreance = creanceList.get(creanceList.size() - 1);
        assertThat(testCreance.getRef()).isEqualTo(UPDATED_REF);
        assertThat(testCreance.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testCreance.getDatePVRec()).isEqualTo(UPDATED_DATE_PV_REC);
        assertThat(testCreance.getNatureLitige()).isEqualTo(UPDATED_NATURE_LITIGE);
        assertThat(testCreance.getMontant()).isEqualTo(UPDATED_MONTANT);
        assertThat(testCreance.getNombreEcheance()).isEqualTo(UPDATED_NOMBRE_ECHEANCE);
        assertThat(testCreance.getTotalRecouvre()).isEqualTo(UPDATED_TOTAL_RECOUVRE);
        assertThat(testCreance.getSoldeARecouvrer()).isEqualTo(UPDATED_SOLDE_A_RECOUVRER);
    }

    @Test
    @Transactional
    public void updateNonExistingCreance() throws Exception {
        int databaseSizeBeforeUpdate = creanceRepository.findAll().size();

        // Create the Creance
        CreanceDTO creanceDTO = creanceMapper.toDto(creance);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCreanceMockMvc
            .perform(put("/api/creances").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(creanceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Creance in the database
        List<Creance> creanceList = creanceRepository.findAll();
        assertThat(creanceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCreance() throws Exception {
        // Initialize the database
        creanceRepository.saveAndFlush(creance);

        int databaseSizeBeforeDelete = creanceRepository.findAll().size();

        // Delete the creance
        restCreanceMockMvc
            .perform(delete("/api/creances/{id}", creance.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Creance> creanceList = creanceRepository.findAll();
        assertThat(creanceList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
