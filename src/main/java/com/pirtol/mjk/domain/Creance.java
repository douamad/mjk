package com.pirtol.mjk.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Creance.
 */
@Entity
@Table(name = "creance")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Creance implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "ref")
    private String ref;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "date_pv_rec")
    private LocalDate datePVRec;

    @Column(name = "nature_litige")
    private String natureLitige;

    @Column(name = "montant")
    private Float montant;

    @Column(name = "nombre_echeance")
    private Integer nombreEcheance;

    @Column(name = "total_recouvre")
    private Float totalRecouvre;

    @Column(name = "solde_a_recouvrer")
    private Float soldeARecouvrer;

    @ManyToOne
    @JsonIgnoreProperties(value = "creances", allowSetters = true)
    private OrigineSaisine origine;

    @ManyToOne
    @JsonIgnoreProperties(value = "creances", allowSetters = true)
    private Conclusion conclusions;

    @ManyToOne
    @JsonIgnoreProperties(value = "creances", allowSetters = true)
    private Maison maison;

    @ManyToOne
    @JsonIgnoreProperties(value = "demandeCreances", allowSetters = true)
    private Requerant demandeur;

    @ManyToOne
    @JsonIgnoreProperties(value = "defenseCreances", allowSetters = true)
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

    public Creance ref(String ref) {
        this.ref = ref;
        return this;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public LocalDate getDate() {
        return date;
    }

    public Creance date(LocalDate date) {
        this.date = date;
        return this;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDatePVRec() {
        return datePVRec;
    }

    public Creance datePVRec(LocalDate datePVRec) {
        this.datePVRec = datePVRec;
        return this;
    }

    public void setDatePVRec(LocalDate datePVRec) {
        this.datePVRec = datePVRec;
    }

    public String getNatureLitige() {
        return natureLitige;
    }

    public Creance natureLitige(String natureLitige) {
        this.natureLitige = natureLitige;
        return this;
    }

    public void setNatureLitige(String natureLitige) {
        this.natureLitige = natureLitige;
    }

    public Float getMontant() {
        return montant;
    }

    public Creance montant(Float montant) {
        this.montant = montant;
        return this;
    }

    public void setMontant(Float montant) {
        this.montant = montant;
    }

    public Integer getNombreEcheance() {
        return nombreEcheance;
    }

    public Creance nombreEcheance(Integer nombreEcheance) {
        this.nombreEcheance = nombreEcheance;
        return this;
    }

    public void setNombreEcheance(Integer nombreEcheance) {
        this.nombreEcheance = nombreEcheance;
    }

    public Float getTotalRecouvre() {
        return totalRecouvre;
    }

    public Creance totalRecouvre(Float totalRecouvre) {
        this.totalRecouvre = totalRecouvre;
        return this;
    }

    public void setTotalRecouvre(Float totalRecouvre) {
        this.totalRecouvre = totalRecouvre;
    }

    public Float getSoldeARecouvrer() {
        return soldeARecouvrer;
    }

    public Creance soldeARecouvrer(Float soldeARecouvrer) {
        this.soldeARecouvrer = soldeARecouvrer;
        return this;
    }

    public void setSoldeARecouvrer(Float soldeARecouvrer) {
        this.soldeARecouvrer = soldeARecouvrer;
    }

    public OrigineSaisine getOrigine() {
        return origine;
    }

    public Creance origine(OrigineSaisine origineSaisine) {
        this.origine = origineSaisine;
        return this;
    }

    public void setOrigine(OrigineSaisine origineSaisine) {
        this.origine = origineSaisine;
    }

    public Conclusion getConclusions() {
        return conclusions;
    }

    public Creance conclusions(Conclusion conclusion) {
        this.conclusions = conclusion;
        return this;
    }

    public void setConclusions(Conclusion conclusion) {
        this.conclusions = conclusion;
    }

    public Maison getMaison() {
        return maison;
    }

    public Creance maison(Maison maison) {
        this.maison = maison;
        return this;
    }

    public void setMaison(Maison maison) {
        this.maison = maison;
    }

    public Requerant getDemandeur() {
        return demandeur;
    }

    public Creance demandeur(Requerant requerant) {
        this.demandeur = requerant;
        return this;
    }

    public void setDemandeur(Requerant requerant) {
        this.demandeur = requerant;
    }

    public Requerant getDefendeur() {
        return defendeur;
    }

    public Creance defendeur(Requerant requerant) {
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
        if (!(o instanceof Creance)) {
            return false;
        }
        return id != null && id.equals(((Creance) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Creance{" +
            "id=" + getId() +
            ", ref='" + getRef() + "'" +
            ", date='" + getDate() + "'" +
            ", datePVRec='" + getDatePVRec() + "'" +
            ", natureLitige='" + getNatureLitige() + "'" +
            ", montant=" + getMontant() +
            ", nombreEcheance=" + getNombreEcheance() +
            ", totalRecouvre=" + getTotalRecouvre() +
            ", soldeARecouvrer=" + getSoldeARecouvrer() +
            "}";
    }
}
