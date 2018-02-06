package com.dbthor.domain.certificado.data.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;

public class ECertificadoDigitalHistoricoPK implements Serializable {
    private String id;
    private Timestamp regCreacionFchhr;

    @Column(name = "id")
    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "reg_creacion_fchhr")
    @Id
    public Timestamp getRegCreacionFchhr() {
        return regCreacionFchhr;
    }

    public void setRegCreacionFchhr(Timestamp regCreacionFchhr) {
        this.regCreacionFchhr = regCreacionFchhr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ECertificadoDigitalHistoricoPK that = (ECertificadoDigitalHistoricoPK) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (regCreacionFchhr != null ? !regCreacionFchhr.equals(that.regCreacionFchhr) : that.regCreacionFchhr != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (regCreacionFchhr != null ? regCreacionFchhr.hashCode() : 0);
        return result;
    }
}
