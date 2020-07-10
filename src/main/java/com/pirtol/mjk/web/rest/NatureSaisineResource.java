package com.pirtol.mjk.web.rest;

import com.pirtol.mjk.service.NatureSaisineService;
import com.pirtol.mjk.service.dto.NatureSaisineDTO;
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
 * REST controller for managing {@link com.pirtol.mjk.domain.NatureSaisine}.
 */
@RestController
@RequestMapping("/api")
public class NatureSaisineResource {
    private final Logger log = LoggerFactory.getLogger(NatureSaisineResource.class);

    private static final String ENTITY_NAME = "natureSaisine";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NatureSaisineService natureSaisineService;

    public NatureSaisineResource(NatureSaisineService natureSaisineService) {
        this.natureSaisineService = natureSaisineService;
    }

    /**
     * {@code POST  /nature-saisines} : Create a new natureSaisine.
     *
     * @param natureSaisineDTO the natureSaisineDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new natureSaisineDTO, or with status {@code 400 (Bad Request)} if the natureSaisine has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/nature-saisines")
    public ResponseEntity<NatureSaisineDTO> createNatureSaisine(@RequestBody NatureSaisineDTO natureSaisineDTO) throws URISyntaxException {
        log.debug("REST request to save NatureSaisine : {}", natureSaisineDTO);
        if (natureSaisineDTO.getId() != null) {
            throw new BadRequestAlertException("A new natureSaisine cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NatureSaisineDTO result = natureSaisineService.save(natureSaisineDTO);
        return ResponseEntity
            .created(new URI("/api/nature-saisines/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /nature-saisines} : Updates an existing natureSaisine.
     *
     * @param natureSaisineDTO the natureSaisineDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated natureSaisineDTO,
     * or with status {@code 400 (Bad Request)} if the natureSaisineDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the natureSaisineDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/nature-saisines")
    public ResponseEntity<NatureSaisineDTO> updateNatureSaisine(@RequestBody NatureSaisineDTO natureSaisineDTO) throws URISyntaxException {
        log.debug("REST request to update NatureSaisine : {}", natureSaisineDTO);
        if (natureSaisineDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NatureSaisineDTO result = natureSaisineService.save(natureSaisineDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, natureSaisineDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /nature-saisines} : get all the natureSaisines.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of natureSaisines in body.
     */
    @GetMapping("/nature-saisines")
    public ResponseEntity<List<NatureSaisineDTO>> getAllNatureSaisines(Pageable pageable) {
        log.debug("REST request to get a page of NatureSaisines");
        Page<NatureSaisineDTO> page = natureSaisineService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /nature-saisines/:id} : get the "id" natureSaisine.
     *
     * @param id the id of the natureSaisineDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the natureSaisineDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/nature-saisines/{id}")
    public ResponseEntity<NatureSaisineDTO> getNatureSaisine(@PathVariable Long id) {
        log.debug("REST request to get NatureSaisine : {}", id);
        Optional<NatureSaisineDTO> natureSaisineDTO = natureSaisineService.findOne(id);
        return ResponseUtil.wrapOrNotFound(natureSaisineDTO);
    }

    /**
     * {@code DELETE  /nature-saisines/:id} : delete the "id" natureSaisine.
     *
     * @param id the id of the natureSaisineDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/nature-saisines/{id}")
    public ResponseEntity<Void> deleteNatureSaisine(@PathVariable Long id) {
        log.debug("REST request to delete NatureSaisine : {}", id);
        natureSaisineService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
