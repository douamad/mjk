package com.pirtol.mjk.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProfessionMapperTest {
    private ProfessionMapper professionMapper;

    @BeforeEach
    public void setUp() {
        professionMapper = new ProfessionMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(professionMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(professionMapper.fromId(null)).isNull();
    }
}
