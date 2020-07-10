package com.pirtol.mjk.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.pirtol.mjk.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class ObjetSaisineTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ObjetSaisine.class);
        ObjetSaisine objetSaisine1 = new ObjetSaisine();
        objetSaisine1.setId(1L);
        ObjetSaisine objetSaisine2 = new ObjetSaisine();
        objetSaisine2.setId(objetSaisine1.getId());
        assertThat(objetSaisine1).isEqualTo(objetSaisine2);
        objetSaisine2.setId(2L);
        assertThat(objetSaisine1).isNotEqualTo(objetSaisine2);
        objetSaisine1.setId(null);
        assertThat(objetSaisine1).isNotEqualTo(objetSaisine2);
    }
}
