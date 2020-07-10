package com.pirtol.mjk.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.pirtol.mjk.domain.Assistance} entity.
 */
public class AssistanceDTO implements Serializable {
    private Long id;

    private String reference;

    private String date;

    private String cout;

    private Long objetAssistanceId;

    private Long maisonId;

    private Long demandeurId;

    private Long defendeurId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCout() {
        return cout;
    }

    public void setCout(String cout) {
        this.cout = cout;
    }

    public Long getObjetAssistanceId() {
        return objetAssistanceId;
    }

    public void setObjetAssistanceId(Long objetAssistanceId) {
        this.objetAssistanceId = objetAssistanceId;
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
        if (!(o instanceof AssistanceDTO)) {
            return false;
        }

        return id != null && id.equals(((AssistanceDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AssistanceDTO{" +
            "id=" + getId() +
            ", reference='" + getReference() + "'" +
            ", date='" + getDate() + "'" +
            ", cout='" + getCout() + "'" +
            ", objetAssistanceId=" + getObjetAssistanceId() +
            ", maisonId=" + getMaisonId() +
            ", demandeurId=" + getDemandeurId() +
            ", defendeurId=" + getDefendeurId() +
            "}";
    }
}
