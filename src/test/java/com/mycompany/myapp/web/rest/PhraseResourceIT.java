package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.SpringBootDemoApp;
import com.mycompany.myapp.domain.Phrase;
import com.mycompany.myapp.repository.PhraseRepository;
import com.mycompany.myapp.service.PhraseService;
import com.mycompany.myapp.service.dto.PhraseDTO;
import com.mycompany.myapp.service.mapper.PhraseMapper;
import com.mycompany.myapp.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static com.mycompany.myapp.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link PhraseResource} REST controller.
 */
@SpringBootTest(classes = SpringBootDemoApp.class)
public class PhraseResourceIT {

    private static final String DEFAULT_AUTHOR = "AAAAAAAAAA";
    private static final String UPDATED_AUTHOR = "BBBBBBBBBB";

    private static final String DEFAULT_TEXT = "AAAAAAAAAA";
    private static final String UPDATED_TEXT = "BBBBBBBBBB";

    private static final String DEFAULT_REFERENSE = "AAAAAAAAAA";
    private static final String UPDATED_REFERENSE = "BBBBBBBBBB";

    @Autowired
    private PhraseRepository phraseRepository;

    @Autowired
    private PhraseMapper phraseMapper;

    @Autowired
    private PhraseService phraseService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restPhraseMockMvc;

    private Phrase phrase;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PhraseResource phraseResource = new PhraseResource(phraseService);
        this.restPhraseMockMvc = MockMvcBuilders.standaloneSetup(phraseResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Phrase createEntity(EntityManager em) {
        Phrase phrase = new Phrase()
            .author(DEFAULT_AUTHOR)
            .text(DEFAULT_TEXT)
            .referense(DEFAULT_REFERENSE);
        return phrase;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Phrase createUpdatedEntity(EntityManager em) {
        Phrase phrase = new Phrase()
            .author(UPDATED_AUTHOR)
            .text(UPDATED_TEXT)
            .referense(UPDATED_REFERENSE);
        return phrase;
    }

    @BeforeEach
    public void initTest() {
        phrase = createEntity(em);
    }

    @Test
    @Transactional
    public void createPhrase() throws Exception {
        int databaseSizeBeforeCreate = phraseRepository.findAll().size();

        // Create the Phrase
        PhraseDTO phraseDTO = phraseMapper.toDto(phrase);
        restPhraseMockMvc.perform(post("/api/phrases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(phraseDTO)))
            .andExpect(status().isCreated());

        // Validate the Phrase in the database
        List<Phrase> phraseList = phraseRepository.findAll();
        assertThat(phraseList).hasSize(databaseSizeBeforeCreate + 1);
        Phrase testPhrase = phraseList.get(phraseList.size() - 1);
        assertThat(testPhrase.getAuthor()).isEqualTo(DEFAULT_AUTHOR);
        assertThat(testPhrase.getText()).isEqualTo(DEFAULT_TEXT);
        assertThat(testPhrase.getReferense()).isEqualTo(DEFAULT_REFERENSE);
    }

    @Test
    @Transactional
    public void createPhraseWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = phraseRepository.findAll().size();

        // Create the Phrase with an existing ID
        phrase.setId(1L);
        PhraseDTO phraseDTO = phraseMapper.toDto(phrase);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPhraseMockMvc.perform(post("/api/phrases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(phraseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Phrase in the database
        List<Phrase> phraseList = phraseRepository.findAll();
        assertThat(phraseList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllPhrases() throws Exception {
        // Initialize the database
        phraseRepository.saveAndFlush(phrase);

        // Get all the phraseList
        restPhraseMockMvc.perform(get("/api/phrases?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(phrase.getId().intValue())))
            .andExpect(jsonPath("$.[*].author").value(hasItem(DEFAULT_AUTHOR)))
            .andExpect(jsonPath("$.[*].text").value(hasItem(DEFAULT_TEXT)))
            .andExpect(jsonPath("$.[*].referense").value(hasItem(DEFAULT_REFERENSE)));
    }
    
    @Test
    @Transactional
    public void getPhrase() throws Exception {
        // Initialize the database
        phraseRepository.saveAndFlush(phrase);

        // Get the phrase
        restPhraseMockMvc.perform(get("/api/phrases/{id}", phrase.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(phrase.getId().intValue()))
            .andExpect(jsonPath("$.author").value(DEFAULT_AUTHOR))
            .andExpect(jsonPath("$.text").value(DEFAULT_TEXT))
            .andExpect(jsonPath("$.referense").value(DEFAULT_REFERENSE));
    }

    @Test
    @Transactional
    public void getNonExistingPhrase() throws Exception {
        // Get the phrase
        restPhraseMockMvc.perform(get("/api/phrases/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePhrase() throws Exception {
        // Initialize the database
        phraseRepository.saveAndFlush(phrase);

        int databaseSizeBeforeUpdate = phraseRepository.findAll().size();

        // Update the phrase
        Phrase updatedPhrase = phraseRepository.findById(phrase.getId()).get();
        // Disconnect from session so that the updates on updatedPhrase are not directly saved in db
        em.detach(updatedPhrase);
        updatedPhrase
            .author(UPDATED_AUTHOR)
            .text(UPDATED_TEXT)
            .referense(UPDATED_REFERENSE);
        PhraseDTO phraseDTO = phraseMapper.toDto(updatedPhrase);

        restPhraseMockMvc.perform(put("/api/phrases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(phraseDTO)))
            .andExpect(status().isOk());

        // Validate the Phrase in the database
        List<Phrase> phraseList = phraseRepository.findAll();
        assertThat(phraseList).hasSize(databaseSizeBeforeUpdate);
        Phrase testPhrase = phraseList.get(phraseList.size() - 1);
        assertThat(testPhrase.getAuthor()).isEqualTo(UPDATED_AUTHOR);
        assertThat(testPhrase.getText()).isEqualTo(UPDATED_TEXT);
        assertThat(testPhrase.getReferense()).isEqualTo(UPDATED_REFERENSE);
    }

    @Test
    @Transactional
    public void updateNonExistingPhrase() throws Exception {
        int databaseSizeBeforeUpdate = phraseRepository.findAll().size();

        // Create the Phrase
        PhraseDTO phraseDTO = phraseMapper.toDto(phrase);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPhraseMockMvc.perform(put("/api/phrases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(phraseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Phrase in the database
        List<Phrase> phraseList = phraseRepository.findAll();
        assertThat(phraseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePhrase() throws Exception {
        // Initialize the database
        phraseRepository.saveAndFlush(phrase);

        int databaseSizeBeforeDelete = phraseRepository.findAll().size();

        // Delete the phrase
        restPhraseMockMvc.perform(delete("/api/phrases/{id}", phrase.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Phrase> phraseList = phraseRepository.findAll();
        assertThat(phraseList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
