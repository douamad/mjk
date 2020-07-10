package com.pirtol.mjk.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.pirtol.mjk.MjkApp;
import com.pirtol.mjk.domain.Maison;
import com.pirtol.mjk.repository.MaisonRepository;
import com.pirtol.mjk.service.MaisonService;
import com.pirtol.mjk.service.dto.MaisonDTO;
import com.pirtol.mjk.service.mapper.MaisonMapper;
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
 * Integration tests for the {@link MaisonResource} REST controller.
 */
@SpringBootTest(classes = MjkApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class MaisonResourceIT {
    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final String DEFAULT_SLUG = "AAAAAAAAAA";
    private static final String UPDATED_SLUG = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private MaisonRepository maisonRepository;

    @Autowired
    private MaisonMapper maisonMapper;

    @Autowired
    private MaisonService maisonService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMaisonMockMvc;

    private Maison maison;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Maison createEntity(EntityManager em) {
        Maison maison = new Maison().nom(DEFAULT_NOM).slug(DEFAULT_SLUG).description(DEFAULT_DESCRIPTION);
        return maison;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Maison createUpdatedEntity(EntityManager em) {
        Maison maison = new Maison().nom(UPDATED_NOM).slug(UPDATED_SLUG).description(UPDATED_DESCRIPTION);
        return maison;
    }

    @BeforeEach
    public void initTest() {
        maison = createEntity(em);
    }

    @Test
    @Transactional
    public void createMaison() throws Exception {
        int databaseSizeBeforeCreate = maisonRepository.findAll().size();
        // Create the Maison
        MaisonDTO maisonDTO = maisonMapper.toDto(maison);
        restMaisonMockMvc
            .perform(post("/api/maisons").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(maisonDTO)))
            .andExpect(status().isCreated());

        // Validate the Maison in the database
        List<Maison> maisonList = maisonRepository.findAll();
        assertThat(maisonList).hasSize(databaseSizeBeforeCreate + 1);
        Maison testMaison = maisonList.get(maisonList.size() - 1);
        assertThat(testMaison.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testMaison.getSlug()).isEqualTo(DEFAULT_SLUG);
        assertThat(testMaison.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createMaisonWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = maisonRepository.findAll().size();

        // Create the Maison with an existing ID
        maison.setId(1L);
        MaisonDTO maisonDTO = maisonMapper.toDto(maison);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMaisonMockMvc
            .perform(post("/api/maisons").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(maisonDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Maison in the database
        List<Maison> maisonList = maisonRepository.findAll();
        assertThat(maisonList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllMaisons() throws Exception {
        // Initialize the database
        maisonRepository.saveAndFlush(maison);

        // Get all the maisonList
        restMaisonMockMvc
            .perform(get("/api/maisons?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(maison.getId().intValue())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM)))
            .andExpect(jsonPath("$.[*].slug").value(hasItem(DEFAULT_SLUG)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)));
    }

    @Test
    @Transactional
    public void getMaison() throws Exception {
        // Initialize the database
        maisonRepository.saveAndFlush(maison);

        // Get the maison
        restMaisonMockMvc
            .perform(get("/api/maisons/{id}", maison.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(maison.getId().intValue()))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM))
            .andExpect(jsonPath("$.slug").value(DEFAULT_SLUG))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION));
    }

    @Test
    @Transactional
    public void getNonExistingMaison() throws Exception {
        // Get the maison
        restMaisonMockMvc.perform(get("/api/maisons/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMaison() throws Exception {
        // Initialize the database
        maisonRepository.saveAndFlush(maison);

        int databaseSizeBeforeUpdate = maisonRepository.findAll().size();

        // Update the maison
        Maison updatedMaison = maisonRepository.findById(maison.getId()).get();
        // Disconnect from session so that the updates on updatedMaison are not directly saved in db
        em.detach(updatedMaison);
        updatedMaison.nom(UPDATED_NOM).slug(UPDATED_SLUG).description(UPDATED_DESCRIPTION);
        MaisonDTO maisonDTO = maisonMapper.toDto(updatedMaison);

        restMaisonMockMvc
            .perform(put("/api/maisons").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(maisonDTO)))
            .andExpect(status().isOk());

        // Validate the Maison in the database
        List<Maison> maisonList = maisonRepository.findAll();
        assertThat(maisonList).hasSize(databaseSizeBeforeUpdate);
        Maison testMaison = maisonList.get(maisonList.size() - 1);
        assertThat(testMaison.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testMaison.getSlug()).isEqualTo(UPDATED_SLUG);
        assertThat(testMaison.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingMaison() throws Exception {
        int databaseSizeBeforeUpdate = maisonRepository.findAll().size();

        // Create the Maison
        MaisonDTO maisonDTO = maisonMapper.toDto(maison);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMaisonMockMvc
            .perform(put("/api/maisons").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(maisonDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Maison in the database
        List<Maison> maisonList = maisonRepository.findAll();
        assertThat(maisonList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMaison() throws Exception {
        // Initialize the database
        maisonRepository.saveAndFlush(maison);

        int databaseSizeBeforeDelete = maisonRepository.findAll().size();

        // Delete the maison
        restMaisonMockMvc
            .perform(delete("/api/maisons/{id}", maison.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Maison> maisonList = maisonRepository.findAll();
        assertThat(maisonList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
