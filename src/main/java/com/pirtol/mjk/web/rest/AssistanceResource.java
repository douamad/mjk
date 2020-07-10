package com.pirtol.mjk.web.rest;

import com.pirtol.mjk.service.AssistanceService;
import com.pirtol.mjk.service.dto.AssistanceDTO;
import com.pirtol.mjk.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * REST controller for managing {@link com.pirtol.mjk.domain.Assistance}.
 */
@RestController
@RequestMapping("/api")
public class AssistanceResource {
    private final Logger log = LoggerFactory.getLogger(AssistanceResource.class);

    private static final String ENTITY_NAME = "assistance";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AssistanceService assistanceService;

    public AssistanceResource(AssistanceService assistanceService) {
        this.assistanceService = assistanceService;
    }

    /**
     * {@code POST  /assistances} : Create a new assistance.
     *
     * @param assistanceDTO the assistanceDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new assistanceDTO, or with status {@code 400 (Bad Request)} if the assistance has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/assistances")
    public ResponseEntity<AssistanceDTO> createAssistance(@RequestBody AssistanceDTO assistanceDTO) throws URISyntaxException {
        log.debug("REST request to save Assistance : {}", assistanceDTO);
        if (assistanceDTO.getId() != null) {
            throw new BadRequestAlertException("A new assistance cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AssistanceDTO result = assistanceService.save(assistanceDTO);
        return ResponseEntity
            .created(new URI("/api/assistances/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /assistances} : Updates an existing assistance.
     *
     * @param assistanceDTO the assistanceDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated assistanceDTO,
     * or with status {@code 400 (Bad Request)} if the assistanceDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the assistanceDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/assistances")
    public ResponseEntity<AssistanceDTO> updateAssistance(@RequestBody AssistanceDTO assistanceDTO) throws URISyntaxException {
        log.debug("REST request to update Assistance : {}", assistanceDTO);
        if (assistanceDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AssistanceDTO result = assistanceService.save(assistanceDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, assistanceDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /assistances} : get all the assistances.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of assistances in body.
     */
    @GetMapping("/assistances")
    public ResponseEntity<List<AssistanceDTO>> getAllAssistances(Pageable pageable) {
        log.debug("REST request to get a page of Assistances");
        Page<AssistanceDTO> page = assistanceService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /assistances/:id} : get the "id" assistance.
     *
     * @param id the id of the assistanceDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the assistanceDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/assistances/{id}")
    public ResponseEntity<AssistanceDTO> getAssistance(@PathVariable Long id) {
        log.debug("REST request to get Assistance : {}", id);
        Optional<AssistanceDTO> assistanceDTO = assistanceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(assistanceDTO);
    }

    /**
     * {@code DELETE  /assistances/:id} : delete the "id" assistance.
     *
     * @param id the id of the assistanceDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/assistances/{id}")
    public ResponseEntity<Void> deleteAssistance(@PathVariable Long id) {
        log.debug("REST request to delete Assistance : {}", id);
        assistanceService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
