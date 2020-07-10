package com.pirtol.mjk.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.pirtol.mjk.MjkApp;
import com.pirtol.mjk.domain.Saisine;
import com.pirtol.mjk.repository.SaisineRepository;
import com.pirtol.mjk.service.SaisineService;
import com.pirtol.mjk.service.dto.SaisineDTO;
import com.pirtol.mjk.service.mapper.SaisineMapper;
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
 * Integration tests for the {@link SaisineResource} REST controller.
 */
@SpringBootTest(classes = MjkApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class SaisineResourceIT {
    private static final String DEFAULT_REF = "AAAAAAAAAA";
    private static final String UPDATED_REF = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Integer DEFAULT_ORGANISAITON = 1;
    private static final Integer UPDATED_ORGANISAITON = 2;

    @Autowired
    private SaisineRepository saisineRepository;

    @Autowired
    private SaisineMapper saisineMapper;

    @Autowired
    private SaisineService saisineService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSaisineMockMvc;

    private Saisine saisine;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Saisine createEntity(EntityManager em) {
        Saisine saisine = new Saisine()
            .ref(DEFAULT_REF)
            .date(DEFAULT_DATE)
            .description(DEFAULT_DESCRIPTION)
            .organisaiton(DEFAULT_ORGANISAITON);
        return saisine;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Saisine createUpdatedEntity(EntityManager em) {
        Saisine saisine = new Saisine()
            .ref(UPDATED_REF)
            .date(UPDATED_DATE)
            .description(UPDATED_DESCRIPTION)
            .organisaiton(UPDATED_ORGANISAITON);
        return saisine;
    }

    @BeforeEach
    public void initTest() {
        saisine = createEntity(em);
    }

    @Test
    @Transactional
    public void createSaisine() throws Exception {
        int databaseSizeBeforeCreate = saisineRepository.findAll().size();
        // Create the Saisine
        SaisineDTO saisineDTO = saisineMapper.toDto(saisine);
        restSaisineMockMvc
            .perform(post("/api/saisines").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(saisineDTO)))
            .andExpect(status().isCreated());

        // Validate the Saisine in the database
        List<Saisine> saisineList = saisineRepository.findAll();
        assertThat(saisineList).hasSize(databaseSizeBeforeCreate + 1);
        Saisine testSaisine = saisineList.get(saisineList.size() - 1);
        assertThat(testSaisine.getRef()).isEqualTo(DEFAULT_REF);
        assertThat(testSaisine.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testSaisine.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testSaisine.getOrganisaiton()).isEqualTo(DEFAULT_ORGANISAITON);
    }

    @Test
    @Transactional
    public void createSaisineWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = saisineRepository.findAll().size();

        // Create the Saisine with an existing ID
        saisine.setId(1L);
        SaisineDTO saisineDTO = saisineMapper.toDto(saisine);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSaisineMockMvc
            .perform(post("/api/saisines").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(saisineDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Saisine in the database
        List<Saisine> saisineList = saisineRepository.findAll();
        assertThat(saisineList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSaisines() throws Exception {
        // Initialize the database
        saisineRepository.saveAndFlush(saisine);

        // Get all the saisineList
        restSaisineMockMvc
            .perform(get("/api/saisines?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(saisine.getId().intValue())))
            .andExpect(jsonPath("$.[*].ref").value(hasItem(DEFAULT_REF)))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].organisaiton").value(hasItem(DEFAULT_ORGANISAITON)));
    }

    @Test
    @Transactional
    public void getSaisine() throws Exception {
        // Initialize the database
        saisineRepository.saveAndFlush(saisine);

        // Get the saisine
        restSaisineMockMvc
            .perform(get("/api/saisines/{id}", saisine.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(saisine.getId().intValue()))
            .andExpect(jsonPath("$.ref").value(DEFAULT_REF))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.organisaiton").value(DEFAULT_ORGANISAITON));
    }

    @Test
    @Transactional
    public void getNonExistingSaisine() throws Exception {
        // Get the saisine
        restSaisineMockMvc.perform(get("/api/saisines/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSaisine() throws Exception {
        // Initialize the database
        saisineRepository.saveAndFlush(saisine);

        int databaseSizeBeforeUpdate = saisineRepository.findAll().size();

        // Update the saisine
        Saisine updatedSaisine = saisineRepository.findById(saisine.getId()).get();
        // Disconnect from session so that the updates on updatedSaisine are not directly saved in db
        em.detach(updatedSaisine);
        updatedSaisine.ref(UPDATED_REF).date(UPDATED_DATE).description(UPDATED_DESCRIPTION).organisaiton(UPDATED_ORGANISAITON);
        SaisineDTO saisineDTO = saisineMapper.toDto(updatedSaisine);

        restSaisineMockMvc
            .perform(put("/api/saisines").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(saisineDTO)))
            .andExpect(status().isOk());

        // Validate the Saisine in the database
        List<Saisine> saisineList = saisineRepository.findAll();
        assertThat(saisineList).hasSize(databaseSizeBeforeUpdate);
        Saisine testSaisine = saisineList.get(saisineList.size() - 1);
        assertThat(testSaisine.getRef()).isEqualTo(UPDATED_REF);
        assertThat(testSaisine.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testSaisine.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testSaisine.getOrganisaiton()).isEqualTo(UPDATED_ORGANISAITON);
    }

    @Test
    @Transactional
    public void updateNonExistingSaisine() throws Exception {
        int databaseSizeBeforeUpdate = saisineRepository.findAll().size();

        // Create the Saisine
        SaisineDTO saisineDTO = saisineMapper.toDto(saisine);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSaisineMockMvc
            .perform(put("/api/saisines").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(saisineDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Saisine in the database
        List<Saisine> saisineList = saisineRepository.findAll();
        assertThat(saisineList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSaisine() throws Exception {
        // Initialize the database
        saisineRepository.saveAndFlush(saisine);

        int databaseSizeBeforeDelete = saisineRepository.findAll().size();

        // Delete the saisine
        restSaisineMockMvc
            .perform(delete("/api/saisines/{id}", saisine.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Saisine> saisineList = saisineRepository.findAll();
        assertThat(saisineList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
