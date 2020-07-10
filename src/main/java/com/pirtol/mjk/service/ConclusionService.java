package com.pirtol.mjk.service;

import com.pirtol.mjk.service.dto.ConclusionDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.pirtol.mjk.domain.Conclusion}.
 */
public interface ConclusionService {
    /**
     * Save a conclusion.
     *
     * @param conclusionDTO the entity to save.
     * @return the persisted entity.
     */
    ConclusionDTO save(ConclusionDTO conclusionDTO);

    /**
     * Get all the conclusions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ConclusionDTO> findAll(Pageable pageable);

    /**
     * Get the "id" conclusion.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ConclusionDTO> findOne(Long id);

    /**
     * Delete the "id" conclusion.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
