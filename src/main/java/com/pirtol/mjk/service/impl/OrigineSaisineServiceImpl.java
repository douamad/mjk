package com.pirtol.mjk.service.impl;

import com.pirtol.mjk.domain.OrigineSaisine;
import com.pirtol.mjk.repository.OrigineSaisineRepository;
import com.pirtol.mjk.service.OrigineSaisineService;
import com.pirtol.mjk.service.dto.OrigineSaisineDTO;
import com.pirtol.mjk.service.mapper.OrigineSaisineMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link OrigineSaisine}.
 */
@Service
@Transactional
public class OrigineSaisineServiceImpl implements OrigineSaisineService {
    private final Logger log = LoggerFactory.getLogger(OrigineSaisineServiceImpl.class);

    private final OrigineSaisineRepository origineSaisineRepository;

    private final OrigineSaisineMapper origineSaisineMapper;

    public OrigineSaisineServiceImpl(OrigineSaisineRepository origineSaisineRepository, OrigineSaisineMapper origineSaisineMapper) {
        this.origineSaisineRepository = origineSaisineRepository;
        this.origineSaisineMapper = origineSaisineMapper;
    }

    @Override
    public OrigineSaisineDTO save(OrigineSaisineDTO origineSaisineDTO) {
        log.debug("Request to save OrigineSaisine : {}", origineSaisineDTO);
        OrigineSaisine origineSaisine = origineSaisineMapper.toEntity(origineSaisineDTO);
        origineSaisine = origineSaisineRepository.save(origineSaisine);
        return origineSaisineMapper.toDto(origineSaisine);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<OrigineSaisineDTO> findAll(Pageable pageable) {
        log.debug("Request to get all OrigineSaisines");
        return origineSaisineRepository.findAll(pageable).map(origineSaisineMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<OrigineSaisineDTO> findOne(Long id) {
        log.debug("Request to get OrigineSaisine : {}", id);
        return origineSaisineRepository.findById(id).map(origineSaisineMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete OrigineSaisine : {}", id);
        origineSaisineRepository.deleteById(id);
    }
}
