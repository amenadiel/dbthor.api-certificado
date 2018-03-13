package com.dbthor.domain.certificado.data.entity;

import javax.persistence.*;

/**
 * Created by CSATTLER on 07-02-2017.
 */
@Entity
@Table(name = "persona_certificado_digital",  schema = "certificado_schema" , catalog = "certificado_catalog")
@IdClass(EPersonaCertificadoDigitalPK.class)
public class EPersonaCertificadoDigital {
    private String personaId;
    private String certificadoDigitalId;
    private ECertificadoDigital certificadoDigital;
    private ETipoCertificadoUso tipoCertificadoUso;

    @Id
    @Column(name = "persona_id", nullable = false, length = 36)
    public String getPersonaId() {
        return personaId;
    }

    public void setPersonaId(String personaId) {
        this.personaId = personaId;
    }

    @Id
    @Column(name = "certificado_digital_id", nullable = false, length = 36)
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

        EPersonaCertificadoDigital that = (EPersonaCertificadoDigital) o;

        return (personaId != null ? personaId.equals(that.personaId) : that.personaId == null)
                && (certificadoDigitalId != null ? certificadoDigitalId.equals(that.certificadoDigitalId) : that.certificadoDigitalId == null);
    }

    @Override
    public int hashCode() {
        int result = personaId != null ? personaId.hashCode() : 0;
        result = 31 * result + (certificadoDigitalId != null ? certificadoDigitalId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "certificado_digital_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public ECertificadoDigital getCertificadoDigital() {
        return certificadoDigital;
    }

    public void setCertificadoDigital(ECertificadoDigital certificadoDigital) {
        this.certificadoDigital = certificadoDigital;
    }

    @ManyToOne
    @JoinColumn(name = "tipo_certificado_uso_id", referencedColumnName = "id")
    public ETipoCertificadoUso getTipoCertificadoUso() {
        return tipoCertificadoUso;
    }

    public void setTipoCertificadoUso(ETipoCertificadoUso tipoCertificadoUso) {
        this.tipoCertificadoUso = tipoCertificadoUso;
    }
}