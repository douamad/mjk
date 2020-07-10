package com.pirtol.mjk.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.pirtol.mjk.MjkApp;
import com.pirtol.mjk.domain.ObjetSaisine;
import com.pirtol.mjk.repository.ObjetSaisineRepository;
import com.pirtol.mjk.service.ObjetSaisineService;
import com.pirtol.mjk.service.dto.ObjetSaisineDTO;
import com.pirtol.mjk.service.mapper.ObjetSaisineMapper;
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
 * Integration tests for the {@link ObjetSaisineResource} REST controller.
 */
@SpringBootTest(classes = MjkApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ObjetSaisineResourceIT {
    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final String DEFAULT_SLUG = "AAAAAAAAAA";
    private static final String UPDATED_SLUG = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private ObjetSaisineRepository objetSaisineRepository;

    @Autowired
    private ObjetSaisineMapper objetSaisineMapper;

    @Autowired
    private ObjetSaisineService objetSaisineService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restObjetSaisineMockMvc;

    private ObjetSaisine objetSaisine;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ObjetSaisine createEntity(EntityManager em) {
        ObjetSaisine objetSaisine = new ObjetSaisine().nom(DEFAULT_NOM).slug(DEFAULT_SLUG).description(DEFAULT_DESCRIPTION);
        return objetSaisine;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ObjetSaisine createUpdatedEntity(EntityManager em) {
        ObjetSaisine objetSaisine = new ObjetSaisine().nom(UPDATED_NOM).slug(UPDATED_SLUG).description(UPDATED_DESCRIPTION);
        return objetSaisine;
    }

    @BeforeEach
    public void initTest() {
        objetSaisine = createEntity(em);
    }

    @Test
    @Transactional
    public void createObjetSaisine() throws Exception {
        int databaseSizeBeforeCreate = objetSaisineRepository.findAll().size();
        // Create the ObjetSaisine
        ObjetSaisineDTO objetSaisineDTO = objetSaisineMapper.toDto(objetSaisine);
        restObjetSaisineMockMvc
            .perform(
                post("/api/objet-saisines")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(objetSaisineDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ObjetSaisine in the database
        List<ObjetSaisine> objetSaisineList = objetSaisineRepository.findAll();
        assertThat(objetSaisineList).hasSize(databaseSizeBeforeCreate + 1);
        ObjetSaisine testObjetSaisine = objetSaisineList.get(objetSaisineList.size() - 1);
        assertThat(testObjetSaisine.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testObjetSaisine.getSlug()).isEqualTo(DEFAULT_SLUG);
        assertThat(testObjetSaisine.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createObjetSaisineWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = objetSaisineRepository.findAll().size();

        // Create the ObjetSaisine with an existing ID
        objetSaisine.setId(1L);
        ObjetSaisineDTO objetSaisineDTO = objetSaisineMapper.toDto(objetSaisine);

        // An entity with an existing ID cannot be created, so this API call must fail
        restObjetSaisineMockMvc
            .perform(
                post("/api/objet-saisines")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(objetSaisineDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ObjetSaisine in the database
        List<ObjetSaisine> objetSaisineList = objetSaisineRepository.findAll();
        assertThat(objetSaisineList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllObjetSaisines() throws Exception {
        // Initialize the database
        objetSaisineRepository.saveAndFlush(objetSaisine);

        // Get all the objetSaisineList
        restObjetSaisineMockMvc
            .perform(get("/api/objet-saisines?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(objetSaisine.getId().intValue())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM)))
            .andExpect(jsonPath("$.[*].slug").value(hasItem(DEFAULT_SLUG)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)));
    }

    @Test
    @Transactional
    public void getObjetSaisine() throws Exception {
        // Initialize the database
        objetSaisineRepository.saveAndFlush(objetSaisine);

        // Get the objetSaisine
        restObjetSaisineMockMvc
            .perform(get("/api/objet-saisines/{id}", objetSaisine.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(objetSaisine.getId().intValue()))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM))
            .andExpect(jsonPath("$.slug").value(DEFAULT_SLUG))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION));
    }

    @Test
    @Transactional
    public void getNonExistingObjetSaisine() throws Exception {
        // Get the objetSaisine
        restObjetSaisineMockMvc.perform(get("/api/objet-saisines/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateObjetSaisine() throws Exception {
        // Initialize the database
        objetSaisineRepository.saveAndFlush(objetSaisine);

        int databaseSizeBeforeUpdate = objetSaisineRepository.findAll().size();

        // Update the objetSaisine
        ObjetSaisine updatedObjetSaisine = objetSaisineRepository.findById(objetSaisine.getId()).get();
        // Disconnect from session so that the updates on updatedObjetSaisine are not directly saved in db
        em.detach(updatedObjetSaisine);
        updatedObjetSaisine.nom(UPDATED_NOM).slug(UPDATED_SLUG).description(UPDATED_DESCRIPTION);
        ObjetSaisineDTO objetSaisineDTO = objetSaisineMapper.toDto(updatedObjetSaisine);

        restObjetSaisineMockMvc
            .perform(
                put("/api/objet-saisines")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(objetSaisineDTO))
            )
            .andExpect(status().isOk());

        // Validate the ObjetSaisine in the database
        List<ObjetSaisine> objetSaisineList = objetSaisineRepository.findAll();
        assertThat(objetSaisineList).hasSize(databaseSizeBeforeUpdate);
        ObjetSaisine testObjetSaisine = objetSaisineList.get(objetSaisineList.size() - 1);
        assertThat(testObjetSaisine.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testObjetSaisine.getSlug()).isEqualTo(UPDATED_SLUG);
        assertThat(testObjetSaisine.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingObjetSaisine() throws Exception {
        int databaseSizeBeforeUpdate = objetSaisineRepository.findAll().size();

        // Create the ObjetSaisine
        ObjetSaisineDTO objetSaisineDTO = objetSaisineMapper.toDto(objetSaisine);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restObjetSaisineMockMvc
            .perform(
                put("/api/objet-saisines")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(objetSaisineDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ObjetSaisine in the database
        List<ObjetSaisine> objetSaisineList = objetSaisineRepository.findAll();
        assertThat(objetSaisineList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteObjetSaisine() throws Exception {
        // Initialize the database
        objetSaisineRepository.saveAndFlush(objetSaisine);

        int databaseSizeBeforeDelete = objetSaisineRepository.findAll().size();

        // Delete the objetSaisine
        restObjetSaisineMockMvc
            .perform(delete("/api/objet-saisines/{id}", objetSaisine.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ObjetSaisine> objetSaisineList = objetSaisineRepository.findAll();
        assertThat(objetSaisineList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
