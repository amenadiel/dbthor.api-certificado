package com.dbthor.domain.certificado.data.entity;

import javax.persistence.*;

/**
 * Created by CSATTLER on 07-02-2017.
 */
@Entity
@Table(name = "tipo_certificado_uso",  schema = "certificado_schema" , catalog = "certificado_catalog")
public class ETipoCertificadoUso {
    private String id;
    private String descripcion;

    /*Constructores*/
    public ETipoCertificadoUso() {}

    public ETipoCertificadoUso(String id) {this.setId(id);}

    /*Atributos*/
    @Id
    @Column(name = "id", nullable = false, length = 10)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "descripcion", nullable = true, length = 50)
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ETipoCertificadoUso that = (ETipoCertificadoUso) o;

        return (id != null ? id.equals(that.id) : that.id == null)
                && (descripcion != null ? descripcion.equals(that.descripcion) : that.descripcion == null);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (descripcion != null ? descripcion.hashCode() : 0);
        return result;
    }
}