package com.pirtol.mjk.service.dto;

import com.pirtol.mjk.domain.enumeration.Genre;
import java.io.Serializable;

/**
 * A DTO for the {@link com.pirtol.mjk.domain.Requerant} entity.
 */
public class RequerantDTO implements Serializable {
    private Long id;

    private String prenom;

    private String nom;

    private String telephone;

    private String mail;

    private String localite;

    private Genre genre;

    private Integer age;

    private Long professionId;

    private Long ethnieId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getLocalite() {
        return localite;
    }

    public void setLocalite(String localite) {
        this.localite = localite;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Long getProfessionId() {
        return professionId;
    }

    public void setProfessionId(Long professionId) {
        this.professionId = professionId;
    }

    public Long getEthnieId() {
        return ethnieId;
    }

    public void setEthnieId(Long ethnieId) {
        this.ethnieId = ethnieId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RequerantDTO)) {
            return false;
        }

        return id != null && id.equals(((RequerantDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RequerantDTO{" +
            "id=" + getId() +
            ", prenom='" + getPrenom() + "'" +
            ", nom='" + getNom() + "'" +
            ", telephone='" + getTelephone() + "'" +
            ", mail='" + getMail() + "'" +
            ", localite='" + getLocalite() + "'" +
            ", genre='" + getGenre() + "'" +
            ", age=" + getAge() +
            ", professionId=" + getProfessionId() +
            ", ethnieId=" + getEthnieId() +
            "}";
    }
}
