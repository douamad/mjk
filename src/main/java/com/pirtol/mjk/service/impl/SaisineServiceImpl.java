package com.pirtol.mjk.service.impl;

import com.pirtol.mjk.domain.Saisine;
import com.pirtol.mjk.repository.SaisineRepository;
import com.pirtol.mjk.service.SaisineService;
import com.pirtol.mjk.service.dto.SaisineDTO;
import com.pirtol.mjk.service.mapper.SaisineMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Saisine}.
 */
@Service
@Transactional
public class SaisineServiceImpl implements SaisineService {
    private final Logger log = LoggerFactory.getLogger(SaisineServiceImpl.class);

    private final SaisineRepository saisineRepository;

    private final SaisineMapper saisineMapper;

    public SaisineServiceImpl(SaisineRepository saisineRepository, SaisineMapper saisineMapper) {
        this.saisineRepository = saisineRepository;
        this.saisineMapper = saisineMapper;
    }

    @Override
    public SaisineDTO save(SaisineDTO saisineDTO) {
        log.debug("Request to save Saisine : {}", saisineDTO);
        Saisine saisine = saisineMapper.toEntity(saisineDTO);
        saisine = saisineRepository.save(saisine);
        return saisineMapper.toDto(saisine);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SaisineDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Saisines");
        return saisineRepository.findAll(pageable).map(saisineMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SaisineDTO> findOne(Long id) {
        log.debug("Request to get Saisine : {}", id);
        return saisineRepository.findById(id).map(saisineMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Saisine : {}", id);
        saisineRepository.deleteById(id);
    }
}
