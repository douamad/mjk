package com.pirtol.mjk.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.pirtol.mjk.MjkApp;
import com.pirtol.mjk.domain.OrigineSaisine;
import com.pirtol.mjk.repository.OrigineSaisineRepository;
import com.pirtol.mjk.service.OrigineSaisineService;
import com.pirtol.mjk.service.dto.OrigineSaisineDTO;
import com.pirtol.mjk.service.mapper.OrigineSaisineMapper;
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
 * Integration tests for the {@link OrigineSaisineResource} REST controller.
 */
@SpringBootTest(classes = MjkApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class OrigineSaisineResourceIT {
    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final String DEFAULT_SLUG = "AAAAAAAAAA";
    private static final String UPDATED_SLUG = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private OrigineSaisineRepository origineSaisineRepository;

    @Autowired
    private OrigineSaisineMapper origineSaisineMapper;

    @Autowired
    private OrigineSaisineService origineSaisineService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOrigineSaisineMockMvc;

    private OrigineSaisine origineSaisine;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OrigineSaisine createEntity(EntityManager em) {
        OrigineSaisine origineSaisine = new OrigineSaisine().nom(DEFAULT_NOM).slug(DEFAULT_SLUG).description(DEFAULT_DESCRIPTION);
        return origineSaisine;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OrigineSaisine createUpdatedEntity(EntityManager em) {
        OrigineSaisine origineSaisine = new OrigineSaisine().nom(UPDATED_NOM).slug(UPDATED_SLUG).description(UPDATED_DESCRIPTION);
        return origineSaisine;
    }

    @BeforeEach
    public void initTest() {
        origineSaisine = createEntity(em);
    }

    @Test
    @Transactional
    public void createOrigineSaisine() throws Exception {
        int databaseSizeBeforeCreate = origineSaisineRepository.findAll().size();
        // Create the OrigineSaisine
        OrigineSaisineDTO origineSaisineDTO = origineSaisineMapper.toDto(origineSaisine);
        restOrigineSaisineMockMvc
            .perform(
                post("/api/origine-saisines")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(origineSaisineDTO))
            )
            .andExpect(status().isCreated());

        // Validate the OrigineSaisine in the database
        List<OrigineSaisine> origineSaisineList = origineSaisineRepository.findAll();
        assertThat(origineSaisineList).hasSize(databaseSizeBeforeCreate + 1);
        OrigineSaisine testOrigineSaisine = origineSaisineList.get(origineSaisineList.size() - 1);
        assertThat(testOrigineSaisine.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testOrigineSaisine.getSlug()).isEqualTo(DEFAULT_SLUG);
        assertThat(testOrigineSaisine.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createOrigineSaisineWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = origineSaisineRepository.findAll().size();

        // Create the OrigineSaisine with an existing ID
        origineSaisine.setId(1L);
        OrigineSaisineDTO origineSaisineDTO = origineSaisineMapper.toDto(origineSaisine);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOrigineSaisineMockMvc
            .perform(
                post("/api/origine-saisines")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(origineSaisineDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the OrigineSaisine in the database
        List<OrigineSaisine> origineSaisineList = origineSaisineRepository.findAll();
        assertThat(origineSaisineList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllOrigineSaisines() throws Exception {
        // Initialize the database
        origineSaisineRepository.saveAndFlush(origineSaisine);

        // Get all the origineSaisineList
        restOrigineSaisineMockMvc
            .perform(get("/api/origine-saisines?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(origineSaisine.getId().intValue())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM)))
            .andExpect(jsonPath("$.[*].slug").value(hasItem(DEFAULT_SLUG)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)));
    }

    @Test
    @Transactional
    public void getOrigineSaisine() throws Exception {
        // Initialize the database
        origineSaisineRepository.saveAndFlush(origineSaisine);

        // Get the origineSaisine
        restOrigineSaisineMockMvc
            .perform(get("/api/origine-saisines/{id}", origineSaisine.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(origineSaisine.getId().intValue()))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM))
            .andExpect(jsonPath("$.slug").value(DEFAULT_SLUG))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION));
    }

    @Test
    @Transactional
    public void getNonExistingOrigineSaisine() throws Exception {
        // Get the origineSaisine
        restOrigineSaisineMockMvc.perform(get("/api/origine-saisines/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOrigineSaisine() throws Exception {
        // Initialize the database
        origineSaisineRepository.saveAndFlush(origineSaisine);

        int databaseSizeBeforeUpdate = origineSaisineRepository.findAll().size();

        // Update the origineSaisine
        OrigineSaisine updatedOrigineSaisine = origineSaisineRepository.findById(origineSaisine.getId()).get();
        // Disconnect from session so that the updates on updatedOrigineSaisine are not directly saved in db
        em.detach(updatedOrigineSaisine);
        updatedOrigineSaisine.nom(UPDATED_NOM).slug(UPDATED_SLUG).description(UPDATED_DESCRIPTION);
        OrigineSaisineDTO origineSaisineDTO = origineSaisineMapper.toDto(updatedOrigineSaisine);

        restOrigineSaisineMockMvc
            .perform(
                put("/api/origine-saisines")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(origineSaisineDTO))
            )
            .andExpect(status().isOk());

        // Validate the OrigineSaisine in the database
        List<OrigineSaisine> origineSaisineList = origineSaisineRepository.findAll();
        assertThat(origineSaisineList).hasSize(databaseSizeBeforeUpdate);
        OrigineSaisine testOrigineSaisine = origineSaisineList.get(origineSaisineList.size() - 1);
        assertThat(testOrigineSaisine.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testOrigineSaisine.getSlug()).isEqualTo(UPDATED_SLUG);
        assertThat(testOrigineSaisine.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingOrigineSaisine() throws Exception {
        int databaseSizeBeforeUpdate = origineSaisineRepository.findAll().size();

        // Create the OrigineSaisine
        OrigineSaisineDTO origineSaisineDTO = origineSaisineMapper.toDto(origineSaisine);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOrigineSaisineMockMvc
            .perform(
                put("/api/origine-saisines")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(origineSaisineDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the OrigineSaisine in the database
        List<OrigineSaisine> origineSaisineList = origineSaisineRepository.findAll();
        assertThat(origineSaisineList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOrigineSaisine() throws Exception {
        // Initialize the database
        origineSaisineRepository.saveAndFlush(origineSaisine);

        int databaseSizeBeforeDelete = origineSaisineRepository.findAll().size();

        // Delete the origineSaisine
        restOrigineSaisineMockMvc
            .perform(delete("/api/origine-saisines/{id}", origineSaisine.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<OrigineSaisine> origineSaisineList = origineSaisineRepository.findAll();
        assertThat(origineSaisineList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
