package com.pirtol.mjk.web.rest;

import com.pirtol.mjk.service.EthnieService;
import com.pirtol.mjk.service.dto.EthnieDTO;
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
 * REST controller for managing {@link com.pirtol.mjk.domain.Ethnie}.
 */
@RestController
@RequestMapping("/api")
public class EthnieResource {
    private final Logger log = LoggerFactory.getLogger(EthnieResource.class);

    private static final String ENTITY_NAME = "ethnie";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EthnieService ethnieService;

    public EthnieResource(EthnieService ethnieService) {
        this.ethnieService = ethnieService;
    }

    /**
     * {@code POST  /ethnies} : Create a new ethnie.
     *
     * @param ethnieDTO the ethnieDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ethnieDTO, or with status {@code 400 (Bad Request)} if the ethnie has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ethnies")
    public ResponseEntity<EthnieDTO> createEthnie(@RequestBody EthnieDTO ethnieDTO) throws URISyntaxException {
        log.debug("REST request to save Ethnie : {}", ethnieDTO);
        if (ethnieDTO.getId() != null) {
            throw new BadRequestAlertException("A new ethnie cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EthnieDTO result = ethnieService.save(ethnieDTO);
        return ResponseEntity
            .created(new URI("/api/ethnies/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ethnies} : Updates an existing ethnie.
     *
     * @param ethnieDTO the ethnieDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ethnieDTO,
     * or with status {@code 400 (Bad Request)} if the ethnieDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ethnieDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ethnies")
    public ResponseEntity<EthnieDTO> updateEthnie(@RequestBody EthnieDTO ethnieDTO) throws URISyntaxException {
        log.debug("REST request to update Ethnie : {}", ethnieDTO);
        if (ethnieDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EthnieDTO result = ethnieService.save(ethnieDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ethnieDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /ethnies} : get all the ethnies.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ethnies in body.
     */
    @GetMapping("/ethnies")
    public ResponseEntity<List<EthnieDTO>> getAllEthnies(Pageable pageable) {
        log.debug("REST request to get a page of Ethnies");
        Page<EthnieDTO> page = ethnieService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /ethnies/:id} : get the "id" ethnie.
     *
     * @param id the id of the ethnieDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ethnieDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ethnies/{id}")
    public ResponseEntity<EthnieDTO> getEthnie(@PathVariable Long id) {
        log.debug("REST request to get Ethnie : {}", id);
        Optional<EthnieDTO> ethnieDTO = ethnieService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ethnieDTO);
    }

    /**
     * {@code DELETE  /ethnies/:id} : delete the "id" ethnie.
     *
     * @param id the id of the ethnieDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ethnies/{id}")
    public ResponseEntity<Void> deleteEthnie(@PathVariable Long id) {
        log.debug("REST request to delete Ethnie : {}", id);
        ethnieService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
