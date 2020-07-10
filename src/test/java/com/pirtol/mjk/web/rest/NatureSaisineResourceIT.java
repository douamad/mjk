package com.pirtol.mjk.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.pirtol.mjk.MjkApp;
import com.pirtol.mjk.domain.NatureSaisine;
import com.pirtol.mjk.repository.NatureSaisineRepository;
import com.pirtol.mjk.service.NatureSaisineService;
import com.pirtol.mjk.service.dto.NatureSaisineDTO;
import com.pirtol.mjk.service.mapper.NatureSaisineMapper;
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
 * Integration tests for the {@link NatureSaisineResource} REST controller.
 */
@SpringBootTest(classes = MjkApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class NatureSaisineResourceIT {
    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final String DEFAULT_SLUG = "AAAAAAAAAA";
    private static final String UPDATED_SLUG = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private NatureSaisineRepository natureSaisineRepository;

    @Autowired
    private NatureSaisineMapper natureSaisineMapper;

    @Autowired
    private NatureSaisineService natureSaisineService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restNatureSaisineMockMvc;

    private NatureSaisine natureSaisine;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NatureSaisine createEntity(EntityManager em) {
        NatureSaisine natureSaisine = new NatureSaisine().nom(DEFAULT_NOM).slug(DEFAULT_SLUG).description(DEFAULT_DESCRIPTION);
        return natureSaisine;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NatureSaisine createUpdatedEntity(EntityManager em) {
        NatureSaisine natureSaisine = new NatureSaisine().nom(UPDATED_NOM).slug(UPDATED_SLUG).description(UPDATED_DESCRIPTION);
        return natureSaisine;
    }

    @BeforeEach
    public void initTest() {
        natureSaisine = createEntity(em);
    }

    @Test
    @Transactional
    public void createNatureSaisine() throws Exception {
        int databaseSizeBeforeCreate = natureSaisineRepository.findAll().size();
        // Create the NatureSaisine
        NatureSaisineDTO natureSaisineDTO = natureSaisineMapper.toDto(natureSaisine);
        restNatureSaisineMockMvc
            .perform(
                post("/api/nature-saisines")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(natureSaisineDTO))
            )
            .andExpect(status().isCreated());

        // Validate the NatureSaisine in the database
        List<NatureSaisine> natureSaisineList = natureSaisineRepository.findAll();
        assertThat(natureSaisineList).hasSize(databaseSizeBeforeCreate + 1);
        NatureSaisine testNatureSaisine = natureSaisineList.get(natureSaisineList.size() - 1);
        assertThat(testNatureSaisine.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testNatureSaisine.getSlug()).isEqualTo(DEFAULT_SLUG);
        assertThat(testNatureSaisine.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createNatureSaisineWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = natureSaisineRepository.findAll().size();

        // Create the NatureSaisine with an existing ID
        natureSaisine.setId(1L);
        NatureSaisineDTO natureSaisineDTO = natureSaisineMapper.toDto(natureSaisine);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNatureSaisineMockMvc
            .perform(
                post("/api/nature-saisines")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(natureSaisineDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NatureSaisine in the database
        List<NatureSaisine> natureSaisineList = natureSaisineRepository.findAll();
        assertThat(natureSaisineList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllNatureSaisines() throws Exception {
        // Initialize the database
        natureSaisineRepository.saveAndFlush(natureSaisine);

        // Get all the natureSaisineList
        restNatureSaisineMockMvc
            .perform(get("/api/nature-saisines?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(natureSaisine.getId().intValue())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM)))
            .andExpect(jsonPath("$.[*].slug").value(hasItem(DEFAULT_SLUG)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)));
    }

    @Test
    @Transactional
    public void getNatureSaisine() throws Exception {
        // Initialize the database
        natureSaisineRepository.saveAndFlush(natureSaisine);

        // Get the natureSaisine
        restNatureSaisineMockMvc
            .perform(get("/api/nature-saisines/{id}", natureSaisine.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(natureSaisine.getId().intValue()))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM))
            .andExpect(jsonPath("$.slug").value(DEFAULT_SLUG))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION));
    }

    @Test
    @Transactional
    public void getNonExistingNatureSaisine() throws Exception {
        // Get the natureSaisine
        restNatureSaisineMockMvc.perform(get("/api/nature-saisines/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNatureSaisine() throws Exception {
        // Initialize the database
        natureSaisineRepository.saveAndFlush(natureSaisine);

        int databaseSizeBeforeUpdate = natureSaisineRepository.findAll().size();

        // Update the natureSaisine
        NatureSaisine updatedNatureSaisine = natureSaisineRepository.findById(natureSaisine.getId()).get();
        // Disconnect from session so that the updates on updatedNatureSaisine are not directly saved in db
        em.detach(updatedNatureSaisine);
        updatedNatureSaisine.nom(UPDATED_NOM).slug(UPDATED_SLUG).description(UPDATED_DESCRIPTION);
        NatureSaisineDTO natureSaisineDTO = natureSaisineMapper.toDto(updatedNatureSaisine);

        restNatureSaisineMockMvc
            .perform(
                put("/api/nature-saisines")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(natureSaisineDTO))
            )
            .andExpect(status().isOk());

        // Validate the NatureSaisine in the database
        List<NatureSaisine> natureSaisineList = natureSaisineRepository.findAll();
        assertThat(natureSaisineList).hasSize(databaseSizeBeforeUpdate);
        NatureSaisine testNatureSaisine = natureSaisineList.get(natureSaisineList.size() - 1);
        assertThat(testNatureSaisine.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testNatureSaisine.getSlug()).isEqualTo(UPDATED_SLUG);
        assertThat(testNatureSaisine.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingNatureSaisine() throws Exception {
        int databaseSizeBeforeUpdate = natureSaisineRepository.findAll().size();

        // Create the NatureSaisine
        NatureSaisineDTO natureSaisineDTO = natureSaisineMapper.toDto(natureSaisine);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNatureSaisineMockMvc
            .perform(
                put("/api/nature-saisines")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(natureSaisineDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NatureSaisine in the database
        List<NatureSaisine> natureSaisineList = natureSaisineRepository.findAll();
        assertThat(natureSaisineList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteNatureSaisine() throws Exception {
        // Initialize the database
        natureSaisineRepository.saveAndFlush(natureSaisine);

        int databaseSizeBeforeDelete = natureSaisineRepository.findAll().size();

        // Delete the natureSaisine
        restNatureSaisineMockMvc
            .perform(delete("/api/nature-saisines/{id}", natureSaisine.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<NatureSaisine> natureSaisineList = natureSaisineRepository.findAll();
        assertThat(natureSaisineList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
