package com.dbthor.domain.certificado.data.entity;

import com.dbthor.tools.DateTools;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "certificado_digital_historico", schema = "certificado_schema" , catalog = "certificado_catalog")
@IdClass(ECertificadoDigitalHistoricoPK.class)
public class ECertificadoDigitalHistorico {
    private String id;
    private String dataEncode64Val;
    private String archivoNombre;
    private String usuarioCorreoVal;
    private String passwordVal;
    private Timestamp creacionFchhr;
    private Timestamp expiracionFchhr;
    private String subjectDnVal;
    private String issuerDnVal;
    private Timestamp regCreacionFchhr = DateTools.convertUtil2SqlTimestamp(new Date());
    private Long certRut;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "data_encode64_val")
    public String getDataEncode64Val() {
        return dataEncode64Val;
    }

    public void setDataEncode64Val(String dataEncode64Val) {
        this.dataEncode64Val = dataEncode64Val;
    }

    @Basic
    @Column(name = "archivo_nombre")
    public String getArchivoNombre() {
        return archivoNombre;
    }

    public void setArchivoNombre(String archivoNombre) {
        this.archivoNombre = archivoNombre;
    }

    @Basic
    @Column(name = "usuario_correo_val")
    public String getUsuarioCorreoVal() {
        return usuarioCorreoVal;
    }

    public void setUsuarioCorreoVal(String usuarioCorreoVal) {
        this.usuarioCorreoVal = usuarioCorreoVal;
    }

    @Basic
    @Column(name = "password_val")
    public String getPasswordVal() {
        return passwordVal;
    }

    public void setPasswordVal(String passwordVal) {
        this.passwordVal = passwordVal;
    }

    @Basic
    @Column(name = "creacion_fchhr")
    public Timestamp getCreacionFchhr() {
        return creacionFchhr;
    }

    public void setCreacionFchhr(Timestamp creacionFchhr) {
        this.creacionFchhr = creacionFchhr;
    }

    @Basic
    @Column(name = "expiracion_fchhr")
    public Timestamp getExpiracionFchhr() {
        return expiracionFchhr;
    }

    public void setExpiracionFchhr(Timestamp expiracionFchhr) {
        this.expiracionFchhr = expiracionFchhr;
    }

    @Basic
    @Column(name = "subjectDN_val")
    public String getSubjectDnVal() {
        return subjectDnVal;
    }

    public void setSubjectDnVal(String subjectDnVal) {
        this.subjectDnVal = subjectDnVal;
    }

    @Basic
    @Column(name = "issuerDN_val")
    public String getIssuerDnVal() {
        return issuerDnVal;
    }

    public void setIssuerDnVal(String issuerDnVal) {
        this.issuerDnVal = issuerDnVal;
    }

    @Id
    @Column(name = "reg_creacion_fchhr")
    public Timestamp getRegCreacionFchhr() {
        return regCreacionFchhr;
    }

    public void setRegCreacionFchhr(Timestamp regCreacionFchhr) {
        this.regCreacionFchhr = regCreacionFchhr;
    }

    @Basic
    @Column(name = "cert_rut")
    public Long getCertRut() {
        return certRut;
    }

    public void setCertRut(Long clienteVal) {
        this.certRut = clienteVal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ECertificadoDigitalHistorico that = (ECertificadoDigitalHistorico) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (dataEncode64Val != null ? !dataEncode64Val.equals(that.dataEncode64Val) : that.dataEncode64Val != null)
            return false;
        if (archivoNombre != null ? !archivoNombre.equals(that.archivoNombre) : that.archivoNombre != null)
            return false;
        if (usuarioCorreoVal != null ? !usuarioCorreoVal.equals(that.usuarioCorreoVal) : that.usuarioCorreoVal != null)
            return false;
        if (passwordVal != null ? !passwordVal.equals(that.passwordVal) : that.passwordVal != null) return false;
        if (creacionFchhr != null ? !creacionFchhr.equals(that.creacionFchhr) : that.creacionFchhr != null)
            return false;
        if (expiracionFchhr != null ? !expiracionFchhr.equals(that.expiracionFchhr) : that.expiracionFchhr != null)
            return false;
        if (subjectDnVal != null ? !subjectDnVal.equals(that.subjectDnVal) : that.subjectDnVal != null) return false;
        if (issuerDnVal != null ? !issuerDnVal.equals(that.issuerDnVal) : that.issuerDnVal != null) return false;
        if (regCreacionFchhr != null ? !regCreacionFchhr.equals(that.regCreacionFchhr) : that.regCreacionFchhr != null)
            return false;
        if (certRut != null ? !certRut.equals(that.certRut) : that.certRut != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (dataEncode64Val != null ? dataEncode64Val.hashCode() : 0);
        result = 31 * result + (archivoNombre != null ? archivoNombre.hashCode() : 0);
        result = 31 * result + (usuarioCorreoVal != null ? usuarioCorreoVal.hashCode() : 0);
        result = 31 * result + (passwordVal != null ? passwordVal.hashCode() : 0);
        result = 31 * result + (creacionFchhr != null ? creacionFchhr.hashCode() : 0);
        result = 31 * result + (expiracionFchhr != null ? expiracionFchhr.hashCode() : 0);
        result = 31 * result + (subjectDnVal != null ? subjectDnVal.hashCode() : 0);
        result = 31 * result + (issuerDnVal != null ? issuerDnVal.hashCode() : 0);
        result = 31 * result + (regCreacionFchhr != null ? regCreacionFchhr.hashCode() : 0);
        result = 31 * result + (certRut != null ? certRut.hashCode() : 0);
        return result;
    }
}
