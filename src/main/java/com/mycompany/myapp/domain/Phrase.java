package com.mycompany.myapp.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Phrase.
 */
@Entity
@Table(name = "phrase")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Phrase implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "author")
    private String author;

    @Column(name = "text")
    private String text;

    @Column(name = "referense")
    private String referense;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public Phrase author(String author) {
        this.author = author;
        return this;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public Phrase text(String text) {
        this.text = text;
        return this;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getReferense() {
        return referense;
    }

    public Phrase referense(String referense) {
        this.referense = referense;
        return this;
    }

    public void setReferense(String referense) {
        this.referense = referense;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Phrase)) {
            return false;
        }
        return id != null && id.equals(((Phrase) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Phrase{" +
            "id=" + getId() +
            ", author='" + getAuthor() + "'" +
            ", text='" + getText() + "'" +
            ", referense='" + getReferense() + "'" +
            "}";
    }
}
