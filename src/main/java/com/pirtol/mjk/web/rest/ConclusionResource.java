package com.pirtol.mjk.web.rest;

import com.pirtol.mjk.service.ConclusionService;
import com.pirtol.mjk.service.dto.ConclusionDTO;
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
 * REST controller for managing {@link com.pirtol.mjk.domain.Conclusion}.
 */
@RestController
@RequestMapping("/api")
public class ConclusionResource {
    private final Logger log = LoggerFactory.getLogger(ConclusionResource.class);

    private static final String ENTITY_NAME = "conclusion";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ConclusionService conclusionService;

    public ConclusionResource(ConclusionService conclusionService) {
        this.conclusionService = conclusionService;
    }

    /**
     * {@code POST  /conclusions} : Create a new conclusion.
     *
     * @param conclusionDTO the conclusionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new conclusionDTO, or with status {@code 400 (Bad Request)} if the conclusion has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/conclusions")
    public ResponseEntity<ConclusionDTO> createConclusion(@RequestBody ConclusionDTO conclusionDTO) throws URISyntaxException {
        log.debug("REST request to save Conclusion : {}", conclusionDTO);
        if (conclusionDTO.getId() != null) {
            throw new BadRequestAlertException("A new conclusion cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ConclusionDTO result = conclusionService.save(conclusionDTO);
        return ResponseEntity
            .created(new URI("/api/conclusions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /conclusions} : Updates an existing conclusion.
     *
     * @param conclusionDTO the conclusionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated conclusionDTO,
     * or with status {@code 400 (Bad Request)} if the conclusionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the conclusionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/conclusions")
    public ResponseEntity<ConclusionDTO> updateConclusion(@RequestBody ConclusionDTO conclusionDTO) throws URISyntaxException {
        log.debug("REST request to update Conclusion : {}", conclusionDTO);
        if (conclusionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ConclusionDTO result = conclusionService.save(conclusionDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, conclusionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /conclusions} : get all the conclusions.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of conclusions in body.
     */
    @GetMapping("/conclusions")
    public ResponseEntity<List<ConclusionDTO>> getAllConclusions(Pageable pageable) {
        log.debug("REST request to get a page of Conclusions");
        Page<ConclusionDTO> page = conclusionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /conclusions/:id} : get the "id" conclusion.
     *
     * @param id the id of the conclusionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the conclusionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/conclusions/{id}")
    public ResponseEntity<ConclusionDTO> getConclusion(@PathVariable Long id) {
        log.debug("REST request to get Conclusion : {}", id);
        Optional<ConclusionDTO> conclusionDTO = conclusionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(conclusionDTO);
    }

    /**
     * {@code DELETE  /conclusions/:id} : delete the "id" conclusion.
     *
     * @param id the id of the conclusionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/conclusions/{id}")
    public ResponseEntity<Void> deleteConclusion(@PathVariable Long id) {
        log.debug("REST request to delete Conclusion : {}", id);
        conclusionService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
