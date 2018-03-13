package com.dbthor.domain.certificado.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "cliente_autorizacion", schema = "", catalog = "")
@IdClass(EClienteAutorizacionPK.class)
public class EClienteAutorizacion {
    private long rutEmpresa;
    private long rutCliente;
    private int tipoAplicationAutorizacionId;
    private Long rutUsuario;
    private short activoInd;
    private Timestamp estadoFchhr;
    private Timestamp regFechaCreacion;

    @Id
    @Column(name = "rut_empresa")
    public long getRutEmpresa() {
        return rutEmpresa;
    }

    public void setRutEmpresa(long rutEmpresa) {
        this.rutEmpresa = rutEmpresa;
    }

    @Id
    @Column(name = "rut_cliente")
    public long getRutCliente() {
        return rutCliente;
    }

    public void setRutCliente(long rutCliente) {
        this.rutCliente = rutCliente;
    }

    @Id
    @Column(name = "tipo_aplication_autorizacion_id")
    public int getTipoAplicationAutorizacionId() {
        return tipoAplicationAutorizacionId;
    }

    public void setTipoAplicationAutorizacionId(int tipoAplicationAutorizacionId) {
        this.tipoAplicationAutorizacionId = tipoAplicationAutorizacionId;
    }

    @Basic
    @Column(name = "rut_usuario")
    public Long getRutUsuario() {
        return rutUsuario;
    }

    public void setRutUsuario(Long rutUsuario) {
        this.rutUsuario = rutUsuario;
    }

    @Basic
    @Column(name = "activo_ind")
    public short getActivoInd() {
        return activoInd;
    }

    public void setActivoInd(short activoInd) {
        this.activoInd = activoInd;
    }

    @Basic
    @Column(name = "estado_fchhr")
    public Timestamp getEstadoFchhr() {
        return estadoFchhr;
    }

    public void setEstadoFchhr(Timestamp estadoFchhr) {
        this.estadoFchhr = estadoFchhr;
    }

    @Basic
    @Column(name = "reg_fecha_creacion")
    public Timestamp getRegFechaCreacion() {
        return regFechaCreacion;
    }

    public void setRegFechaCreacion(Timestamp regFechaCreacion) {
        this.regFechaCreacion = regFechaCreacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EClienteAutorizacion that = (EClienteAutorizacion) o;
        return rutEmpresa == that.rutEmpresa &&
                rutCliente == that.rutCliente &&
                tipoAplicationAutorizacionId == that.tipoAplicationAutorizacionId &&
                activoInd == that.activoInd &&
                Objects.equals(rutUsuario, that.rutUsuario) &&
                Objects.equals(estadoFchhr, that.estadoFchhr) &&
                Objects.equals(regFechaCreacion, that.regFechaCreacion);
    }

    @Override
    public int hashCode() {

        return Objects.hash(rutEmpresa, rutCliente, tipoAplicationAutorizacionId, rutUsuario, activoInd, estadoFchhr, regFechaCreacion);
    }
}
