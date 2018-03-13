package com.dbthor.domain.certificado.entity.persona;

import javax.persistence.*;
import java.sql.Date;

/**
 * Clase que representa la entidad persona_natural
 *
 * Created by CSATTLER on 01-02-2017.
 */
@SuppressWarnings("unused")
@Entity
@Table(name = "persona_natural", schema = "persona_schema", catalog = "persona_catalog")
public class EPersonaNatural {
    private String personaId;
    private String nombres;
    private String apellidoPrimero;
    private String apellidoSegundo;
    private Date nacimientoFch;
    private ETipoPersonaProfesion tipoPersonaProfesion;
    private ETipoPersonaGenero tipoPersonaGenero;
    private ETipoPersonaEstadoCivil tipoPersonaEstadoCivil;
    private EPais paisNacionalidad;

    @Id
    @Column(name = "persona_id", nullable = false, length = 36)
    public String getPersonaId() {
        return personaId;
    }

    public void setPersonaId(String personaId) {
        this.personaId = personaId;
    }

    @Basic
    @Column(name = "nombres", nullable = false, length = 100)
    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    @Basic
    @Column(name = "apellido_primero", length = 100)
    public String getApellidoPrimero() {
        return apellidoPrimero;
    }

    public void setApellidoPrimero(String apellidoPrimero) {
        this.apellidoPrimero = apellidoPrimero;
    }

    @Basic
    @Column(name = "apellido_segundo", length = 100)
    public String getApellidoSegundo() {
        return apellidoSegundo;
    }

    public void setApellidoSegundo(String apellidoSegundo) {
        this.apellidoSegundo = apellidoSegundo;
    }

    @Basic
    @Column(name = "nacimiento_fch")
    public Date getNacimientoFch() {
        return nacimientoFch;
    }

    public void setNacimientoFch(Date nacimientoFch) {
        this.nacimientoFch = nacimientoFch;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EPersonaNatural that = (EPersonaNatural) o;

        return (personaId != null ? personaId.equals(that.personaId) : that.personaId == null)
                && (nombres != null ? nombres.equals(that.nombres) : that.nombres == null)
                && (apellidoPrimero != null ? apellidoPrimero.equals(that.apellidoPrimero) : that.apellidoPrimero == null)
                && (apellidoSegundo != null ? apellidoSegundo.equals(that.apellidoSegundo) : that.apellidoSegundo == null)
                && (nacimientoFch != null ? nacimientoFch.equals(that.nacimientoFch) : that.nacimientoFch == null);
    }

    @Override
    public int hashCode() {
        int result = personaId != null ? personaId.hashCode() : 0;
        result = 31 * result + (nombres != null ? nombres.hashCode() : 0);
        result = 31 * result + (apellidoPrimero != null ? apellidoPrimero.hashCode() : 0);
        result = 31 * result + (apellidoSegundo != null ? apellidoSegundo.hashCode() : 0);
        result = 31 * result + (nacimientoFch != null ? nacimientoFch.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "tipo_persona_profesion_id", referencedColumnName = "id")
    public ETipoPersonaProfesion getTipoPersonaProfesion() {
        return tipoPersonaProfesion;
    }

    public void setTipoPersonaProfesion(ETipoPersonaProfesion tipoPersonaProfesion) {
        this.tipoPersonaProfesion = tipoPersonaProfesion;
    }

    @ManyToOne
    @JoinColumn(name = "tipo_persona_genero_id", referencedColumnName = "id")
    public ETipoPersonaGenero getTipoPersonaGenero() {
        return tipoPersonaGenero;
    }

    public void setTipoPersonaGenero(ETipoPersonaGenero tipoPersonaGenero) {
        this.tipoPersonaGenero = tipoPersonaGenero;
    }

    @ManyToOne
    @JoinColumn(name = "tipo_persona_estado_civil_id", referencedColumnName = "id")
    public ETipoPersonaEstadoCivil getTipoPersonaEstadoCivil() {
        return tipoPersonaEstadoCivil;
    }

    public void setTipoPersonaEstadoCivil(ETipoPersonaEstadoCivil tipoPersonaEstadoCivil) {
        this.tipoPersonaEstadoCivil = tipoPersonaEstadoCivil;
    }

    @ManyToOne
    @JoinColumn(name = "nacionalidad_pais_id", referencedColumnName = "id")
    public EPais getPaisNacionalidad() {
        return paisNacionalidad;
    }

    public void setPaisNacionalidad(EPais paisNacionalidad) {
        this.paisNacionalidad = paisNacionalidad;
    }
}
