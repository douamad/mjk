package com.pirtol.mjk.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NatureSaisineMapperTest {
    private NatureSaisineMapper natureSaisineMapper;

    @BeforeEach
    public void setUp() {
        natureSaisineMapper = new NatureSaisineMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(natureSaisineMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(natureSaisineMapper.fromId(null)).isNull();
    }
}
