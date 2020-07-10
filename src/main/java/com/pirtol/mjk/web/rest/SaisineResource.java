package com.pirtol.mjk.web.rest;

import com.pirtol.mjk.service.SaisineService;
import com.pirtol.mjk.service.dto.SaisineDTO;
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
 * REST controller for managing {@link com.pirtol.mjk.domain.Saisine}.
 */
@RestController
@RequestMapping("/api")
public class SaisineResource {
    private final Logger log = LoggerFactory.getLogger(SaisineResource.class);

    private static final String ENTITY_NAME = "saisine";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SaisineService saisineService;

    public SaisineResource(SaisineService saisineService) {
        this.saisineService = saisineService;
    }

    /**
     * {@code POST  /saisines} : Create a new saisine.
     *
     * @param saisineDTO the saisineDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new saisineDTO, or with status {@code 400 (Bad Request)} if the saisine has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/saisines")
    public ResponseEntity<SaisineDTO> createSaisine(@RequestBody SaisineDTO saisineDTO) throws URISyntaxException {
        log.debug("REST request to save Saisine : {}", saisineDTO);
        if (saisineDTO.getId() != null) {
            throw new BadRequestAlertException("A new saisine cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SaisineDTO result = saisineService.save(saisineDTO);
        return ResponseEntity
            .created(new URI("/api/saisines/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /saisines} : Updates an existing saisine.
     *
     * @param saisineDTO the saisineDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated saisineDTO,
     * or with status {@code 400 (Bad Request)} if the saisineDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the saisineDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/saisines")
    public ResponseEntity<SaisineDTO> updateSaisine(@RequestBody SaisineDTO saisineDTO) throws URISyntaxException {
        log.debug("REST request to update Saisine : {}", saisineDTO);
        if (saisineDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SaisineDTO result = saisineService.save(saisineDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, saisineDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /saisines} : get all the saisines.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of saisines in body.
     */
    @GetMapping("/saisines")
    public ResponseEntity<List<SaisineDTO>> getAllSaisines(Pageable pageable) {
        log.debug("REST request to get a page of Saisines");
        Page<SaisineDTO> page = saisineService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /saisines/:id} : get the "id" saisine.
     *
     * @param id the id of the saisineDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the saisineDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/saisines/{id}")
    public ResponseEntity<SaisineDTO> getSaisine(@PathVariable Long id) {
        log.debug("REST request to get Saisine : {}", id);
        Optional<SaisineDTO> saisineDTO = saisineService.findOne(id);
        return ResponseUtil.wrapOrNotFound(saisineDTO);
    }

    /**
     * {@code DELETE  /saisines/:id} : delete the "id" saisine.
     *
     * @param id the id of the saisineDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/saisines/{id}")
    public ResponseEntity<Void> deleteSaisine(@PathVariable Long id) {
        log.debug("REST request to delete Saisine : {}", id);
        saisineService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
