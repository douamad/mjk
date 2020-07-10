package com.pirtol.mjk.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.pirtol.mjk.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class RequerantDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RequerantDTO.class);
        RequerantDTO requerantDTO1 = new RequerantDTO();
        requerantDTO1.setId(1L);
        RequerantDTO requerantDTO2 = new RequerantDTO();
        assertThat(requerantDTO1).isNotEqualTo(requerantDTO2);
        requerantDTO2.setId(requerantDTO1.getId());
        assertThat(requerantDTO1).isEqualTo(requerantDTO2);
        requerantDTO2.setId(2L);
        assertThat(requerantDTO1).isNotEqualTo(requerantDTO2);
        requerantDTO1.setId(null);
        assertThat(requerantDTO1).isNotEqualTo(requerantDTO2);
    }
}
