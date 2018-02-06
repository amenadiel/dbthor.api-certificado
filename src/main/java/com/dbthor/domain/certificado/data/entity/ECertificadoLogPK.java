package com.dbthor.domain.certificado.data.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;

public class ECertificadoLogPK implements Serializable {
    private String certificadoId;
    private Timestamp regModificacionFchhr;

    @Column(name = "certificado_id")
    @Id
    public String getCertificadoId() {
        return certificadoId;
    }

    public void setCertificadoId(String certificadoId) {
        this.certificadoId = certificadoId;
    }

    @Column(name = "reg_modificacion_fchhr")
    @Id
    public Timestamp getRegModificacionFchhr() {
        return regModificacionFchhr;
    }

    public void setRegModificacionFchhr(Timestamp regModificacionFchhr) {
        this.regModificacionFchhr = regModificacionFchhr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ECertificadoLogPK that = (ECertificadoLogPK) o;

        if (certificadoId != null ? !certificadoId.equals(that.certificadoId) : that.certificadoId != null)
            return false;
        if (regModificacionFchhr != null ? !regModificacionFchhr.equals(that.regModificacionFchhr) : that.regModificacionFchhr != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = certificadoId != null ? certificadoId.hashCode() : 0;
        result = 31 * result + (regModificacionFchhr != null ? regModificacionFchhr.hashCode() : 0);
        return result;
    }
}
