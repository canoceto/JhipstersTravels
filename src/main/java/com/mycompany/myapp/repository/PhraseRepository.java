package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Phrase;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Phrase entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PhraseRepository extends JpaRepository<Phrase, Long> {

}
