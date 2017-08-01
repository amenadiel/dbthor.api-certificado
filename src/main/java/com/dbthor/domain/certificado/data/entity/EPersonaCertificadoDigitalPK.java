package com.dbthor.domain.certificado.data.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by CSATTLER on 07-02-2017.
 */
public class EPersonaCertificadoDigitalPK implements Serializable {
    private String personaId;
    private String certificadoDigitalId;

    @Column(name = "persona_id", nullable = false, length = 36)
    @Id
    public String getPersonaId() {
        return personaId;
    }

    public void setPersonaId(String personaId) {
        this.personaId = personaId;
    }

    @Column(name = "certificado_digital_id", nullable = false, length = 36)
    @Id
    public String getCertificadoDigitalId() {
        return certificadoDigitalId;
    }

    public void setCertificadoDigitalId(String certificadoDigitalId) {
        this.certificadoDigitalId = certificadoDigitalId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EPersonaCertificadoDigitalPK that = (EPersonaCertificadoDigitalPK) o;

        return (personaId != null ? personaId.equals(that.personaId) : that.personaId == null)
                && (certificadoDigitalId != null ? certificadoDigitalId.equals(that.certificadoDigitalId) : that.certificadoDigitalId == null);
    }

    @Override
    public int hashCode() {
        int result = personaId != null ? personaId.hashCode() : 0;
        result = 31 * result + (certificadoDigitalId != null ? certificadoDigitalId.hashCode() : 0);
        return result;
    }
}