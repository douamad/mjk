package com.pirtol.mjk.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ObjetAssistance.
 */
@Entity
@Table(name = "objet_assistance")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ObjetAssistance implements Serializable {
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

    @OneToMany(mappedBy = "objetAssistance")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Assistance> objets = new HashSet<>();

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

    public ObjetAssistance nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getSlug() {
        return slug;
    }

    public ObjetAssistance slug(String slug) {
        this.slug = slug;
        return this;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getDescription() {
        return description;
    }

    public ObjetAssistance description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Assistance> getObjets() {
        return objets;
    }

    public ObjetAssistance objets(Set<Assistance> assistances) {
        this.objets = assistances;
        return this;
    }

    public ObjetAssistance addObjet(Assistance assistance) {
        this.objets.add(assistance);
        assistance.setObjetAssistance(this);
        return this;
    }

    public ObjetAssistance removeObjet(Assistance assistance) {
        this.objets.remove(assistance);
        assistance.setObjetAssistance(null);
        return this;
    }

    public void setObjets(Set<Assistance> assistances) {
        this.objets = assistances;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ObjetAssistance)) {
            return false;
        }
        return id != null && id.equals(((ObjetAssistance) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ObjetAssistance{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", slug='" + getSlug() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
