package com.pirtol.mjk.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EthnieMapperTest {
    private EthnieMapper ethnieMapper;

    @BeforeEach
    public void setUp() {
        ethnieMapper = new EthnieMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(ethnieMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(ethnieMapper.fromId(null)).isNull();
    }
}
