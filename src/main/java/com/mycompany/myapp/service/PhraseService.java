package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.PhraseDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.Phrase}.
 */
public interface PhraseService {

    /**
     * Save a phrase.
     *
     * @param phraseDTO the entity to save.
     * @return the persisted entity.
     */
    PhraseDTO save(PhraseDTO phraseDTO);

    /**
     * Get all the phrases.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PhraseDTO> findAll(Pageable pageable);


    /**
     * Get the "id" phrase.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PhraseDTO> findOne(Long id);

    /**
     * Delete the "id" phrase.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
