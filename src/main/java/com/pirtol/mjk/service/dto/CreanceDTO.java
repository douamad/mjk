package com.pirtol.mjk.service.dto;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A DTO for the {@link com.pirtol.mjk.domain.Creance} entity.
 */
public class CreanceDTO implements Serializable {
    private Long id;

    private String ref;

    private LocalDate date;

    private LocalDate datePVRec;

    private String natureLitige;

    private Float montant;

    private Integer nombreEcheance;

    private Float totalRecouvre;

    private Float soldeARecouvrer;

    private Long origineId;

    private Long conclusionsId;

    private Long maisonId;

    private Long demandeurId;

    private Long defendeurId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDatePVRec() {
        return datePVRec;
    }

    public void setDatePVRec(LocalDate datePVRec) {
        this.datePVRec = datePVRec;
    }

    public String getNatureLitige() {
        return natureLitige;
    }

    public void setNatureLitige(String natureLitige) {
        this.natureLitige = natureLitige;
    }

    public Float getMontant() {
        return montant;
    }

    public void setMontant(Float montant) {
        this.montant = montant;
    }

    public Integer getNombreEcheance() {
        return nombreEcheance;
    }

    public void setNombreEcheance(Integer nombreEcheance) {
        this.nombreEcheance = nombreEcheance;
    }

    public Float getTotalRecouvre() {
        return totalRecouvre;
    }

    public void setTotalRecouvre(Float totalRecouvre) {
        this.totalRecouvre = totalRecouvre;
    }

    public Float getSoldeARecouvrer() {
        return soldeARecouvrer;
    }

    public void setSoldeARecouvrer(Float soldeARecouvrer) {
        this.soldeARecouvrer = soldeARecouvrer;
    }

    public Long getOrigineId() {
        return origineId;
    }

    public void setOrigineId(Long origineSaisineId) {
        this.origineId = origineSaisineId;
    }

    public Long getConclusionsId() {
        return conclusionsId;
    }

    public void setConclusionsId(Long conclusionId) {
        this.conclusionsId = conclusionId;
    }

    public Long getMaisonId() {
        return maisonId;
    }

    public void setMaisonId(Long maisonId) {
        this.maisonId = maisonId;
    }

    public Long getDemandeurId() {
        return demandeurId;
    }

    public void setDemandeurId(Long requerantId) {
        this.demandeurId = requerantId;
    }

    public Long getDefendeurId() {
        return defendeurId;
    }

    public void setDefendeurId(Long requerantId) {
        this.defendeurId = requerantId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CreanceDTO)) {
            return false;
        }

        return id != null && id.equals(((CreanceDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CreanceDTO{" +
            "id=" + getId() +
            ", ref='" + getRef() + "'" +
            ", date='" + getDate() + "'" +
            ", datePVRec='" + getDatePVRec() + "'" +
            ", natureLitige='" + getNatureLitige() + "'" +
            ", montant=" + getMontant() +
            ", nombreEcheance=" + getNombreEcheance() +
            ", totalRecouvre=" + getTotalRecouvre() +
            ", soldeARecouvrer=" + getSoldeARecouvrer() +
            ", origineId=" + getOrigineId() +
            ", conclusionsId=" + getConclusionsId() +
            ", maisonId=" + getMaisonId() +
            ", demandeurId=" + getDemandeurId() +
            ", defendeurId=" + getDefendeurId() +
            "}";
    }
}
