package com.pirtol.mjk.service;

import com.pirtol.mjk.service.dto.CreanceDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.pirtol.mjk.domain.Creance}.
 */
public interface CreanceService {
    /**
     * Save a creance.
     *
     * @param creanceDTO the entity to save.
     * @return the persisted entity.
     */
    CreanceDTO save(CreanceDTO creanceDTO);

    /**
     * Get all the creances.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CreanceDTO> findAll(Pageable pageable);

    /**
     * Get the "id" creance.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CreanceDTO> findOne(Long id);

    /**
     * Delete the "id" creance.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
