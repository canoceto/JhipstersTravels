package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class PhraseMapperTest {

    private PhraseMapper phraseMapper;

    @BeforeEach
    public void setUp() {
        phraseMapper = new PhraseMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(phraseMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(phraseMapper.fromId(null)).isNull();
    }
}
