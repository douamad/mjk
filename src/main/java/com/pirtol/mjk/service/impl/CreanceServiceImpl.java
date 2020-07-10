package com.pirtol.mjk.service.impl;

import com.pirtol.mjk.domain.Creance;
import com.pirtol.mjk.repository.CreanceRepository;
import com.pirtol.mjk.service.CreanceService;
import com.pirtol.mjk.service.dto.CreanceDTO;
import com.pirtol.mjk.service.mapper.CreanceMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Creance}.
 */
@Service
@Transactional
public class CreanceServiceImpl implements CreanceService {
    private final Logger log = LoggerFactory.getLogger(CreanceServiceImpl.class);

    private final CreanceRepository creanceRepository;

    private final CreanceMapper creanceMapper;

    public CreanceServiceImpl(CreanceRepository creanceRepository, CreanceMapper creanceMapper) {
        this.creanceRepository = creanceRepository;
        this.creanceMapper = creanceMapper;
    }

    @Override
    public CreanceDTO save(CreanceDTO creanceDTO) {
        log.debug("Request to save Creance : {}", creanceDTO);
        Creance creance = creanceMapper.toEntity(creanceDTO);
        creance = creanceRepository.save(creance);
        return creanceMapper.toDto(creance);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CreanceDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Creances");
        return creanceRepository.findAll(pageable).map(creanceMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CreanceDTO> findOne(Long id) {
        log.debug("Request to get Creance : {}", id);
        return creanceRepository.findById(id).map(creanceMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Creance : {}", id);
        creanceRepository.deleteById(id);
    }
}
