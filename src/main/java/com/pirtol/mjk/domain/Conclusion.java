package com.pirtol.mjk.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Conclusion.
 */
@Entity
@Table(name = "conclusion")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Conclusion implements Serializable {
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

    @OneToMany(mappedBy = "conclusions")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Creance> creances = new HashSet<>();

    @OneToMany(mappedBy = "conclusion")
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

    public Conclusion nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getSlug() {
        return slug;
    }

    public Conclusion slug(String slug) {
        this.slug = slug;
        return this;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getDescription() {
        return description;
    }

    public Conclusion description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Creance> getCreances() {
        return creances;
    }

    public Conclusion creances(Set<Creance> creances) {
        this.creances = creances;
        return this;
    }

    public Conclusion addCreances(Creance creance) {
        this.creances.add(creance);
        creance.setConclusions(this);
        return this;
    }

    public Conclusion removeCreances(Creance creance) {
        this.creances.remove(creance);
        creance.setConclusions(null);
        return this;
    }

    public void setCreances(Set<Creance> creances) {
        this.creances = creances;
    }

    public Set<Saisine> getSaisines() {
        return saisines;
    }

    public Conclusion saisines(Set<Saisine> saisines) {
        this.saisines = saisines;
        return this;
    }

    public Conclusion addSaisines(Saisine saisine) {
        this.saisines.add(saisine);
        saisine.setConclusion(this);
        return this;
    }

    public Conclusion removeSaisines(Saisine saisine) {
        this.saisines.remove(saisine);
        saisine.setConclusion(null);
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
        if (!(o instanceof Conclusion)) {
            return false;
        }
        return id != null && id.equals(((Conclusion) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Conclusion{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", slug='" + getSlug() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
