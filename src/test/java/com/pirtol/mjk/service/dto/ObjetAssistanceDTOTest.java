package com.pirtol.mjk.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.pirtol.mjk.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class ObjetAssistanceDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ObjetAssistanceDTO.class);
        ObjetAssistanceDTO objetAssistanceDTO1 = new ObjetAssistanceDTO();
        objetAssistanceDTO1.setId(1L);
        ObjetAssistanceDTO objetAssistanceDTO2 = new ObjetAssistanceDTO();
        assertThat(objetAssistanceDTO1).isNotEqualTo(objetAssistanceDTO2);
        objetAssistanceDTO2.setId(objetAssistanceDTO1.getId());
        assertThat(objetAssistanceDTO1).isEqualTo(objetAssistanceDTO2);
        objetAssistanceDTO2.setId(2L);
        assertThat(objetAssistanceDTO1).isNotEqualTo(objetAssistanceDTO2);
        objetAssistanceDTO1.setId(null);
        assertThat(objetAssistanceDTO1).isNotEqualTo(objetAssistanceDTO2);
    }
}
