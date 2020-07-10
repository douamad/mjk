package com.pirtol.mjk.service;

import com.pirtol.mjk.service.dto.ObjetAssistanceDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.pirtol.mjk.domain.ObjetAssistance}.
 */
public interface ObjetAssistanceService {
    /**
     * Save a objetAssistance.
     *
     * @param objetAssistanceDTO the entity to save.
     * @return the persisted entity.
     */
    ObjetAssistanceDTO save(ObjetAssistanceDTO objetAssistanceDTO);

    /**
     * Get all the objetAssistances.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ObjetAssistanceDTO> findAll(Pageable pageable);

    /**
     * Get the "id" objetAssistance.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ObjetAssistanceDTO> findOne(Long id);

    /**
     * Delete the "id" objetAssistance.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
