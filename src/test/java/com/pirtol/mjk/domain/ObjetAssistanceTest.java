package com.pirtol.mjk.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.pirtol.mjk.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class ObjetAssistanceTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ObjetAssistance.class);
        ObjetAssistance objetAssistance1 = new ObjetAssistance();
        objetAssistance1.setId(1L);
        ObjetAssistance objetAssistance2 = new ObjetAssistance();
        objetAssistance2.setId(objetAssistance1.getId());
        assertThat(objetAssistance1).isEqualTo(objetAssistance2);
        objetAssistance2.setId(2L);
        assertThat(objetAssistance1).isNotEqualTo(objetAssistance2);
        objetAssistance1.setId(null);
        assertThat(objetAssistance1).isNotEqualTo(objetAssistance2);
    }
}
