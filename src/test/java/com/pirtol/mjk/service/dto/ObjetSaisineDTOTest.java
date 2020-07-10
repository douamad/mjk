package com.pirtol.mjk.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.pirtol.mjk.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class ObjetSaisineDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ObjetSaisineDTO.class);
        ObjetSaisineDTO objetSaisineDTO1 = new ObjetSaisineDTO();
        objetSaisineDTO1.setId(1L);
        ObjetSaisineDTO objetSaisineDTO2 = new ObjetSaisineDTO();
        assertThat(objetSaisineDTO1).isNotEqualTo(objetSaisineDTO2);
        objetSaisineDTO2.setId(objetSaisineDTO1.getId());
        assertThat(objetSaisineDTO1).isEqualTo(objetSaisineDTO2);
        objetSaisineDTO2.setId(2L);
        assertThat(objetSaisineDTO1).isNotEqualTo(objetSaisineDTO2);
        objetSaisineDTO1.setId(null);
        assertThat(objetSaisineDTO1).isNotEqualTo(objetSaisineDTO2);
    }
}
