package com.pirtol.mjk.service;

import com.pirtol.mjk.service.dto.AssistanceDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.pirtol.mjk.domain.Assistance}.
 */
public interface AssistanceService {
    /**
     * Save a assistance.
     *
     * @param assistanceDTO the entity to save.
     * @return the persisted entity.
     */
    AssistanceDTO save(AssistanceDTO assistanceDTO);

    /**
     * Get all the assistances.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AssistanceDTO> findAll(Pageable pageable);

    /**
     * Get the "id" assistance.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AssistanceDTO> findOne(Long id);

    /**
     * Delete the "id" assistance.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
