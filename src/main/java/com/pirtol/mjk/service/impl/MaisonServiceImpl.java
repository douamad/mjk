package com.pirtol.mjk.service.impl;

import com.pirtol.mjk.domain.Maison;
import com.pirtol.mjk.repository.MaisonRepository;
import com.pirtol.mjk.service.MaisonService;
import com.pirtol.mjk.service.dto.MaisonDTO;
import com.pirtol.mjk.service.mapper.MaisonMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Maison}.
 */
@Service
@Transactional
public class MaisonServiceImpl implements MaisonService {
    private final Logger log = LoggerFactory.getLogger(MaisonServiceImpl.class);

    private final MaisonRepository maisonRepository;

    private final MaisonMapper maisonMapper;

    public MaisonServiceImpl(MaisonRepository maisonRepository, MaisonMapper maisonMapper) {
        this.maisonRepository = maisonRepository;
        this.maisonMapper = maisonMapper;
    }

    @Override
    public MaisonDTO save(MaisonDTO maisonDTO) {
        log.debug("Request to save Maison : {}", maisonDTO);
        Maison maison = maisonMapper.toEntity(maisonDTO);
        maison = maisonRepository.save(maison);
        return maisonMapper.toDto(maison);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<MaisonDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Maisons");
        return maisonRepository.findAll(pageable).map(maisonMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<MaisonDTO> findOne(Long id) {
        log.debug("Request to get Maison : {}", id);
        return maisonRepository.findById(id).map(maisonMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Maison : {}", id);
        maisonRepository.deleteById(id);
    }
}
