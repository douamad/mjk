package com.pirtol.mjk.service.dto;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A DTO for the {@link com.pirtol.mjk.domain.Saisine} entity.
 */
public class SaisineDTO implements Serializable {
    private Long id;

    private String ref;

    private LocalDate date;

    private String description;

    private Integer organisaiton;

    private Long objetId;

    private Long natureId;

    private Long origineId;

    private Long conclusionId;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getOrganisaiton() {
        return organisaiton;
    }

    public void setOrganisaiton(Integer organisaiton) {
        this.organisaiton = organisaiton;
    }

    public Long getObjetId() {
        return objetId;
    }

    public void setObjetId(Long objetSaisineId) {
        this.objetId = objetSaisineId;
    }

    public Long getNatureId() {
        return natureId;
    }

    public void setNatureId(Long natureSaisineId) {
        this.natureId = natureSaisineId;
    }

    public Long getOrigineId() {
        return origineId;
    }

    public void setOrigineId(Long origineSaisineId) {
        this.origineId = origineSaisineId;
    }

    public Long getConclusionId() {
        return conclusionId;
    }

    public void setConclusionId(Long conclusionId) {
        this.conclusionId = conclusionId;
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
        if (!(o instanceof SaisineDTO)) {
            return false;
        }

        return id != null && id.equals(((SaisineDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SaisineDTO{" +
            "id=" + getId() +
            ", ref='" + getRef() + "'" +
            ", date='" + getDate() + "'" +
            ", description='" + getDescription() + "'" +
            ", organisaiton=" + getOrganisaiton() +
            ", objetId=" + getObjetId() +
            ", natureId=" + getNatureId() +
            ", origineId=" + getOrigineId() +
            ", conclusionId=" + getConclusionId() +
            ", maisonId=" + getMaisonId() +
            ", demandeurId=" + getDemandeurId() +
            ", defendeurId=" + getDefendeurId() +
            "}";
    }
}
