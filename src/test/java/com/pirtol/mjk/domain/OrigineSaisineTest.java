package com.pirtol.mjk.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.pirtol.mjk.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class OrigineSaisineTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrigineSaisine.class);
        OrigineSaisine origineSaisine1 = new OrigineSaisine();
        origineSaisine1.setId(1L);
        OrigineSaisine origineSaisine2 = new OrigineSaisine();
        origineSaisine2.setId(origineSaisine1.getId());
        assertThat(origineSaisine1).isEqualTo(origineSaisine2);
        origineSaisine2.setId(2L);
        assertThat(origineSaisine1).isNotEqualTo(origineSaisine2);
        origineSaisine1.setId(null);
        assertThat(origineSaisine1).isNotEqualTo(origineSaisine2);
    }
}
