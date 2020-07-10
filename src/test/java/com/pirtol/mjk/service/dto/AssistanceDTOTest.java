package com.pirtol.mjk.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.pirtol.mjk.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class AssistanceDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AssistanceDTO.class);
        AssistanceDTO assistanceDTO1 = new AssistanceDTO();
        assistanceDTO1.setId(1L);
        AssistanceDTO assistanceDTO2 = new AssistanceDTO();
        assertThat(assistanceDTO1).isNotEqualTo(assistanceDTO2);
        assistanceDTO2.setId(assistanceDTO1.getId());
        assertThat(assistanceDTO1).isEqualTo(assistanceDTO2);
        assistanceDTO2.setId(2L);
        assertThat(assistanceDTO1).isNotEqualTo(assistanceDTO2);
        assistanceDTO1.setId(null);
        assertThat(assistanceDTO1).isNotEqualTo(assistanceDTO2);
    }
}
