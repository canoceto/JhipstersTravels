package com.mycompany.myapp.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Phrase} entity.
 */
public class PhraseDTO implements Serializable {

    private Long id;

    private String author;

    private String text;

    private String referense;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getReferense() {
        return referense;
    }

    public void setReferense(String referense) {
        this.referense = referense;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PhraseDTO phraseDTO = (PhraseDTO) o;
        if (phraseDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), phraseDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PhraseDTO{" +
            "id=" + getId() +
            ", author='" + getAuthor() + "'" +
            ", text='" + getText() + "'" +
            ", referense='" + getReferense() + "'" +
            "}";
    }
}
