package com.pirtol.mjk.service.impl;

import com.pirtol.mjk.domain.Requerant;
import com.pirtol.mjk.repository.RequerantRepository;
import com.pirtol.mjk.service.RequerantService;
import com.pirtol.mjk.service.dto.RequerantDTO;
import com.pirtol.mjk.service.mapper.RequerantMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Requerant}.
 */
@Service
@Transactional
public class RequerantServiceImpl implements RequerantService {
    private final Logger log = LoggerFactory.getLogger(RequerantServiceImpl.class);

    private final RequerantRepository requerantRepository;

    private final RequerantMapper requerantMapper;

    public RequerantServiceImpl(RequerantRepository requerantRepository, RequerantMapper requerantMapper) {
        this.requerantRepository = requerantRepository;
        this.requerantMapper = requerantMapper;
    }

    @Override
    public RequerantDTO save(RequerantDTO requerantDTO) {
        log.debug("Request to save Requerant : {}", requerantDTO);
        Requerant requerant = requerantMapper.toEntity(requerantDTO);
        requerant = requerantRepository.save(requerant);
        return requerantMapper.toDto(requerant);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<RequerantDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Requerants");
        return requerantRepository.findAll(pageable).map(requerantMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RequerantDTO> findOne(Long id) {
        log.debug("Request to get Requerant : {}", id);
        return requerantRepository.findById(id).map(requerantMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Requerant : {}", id);
        requerantRepository.deleteById(id);
    }
}
