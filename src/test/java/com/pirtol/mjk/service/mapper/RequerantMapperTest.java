package com.pirtol.mjk.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RequerantMapperTest {
    private RequerantMapper requerantMapper;

    @BeforeEach
    public void setUp() {
        requerantMapper = new RequerantMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(requerantMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(requerantMapper.fromId(null)).isNull();
    }
}
