package com.pirtol.mjk.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Ethnie.
 */
@Entity
@Table(name = "ethnie")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Ethnie implements Serializable {
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

    @OneToMany(mappedBy = "ethnie")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Requerant> ethnis = new HashSet<>();

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

    public Ethnie nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getSlug() {
        return slug;
    }

    public Ethnie slug(String slug) {
        this.slug = slug;
        return this;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getDescription() {
        return description;
    }

    public Ethnie description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Requerant> getEthnis() {
        return ethnis;
    }

    public Ethnie ethnis(Set<Requerant> requerants) {
        this.ethnis = requerants;
        return this;
    }

    public Ethnie addEthni(Requerant requerant) {
        this.ethnis.add(requerant);
        requerant.setEthnie(this);
        return this;
    }

    public Ethnie removeEthni(Requerant requerant) {
        this.ethnis.remove(requerant);
        requerant.setEthnie(null);
        return this;
    }

    public void setEthnis(Set<Requerant> requerants) {
        this.ethnis = requerants;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Ethnie)) {
            return false;
        }
        return id != null && id.equals(((Ethnie) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Ethnie{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", slug='" + getSlug() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
