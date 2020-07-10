package com.pirtol.mjk.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ConclusionMapperTest {
    private ConclusionMapper conclusionMapper;

    @BeforeEach
    public void setUp() {
        conclusionMapper = new ConclusionMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(conclusionMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(conclusionMapper.fromId(null)).isNull();
    }
}
