package com.pirtol.mjk.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Saisine.
 */
@Entity
@Table(name = "saisine")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Saisine implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "ref")
    private String ref;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "description")
    private String description;

    @Column(name = "organisaiton")
    private Integer organisaiton;

    @ManyToOne
    @JsonIgnoreProperties(value = "saisines", allowSetters = true)
    private ObjetSaisine objet;

    @ManyToOne
    @JsonIgnoreProperties(value = "saisines", allowSetters = true)
    private NatureSaisine nature;

    @ManyToOne
    @JsonIgnoreProperties(value = "saisines", allowSetters = true)
    private OrigineSaisine origine;

    @ManyToOne
    @JsonIgnoreProperties(value = "saisines", allowSetters = true)
    private Conclusion conclusion;

    @ManyToOne
    @JsonIgnoreProperties(value = "saisines", allowSetters = true)
    private Maison maison;

    @ManyToOne
    @JsonIgnoreProperties(value = "demandeSaisines", allowSetters = true)
    private Requerant demandeur;

    @ManyToOne
    @JsonIgnoreProperties(value = "defenseSaisines", allowSetters = true)
    private Requerant defendeur;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRef() {
        return ref;
    }

    public Saisine ref(String ref) {
        this.ref = ref;
        return this;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public LocalDate getDate() {
        return date;
    }

    public Saisine date(LocalDate date) {
        this.date = date;
        return this;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public Saisine description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getOrganisaiton() {
        return organisaiton;
    }

    public Saisine organisaiton(Integer organisaiton) {
        this.organisaiton = organisaiton;
        return this;
    }

    public void setOrganisaiton(Integer organisaiton) {
        this.organisaiton = organisaiton;
    }

    public ObjetSaisine getObjet() {
        return objet;
    }

    public Saisine objet(ObjetSaisine objetSaisine) {
        this.objet = objetSaisine;
        return this;
    }

    public void setObjet(ObjetSaisine objetSaisine) {
        this.objet = objetSaisine;
    }

    public NatureSaisine getNature() {
        return nature;
    }

    public Saisine nature(NatureSaisine natureSaisine) {
        this.nature = natureSaisine;
        return this;
    }

    public void setNature(NatureSaisine natureSaisine) {
        this.nature = natureSaisine;
    }

    public OrigineSaisine getOrigine() {
        return origine;
    }

    public Saisine origine(OrigineSaisine origineSaisine) {
        this.origine = origineSaisine;
        return this;
    }

    public void setOrigine(OrigineSaisine origineSaisine) {
        this.origine = origineSaisine;
    }

    public Conclusion getConclusion() {
        return conclusion;
    }

    public Saisine conclusion(Conclusion conclusion) {
        this.conclusion = conclusion;
        return this;
    }

    public void setConclusion(Conclusion conclusion) {
        this.conclusion = conclusion;
    }

    public Maison getMaison() {
        return maison;
    }

    public Saisine maison(Maison maison) {
        this.maison = maison;
        return this;
    }

    public void setMaison(Maison maison) {
        this.maison = maison;
    }

    public Requerant getDemandeur() {
        return demandeur;
    }

    public Saisine demandeur(Requerant requerant) {
        this.demandeur = requerant;
        return this;
    }

    public void setDemandeur(Requerant requerant) {
        this.demandeur = requerant;
    }

    public Requerant getDefendeur() {
        return defendeur;
    }

    public Saisine defendeur(Requerant requerant) {
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
        if (!(o instanceof Saisine)) {
            return false;
        }
        return id != null && id.equals(((Saisine) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Saisine{" +
            "id=" + getId() +
            ", ref='" + getRef() + "'" +
            ", date='" + getDate() + "'" +
            ", description='" + getDescription() + "'" +
            ", organisaiton=" + getOrganisaiton() +
            "}";
    }
}
