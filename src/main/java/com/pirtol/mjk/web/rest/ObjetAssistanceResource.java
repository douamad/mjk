package com.pirtol.mjk.web.rest;

import com.pirtol.mjk.service.ObjetAssistanceService;
import com.pirtol.mjk.service.dto.ObjetAssistanceDTO;
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
 * REST controller for managing {@link com.pirtol.mjk.domain.ObjetAssistance}.
 */
@RestController
@RequestMapping("/api")
public class ObjetAssistanceResource {
    private final Logger log = LoggerFactory.getLogger(ObjetAssistanceResource.class);

    private static final String ENTITY_NAME = "objetAssistance";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ObjetAssistanceService objetAssistanceService;

    public ObjetAssistanceResource(ObjetAssistanceService objetAssistanceService) {
        this.objetAssistanceService = objetAssistanceService;
    }

    /**
     * {@code POST  /objet-assistances} : Create a new objetAssistance.
     *
     * @param objetAssistanceDTO the objetAssistanceDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new objetAssistanceDTO, or with status {@code 400 (Bad Request)} if the objetAssistance has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/objet-assistances")
    public ResponseEntity<ObjetAssistanceDTO> createObjetAssistance(@RequestBody ObjetAssistanceDTO objetAssistanceDTO)
        throws URISyntaxException {
        log.debug("REST request to save ObjetAssistance : {}", objetAssistanceDTO);
        if (objetAssistanceDTO.getId() != null) {
            throw new BadRequestAlertException("A new objetAssistance cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ObjetAssistanceDTO result = objetAssistanceService.save(objetAssistanceDTO);
        return ResponseEntity
            .created(new URI("/api/objet-assistances/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /objet-assistances} : Updates an existing objetAssistance.
     *
     * @param objetAssistanceDTO the objetAssistanceDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated objetAssistanceDTO,
     * or with status {@code 400 (Bad Request)} if the objetAssistanceDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the objetAssistanceDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/objet-assistances")
    public ResponseEntity<ObjetAssistanceDTO> updateObjetAssistance(@RequestBody ObjetAssistanceDTO objetAssistanceDTO)
        throws URISyntaxException {
        log.debug("REST request to update ObjetAssistance : {}", objetAssistanceDTO);
        if (objetAssistanceDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ObjetAssistanceDTO result = objetAssistanceService.save(objetAssistanceDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, objetAssistanceDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /objet-assistances} : get all the objetAssistances.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of objetAssistances in body.
     */
    @GetMapping("/objet-assistances")
    public ResponseEntity<List<ObjetAssistanceDTO>> getAllObjetAssistances(Pageable pageable) {
        log.debug("REST request to get a page of ObjetAssistances");
        Page<ObjetAssistanceDTO> page = objetAssistanceService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /objet-assistances/:id} : get the "id" objetAssistance.
     *
     * @param id the id of the objetAssistanceDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the objetAssistanceDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/objet-assistances/{id}")
    public ResponseEntity<ObjetAssistanceDTO> getObjetAssistance(@PathVariable Long id) {
        log.debug("REST request to get ObjetAssistance : {}", id);
        Optional<ObjetAssistanceDTO> objetAssistanceDTO = objetAssistanceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(objetAssistanceDTO);
    }

    /**
     * {@code DELETE  /objet-assistances/:id} : delete the "id" objetAssistance.
     *
     * @param id the id of the objetAssistanceDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/objet-assistances/{id}")
    public ResponseEntity<Void> deleteObjetAssistance(@PathVariable Long id) {
        log.debug("REST request to delete ObjetAssistance : {}", id);
        objetAssistanceService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
