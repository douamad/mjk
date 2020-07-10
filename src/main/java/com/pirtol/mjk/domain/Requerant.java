package com.pirtol.mjk.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pirtol.mjk.domain.enumeration.Genre;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Requerant.
 */
@Entity
@Table(name = "requerant")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Requerant implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "nom")
    private String nom;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "mail")
    private String mail;

    @Column(name = "localite")
    private String localite;

    @Enumerated(EnumType.STRING)
    @Column(name = "genre")
    private Genre genre;

    @Column(name = "age")
    private Integer age;

    @OneToMany(mappedBy = "demandeur")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Assistance> demandeAssistances = new HashSet<>();

    @OneToMany(mappedBy = "defendeur")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Assistance> defenseAssistances = new HashSet<>();

    @OneToMany(mappedBy = "demandeur")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Creance> demandeCreances = new HashSet<>();

    @OneToMany(mappedBy = "defendeur")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Creance> defenseCreances = new HashSet<>();

    @OneToMany(mappedBy = "demandeur")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Saisine> demandeSaisines = new HashSet<>();

    @OneToMany(mappedBy = "defendeur")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Saisine> defenseSaisines = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "professions", allowSetters = true)
    private Profession profession;

    @ManyToOne
    @JsonIgnoreProperties(value = "ethnis", allowSetters = true)
    private Ethnie ethnie;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrenom() {
        return prenom;
    }

    public Requerant prenom(String prenom) {
        this.prenom = prenom;
        return this;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public Requerant nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTelephone() {
        return telephone;
    }

    public Requerant telephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMail() {
        return mail;
    }

    public Requerant mail(String mail) {
        this.mail = mail;
        return this;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getLocalite() {
        return localite;
    }

    public Requerant localite(String localite) {
        this.localite = localite;
        return this;
    }

    public void setLocalite(String localite) {
        this.localite = localite;
    }

    public Genre getGenre() {
        return genre;
    }

    public Requerant genre(Genre genre) {
        this.genre = genre;
        return this;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Integer getAge() {
        return age;
    }

    public Requerant age(Integer age) {
        this.age = age;
        return this;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Set<Assistance> getDemandeAssistances() {
        return demandeAssistances;
    }

    public Requerant demandeAssistances(Set<Assistance> assistances) {
        this.demandeAssistances = assistances;
        return this;
    }

    public Requerant addDemandeAssistance(Assistance assistance) {
        this.demandeAssistances.add(assistance);
        assistance.setDemandeur(this);
        return this;
    }

    public Requerant removeDemandeAssistance(Assistance assistance) {
        this.demandeAssistances.remove(assistance);
        assistance.setDemandeur(null);
        return this;
    }

    public void setDemandeAssistances(Set<Assistance> assistances) {
        this.demandeAssistances = assistances;
    }

    public Set<Assistance> getDefenseAssistances() {
        return defenseAssistances;
    }

    public Requerant defenseAssistances(Set<Assistance> assistances) {
        this.defenseAssistances = assistances;
        return this;
    }

    public Requerant addDefenseAssistance(Assistance assistance) {
        this.defenseAssistances.add(assistance);
        assistance.setDefendeur(this);
        return this;
    }

    public Requerant removeDefenseAssistance(Assistance assistance) {
        this.defenseAssistances.remove(assistance);
        assistance.setDefendeur(null);
        return this;
    }

    public void setDefenseAssistances(Set<Assistance> assistances) {
        this.defenseAssistances = assistances;
    }

    public Set<Creance> getDemandeCreances() {
        return demandeCreances;
    }

    public Requerant demandeCreances(Set<Creance> creances) {
        this.demandeCreances = creances;
        return this;
    }

    public Requerant addDemandeCreances(Creance creance) {
        this.demandeCreances.add(creance);
        creance.setDemandeur(this);
        return this;
    }

    public Requerant removeDemandeCreances(Creance creance) {
        this.demandeCreances.remove(creance);
        creance.setDemandeur(null);
        return this;
    }

    public void setDemandeCreances(Set<Creance> creances) {
        this.demandeCreances = creances;
    }

    public Set<Creance> getDefenseCreances() {
        return defenseCreances;
    }

    public Requerant defenseCreances(Set<Creance> creances) {
        this.defenseCreances = creances;
        return this;
    }

    public Requerant addDefenseCreances(Creance creance) {
        this.defenseCreances.add(creance);
        creance.setDefendeur(this);
        return this;
    }

    public Requerant removeDefenseCreances(Creance creance) {
        this.defenseCreances.remove(creance);
        creance.setDefendeur(null);
        return this;
    }

    public void setDefenseCreances(Set<Creance> creances) {
        this.defenseCreances = creances;
    }

    public Set<Saisine> getDemandeSaisines() {
        return demandeSaisines;
    }

    public Requerant demandeSaisines(Set<Saisine> saisines) {
        this.demandeSaisines = saisines;
        return this;
    }

    public Requerant addDemandeSaisines(Saisine saisine) {
        this.demandeSaisines.add(saisine);
        saisine.setDemandeur(this);
        return this;
    }

    public Requerant removeDemandeSaisines(Saisine saisine) {
        this.demandeSaisines.remove(saisine);
        saisine.setDemandeur(null);
        return this;
    }

    public void setDemandeSaisines(Set<Saisine> saisines) {
        this.demandeSaisines = saisines;
    }

    public Set<Saisine> getDefenseSaisines() {
        return defenseSaisines;
    }

    public Requerant defenseSaisines(Set<Saisine> saisines) {
        this.defenseSaisines = saisines;
        return this;
    }

    public Requerant addDefenseSaisines(Saisine saisine) {
        this.defenseSaisines.add(saisine);
        saisine.setDefendeur(this);
        return this;
    }

    public Requerant removeDefenseSaisines(Saisine saisine) {
        this.defenseSaisines.remove(saisine);
        saisine.setDefendeur(null);
        return this;
    }

    public void setDefenseSaisines(Set<Saisine> saisines) {
        this.defenseSaisines = saisines;
    }

    public Profession getProfession() {
        return profession;
    }

    public Requerant profession(Profession profession) {
        this.profession = profession;
        return this;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public Ethnie getEthnie() {
        return ethnie;
    }

    public Requerant ethnie(Ethnie ethnie) {
        this.ethnie = ethnie;
        return this;
    }

    public void setEthnie(Ethnie ethnie) {
        this.ethnie = ethnie;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Requerant)) {
            return false;
        }
        return id != null && id.equals(((Requerant) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Requerant{" +
            "id=" + getId() +
            ", prenom='" + getPrenom() + "'" +
            ", nom='" + getNom() + "'" +
            ", telephone='" + getTelephone() + "'" +
            ", mail='" + getMail() + "'" +
            ", localite='" + getLocalite() + "'" +
            ", genre='" + getGenre() + "'" +
            ", age=" + getAge() +
            "}";
    }
}
