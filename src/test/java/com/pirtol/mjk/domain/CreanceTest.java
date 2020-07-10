package com.pirtol.mjk.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.pirtol.mjk.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class CreanceTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Creance.class);
        Creance creance1 = new Creance();
        creance1.setId(1L);
        Creance creance2 = new Creance();
        creance2.setId(creance1.getId());
        assertThat(creance1).isEqualTo(creance2);
        creance2.setId(2L);
        assertThat(creance1).isNotEqualTo(creance2);
        creance1.setId(null);
        assertThat(creance1).isNotEqualTo(creance2);
    }
}
