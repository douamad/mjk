package com.pirtol.mjk.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ObjetSaisineMapperTest {
    private ObjetSaisineMapper objetSaisineMapper;

    @BeforeEach
    public void setUp() {
        objetSaisineMapper = new ObjetSaisineMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(objetSaisineMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(objetSaisineMapper.fromId(null)).isNull();
    }
}
