package com.pirtol.mjk.service;

import com.pirtol.mjk.service.dto.OrigineSaisineDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.pirtol.mjk.domain.OrigineSaisine}.
 */
public interface OrigineSaisineService {
    /**
     * Save a origineSaisine.
     *
     * @param origineSaisineDTO the entity to save.
     * @return the persisted entity.
     */
    OrigineSaisineDTO save(OrigineSaisineDTO origineSaisineDTO);

    /**
     * Get all the origineSaisines.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<OrigineSaisineDTO> findAll(Pageable pageable);

    /**
     * Get the "id" origineSaisine.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<OrigineSaisineDTO> findOne(Long id);

    /**
     * Delete the "id" origineSaisine.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
