package com.pirtol.mjk.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SaisineMapperTest {
    private SaisineMapper saisineMapper;

    @BeforeEach
    public void setUp() {
        saisineMapper = new SaisineMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(saisineMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(saisineMapper.fromId(null)).isNull();
    }
}
