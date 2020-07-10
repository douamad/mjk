package com.pirtol.mjk.service.impl;

import com.pirtol.mjk.domain.Conclusion;
import com.pirtol.mjk.repository.ConclusionRepository;
import com.pirtol.mjk.service.ConclusionService;
import com.pirtol.mjk.service.dto.ConclusionDTO;
import com.pirtol.mjk.service.mapper.ConclusionMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Conclusion}.
 */
@Service
@Transactional
public class ConclusionServiceImpl implements ConclusionService {
    private final Logger log = LoggerFactory.getLogger(ConclusionServiceImpl.class);

    private final ConclusionRepository conclusionRepository;

    private final ConclusionMapper conclusionMapper;

    public ConclusionServiceImpl(ConclusionRepository conclusionRepository, ConclusionMapper conclusionMapper) {
        this.conclusionRepository = conclusionRepository;
        this.conclusionMapper = conclusionMapper;
    }

    @Override
    public ConclusionDTO save(ConclusionDTO conclusionDTO) {
        log.debug("Request to save Conclusion : {}", conclusionDTO);
        Conclusion conclusion = conclusionMapper.toEntity(conclusionDTO);
        conclusion = conclusionRepository.save(conclusion);
        return conclusionMapper.toDto(conclusion);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ConclusionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Conclusions");
        return conclusionRepository.findAll(pageable).map(conclusionMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ConclusionDTO> findOne(Long id) {
        log.debug("Request to get Conclusion : {}", id);
        return conclusionRepository.findById(id).map(conclusionMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Conclusion : {}", id);
        conclusionRepository.deleteById(id);
    }
}
