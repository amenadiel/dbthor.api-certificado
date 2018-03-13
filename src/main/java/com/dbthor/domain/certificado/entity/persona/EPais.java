package com.dbthor.domain.certificado.entity.persona;

import javax.persistence.*;

/**
 * Clase que representa la entidad pais
 *
 * Created by CSATTLER on 01-02-2017.
 */
@SuppressWarnings("unused")
@Entity
@Table(name = "pais", schema = "persona_schema", catalog = "persona_catalog")
public class EPais {
    private String id;
    private String iso2;
    private String descripcion;
    private String nacionalidad;

    @Id
    @Column(name = "id", nullable = false, length = 3)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "iso2", nullable = false, length = 2)
    public String getIso2() {
        return iso2;
    }

    public void setIso2(String iso2) {
        this.iso2 = iso2;
    }

    @Basic
    @Column(name = "descripcion", length = 50)
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Basic
    @Column(name = "nacionalidad", length = 50)
    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EPais ePais = (EPais) o;

        return (id != null ? id.equals(ePais.id) : ePais.id == null)
                && (iso2 != null ? iso2.equals(ePais.iso2) : ePais.iso2 == null)
                && (descripcion != null ? descripcion.equals(ePais.descripcion) : ePais.descripcion == null)
                && (nacionalidad != null ? nacionalidad.equals(ePais.nacionalidad) : ePais.nacionalidad == null);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (iso2 != null ? iso2.hashCode() : 0);
        result = 31 * result + (descripcion != null ? descripcion.hashCode() : 0);
        result = 31 * result + (nacionalidad != null ? nacionalidad.hashCode() : 0);
        return result;
    }
}
