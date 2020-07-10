package com.pirtol.mjk.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ObjetAssistanceMapperTest {
    private ObjetAssistanceMapper objetAssistanceMapper;

    @BeforeEach
    public void setUp() {
        objetAssistanceMapper = new ObjetAssistanceMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(objetAssistanceMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(objetAssistanceMapper.fromId(null)).isNull();
    }
}
