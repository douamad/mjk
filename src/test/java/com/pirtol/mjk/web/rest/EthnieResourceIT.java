package com.pirtol.mjk.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.pirtol.mjk.MjkApp;
import com.pirtol.mjk.domain.Ethnie;
import com.pirtol.mjk.repository.EthnieRepository;
import com.pirtol.mjk.service.EthnieService;
import com.pirtol.mjk.service.dto.EthnieDTO;
import com.pirtol.mjk.service.mapper.EthnieMapper;
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
 * Integration tests for the {@link EthnieResource} REST controller.
 */
@SpringBootTest(classes = MjkApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class EthnieResourceIT {
    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final String DEFAULT_SLUG = "AAAAAAAAAA";
    private static final String UPDATED_SLUG = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private EthnieRepository ethnieRepository;

    @Autowired
    private EthnieMapper ethnieMapper;

    @Autowired
    private EthnieService ethnieService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEthnieMockMvc;

    private Ethnie ethnie;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Ethnie createEntity(EntityManager em) {
        Ethnie ethnie = new Ethnie().nom(DEFAULT_NOM).slug(DEFAULT_SLUG).description(DEFAULT_DESCRIPTION);
        return ethnie;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Ethnie createUpdatedEntity(EntityManager em) {
        Ethnie ethnie = new Ethnie().nom(UPDATED_NOM).slug(UPDATED_SLUG).description(UPDATED_DESCRIPTION);
        return ethnie;
    }

    @BeforeEach
    public void initTest() {
        ethnie = createEntity(em);
    }

    @Test
    @Transactional
    public void createEthnie() throws Exception {
        int databaseSizeBeforeCreate = ethnieRepository.findAll().size();
        // Create the Ethnie
        EthnieDTO ethnieDTO = ethnieMapper.toDto(ethnie);
        restEthnieMockMvc
            .perform(post("/api/ethnies").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ethnieDTO)))
            .andExpect(status().isCreated());

        // Validate the Ethnie in the database
        List<Ethnie> ethnieList = ethnieRepository.findAll();
        assertThat(ethnieList).hasSize(databaseSizeBeforeCreate + 1);
        Ethnie testEthnie = ethnieList.get(ethnieList.size() - 1);
        assertThat(testEthnie.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testEthnie.getSlug()).isEqualTo(DEFAULT_SLUG);
        assertThat(testEthnie.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createEthnieWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ethnieRepository.findAll().size();

        // Create the Ethnie with an existing ID
        ethnie.setId(1L);
        EthnieDTO ethnieDTO = ethnieMapper.toDto(ethnie);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEthnieMockMvc
            .perform(post("/api/ethnies").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ethnieDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Ethnie in the database
        List<Ethnie> ethnieList = ethnieRepository.findAll();
        assertThat(ethnieList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllEthnies() throws Exception {
        // Initialize the database
        ethnieRepository.saveAndFlush(ethnie);

        // Get all the ethnieList
        restEthnieMockMvc
            .perform(get("/api/ethnies?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ethnie.getId().intValue())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM)))
            .andExpect(jsonPath("$.[*].slug").value(hasItem(DEFAULT_SLUG)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)));
    }

    @Test
    @Transactional
    public void getEthnie() throws Exception {
        // Initialize the database
        ethnieRepository.saveAndFlush(ethnie);

        // Get the ethnie
        restEthnieMockMvc
            .perform(get("/api/ethnies/{id}", ethnie.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(ethnie.getId().intValue()))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM))
            .andExpect(jsonPath("$.slug").value(DEFAULT_SLUG))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION));
    }

    @Test
    @Transactional
    public void getNonExistingEthnie() throws Exception {
        // Get the ethnie
        restEthnieMockMvc.perform(get("/api/ethnies/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEthnie() throws Exception {
        // Initialize the database
        ethnieRepository.saveAndFlush(ethnie);

        int databaseSizeBeforeUpdate = ethnieRepository.findAll().size();

        // Update the ethnie
        Ethnie updatedEthnie = ethnieRepository.findById(ethnie.getId()).get();
        // Disconnect from session so that the updates on updatedEthnie are not directly saved in db
        em.detach(updatedEthnie);
        updatedEthnie.nom(UPDATED_NOM).slug(UPDATED_SLUG).description(UPDATED_DESCRIPTION);
        EthnieDTO ethnieDTO = ethnieMapper.toDto(updatedEthnie);

        restEthnieMockMvc
            .perform(put("/api/ethnies").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ethnieDTO)))
            .andExpect(status().isOk());

        // Validate the Ethnie in the database
        List<Ethnie> ethnieList = ethnieRepository.findAll();
        assertThat(ethnieList).hasSize(databaseSizeBeforeUpdate);
        Ethnie testEthnie = ethnieList.get(ethnieList.size() - 1);
        assertThat(testEthnie.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testEthnie.getSlug()).isEqualTo(UPDATED_SLUG);
        assertThat(testEthnie.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingEthnie() throws Exception {
        int databaseSizeBeforeUpdate = ethnieRepository.findAll().size();

        // Create the Ethnie
        EthnieDTO ethnieDTO = ethnieMapper.toDto(ethnie);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEthnieMockMvc
            .perform(put("/api/ethnies").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ethnieDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Ethnie in the database
        List<Ethnie> ethnieList = ethnieRepository.findAll();
        assertThat(ethnieList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEthnie() throws Exception {
        // Initialize the database
        ethnieRepository.saveAndFlush(ethnie);

        int databaseSizeBeforeDelete = ethnieRepository.findAll().size();

        // Delete the ethnie
        restEthnieMockMvc
            .perform(delete("/api/ethnies/{id}", ethnie.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Ethnie> ethnieList = ethnieRepository.findAll();
        assertThat(ethnieList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
