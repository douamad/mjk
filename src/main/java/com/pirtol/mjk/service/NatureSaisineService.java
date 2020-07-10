package com.pirtol.mjk.service;

import com.pirtol.mjk.service.dto.NatureSaisineDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.pirtol.mjk.domain.NatureSaisine}.
 */
public interface NatureSaisineService {
    /**
     * Save a natureSaisine.
     *
     * @param natureSaisineDTO the entity to save.
     * @return the persisted entity.
     */
    NatureSaisineDTO save(NatureSaisineDTO natureSaisineDTO);

    /**
     * Get all the natureSaisines.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<NatureSaisineDTO> findAll(Pageable pageable);

    /**
     * Get the "id" natureSaisine.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<NatureSaisineDTO> findOne(Long id);

    /**
     * Delete the "id" natureSaisine.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
