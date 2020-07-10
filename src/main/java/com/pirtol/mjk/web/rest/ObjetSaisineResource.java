package com.pirtol.mjk.web.rest;

import com.pirtol.mjk.service.ObjetSaisineService;
import com.pirtol.mjk.service.dto.ObjetSaisineDTO;
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
 * REST controller for managing {@link com.pirtol.mjk.domain.ObjetSaisine}.
 */
@RestController
@RequestMapping("/api")
public class ObjetSaisineResource {
    private final Logger log = LoggerFactory.getLogger(ObjetSaisineResource.class);

    private static final String ENTITY_NAME = "objetSaisine";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ObjetSaisineService objetSaisineService;

    public ObjetSaisineResource(ObjetSaisineService objetSaisineService) {
        this.objetSaisineService = objetSaisineService;
    }

    /**
     * {@code POST  /objet-saisines} : Create a new objetSaisine.
     *
     * @param objetSaisineDTO the objetSaisineDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new objetSaisineDTO, or with status {@code 400 (Bad Request)} if the objetSaisine has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/objet-saisines")
    public ResponseEntity<ObjetSaisineDTO> createObjetSaisine(@RequestBody ObjetSaisineDTO objetSaisineDTO) throws URISyntaxException {
        log.debug("REST request to save ObjetSaisine : {}", objetSaisineDTO);
        if (objetSaisineDTO.getId() != null) {
            throw new BadRequestAlertException("A new objetSaisine cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ObjetSaisineDTO result = objetSaisineService.save(objetSaisineDTO);
        return ResponseEntity
            .created(new URI("/api/objet-saisines/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /objet-saisines} : Updates an existing objetSaisine.
     *
     * @param objetSaisineDTO the objetSaisineDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated objetSaisineDTO,
     * or with status {@code 400 (Bad Request)} if the objetSaisineDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the objetSaisineDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/objet-saisines")
    public ResponseEntity<ObjetSaisineDTO> updateObjetSaisine(@RequestBody ObjetSaisineDTO objetSaisineDTO) throws URISyntaxException {
        log.debug("REST request to update ObjetSaisine : {}", objetSaisineDTO);
        if (objetSaisineDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ObjetSaisineDTO result = objetSaisineService.save(objetSaisineDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, objetSaisineDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /objet-saisines} : get all the objetSaisines.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of objetSaisines in body.
     */
    @GetMapping("/objet-saisines")
    public ResponseEntity<List<ObjetSaisineDTO>> getAllObjetSaisines(Pageable pageable) {
        log.debug("REST request to get a page of ObjetSaisines");
        Page<ObjetSaisineDTO> page = objetSaisineService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /objet-saisines/:id} : get the "id" objetSaisine.
     *
     * @param id the id of the objetSaisineDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the objetSaisineDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/objet-saisines/{id}")
    public ResponseEntity<ObjetSaisineDTO> getObjetSaisine(@PathVariable Long id) {
        log.debug("REST request to get ObjetSaisine : {}", id);
        Optional<ObjetSaisineDTO> objetSaisineDTO = objetSaisineService.findOne(id);
        return ResponseUtil.wrapOrNotFound(objetSaisineDTO);
    }

    /**
     * {@code DELETE  /objet-saisines/:id} : delete the "id" objetSaisine.
     *
     * @param id the id of the objetSaisineDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/objet-saisines/{id}")
    public ResponseEntity<Void> deleteObjetSaisine(@PathVariable Long id) {
        log.debug("REST request to delete ObjetSaisine : {}", id);
        objetSaisineService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
