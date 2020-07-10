package com.pirtol.mjk.service;

import com.pirtol.mjk.service.dto.RequerantDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.pirtol.mjk.domain.Requerant}.
 */
public interface RequerantService {
    /**
     * Save a requerant.
     *
     * @param requerantDTO the entity to save.
     * @return the persisted entity.
     */
    RequerantDTO save(RequerantDTO requerantDTO);

    /**
     * Get all the requerants.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<RequerantDTO> findAll(Pageable pageable);

    /**
     * Get the "id" requerant.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RequerantDTO> findOne(Long id);

    /**
     * Delete the "id" requerant.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
