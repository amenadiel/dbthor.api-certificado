package com.dbthor.domain.certificado.entity.persona;

import javax.persistence.*;

/**
 * Clase que representa la entidad tipo_persona
 *
 * Created by CSATTLER on 01-02-2017.
 */
@SuppressWarnings("unused")
@Entity
@Table(name = "tipo_persona", schema = "persona_schema", catalog = "persona_catalog")
public class ETipoPersona {
    private Short id;
    private String descripcion;

    @Id
    @Column(name = "id", nullable = false)
    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    @Basic
    @Column(name = "descripcion", length = 50)
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

        ETipoPersona that = (ETipoPersona) o;

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