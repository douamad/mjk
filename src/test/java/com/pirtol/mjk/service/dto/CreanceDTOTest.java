package com.pirtol.mjk.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.pirtol.mjk.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class CreanceDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CreanceDTO.class);
        CreanceDTO creanceDTO1 = new CreanceDTO();
        creanceDTO1.setId(1L);
        CreanceDTO creanceDTO2 = new CreanceDTO();
        assertThat(creanceDTO1).isNotEqualTo(creanceDTO2);
        creanceDTO2.setId(creanceDTO1.getId());
        assertThat(creanceDTO1).isEqualTo(creanceDTO2);
        creanceDTO2.setId(2L);
        assertThat(creanceDTO1).isNotEqualTo(creanceDTO2);
        creanceDTO1.setId(null);
        assertThat(creanceDTO1).isNotEqualTo(creanceDTO2);
    }
}
