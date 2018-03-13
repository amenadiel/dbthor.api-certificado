package com.dbthor.domain.certificado.entity.persona;

import javax.persistence.*;

/**
 * Clase que representa la entidad tipo_aplicacion_rel
 *
 * Created by chris on 09-07-2017.
 */
@SuppressWarnings("unused")
@Entity
@Table(name = "tipo_aplicacion_rel", schema = "persona_schema", catalog = "persona_catalog")
public class ETipoAplicacionRel {
    private Integer id;
    private String enumName;
    private String descripcion;

    @Id
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "enum_name")
    public String getEnumName() {
        return enumName;
    }

    public void setEnumName(String enumName) {
        this.enumName = enumName;
    }

    @Basic
    @Column(name = "descripcion")
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

        ETipoAplicacionRel that = (ETipoAplicacionRel) o;

        return (id != null ? id.equals(that.id) : that.id == null)
                && (enumName != null ? enumName.equals(that.enumName) : that.enumName == null)
                && (descripcion != null ? descripcion.equals(that.descripcion) : that.descripcion == null);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (enumName != null ? enumName.hashCode() : 0);
        result = 31 * result + (descripcion != null ? descripcion.hashCode() : 0);
        return result;
    }
}
