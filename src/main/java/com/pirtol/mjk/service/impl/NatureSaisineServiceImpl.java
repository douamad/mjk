package com.pirtol.mjk.service.impl;

import com.pirtol.mjk.domain.NatureSaisine;
import com.pirtol.mjk.repository.NatureSaisineRepository;
import com.pirtol.mjk.service.NatureSaisineService;
import com.pirtol.mjk.service.dto.NatureSaisineDTO;
import com.pirtol.mjk.service.mapper.NatureSaisineMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link NatureSaisine}.
 */
@Service
@Transactional
public class NatureSaisineServiceImpl implements NatureSaisineService {
    private final Logger log = LoggerFactory.getLogger(NatureSaisineServiceImpl.class);

    private final NatureSaisineRepository natureSaisineRepository;

    private final NatureSaisineMapper natureSaisineMapper;

    public NatureSaisineServiceImpl(NatureSaisineRepository natureSaisineRepository, NatureSaisineMapper natureSaisineMapper) {
        this.natureSaisineRepository = natureSaisineRepository;
        this.natureSaisineMapper = natureSaisineMapper;
    }

    @Override
    public NatureSaisineDTO save(NatureSaisineDTO natureSaisineDTO) {
        log.debug("Request to save NatureSaisine : {}", natureSaisineDTO);
        NatureSaisine natureSaisine = natureSaisineMapper.toEntity(natureSaisineDTO);
        natureSaisine = natureSaisineRepository.save(natureSaisine);
        return natureSaisineMapper.toDto(natureSaisine);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<NatureSaisineDTO> findAll(Pageable pageable) {
        log.debug("Request to get all NatureSaisines");
        return natureSaisineRepository.findAll(pageable).map(natureSaisineMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<NatureSaisineDTO> findOne(Long id) {
        log.debug("Request to get NatureSaisine : {}", id);
        return natureSaisineRepository.findById(id).map(natureSaisineMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete NatureSaisine : {}", id);
        natureSaisineRepository.deleteById(id);
    }
}
