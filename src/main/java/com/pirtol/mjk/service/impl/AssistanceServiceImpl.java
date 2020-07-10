package com.pirtol.mjk.service.impl;

import com.pirtol.mjk.domain.Assistance;
import com.pirtol.mjk.repository.AssistanceRepository;
import com.pirtol.mjk.service.AssistanceService;
import com.pirtol.mjk.service.dto.AssistanceDTO;
import com.pirtol.mjk.service.mapper.AssistanceMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Assistance}.
 */
@Service
@Transactional
public class AssistanceServiceImpl implements AssistanceService {
    private final Logger log = LoggerFactory.getLogger(AssistanceServiceImpl.class);

    private final AssistanceRepository assistanceRepository;

    private final AssistanceMapper assistanceMapper;

    public AssistanceServiceImpl(AssistanceRepository assistanceRepository, AssistanceMapper assistanceMapper) {
        this.assistanceRepository = assistanceRepository;
        this.assistanceMapper = assistanceMapper;
    }

    @Override
    public AssistanceDTO save(AssistanceDTO assistanceDTO) {
        log.debug("Request to save Assistance : {}", assistanceDTO);
        Assistance assistance = assistanceMapper.toEntity(assistanceDTO);
        assistance = assistanceRepository.save(assistance);
        return assistanceMapper.toDto(assistance);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AssistanceDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Assistances");
        return assistanceRepository.findAll(pageable).map(assistanceMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AssistanceDTO> findOne(Long id) {
        log.debug("Request to get Assistance : {}", id);
        return assistanceRepository.findById(id).map(assistanceMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Assistance : {}", id);
        assistanceRepository.deleteById(id);
    }
}
