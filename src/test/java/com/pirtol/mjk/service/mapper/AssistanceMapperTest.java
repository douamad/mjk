package com.pirtol.mjk.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AssistanceMapperTest {
    private AssistanceMapper assistanceMapper;

    @BeforeEach
    public void setUp() {
        assistanceMapper = new AssistanceMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(assistanceMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(assistanceMapper.fromId(null)).isNull();
    }
}
