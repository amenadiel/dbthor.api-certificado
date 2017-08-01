package com.dbthor.domain.certificado.entity.persona;

import javax.persistence.*;

/**
 * Clase que representa la entidad tipo_persona_estado_civil
 *
 * Created by CSATTLER on 01-02-2017.
 */
@SuppressWarnings("unused")
@Entity
@Table(name = "tipo_persona_estado_civil", schema = "persona_schema", catalog = "persona_catalog")
public class ETipoPersonaEstadoCivil {
    private Byte id;
    private String descripcion;
    private Byte activoInd;

    @Id
    @Column(name = "id", nullable = false)
    public Byte getId() {
        return id;
    }

    public void setId(Byte id) {
        this.id = id;
    }

    @Basic
    @Column(name = "descripcion", length = 100)
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Basic
    @Column(name = "activo_ind", nullable = false)
    public Byte getActivoInd() {
        return activoInd;
    }

    public void setActivoInd(Byte activoInd) {
        this.activoInd = activoInd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ETipoPersonaEstadoCivil that = (ETipoPersonaEstadoCivil) o;

        return (id != null ? id.equals(that.id) : that.id == null)
                && (descripcion != null ? descripcion.equals(that.descripcion) : that.descripcion == null)
                && (activoInd != null ? activoInd.equals(that.activoInd) : that.activoInd == null);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (descripcion != null ? descripcion.hashCode() : 0);
        result = 31 * result + (activoInd != null ? activoInd.hashCode() : 0);
        return result;
    }
}