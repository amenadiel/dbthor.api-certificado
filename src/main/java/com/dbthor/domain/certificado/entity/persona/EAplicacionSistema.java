package com.dbthor.domain.certificado.entity.persona;

import javax.persistence.*;

/**
 * Clase que representa la entidad aplicacion_sistema
 *
 * Created by chris on 09-07-2017.
 */
@SuppressWarnings("unused")
@Entity
@Table(name = "aplicacion_sistema", schema = "persona_schema", catalog = "persona_catalog")
public class EAplicacionSistema {
    private String id;
    private String descripcion;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

        EAplicacionSistema that = (EAplicacionSistema) o;

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
