package com.dbthor.domain.certificado.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class EClienteAutorizacionPK implements Serializable {
    private long rutEmpresa;
    private long rutCliente;
    private int tipoAplicationAutorizacionId;

    @Column(name = "rut_empresa")
    @Id
    public long getRutEmpresa() {
        return rutEmpresa;
    }

    public void setRutEmpresa(long rutEmpresa) {
        this.rutEmpresa = rutEmpresa;
    }

    @Column(name = "rut_cliente")
    @Id
    public long getRutCliente() {
        return rutCliente;
    }

    public void setRutCliente(long rutCliente) {
        this.rutCliente = rutCliente;
    }

    @Column(name = "tipo_aplication_autorizacion_id")
    @Id
    public int getTipoAplicationAutorizacionId() {
        return tipoAplicationAutorizacionId;
    }

    public void setTipoAplicationAutorizacionId(int tipoAplicationAutorizacionId) {
        this.tipoAplicationAutorizacionId = tipoAplicationAutorizacionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EClienteAutorizacionPK that = (EClienteAutorizacionPK) o;
        return rutEmpresa == that.rutEmpresa &&
                rutCliente == that.rutCliente &&
                tipoAplicationAutorizacionId == that.tipoAplicationAutorizacionId;
    }

    @Override
    public int hashCode() {

        return Objects.hash(rutEmpresa, rutCliente, tipoAplicationAutorizacionId);
    }
}
