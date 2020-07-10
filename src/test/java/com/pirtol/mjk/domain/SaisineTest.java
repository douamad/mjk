package com.pirtol.mjk.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.pirtol.mjk.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class SaisineTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Saisine.class);
        Saisine saisine1 = new Saisine();
        saisine1.setId(1L);
        Saisine saisine2 = new Saisine();
        saisine2.setId(saisine1.getId());
        assertThat(saisine1).isEqualTo(saisine2);
        saisine2.setId(2L);
        assertThat(saisine1).isNotEqualTo(saisine2);
        saisine1.setId(null);
        assertThat(saisine1).isNotEqualTo(saisine2);
    }
}
