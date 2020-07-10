package com.pirtol.mjk.service;

import com.pirtol.mjk.service.dto.SaisineDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.pirtol.mjk.domain.Saisine}.
 */
public interface SaisineService {
    /**
     * Save a saisine.
     *
     * @param saisineDTO the entity to save.
     * @return the persisted entity.
     */
    SaisineDTO save(SaisineDTO saisineDTO);

    /**
     * Get all the saisines.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SaisineDTO> findAll(Pageable pageable);

    /**
     * Get the "id" saisine.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SaisineDTO> findOne(Long id);

    /**
     * Delete the "id" saisine.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
