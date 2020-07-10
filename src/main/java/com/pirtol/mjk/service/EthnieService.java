package com.pirtol.mjk.service;

import com.pirtol.mjk.service.dto.EthnieDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.pirtol.mjk.domain.Ethnie}.
 */
public interface EthnieService {
    /**
     * Save a ethnie.
     *
     * @param ethnieDTO the entity to save.
     * @return the persisted entity.
     */
    EthnieDTO save(EthnieDTO ethnieDTO);

    /**
     * Get all the ethnies.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<EthnieDTO> findAll(Pageable pageable);

    /**
     * Get the "id" ethnie.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EthnieDTO> findOne(Long id);

    /**
     * Delete the "id" ethnie.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
