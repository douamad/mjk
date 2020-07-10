package com.pirtol.mjk.web.rest;

import com.pirtol.mjk.service.MaisonService;
import com.pirtol.mjk.service.dto.MaisonDTO;
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
 * REST controller for managing {@link com.pirtol.mjk.domain.Maison}.
 */
@RestController
@RequestMapping("/api")
public class MaisonResource {
    private final Logger log = LoggerFactory.getLogger(MaisonResource.class);

    private static final String ENTITY_NAME = "maison";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MaisonService maisonService;

    public MaisonResource(MaisonService maisonService) {
        this.maisonService = maisonService;
    }

    /**
     * {@code POST  /maisons} : Create a new maison.
     *
     * @param maisonDTO the maisonDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new maisonDTO, or with status {@code 400 (Bad Request)} if the maison has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/maisons")
    public ResponseEntity<MaisonDTO> createMaison(@RequestBody MaisonDTO maisonDTO) throws URISyntaxException {
        log.debug("REST request to save Maison : {}", maisonDTO);
        if (maisonDTO.getId() != null) {
            throw new BadRequestAlertException("A new maison cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MaisonDTO result = maisonService.save(maisonDTO);
        return ResponseEntity
            .created(new URI("/api/maisons/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /maisons} : Updates an existing maison.
     *
     * @param maisonDTO the maisonDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated maisonDTO,
     * or with status {@code 400 (Bad Request)} if the maisonDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the maisonDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/maisons")
    public ResponseEntity<MaisonDTO> updateMaison(@RequestBody MaisonDTO maisonDTO) throws URISyntaxException {
        log.debug("REST request to update Maison : {}", maisonDTO);
        if (maisonDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MaisonDTO result = maisonService.save(maisonDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, maisonDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /maisons} : get all the maisons.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of maisons in body.
     */
    @GetMapping("/maisons")
    public ResponseEntity<List<MaisonDTO>> getAllMaisons(Pageable pageable) {
        log.debug("REST request to get a page of Maisons");
        Page<MaisonDTO> page = maisonService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /maisons/:id} : get the "id" maison.
     *
     * @param id the id of the maisonDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the maisonDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/maisons/{id}")
    public ResponseEntity<MaisonDTO> getMaison(@PathVariable Long id) {
        log.debug("REST request to get Maison : {}", id);
        Optional<MaisonDTO> maisonDTO = maisonService.findOne(id);
        return ResponseUtil.wrapOrNotFound(maisonDTO);
    }

    /**
     * {@code DELETE  /maisons/:id} : delete the "id" maison.
     *
     * @param id the id of the maisonDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/maisons/{id}")
    public ResponseEntity<Void> deleteMaison(@PathVariable Long id) {
        log.debug("REST request to delete Maison : {}", id);
        maisonService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
