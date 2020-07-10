package com.pirtol.mjk.service;

import com.pirtol.mjk.service.dto.MaisonDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.pirtol.mjk.domain.Maison}.
 */
public interface MaisonService {
    /**
     * Save a maison.
     *
     * @param maisonDTO the entity to save.
     * @return the persisted entity.
     */
    MaisonDTO save(MaisonDTO maisonDTO);

    /**
     * Get all the maisons.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<MaisonDTO> findAll(Pageable pageable);

    /**
     * Get the "id" maison.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MaisonDTO> findOne(Long id);

    /**
     * Delete the "id" maison.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
