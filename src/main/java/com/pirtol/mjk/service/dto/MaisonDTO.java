package com.pirtol.mjk.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.pirtol.mjk.domain.Maison} entity.
 */
public class MaisonDTO implements Serializable {
    private Long id;

    private String nom;

    private String slug;

    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MaisonDTO)) {
            return false;
        }

        return id != null && id.equals(((MaisonDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MaisonDTO{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", slug='" + getSlug() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
