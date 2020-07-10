package com.pirtol.mjk.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.pirtol.mjk.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class EthnieTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Ethnie.class);
        Ethnie ethnie1 = new Ethnie();
        ethnie1.setId(1L);
        Ethnie ethnie2 = new Ethnie();
        ethnie2.setId(ethnie1.getId());
        assertThat(ethnie1).isEqualTo(ethnie2);
        ethnie2.setId(2L);
        assertThat(ethnie1).isNotEqualTo(ethnie2);
        ethnie1.setId(null);
        assertThat(ethnie1).isNotEqualTo(ethnie2);
    }
}
