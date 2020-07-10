package com.pirtol.mjk.service;

import com.pirtol.mjk.service.dto.ObjetSaisineDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.pirtol.mjk.domain.ObjetSaisine}.
 */
public interface ObjetSaisineService {
    /**
     * Save a objetSaisine.
     *
     * @param objetSaisineDTO the entity to save.
     * @return the persisted entity.
     */
    ObjetSaisineDTO save(ObjetSaisineDTO objetSaisineDTO);

    /**
     * Get all the objetSaisines.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ObjetSaisineDTO> findAll(Pageable pageable);

    /**
     * Get the "id" objetSaisine.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ObjetSaisineDTO> findOne(Long id);

    /**
     * Delete the "id" objetSaisine.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
