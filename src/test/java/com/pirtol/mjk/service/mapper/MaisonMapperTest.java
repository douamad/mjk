package com.pirtol.mjk.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MaisonMapperTest {
    private MaisonMapper maisonMapper;

    @BeforeEach
    public void setUp() {
        maisonMapper = new MaisonMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(maisonMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(maisonMapper.fromId(null)).isNull();
    }
}
