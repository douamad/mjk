package com.pirtol.mjk.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.pirtol.mjk.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class ConclusionDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ConclusionDTO.class);
        ConclusionDTO conclusionDTO1 = new ConclusionDTO();
        conclusionDTO1.setId(1L);
        ConclusionDTO conclusionDTO2 = new ConclusionDTO();
        assertThat(conclusionDTO1).isNotEqualTo(conclusionDTO2);
        conclusionDTO2.setId(conclusionDTO1.getId());
        assertThat(conclusionDTO1).isEqualTo(conclusionDTO2);
        conclusionDTO2.setId(2L);
        assertThat(conclusionDTO1).isNotEqualTo(conclusionDTO2);
        conclusionDTO1.setId(null);
        assertThat(conclusionDTO1).isNotEqualTo(conclusionDTO2);
    }
}
