package com.pirtol.mjk.web.rest;

import com.pirtol.mjk.service.OrigineSaisineService;
import com.pirtol.mjk.service.dto.OrigineSaisineDTO;
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
 * REST controller for managing {@link com.pirtol.mjk.domain.OrigineSaisine}.
 */
@RestController
@RequestMapping("/api")
public class OrigineSaisineResource {
    private final Logger log = LoggerFactory.getLogger(OrigineSaisineResource.class);

    private static final String ENTITY_NAME = "origineSaisine";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OrigineSaisineService origineSaisineService;

    public OrigineSaisineResource(OrigineSaisineService origineSaisineService) {
        this.origineSaisineService = origineSaisineService;
    }

    /**
     * {@code POST  /origine-saisines} : Create a new origineSaisine.
     *
     * @param origineSaisineDTO the origineSaisineDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new origineSaisineDTO, or with status {@code 400 (Bad Request)} if the origineSaisine has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/origine-saisines")
    public ResponseEntity<OrigineSaisineDTO> createOrigineSaisine(@RequestBody OrigineSaisineDTO origineSaisineDTO)
        throws URISyntaxException {
        log.debug("REST request to save OrigineSaisine : {}", origineSaisineDTO);
        if (origineSaisineDTO.getId() != null) {
            throw new BadRequestAlertException("A new origineSaisine cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OrigineSaisineDTO result = origineSaisineService.save(origineSaisineDTO);
        return ResponseEntity
            .created(new URI("/api/origine-saisines/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /origine-saisines} : Updates an existing origineSaisine.
     *
     * @param origineSaisineDTO the origineSaisineDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated origineSaisineDTO,
     * or with status {@code 400 (Bad Request)} if the origineSaisineDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the origineSaisineDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/origine-saisines")
    public ResponseEntity<OrigineSaisineDTO> updateOrigineSaisine(@RequestBody OrigineSaisineDTO origineSaisineDTO)
        throws URISyntaxException {
        log.debug("REST request to update OrigineSaisine : {}", origineSaisineDTO);
        if (origineSaisineDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        OrigineSaisineDTO result = origineSaisineService.save(origineSaisineDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, origineSaisineDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /origine-saisines} : get all the origineSaisines.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of origineSaisines in body.
     */
    @GetMapping("/origine-saisines")
    public ResponseEntity<List<OrigineSaisineDTO>> getAllOrigineSaisines(Pageable pageable) {
        log.debug("REST request to get a page of OrigineSaisines");
        Page<OrigineSaisineDTO> page = origineSaisineService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /origine-saisines/:id} : get the "id" origineSaisine.
     *
     * @param id the id of the origineSaisineDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the origineSaisineDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/origine-saisines/{id}")
    public ResponseEntity<OrigineSaisineDTO> getOrigineSaisine(@PathVariable Long id) {
        log.debug("REST request to get OrigineSaisine : {}", id);
        Optional<OrigineSaisineDTO> origineSaisineDTO = origineSaisineService.findOne(id);
        return ResponseUtil.wrapOrNotFound(origineSaisineDTO);
    }

    /**
     * {@code DELETE  /origine-saisines/:id} : delete the "id" origineSaisine.
     *
     * @param id the id of the origineSaisineDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/origine-saisines/{id}")
    public ResponseEntity<Void> deleteOrigineSaisine(@PathVariable Long id) {
        log.debug("REST request to delete OrigineSaisine : {}", id);
        origineSaisineService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
