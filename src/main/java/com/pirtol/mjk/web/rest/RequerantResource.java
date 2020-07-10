package com.pirtol.mjk.web.rest;

import com.pirtol.mjk.service.RequerantService;
import com.pirtol.mjk.service.dto.RequerantDTO;
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
 * REST controller for managing {@link com.pirtol.mjk.domain.Requerant}.
 */
@RestController
@RequestMapping("/api")
public class RequerantResource {
    private final Logger log = LoggerFactory.getLogger(RequerantResource.class);

    private static final String ENTITY_NAME = "requerant";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RequerantService requerantService;

    public RequerantResource(RequerantService requerantService) {
        this.requerantService = requerantService;
    }

    /**
     * {@code POST  /requerants} : Create a new requerant.
     *
     * @param requerantDTO the requerantDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new requerantDTO, or with status {@code 400 (Bad Request)} if the requerant has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/requerants")
    public ResponseEntity<RequerantDTO> createRequerant(@RequestBody RequerantDTO requerantDTO) throws URISyntaxException {
        log.debug("REST request to save Requerant : {}", requerantDTO);
        if (requerantDTO.getId() != null) {
            throw new BadRequestAlertException("A new requerant cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RequerantDTO result = requerantService.save(requerantDTO);
        return ResponseEntity
            .created(new URI("/api/requerants/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /requerants} : Updates an existing requerant.
     *
     * @param requerantDTO the requerantDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated requerantDTO,
     * or with status {@code 400 (Bad Request)} if the requerantDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the requerantDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/requerants")
    public ResponseEntity<RequerantDTO> updateRequerant(@RequestBody RequerantDTO requerantDTO) throws URISyntaxException {
        log.debug("REST request to update Requerant : {}", requerantDTO);
        if (requerantDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RequerantDTO result = requerantService.save(requerantDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, requerantDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /requerants} : get all the requerants.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of requerants in body.
     */
    @GetMapping("/requerants")
    public ResponseEntity<List<RequerantDTO>> getAllRequerants(Pageable pageable) {
        log.debug("REST request to get a page of Requerants");
        Page<RequerantDTO> page = requerantService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /requerants/:id} : get the "id" requerant.
     *
     * @param id the id of the requerantDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the requerantDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/requerants/{id}")
    public ResponseEntity<RequerantDTO> getRequerant(@PathVariable Long id) {
        log.debug("REST request to get Requerant : {}", id);
        Optional<RequerantDTO> requerantDTO = requerantService.findOne(id);
        return ResponseUtil.wrapOrNotFound(requerantDTO);
    }

    /**
     * {@code DELETE  /requerants/:id} : delete the "id" requerant.
     *
     * @param id the id of the requerantDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/requerants/{id}")
    public ResponseEntity<Void> deleteRequerant(@PathVariable Long id) {
        log.debug("REST request to delete Requerant : {}", id);
        requerantService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
