package com.dbthor.domain.certificado.data.entity;

import com.dbthor.tools.DateTools;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * Created by CSATTLER on 15-02-2017.
 */
@Entity
@Table(name = "certificado_digital_token",  schema = "certificado_schema" , catalog = "certificado_catalog")
public class ECertificadoDigitalToken {
    private String certificadoDigitalId;
    private String tokenVal;
    private Timestamp regCreacionFchhr = DateTools.convertUtil2SqlTimestamp(new Date());
    private Timestamp regUpdateFchhr = DateTools.convertUtil2SqlTimestamp(new Date());

    @Id
    @Column(name = "certificado_digital_id", nullable = false, length = 36)
    public String getCertificadoDigitalId() {
        return certificadoDigitalId;
    }

    public void setCertificadoDigitalId(String certificadoDigitalId) {
        this.certificadoDigitalId = certificadoDigitalId;
    }

    @Basic
    @Column(name = "token_val", nullable = false, length = -1)
    public String getTokenVal() {
        return tokenVal;
    }

    public void setTokenVal(String tokenVal) {
        this.tokenVal = tokenVal;
    }

    @Basic
    @Column(name = "reg_creacion_fchhr", nullable = false)
    public Timestamp getRegCreacionFchhr() {
        return regCreacionFchhr;
    }

    public void setRegCreacionFchhr(Timestamp regCreacionFchhr) {
        this.regCreacionFchhr = regCreacionFchhr;
    }

    @Basic
    @Column(name = "reg_update_fchhr", nullable = true)
    public Timestamp getRegUpdateFchhr() {
        return regUpdateFchhr;
    }

    public void setRegUpdateFchhr(Timestamp regUpdateFchhr) {
        this.regUpdateFchhr = regUpdateFchhr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ECertificadoDigitalToken that = (ECertificadoDigitalToken) o;

        return (certificadoDigitalId != null ? certificadoDigitalId.equals(that.certificadoDigitalId) : that.certificadoDigitalId == null)
                && (tokenVal != null ? tokenVal.equals(that.tokenVal) : that.tokenVal == null)
                && (regCreacionFchhr != null ? regCreacionFchhr.equals(that.regCreacionFchhr) : that.regCreacionFchhr == null)
                && (regUpdateFchhr != null ? regUpdateFchhr.equals(that.regUpdateFchhr) : that.regUpdateFchhr == null);
    }

    @Override
    public int hashCode() {
        int result = certificadoDigitalId != null ? certificadoDigitalId.hashCode() : 0;
        result = 31 * result + (tokenVal != null ? tokenVal.hashCode() : 0);
        result = 31 * result + (regCreacionFchhr != null ? regCreacionFchhr.hashCode() : 0);
        result = 31 * result + (regUpdateFchhr != null ? regUpdateFchhr.hashCode() : 0);
        return result;
    }
}
