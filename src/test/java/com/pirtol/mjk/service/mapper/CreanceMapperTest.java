package com.pirtol.mjk.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CreanceMapperTest {
    private CreanceMapper creanceMapper;

    @BeforeEach
    public void setUp() {
        creanceMapper = new CreanceMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(creanceMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(creanceMapper.fromId(null)).isNull();
    }
}
