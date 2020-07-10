package com.pirtol.mjk.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.pirtol.mjk.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class MaisonDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MaisonDTO.class);
        MaisonDTO maisonDTO1 = new MaisonDTO();
        maisonDTO1.setId(1L);
        MaisonDTO maisonDTO2 = new MaisonDTO();
        assertThat(maisonDTO1).isNotEqualTo(maisonDTO2);
        maisonDTO2.setId(maisonDTO1.getId());
        assertThat(maisonDTO1).isEqualTo(maisonDTO2);
        maisonDTO2.setId(2L);
        assertThat(maisonDTO1).isNotEqualTo(maisonDTO2);
        maisonDTO1.setId(null);
        assertThat(maisonDTO1).isNotEqualTo(maisonDTO2);
    }
}
