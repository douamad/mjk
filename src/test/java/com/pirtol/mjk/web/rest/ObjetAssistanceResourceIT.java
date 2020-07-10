package com.pirtol.mjk.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.pirtol.mjk.MjkApp;
import com.pirtol.mjk.domain.ObjetAssistance;
import com.pirtol.mjk.repository.ObjetAssistanceRepository;
import com.pirtol.mjk.service.ObjetAssistanceService;
import com.pirtol.mjk.service.dto.ObjetAssistanceDTO;
import com.pirtol.mjk.service.mapper.ObjetAssistanceMapper;
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
 * Integration tests for the {@link ObjetAssistanceResource} REST controller.
 */
@SpringBootTest(classes = MjkApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ObjetAssistanceResourceIT {
    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final String DEFAULT_SLUG = "AAAAAAAAAA";
    private static final String UPDATED_SLUG = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private ObjetAssistanceRepository objetAssistanceRepository;

    @Autowired
    private ObjetAssistanceMapper objetAssistanceMapper;

    @Autowired
    private ObjetAssistanceService objetAssistanceService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restObjetAssistanceMockMvc;

    private ObjetAssistance objetAssistance;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ObjetAssistance createEntity(EntityManager em) {
        ObjetAssistance objetAssistance = new ObjetAssistance().nom(DEFAULT_NOM).slug(DEFAULT_SLUG).description(DEFAULT_DESCRIPTION);
        return objetAssistance;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ObjetAssistance createUpdatedEntity(EntityManager em) {
        ObjetAssistance objetAssistance = new ObjetAssistance().nom(UPDATED_NOM).slug(UPDATED_SLUG).description(UPDATED_DESCRIPTION);
        return objetAssistance;
    }

    @BeforeEach
    public void initTest() {
        objetAssistance = createEntity(em);
    }

    @Test
    @Transactional
    public void createObjetAssistance() throws Exception {
        int databaseSizeBeforeCreate = objetAssistanceRepository.findAll().size();
        // Create the ObjetAssistance
        ObjetAssistanceDTO objetAssistanceDTO = objetAssistanceMapper.toDto(objetAssistance);
        restObjetAssistanceMockMvc
            .perform(
                post("/api/objet-assistances")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(objetAssistanceDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ObjetAssistance in the database
        List<ObjetAssistance> objetAssistanceList = objetAssistanceRepository.findAll();
        assertThat(objetAssistanceList).hasSize(databaseSizeBeforeCreate + 1);
        ObjetAssistance testObjetAssistance = objetAssistanceList.get(objetAssistanceList.size() - 1);
        assertThat(testObjetAssistance.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testObjetAssistance.getSlug()).isEqualTo(DEFAULT_SLUG);
        assertThat(testObjetAssistance.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createObjetAssistanceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = objetAssistanceRepository.findAll().size();

        // Create the ObjetAssistance with an existing ID
        objetAssistance.setId(1L);
        ObjetAssistanceDTO objetAssistanceDTO = objetAssistanceMapper.toDto(objetAssistance);

        // An entity with an existing ID cannot be created, so this API call must fail
        restObjetAssistanceMockMvc
            .perform(
                post("/api/objet-assistances")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(objetAssistanceDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ObjetAssistance in the database
        List<ObjetAssistance> objetAssistanceList = objetAssistanceRepository.findAll();
        assertThat(objetAssistanceList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllObjetAssistances() throws Exception {
        // Initialize the database
        objetAssistanceRepository.saveAndFlush(objetAssistance);

        // Get all the objetAssistanceList
        restObjetAssistanceMockMvc
            .perform(get("/api/objet-assistances?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(objetAssistance.getId().intValue())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM)))
            .andExpect(jsonPath("$.[*].slug").value(hasItem(DEFAULT_SLUG)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)));
    }

    @Test
    @Transactional
    public void getObjetAssistance() throws Exception {
        // Initialize the database
        objetAssistanceRepository.saveAndFlush(objetAssistance);

        // Get the objetAssistance
        restObjetAssistanceMockMvc
            .perform(get("/api/objet-assistances/{id}", objetAssistance.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(objetAssistance.getId().intValue()))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM))
            .andExpect(jsonPath("$.slug").value(DEFAULT_SLUG))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION));
    }

    @Test
    @Transactional
    public void getNonExistingObjetAssistance() throws Exception {
        // Get the objetAssistance
        restObjetAssistanceMockMvc.perform(get("/api/objet-assistances/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateObjetAssistance() throws Exception {
        // Initialize the database
        objetAssistanceRepository.saveAndFlush(objetAssistance);

        int databaseSizeBeforeUpdate = objetAssistanceRepository.findAll().size();

        // Update the objetAssistance
        ObjetAssistance updatedObjetAssistance = objetAssistanceRepository.findById(objetAssistance.getId()).get();
        // Disconnect from session so that the updates on updatedObjetAssistance are not directly saved in db
        em.detach(updatedObjetAssistance);
        updatedObjetAssistance.nom(UPDATED_NOM).slug(UPDATED_SLUG).description(UPDATED_DESCRIPTION);
        ObjetAssistanceDTO objetAssistanceDTO = objetAssistanceMapper.toDto(updatedObjetAssistance);

        restObjetAssistanceMockMvc
            .perform(
                put("/api/objet-assistances")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(objetAssistanceDTO))
            )
            .andExpect(status().isOk());

        // Validate the ObjetAssistance in the database
        List<ObjetAssistance> objetAssistanceList = objetAssistanceRepository.findAll();
        assertThat(objetAssistanceList).hasSize(databaseSizeBeforeUpdate);
        ObjetAssistance testObjetAssistance = objetAssistanceList.get(objetAssistanceList.size() - 1);
        assertThat(testObjetAssistance.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testObjetAssistance.getSlug()).isEqualTo(UPDATED_SLUG);
        assertThat(testObjetAssistance.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingObjetAssistance() throws Exception {
        int databaseSizeBeforeUpdate = objetAssistanceRepository.findAll().size();

        // Create the ObjetAssistance
        ObjetAssistanceDTO objetAssistanceDTO = objetAssistanceMapper.toDto(objetAssistance);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restObjetAssistanceMockMvc
            .perform(
                put("/api/objet-assistances")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(objetAssistanceDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ObjetAssistance in the database
        List<ObjetAssistance> objetAssistanceList = objetAssistanceRepository.findAll();
        assertThat(objetAssistanceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteObjetAssistance() throws Exception {
        // Initialize the database
        objetAssistanceRepository.saveAndFlush(objetAssistance);

        int databaseSizeBeforeDelete = objetAssistanceRepository.findAll().size();

        // Delete the objetAssistance
        restObjetAssistanceMockMvc
            .perform(delete("/api/objet-assistances/{id}", objetAssistance.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ObjetAssistance> objetAssistanceList = objetAssistanceRepository.findAll();
        assertThat(objetAssistanceList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
