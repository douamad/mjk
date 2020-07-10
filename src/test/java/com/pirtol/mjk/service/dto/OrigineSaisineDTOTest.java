package com.pirtol.mjk.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.pirtol.mjk.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class OrigineSaisineDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrigineSaisineDTO.class);
        OrigineSaisineDTO origineSaisineDTO1 = new OrigineSaisineDTO();
        origineSaisineDTO1.setId(1L);
        OrigineSaisineDTO origineSaisineDTO2 = new OrigineSaisineDTO();
        assertThat(origineSaisineDTO1).isNotEqualTo(origineSaisineDTO2);
        origineSaisineDTO2.setId(origineSaisineDTO1.getId());
        assertThat(origineSaisineDTO1).isEqualTo(origineSaisineDTO2);
        origineSaisineDTO2.setId(2L);
        assertThat(origineSaisineDTO1).isNotEqualTo(origineSaisineDTO2);
        origineSaisineDTO1.setId(null);
        assertThat(origineSaisineDTO1).isNotEqualTo(origineSaisineDTO2);
    }
}
