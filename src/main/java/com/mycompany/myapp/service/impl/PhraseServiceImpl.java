package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.PhraseService;
import com.mycompany.myapp.domain.Phrase;
import com.mycompany.myapp.repository.PhraseRepository;
import com.mycompany.myapp.service.dto.PhraseDTO;
import com.mycompany.myapp.service.mapper.PhraseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Phrase}.
 */
@Service
@Transactional
public class PhraseServiceImpl implements PhraseService {

    private final Logger log = LoggerFactory.getLogger(PhraseServiceImpl.class);

    private final PhraseRepository phraseRepository;

    private final PhraseMapper phraseMapper;

    public PhraseServiceImpl(PhraseRepository phraseRepository, PhraseMapper phraseMapper) {
        this.phraseRepository = phraseRepository;
        this.phraseMapper = phraseMapper;
    }

    /**
     * Save a phrase.
     *
     * @param phraseDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public PhraseDTO save(PhraseDTO phraseDTO) {
        log.debug("Request to save Phrase : {}", phraseDTO);
        Phrase phrase = phraseMapper.toEntity(phraseDTO);
        phrase = phraseRepository.save(phrase);
        return phraseMapper.toDto(phrase);
    }

    /**
     * Get all the phrases.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PhraseDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Phrases");
        return phraseRepository.findAll(pageable)
            .map(phraseMapper::toDto);
    }


    /**
     * Get one phrase by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PhraseDTO> findOne(Long id) {
        log.debug("Request to get Phrase : {}", id);
        return phraseRepository.findById(id)
            .map(phraseMapper::toDto);
    }

    /**
     * Delete the phrase by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Phrase : {}", id);
        phraseRepository.deleteById(id);
    }
}
