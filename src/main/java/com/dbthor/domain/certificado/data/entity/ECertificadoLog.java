package com.dbthor.domain.certificado.data.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
//@Table(name = "certificado_log", schema = "dbthor_dte", catalog = "")
@Table(name = "certificado_log", schema = "certificado_schema" , catalog = "certificado_catalog")
@IdClass(ECertificadoLogPK.class)
public class ECertificadoLog {
    private String certificadoId;
    private String usuario;
    private Timestamp regModificacionFchhr;

    @Id
    @Column(name = "certificado_id")
    public String getCertificadoId() {
        return certificadoId;
    }

    public void setCertificadoId(String certificadoId) {
        this.certificadoId = certificadoId;
    }

    @Basic
    @Column(name = "usuario")
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    @Id
    @Column(name = "reg_modificacion_fchhr")
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

        ECertificadoLog that = (ECertificadoLog) o;

        if (certificadoId != null ? !certificadoId.equals(that.certificadoId) : that.certificadoId != null)
            return false;
        if (usuario != null ? !usuario.equals(that.usuario) : that.usuario != null) return false;
        if (regModificacionFchhr != null ? !regModificacionFchhr.equals(that.regModificacionFchhr) : that.regModificacionFchhr != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = certificadoId != null ? certificadoId.hashCode() : 0;
        result = 31 * result + (usuario != null ? usuario.hashCode() : 0);
        result = 31 * result + (regModificacionFchhr != null ? regModificacionFchhr.hashCode() : 0);
        return result;
    }
}
