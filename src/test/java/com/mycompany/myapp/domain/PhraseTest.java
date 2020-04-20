package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class PhraseTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Phrase.class);
        Phrase phrase1 = new Phrase();
        phrase1.setId(1L);
        Phrase phrase2 = new Phrase();
        phrase2.setId(phrase1.getId());
        assertThat(phrase1).isEqualTo(phrase2);
        phrase2.setId(2L);
        assertThat(phrase1).isNotEqualTo(phrase2);
        phrase1.setId(null);
        assertThat(phrase1).isNotEqualTo(phrase2);
    }
}
