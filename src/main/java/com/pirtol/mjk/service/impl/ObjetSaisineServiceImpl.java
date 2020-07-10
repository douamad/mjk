package com.pirtol.mjk.service.impl;

import com.pirtol.mjk.domain.ObjetSaisine;
import com.pirtol.mjk.repository.ObjetSaisineRepository;
import com.pirtol.mjk.service.ObjetSaisineService;
import com.pirtol.mjk.service.dto.ObjetSaisineDTO;
import com.pirtol.mjk.service.mapper.ObjetSaisineMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ObjetSaisine}.
 */
@Service
@Transactional
public class ObjetSaisineServiceImpl implements ObjetSaisineService {
    private final Logger log = LoggerFactory.getLogger(ObjetSaisineServiceImpl.class);

    private final ObjetSaisineRepository objetSaisineRepository;

    private final ObjetSaisineMapper objetSaisineMapper;

    public ObjetSaisineServiceImpl(ObjetSaisineRepository objetSaisineRepository, ObjetSaisineMapper objetSaisineMapper) {
        this.objetSaisineRepository = objetSaisineRepository;
        this.objetSaisineMapper = objetSaisineMapper;
    }

    @Override
    public ObjetSaisineDTO save(ObjetSaisineDTO objetSaisineDTO) {
        log.debug("Request to save ObjetSaisine : {}", objetSaisineDTO);
        ObjetSaisine objetSaisine = objetSaisineMapper.toEntity(objetSaisineDTO);
        objetSaisine = objetSaisineRepository.save(objetSaisine);
        return objetSaisineMapper.toDto(objetSaisine);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ObjetSaisineDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ObjetSaisines");
        return objetSaisineRepository.findAll(pageable).map(objetSaisineMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ObjetSaisineDTO> findOne(Long id) {
        log.debug("Request to get ObjetSaisine : {}", id);
        return objetSaisineRepository.findById(id).map(objetSaisineMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ObjetSaisine : {}", id);
        objetSaisineRepository.deleteById(id);
    }
}
