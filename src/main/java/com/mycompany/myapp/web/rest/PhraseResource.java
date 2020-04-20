package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.PhraseService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.PhraseDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.Phrase}.
 */
@RestController
@RequestMapping("/api")
public class PhraseResource {

    private final Logger log = LoggerFactory.getLogger(PhraseResource.class);

    private static final String ENTITY_NAME = "phrase";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PhraseService phraseService;

    public PhraseResource(PhraseService phraseService) {
        this.phraseService = phraseService;
    }

    /**
     * {@code POST  /phrases} : Create a new phrase.
     *
     * @param phraseDTO the phraseDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new phraseDTO, or with status {@code 400 (Bad Request)} if the phrase has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/phrases")
    public ResponseEntity<PhraseDTO> createPhrase(@RequestBody PhraseDTO phraseDTO) throws URISyntaxException {
        log.debug("REST request to save Phrase : {}", phraseDTO);
        if (phraseDTO.getId() != null) {
            throw new BadRequestAlertException("A new phrase cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PhraseDTO result = phraseService.save(phraseDTO);
        return ResponseEntity.created(new URI("/api/phrases/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /phrases} : Updates an existing phrase.
     *
     * @param phraseDTO the phraseDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated phraseDTO,
     * or with status {@code 400 (Bad Request)} if the phraseDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the phraseDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/phrases")
    public ResponseEntity<PhraseDTO> updatePhrase(@RequestBody PhraseDTO phraseDTO) throws URISyntaxException {
        log.debug("REST request to update Phrase : {}", phraseDTO);
        if (phraseDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PhraseDTO result = phraseService.save(phraseDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, phraseDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /phrases} : get all the phrases.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of phrases in body.
     */
    @GetMapping("/phrases")
    public ResponseEntity<List<PhraseDTO>> getAllPhrases(Pageable pageable) {
        log.debug("REST request to get a page of Phrases");
        Page<PhraseDTO> page = phraseService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /phrases/:id} : get the "id" phrase.
     *
     * @param id the id of the phraseDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the phraseDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/phrases/{id}")
    public ResponseEntity<PhraseDTO> getPhrase(@PathVariable Long id) {
        log.debug("REST request to get Phrase : {}", id);
        Optional<PhraseDTO> phraseDTO = phraseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(phraseDTO);
    }

    /**
     * {@code DELETE  /phrases/:id} : delete the "id" phrase.
     *
     * @param id the id of the phraseDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/phrases/{id}")
    public ResponseEntity<Void> deletePhrase(@PathVariable Long id) {
        log.debug("REST request to delete Phrase : {}", id);
        phraseService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
