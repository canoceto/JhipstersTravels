package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.PhraseDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Phrase} and its DTO {@link PhraseDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PhraseMapper extends EntityMapper<PhraseDTO, Phrase> {



    default Phrase fromId(Long id) {
        if (id == null) {
            return null;
        }
        Phrase phrase = new Phrase();
        phrase.setId(id);
        return phrase;
    }
}
