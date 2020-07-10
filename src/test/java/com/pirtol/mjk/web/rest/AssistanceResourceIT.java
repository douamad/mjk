package com.pirtol.mjk.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.pirtol.mjk.MjkApp;
import com.pirtol.mjk.domain.Assistance;
import com.pirtol.mjk.repository.AssistanceRepository;
import com.pirtol.mjk.service.AssistanceService;
import com.pirtol.mjk.service.dto.AssistanceDTO;
import com.pirtol.mjk.service.mapper.AssistanceMapper;
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
 * Integration tests for the {@link AssistanceResource} REST controller.
 */
@SpringBootTest(classes = MjkApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class AssistanceResourceIT {
    private static final String DEFAULT_REFERENCE = "AAAAAAAAAA";
    private static final String UPDATED_REFERENCE = "BBBBBBBBBB";

    private static final String DEFAULT_DATE = "AAAAAAAAAA";
    private static final String UPDATED_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_COUT = "AAAAAAAAAA";
    private static final String UPDATED_COUT = "BBBBBBBBBB";

    @Autowired
    private AssistanceRepository assistanceRepository;

    @Autowired
    private AssistanceMapper assistanceMapper;

    @Autowired
    private AssistanceService assistanceService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAssistanceMockMvc;

    private Assistance assistance;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Assistance createEntity(EntityManager em) {
        Assistance assistance = new Assistance().reference(DEFAULT_REFERENCE).date(DEFAULT_DATE).cout(DEFAULT_COUT);
        return assistance;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Assistance createUpdatedEntity(EntityManager em) {
        Assistance assistance = new Assistance().reference(UPDATED_REFERENCE).date(UPDATED_DATE).cout(UPDATED_COUT);
        return assistance;
    }

    @BeforeEach
    public void initTest() {
        assistance = createEntity(em);
    }

    @Test
    @Transactional
    public void createAssistance() throws Exception {
        int databaseSizeBeforeCreate = assistanceRepository.findAll().size();
        // Create the Assistance
        AssistanceDTO assistanceDTO = assistanceMapper.toDto(assistance);
        restAssistanceMockMvc
            .perform(
                post("/api/assistances").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(assistanceDTO))
            )
            .andExpect(status().isCreated());

        // Validate the Assistance in the database
        List<Assistance> assistanceList = assistanceRepository.findAll();
        assertThat(assistanceList).hasSize(databaseSizeBeforeCreate + 1);
        Assistance testAssistance = assistanceList.get(assistanceList.size() - 1);
        assertThat(testAssistance.getReference()).isEqualTo(DEFAULT_REFERENCE);
        assertThat(testAssistance.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testAssistance.getCout()).isEqualTo(DEFAULT_COUT);
    }

    @Test
    @Transactional
    public void createAssistanceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = assistanceRepository.findAll().size();

        // Create the Assistance with an existing ID
        assistance.setId(1L);
        AssistanceDTO assistanceDTO = assistanceMapper.toDto(assistance);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAssistanceMockMvc
            .perform(
                post("/api/assistances").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(assistanceDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Assistance in the database
        List<Assistance> assistanceList = assistanceRepository.findAll();
        assertThat(assistanceList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllAssistances() throws Exception {
        // Initialize the database
        assistanceRepository.saveAndFlush(assistance);

        // Get all the assistanceList
        restAssistanceMockMvc
            .perform(get("/api/assistances?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(assistance.getId().intValue())))
            .andExpect(jsonPath("$.[*].reference").value(hasItem(DEFAULT_REFERENCE)))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE)))
            .andExpect(jsonPath("$.[*].cout").value(hasItem(DEFAULT_COUT)));
    }

    @Test
    @Transactional
    public void getAssistance() throws Exception {
        // Initialize the database
        assistanceRepository.saveAndFlush(assistance);

        // Get the assistance
        restAssistanceMockMvc
            .perform(get("/api/assistances/{id}", assistance.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(assistance.getId().intValue()))
            .andExpect(jsonPath("$.reference").value(DEFAULT_REFERENCE))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE))
            .andExpect(jsonPath("$.cout").value(DEFAULT_COUT));
    }

    @Test
    @Transactional
    public void getNonExistingAssistance() throws Exception {
        // Get the assistance
        restAssistanceMockMvc.perform(get("/api/assistances/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAssistance() throws Exception {
        // Initialize the database
        assistanceRepository.saveAndFlush(assistance);

        int databaseSizeBeforeUpdate = assistanceRepository.findAll().size();

        // Update the assistance
        Assistance updatedAssistance = assistanceRepository.findById(assistance.getId()).get();
        // Disconnect from session so that the updates on updatedAssistance are not directly saved in db
        em.detach(updatedAssistance);
        updatedAssistance.reference(UPDATED_REFERENCE).date(UPDATED_DATE).cout(UPDATED_COUT);
        AssistanceDTO assistanceDTO = assistanceMapper.toDto(updatedAssistance);

        restAssistanceMockMvc
            .perform(
                put("/api/assistances").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(assistanceDTO))
            )
            .andExpect(status().isOk());

        // Validate the Assistance in the database
        List<Assistance> assistanceList = assistanceRepository.findAll();
        assertThat(assistanceList).hasSize(databaseSizeBeforeUpdate);
        Assistance testAssistance = assistanceList.get(assistanceList.size() - 1);
        assertThat(testAssistance.getReference()).isEqualTo(UPDATED_REFERENCE);
        assertThat(testAssistance.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testAssistance.getCout()).isEqualTo(UPDATED_COUT);
    }

    @Test
    @Transactional
    public void updateNonExistingAssistance() throws Exception {
        int databaseSizeBeforeUpdate = assistanceRepository.findAll().size();

        // Create the Assistance
        AssistanceDTO assistanceDTO = assistanceMapper.toDto(assistance);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAssistanceMockMvc
            .perform(
                put("/api/assistances").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(assistanceDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Assistance in the database
        List<Assistance> assistanceList = assistanceRepository.findAll();
        assertThat(assistanceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAssistance() throws Exception {
        // Initialize the database
        assistanceRepository.saveAndFlush(assistance);

        int databaseSizeBeforeDelete = assistanceRepository.findAll().size();

        // Delete the assistance
        restAssistanceMockMvc
            .perform(delete("/api/assistances/{id}", assistance.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Assistance> assistanceList = assistanceRepository.findAll();
        assertThat(assistanceList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
