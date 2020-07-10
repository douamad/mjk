package com.pirtol.mjk.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.pirtol.mjk.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class NatureSaisineDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NatureSaisineDTO.class);
        NatureSaisineDTO natureSaisineDTO1 = new NatureSaisineDTO();
        natureSaisineDTO1.setId(1L);
        NatureSaisineDTO natureSaisineDTO2 = new NatureSaisineDTO();
        assertThat(natureSaisineDTO1).isNotEqualTo(natureSaisineDTO2);
        natureSaisineDTO2.setId(natureSaisineDTO1.getId());
        assertThat(natureSaisineDTO1).isEqualTo(natureSaisineDTO2);
        natureSaisineDTO2.setId(2L);
        assertThat(natureSaisineDTO1).isNotEqualTo(natureSaisineDTO2);
        natureSaisineDTO1.setId(null);
        assertThat(natureSaisineDTO1).isNotEqualTo(natureSaisineDTO2);
    }
}
