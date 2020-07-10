package com.pirtol.mjk.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.pirtol.mjk.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class NatureSaisineTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NatureSaisine.class);
        NatureSaisine natureSaisine1 = new NatureSaisine();
        natureSaisine1.setId(1L);
        NatureSaisine natureSaisine2 = new NatureSaisine();
        natureSaisine2.setId(natureSaisine1.getId());
        assertThat(natureSaisine1).isEqualTo(natureSaisine2);
        natureSaisine2.setId(2L);
        assertThat(natureSaisine1).isNotEqualTo(natureSaisine2);
        natureSaisine1.setId(null);
        assertThat(natureSaisine1).isNotEqualTo(natureSaisine2);
    }
}
