package com.pirtol.mjk.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrigineSaisineMapperTest {
    private OrigineSaisineMapper origineSaisineMapper;

    @BeforeEach
    public void setUp() {
        origineSaisineMapper = new OrigineSaisineMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(origineSaisineMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(origineSaisineMapper.fromId(null)).isNull();
    }
}
