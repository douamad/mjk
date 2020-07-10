package com.pirtol.mjk.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A NatureSaisine.
 */
@Entity
@Table(name = "nature_saisine")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class NatureSaisine implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "slug")
    private String slug;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "nature")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Saisine> saisines = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public NatureSaisine nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getSlug() {
        return slug;
    }

    public NatureSaisine slug(String slug) {
        this.slug = slug;
        return this;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getDescription() {
        return description;
    }

    public NatureSaisine description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Saisine> getSaisines() {
        return saisines;
    }

    public NatureSaisine saisines(Set<Saisine> saisines) {
        this.saisines = saisines;
        return this;
    }

    public NatureSaisine addSaisines(Saisine saisine) {
        this.saisines.add(saisine);
        saisine.setNature(this);
        return this;
    }

    public NatureSaisine removeSaisines(Saisine saisine) {
        this.saisines.remove(saisine);
        saisine.setNature(null);
        return this;
    }

    public void setSaisines(Set<Saisine> saisines) {
        this.saisines = saisines;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NatureSaisine)) {
            return false;
        }
        return id != null && id.equals(((NatureSaisine) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NatureSaisine{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", slug='" + getSlug() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
