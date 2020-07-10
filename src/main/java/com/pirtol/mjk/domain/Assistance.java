package com.pirtol.mjk.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Assistance.
 */
@Entity
@Table(name = "assistance")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Assistance implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "reference")
    private String reference;

    @Column(name = "date")
    private String date;

    @Column(name = "cout")
    private String cout;

    @ManyToOne
    @JsonIgnoreProperties(value = "objets", allowSetters = true)
    private ObjetAssistance objetAssistance;

    @ManyToOne
    @JsonIgnoreProperties(value = "assistances", allowSetters = true)
    private Maison maison;

    @ManyToOne
    @JsonIgnoreProperties(value = "demandeAssistances", allowSetters = true)
    private Requerant demandeur;

    @ManyToOne
    @JsonIgnoreProperties(value = "defenseAssistances", allowSetters = true)
    private Requerant defendeur;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public Assistance reference(String reference) {
        this.reference = reference;
        return this;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getDate() {
        return date;
    }

    public Assistance date(String date) {
        this.date = date;
        return this;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCout() {
        return cout;
    }

    public Assistance cout(String cout) {
        this.cout = cout;
        return this;
    }

    public void setCout(String cout) {
        this.cout = cout;
    }

    public ObjetAssistance getObjetAssistance() {
        return objetAssistance;
    }

    public Assistance objetAssistance(ObjetAssistance objetAssistance) {
        this.objetAssistance = objetAssistance;
        return this;
    }

    public void setObjetAssistance(ObjetAssistance objetAssistance) {
        this.objetAssistance = objetAssistance;
    }

    public Maison getMaison() {
        return maison;
    }

    public Assistance maison(Maison maison) {
        this.maison = maison;
        return this;
    }

    public void setMaison(Maison maison) {
        this.maison = maison;
    }

    public Requerant getDemandeur() {
        return demandeur;
    }

    public Assistance demandeur(Requerant requerant) {
        this.demandeur = requerant;
        return this;
    }

    public void setDemandeur(Requerant requerant) {
        this.demandeur = requerant;
    }

    public Requerant getDefendeur() {
        return defendeur;
    }

    public Assistance defendeur(Requerant requerant) {
        this.defendeur = requerant;
        return this;
    }

    public void setDefendeur(Requerant requerant) {
        this.defendeur = requerant;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Assistance)) {
            return false;
        }
        return id != null && id.equals(((Assistance) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Assistance{" +
            "id=" + getId() +
            ", reference='" + getReference() + "'" +
            ", date='" + getDate() + "'" +
            ", cout='" + getCout() + "'" +
            "}";
    }
}
