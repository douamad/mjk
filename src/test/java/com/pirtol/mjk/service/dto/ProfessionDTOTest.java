package com.pirtol.mjk.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.pirtol.mjk.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class ProfessionDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProfessionDTO.class);
        ProfessionDTO professionDTO1 = new ProfessionDTO();
        professionDTO1.setId(1L);
        ProfessionDTO professionDTO2 = new ProfessionDTO();
        assertThat(professionDTO1).isNotEqualTo(professionDTO2);
        professionDTO2.setId(professionDTO1.getId());
        assertThat(professionDTO1).isEqualTo(professionDTO2);
        professionDTO2.setId(2L);
        assertThat(professionDTO1).isNotEqualTo(professionDTO2);
        professionDTO1.setId(null);
        assertThat(professionDTO1).isNotEqualTo(professionDTO2);
    }
}
