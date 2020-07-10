package com.pirtol.mjk.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.pirtol.mjk.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class EthnieDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EthnieDTO.class);
        EthnieDTO ethnieDTO1 = new EthnieDTO();
        ethnieDTO1.setId(1L);
        EthnieDTO ethnieDTO2 = new EthnieDTO();
        assertThat(ethnieDTO1).isNotEqualTo(ethnieDTO2);
        ethnieDTO2.setId(ethnieDTO1.getId());
        assertThat(ethnieDTO1).isEqualTo(ethnieDTO2);
        ethnieDTO2.setId(2L);
        assertThat(ethnieDTO1).isNotEqualTo(ethnieDTO2);
        ethnieDTO1.setId(null);
        assertThat(ethnieDTO1).isNotEqualTo(ethnieDTO2);
    }
}
