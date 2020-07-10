package com.pirtol.mjk.service.impl;

import com.pirtol.mjk.domain.Ethnie;
import com.pirtol.mjk.repository.EthnieRepository;
import com.pirtol.mjk.service.EthnieService;
import com.pirtol.mjk.service.dto.EthnieDTO;
import com.pirtol.mjk.service.mapper.EthnieMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Ethnie}.
 */
@Service
@Transactional
public class EthnieServiceImpl implements EthnieService {
    private final Logger log = LoggerFactory.getLogger(EthnieServiceImpl.class);

    private final EthnieRepository ethnieRepository;

    private final EthnieMapper ethnieMapper;

    public EthnieServiceImpl(EthnieRepository ethnieRepository, EthnieMapper ethnieMapper) {
        this.ethnieRepository = ethnieRepository;
        this.ethnieMapper = ethnieMapper;
    }

    @Override
    public EthnieDTO save(EthnieDTO ethnieDTO) {
        log.debug("Request to save Ethnie : {}", ethnieDTO);
        Ethnie ethnie = ethnieMapper.toEntity(ethnieDTO);
        ethnie = ethnieRepository.save(ethnie);
        return ethnieMapper.toDto(ethnie);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<EthnieDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Ethnies");
        return ethnieRepository.findAll(pageable).map(ethnieMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<EthnieDTO> findOne(Long id) {
        log.debug("Request to get Ethnie : {}", id);
        return ethnieRepository.findById(id).map(ethnieMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Ethnie : {}", id);
        ethnieRepository.deleteById(id);
    }
}
