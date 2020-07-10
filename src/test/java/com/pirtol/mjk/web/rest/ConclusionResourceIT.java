package com.pirtol.mjk.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.pirtol.mjk.MjkApp;
import com.pirtol.mjk.domain.Conclusion;
import com.pirtol.mjk.repository.ConclusionRepository;
import com.pirtol.mjk.service.ConclusionService;
import com.pirtol.mjk.service.dto.ConclusionDTO;
import com.pirtol.mjk.service.mapper.ConclusionMapper;
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
 * Integration tests for the {@link ConclusionResource} REST controller.
 */
@SpringBootTest(classes = MjkApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ConclusionResourceIT {
    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final String DEFAULT_SLUG = "AAAAAAAAAA";
    private static final String UPDATED_SLUG = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private ConclusionRepository conclusionRepository;

    @Autowired
    private ConclusionMapper conclusionMapper;

    @Autowired
    private ConclusionService conclusionService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restConclusionMockMvc;

    private Conclusion conclusion;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Conclusion createEntity(EntityManager em) {
        Conclusion conclusion = new Conclusion().nom(DEFAULT_NOM).slug(DEFAULT_SLUG).description(DEFAULT_DESCRIPTION);
        return conclusion;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Conclusion createUpdatedEntity(EntityManager em) {
        Conclusion conclusion = new Conclusion().nom(UPDATED_NOM).slug(UPDATED_SLUG).description(UPDATED_DESCRIPTION);
        return conclusion;
    }

    @BeforeEach
    public void initTest() {
        conclusion = createEntity(em);
    }

    @Test
    @Transactional
    public void createConclusion() throws Exception {
        int databaseSizeBeforeCreate = conclusionRepository.findAll().size();
        // Create the Conclusion
        ConclusionDTO conclusionDTO = conclusionMapper.toDto(conclusion);
        restConclusionMockMvc
            .perform(
                post("/api/conclusions").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(conclusionDTO))
            )
            .andExpect(status().isCreated());

        // Validate the Conclusion in the database
        List<Conclusion> conclusionList = conclusionRepository.findAll();
        assertThat(conclusionList).hasSize(databaseSizeBeforeCreate + 1);
        Conclusion testConclusion = conclusionList.get(conclusionList.size() - 1);
        assertThat(testConclusion.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testConclusion.getSlug()).isEqualTo(DEFAULT_SLUG);
        assertThat(testConclusion.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createConclusionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = conclusionRepository.findAll().size();

        // Create the Conclusion with an existing ID
        conclusion.setId(1L);
        ConclusionDTO conclusionDTO = conclusionMapper.toDto(conclusion);

        // An entity with an existing ID cannot be created, so this API call must fail
        restConclusionMockMvc
            .perform(
                post("/api/conclusions").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(conclusionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Conclusion in the database
        List<Conclusion> conclusionList = conclusionRepository.findAll();
        assertThat(conclusionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllConclusions() throws Exception {
        // Initialize the database
        conclusionRepository.saveAndFlush(conclusion);

        // Get all the conclusionList
        restConclusionMockMvc
            .perform(get("/api/conclusions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(conclusion.getId().intValue())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM)))
            .andExpect(jsonPath("$.[*].slug").value(hasItem(DEFAULT_SLUG)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)));
    }

    @Test
    @Transactional
    public void getConclusion() throws Exception {
        // Initialize the database
        conclusionRepository.saveAndFlush(conclusion);

        // Get the conclusion
        restConclusionMockMvc
            .perform(get("/api/conclusions/{id}", conclusion.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(conclusion.getId().intValue()))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM))
            .andExpect(jsonPath("$.slug").value(DEFAULT_SLUG))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION));
    }

    @Test
    @Transactional
    public void getNonExistingConclusion() throws Exception {
        // Get the conclusion
        restConclusionMockMvc.perform(get("/api/conclusions/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateConclusion() throws Exception {
        // Initialize the database
        conclusionRepository.saveAndFlush(conclusion);

        int databaseSizeBeforeUpdate = conclusionRepository.findAll().size();

        // Update the conclusion
        Conclusion updatedConclusion = conclusionRepository.findById(conclusion.getId()).get();
        // Disconnect from session so that the updates on updatedConclusion are not directly saved in db
        em.detach(updatedConclusion);
        updatedConclusion.nom(UPDATED_NOM).slug(UPDATED_SLUG).description(UPDATED_DESCRIPTION);
        ConclusionDTO conclusionDTO = conclusionMapper.toDto(updatedConclusion);

        restConclusionMockMvc
            .perform(
                put("/api/conclusions").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(conclusionDTO))
            )
            .andExpect(status().isOk());

        // Validate the Conclusion in the database
        List<Conclusion> conclusionList = conclusionRepository.findAll();
        assertThat(conclusionList).hasSize(databaseSizeBeforeUpdate);
        Conclusion testConclusion = conclusionList.get(conclusionList.size() - 1);
        assertThat(testConclusion.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testConclusion.getSlug()).isEqualTo(UPDATED_SLUG);
        assertThat(testConclusion.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingConclusion() throws Exception {
        int databaseSizeBeforeUpdate = conclusionRepository.findAll().size();

        // Create the Conclusion
        ConclusionDTO conclusionDTO = conclusionMapper.toDto(conclusion);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restConclusionMockMvc
            .perform(
                put("/api/conclusions").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(conclusionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Conclusion in the database
        List<Conclusion> conclusionList = conclusionRepository.findAll();
        assertThat(conclusionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteConclusion() throws Exception {
        // Initialize the database
        conclusionRepository.saveAndFlush(conclusion);

        int databaseSizeBeforeDelete = conclusionRepository.findAll().size();

        // Delete the conclusion
        restConclusionMockMvc
            .perform(delete("/api/conclusions/{id}", conclusion.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Conclusion> conclusionList = conclusionRepository.findAll();
        assertThat(conclusionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
