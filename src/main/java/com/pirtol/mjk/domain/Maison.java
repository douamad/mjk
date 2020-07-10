package com.pirtol.mjk.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Maison.
 */
@Entity
@Table(name = "maison")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Maison implements Serializable {
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

    @OneToMany(mappedBy = "maison")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Assistance> assistances = new HashSet<>();

    @OneToMany(mappedBy = "maison")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Creance> creances = new HashSet<>();

    @OneToMany(mappedBy = "maison")
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

    public Maison nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getSlug() {
        return slug;
    }

    public Maison slug(String slug) {
        this.slug = slug;
        return this;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getDescription() {
        return description;
    }

    public Maison description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Assistance> getAssistances() {
        return assistances;
    }

    public Maison assistances(Set<Assistance> assistances) {
        this.assistances = assistances;
        return this;
    }

    public Maison addAssistances(Assistance assistance) {
        this.assistances.add(assistance);
        assistance.setMaison(this);
        return this;
    }

    public Maison removeAssistances(Assistance assistance) {
        this.assistances.remove(assistance);
        assistance.setMaison(null);
        return this;
    }

    public void setAssistances(Set<Assistance> assistances) {
        this.assistances = assistances;
    }

    public Set<Creance> getCreances() {
        return creances;
    }

    public Maison creances(Set<Creance> creances) {
        this.creances = creances;
        return this;
    }

    public Maison addCreances(Creance creance) {
        this.creances.add(creance);
        creance.setMaison(this);
        return this;
    }

    public Maison removeCreances(Creance creance) {
        this.creances.remove(creance);
        creance.setMaison(null);
        return this;
    }

    public void setCreances(Set<Creance> creances) {
        this.creances = creances;
    }

    public Set<Saisine> getSaisines() {
        return saisines;
    }

    public Maison saisines(Set<Saisine> saisines) {
        this.saisines = saisines;
        return this;
    }

    public Maison addSaisines(Saisine saisine) {
        this.saisines.add(saisine);
        saisine.setMaison(this);
        return this;
    }

    public Maison removeSaisines(Saisine saisine) {
        this.saisines.remove(saisine);
        saisine.setMaison(null);
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
        if (!(o instanceof Maison)) {
            return false;
        }
        return id != null && id.equals(((Maison) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Maison{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", slug='" + getSlug() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
