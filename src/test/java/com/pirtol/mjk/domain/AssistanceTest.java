package com.pirtol.mjk.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.pirtol.mjk.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class AssistanceTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Assistance.class);
        Assistance assistance1 = new Assistance();
        assistance1.setId(1L);
        Assistance assistance2 = new Assistance();
        assistance2.setId(assistance1.getId());
        assertThat(assistance1).isEqualTo(assistance2);
        assistance2.setId(2L);
        assertThat(assistance1).isNotEqualTo(assistance2);
        assistance1.setId(null);
        assertThat(assistance1).isNotEqualTo(assistance2);
    }
}
