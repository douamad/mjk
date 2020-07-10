package com.pirtol.mjk.web.rest;

import com.pirtol.mjk.service.CreanceService;
import com.pirtol.mjk.service.dto.CreanceDTO;
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
 * REST controller for managing {@link com.pirtol.mjk.domain.Creance}.
 */
@RestController
@RequestMapping("/api")
public class CreanceResource {
    private final Logger log = LoggerFactory.getLogger(CreanceResource.class);

    private static final String ENTITY_NAME = "creance";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CreanceService creanceService;

    public CreanceResource(CreanceService creanceService) {
        this.creanceService = creanceService;
    }

    /**
     * {@code POST  /creances} : Create a new creance.
     *
     * @param creanceDTO the creanceDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new creanceDTO, or with status {@code 400 (Bad Request)} if the creance has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/creances")
    public ResponseEntity<CreanceDTO> createCreance(@RequestBody CreanceDTO creanceDTO) throws URISyntaxException {
        log.debug("REST request to save Creance : {}", creanceDTO);
        if (creanceDTO.getId() != null) {
            throw new BadRequestAlertException("A new creance cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CreanceDTO result = creanceService.save(creanceDTO);
        return ResponseEntity
            .created(new URI("/api/creances/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /creances} : Updates an existing creance.
     *
     * @param creanceDTO the creanceDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated creanceDTO,
     * or with status {@code 400 (Bad Request)} if the creanceDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the creanceDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/creances")
    public ResponseEntity<CreanceDTO> updateCreance(@RequestBody CreanceDTO creanceDTO) throws URISyntaxException {
        log.debug("REST request to update Creance : {}", creanceDTO);
        if (creanceDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CreanceDTO result = creanceService.save(creanceDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, creanceDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /creances} : get all the creances.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of creances in body.
     */
    @GetMapping("/creances")
    public ResponseEntity<List<CreanceDTO>> getAllCreances(Pageable pageable) {
        log.debug("REST request to get a page of Creances");
        Page<CreanceDTO> page = creanceService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /creances/:id} : get the "id" creance.
     *
     * @param id the id of the creanceDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the creanceDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/creances/{id}")
    public ResponseEntity<CreanceDTO> getCreance(@PathVariable Long id) {
        log.debug("REST request to get Creance : {}", id);
        Optional<CreanceDTO> creanceDTO = creanceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(creanceDTO);
    }

    /**
     * {@code DELETE  /creances/:id} : delete the "id" creance.
     *
     * @param id the id of the creanceDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/creances/{id}")
    public ResponseEntity<Void> deleteCreance(@PathVariable Long id) {
        log.debug("REST request to delete Creance : {}", id);
        creanceService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
