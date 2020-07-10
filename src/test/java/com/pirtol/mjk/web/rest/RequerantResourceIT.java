package com.pirtol.mjk.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.pirtol.mjk.MjkApp;
import com.pirtol.mjk.domain.Requerant;
import com.pirtol.mjk.domain.enumeration.Genre;
import com.pirtol.mjk.repository.RequerantRepository;
import com.pirtol.mjk.service.RequerantService;
import com.pirtol.mjk.service.dto.RequerantDTO;
import com.pirtol.mjk.service.mapper.RequerantMapper;
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
 * Integration tests for the {@link RequerantResource} REST controller.
 */
@SpringBootTest(classes = MjkApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class RequerantResourceIT {
    private static final String DEFAULT_PRENOM = "AAAAAAAAAA";
    private static final String UPDATED_PRENOM = "BBBBBBBBBB";

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final String DEFAULT_TELEPHONE = "AAAAAAAAAA";
    private static final String UPDATED_TELEPHONE = "BBBBBBBBBB";

    private static final String DEFAULT_MAIL = "AAAAAAAAAA";
    private static final String UPDATED_MAIL = "BBBBBBBBBB";

    private static final String DEFAULT_LOCALITE = "AAAAAAAAAA";
    private static final String UPDATED_LOCALITE = "BBBBBBBBBB";

    private static final Genre DEFAULT_GENRE = Genre.H;
    private static final Genre UPDATED_GENRE = Genre.F;

    private static final Integer DEFAULT_AGE = 1;
    private static final Integer UPDATED_AGE = 2;

    @Autowired
    private RequerantRepository requerantRepository;

    @Autowired
    private RequerantMapper requerantMapper;

    @Autowired
    private RequerantService requerantService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRequerantMockMvc;

    private Requerant requerant;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Requerant createEntity(EntityManager em) {
        Requerant requerant = new Requerant()
            .prenom(DEFAULT_PRENOM)
            .nom(DEFAULT_NOM)
            .telephone(DEFAULT_TELEPHONE)
            .mail(DEFAULT_MAIL)
            .localite(DEFAULT_LOCALITE)
            .genre(DEFAULT_GENRE)
            .age(DEFAULT_AGE);
        return requerant;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Requerant createUpdatedEntity(EntityManager em) {
        Requerant requerant = new Requerant()
            .prenom(UPDATED_PRENOM)
            .nom(UPDATED_NOM)
            .telephone(UPDATED_TELEPHONE)
            .mail(UPDATED_MAIL)
            .localite(UPDATED_LOCALITE)
            .genre(UPDATED_GENRE)
            .age(UPDATED_AGE);
        return requerant;
    }

    @BeforeEach
    public void initTest() {
        requerant = createEntity(em);
    }

    @Test
    @Transactional
    public void createRequerant() throws Exception {
        int databaseSizeBeforeCreate = requerantRepository.findAll().size();
        // Create the Requerant
        RequerantDTO requerantDTO = requerantMapper.toDto(requerant);
        restRequerantMockMvc
            .perform(
                post("/api/requerants").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(requerantDTO))
            )
            .andExpect(status().isCreated());

        // Validate the Requerant in the database
        List<Requerant> requerantList = requerantRepository.findAll();
        assertThat(requerantList).hasSize(databaseSizeBeforeCreate + 1);
        Requerant testRequerant = requerantList.get(requerantList.size() - 1);
        assertThat(testRequerant.getPrenom()).isEqualTo(DEFAULT_PRENOM);
        assertThat(testRequerant.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testRequerant.getTelephone()).isEqualTo(DEFAULT_TELEPHONE);
        assertThat(testRequerant.getMail()).isEqualTo(DEFAULT_MAIL);
        assertThat(testRequerant.getLocalite()).isEqualTo(DEFAULT_LOCALITE);
        assertThat(testRequerant.getGenre()).isEqualTo(DEFAULT_GENRE);
        assertThat(testRequerant.getAge()).isEqualTo(DEFAULT_AGE);
    }

    @Test
    @Transactional
    public void createRequerantWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = requerantRepository.findAll().size();

        // Create the Requerant with an existing ID
        requerant.setId(1L);
        RequerantDTO requerantDTO = requerantMapper.toDto(requerant);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRequerantMockMvc
            .perform(
                post("/api/requerants").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(requerantDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Requerant in the database
        List<Requerant> requerantList = requerantRepository.findAll();
        assertThat(requerantList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllRequerants() throws Exception {
        // Initialize the database
        requerantRepository.saveAndFlush(requerant);

        // Get all the requerantList
        restRequerantMockMvc
            .perform(get("/api/requerants?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(requerant.getId().intValue())))
            .andExpect(jsonPath("$.[*].prenom").value(hasItem(DEFAULT_PRENOM)))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM)))
            .andExpect(jsonPath("$.[*].telephone").value(hasItem(DEFAULT_TELEPHONE)))
            .andExpect(jsonPath("$.[*].mail").value(hasItem(DEFAULT_MAIL)))
            .andExpect(jsonPath("$.[*].localite").value(hasItem(DEFAULT_LOCALITE)))
            .andExpect(jsonPath("$.[*].genre").value(hasItem(DEFAULT_GENRE.toString())))
            .andExpect(jsonPath("$.[*].age").value(hasItem(DEFAULT_AGE)));
    }

    @Test
    @Transactional
    public void getRequerant() throws Exception {
        // Initialize the database
        requerantRepository.saveAndFlush(requerant);

        // Get the requerant
        restRequerantMockMvc
            .perform(get("/api/requerants/{id}", requerant.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(requerant.getId().intValue()))
            .andExpect(jsonPath("$.prenom").value(DEFAULT_PRENOM))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM))
            .andExpect(jsonPath("$.telephone").value(DEFAULT_TELEPHONE))
            .andExpect(jsonPath("$.mail").value(DEFAULT_MAIL))
            .andExpect(jsonPath("$.localite").value(DEFAULT_LOCALITE))
            .andExpect(jsonPath("$.genre").value(DEFAULT_GENRE.toString()))
            .andExpect(jsonPath("$.age").value(DEFAULT_AGE));
    }

    @Test
    @Transactional
    public void getNonExistingRequerant() throws Exception {
        // Get the requerant
        restRequerantMockMvc.perform(get("/api/requerants/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRequerant() throws Exception {
        // Initialize the database
        requerantRepository.saveAndFlush(requerant);

        int databaseSizeBeforeUpdate = requerantRepository.findAll().size();

        // Update the requerant
        Requerant updatedRequerant = requerantRepository.findById(requerant.getId()).get();
        // Disconnect from session so that the updates on updatedRequerant are not directly saved in db
        em.detach(updatedRequerant);
        updatedRequerant
            .prenom(UPDATED_PRENOM)
            .nom(UPDATED_NOM)
            .telephone(UPDATED_TELEPHONE)
            .mail(UPDATED_MAIL)
            .localite(UPDATED_LOCALITE)
            .genre(UPDATED_GENRE)
            .age(UPDATED_AGE);
        RequerantDTO requerantDTO = requerantMapper.toDto(updatedRequerant);

        restRequerantMockMvc
            .perform(
                put("/api/requerants").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(requerantDTO))
            )
            .andExpect(status().isOk());

        // Validate the Requerant in the database
        List<Requerant> requerantList = requerantRepository.findAll();
        assertThat(requerantList).hasSize(databaseSizeBeforeUpdate);
        Requerant testRequerant = requerantList.get(requerantList.size() - 1);
        assertThat(testRequerant.getPrenom()).isEqualTo(UPDATED_PRENOM);
        assertThat(testRequerant.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testRequerant.getTelephone()).isEqualTo(UPDATED_TELEPHONE);
        assertThat(testRequerant.getMail()).isEqualTo(UPDATED_MAIL);
        assertThat(testRequerant.getLocalite()).isEqualTo(UPDATED_LOCALITE);
        assertThat(testRequerant.getGenre()).isEqualTo(UPDATED_GENRE);
        assertThat(testRequerant.getAge()).isEqualTo(UPDATED_AGE);
    }

    @Test
    @Transactional
    public void updateNonExistingRequerant() throws Exception {
        int databaseSizeBeforeUpdate = requerantRepository.findAll().size();

        // Create the Requerant
        RequerantDTO requerantDTO = requerantMapper.toDto(requerant);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRequerantMockMvc
            .perform(
                put("/api/requerants").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(requerantDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Requerant in the database
        List<Requerant> requerantList = requerantRepository.findAll();
        assertThat(requerantList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRequerant() throws Exception {
        // Initialize the database
        requerantRepository.saveAndFlush(requerant);

        int databaseSizeBeforeDelete = requerantRepository.findAll().size();

        // Delete the requerant
        restRequerantMockMvc
            .perform(delete("/api/requerants/{id}", requerant.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Requerant> requerantList = requerantRepository.findAll();
        assertThat(requerantList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
