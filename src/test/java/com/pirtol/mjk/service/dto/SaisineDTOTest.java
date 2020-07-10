package com.pirtol.mjk.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.pirtol.mjk.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class SaisineDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SaisineDTO.class);
        SaisineDTO saisineDTO1 = new SaisineDTO();
        saisineDTO1.setId(1L);
        SaisineDTO saisineDTO2 = new SaisineDTO();
        assertThat(saisineDTO1).isNotEqualTo(saisineDTO2);
        saisineDTO2.setId(saisineDTO1.getId());
        assertThat(saisineDTO1).isEqualTo(saisineDTO2);
        saisineDTO2.setId(2L);
        assertThat(saisineDTO1).isNotEqualTo(saisineDTO2);
        saisineDTO1.setId(null);
        assertThat(saisineDTO1).isNotEqualTo(saisineDTO2);
    }
}
