package com.pirtol.mjk.service.impl;

import com.pirtol.mjk.domain.ObjetAssistance;
import com.pirtol.mjk.repository.ObjetAssistanceRepository;
import com.pirtol.mjk.service.ObjetAssistanceService;
import com.pirtol.mjk.service.dto.ObjetAssistanceDTO;
import com.pirtol.mjk.service.mapper.ObjetAssistanceMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ObjetAssistance}.
 */
@Service
@Transactional
public class ObjetAssistanceServiceImpl implements ObjetAssistanceService {
    private final Logger log = LoggerFactory.getLogger(ObjetAssistanceServiceImpl.class);

    private final ObjetAssistanceRepository objetAssistanceRepository;

    private final ObjetAssistanceMapper objetAssistanceMapper;

    public ObjetAssistanceServiceImpl(ObjetAssistanceRepository objetAssistanceRepository, ObjetAssistanceMapper objetAssistanceMapper) {
        this.objetAssistanceRepository = objetAssistanceRepository;
        this.objetAssistanceMapper = objetAssistanceMapper;
    }

    @Override
    public ObjetAssistanceDTO save(ObjetAssistanceDTO objetAssistanceDTO) {
        log.debug("Request to save ObjetAssistance : {}", objetAssistanceDTO);
        ObjetAssistance objetAssistance = objetAssistanceMapper.toEntity(objetAssistanceDTO);
        objetAssistance = objetAssistanceRepository.save(objetAssistance);
        return objetAssistanceMapper.toDto(objetAssistance);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ObjetAssistanceDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ObjetAssistances");
        return objetAssistanceRepository.findAll(pageable).map(objetAssistanceMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ObjetAssistanceDTO> findOne(Long id) {
        log.debug("Request to get ObjetAssistance : {}", id);
        return objetAssistanceRepository.findById(id).map(objetAssistanceMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ObjetAssistance : {}", id);
        objetAssistanceRepository.deleteById(id);
    }
}
