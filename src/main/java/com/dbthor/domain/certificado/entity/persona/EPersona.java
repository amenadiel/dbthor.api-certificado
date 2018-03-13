package com.dbthor.domain.certificado.entity.persona;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

/**
 * Clase que representa la entidad persona
 *
 * Created by CSATTLER on 01-02-2017.
 */
@SuppressWarnings("unused")
@Entity
@Table(name = "persona", schema = "persona_schema", catalog = "persona_catalog")
public class EPersona {
    private String id;
    private Date creacionFch;
    private String observaciones;
    private ETipoPersonaEstado tipoPersonaEstado;
    private ETipoPersona tipoPersona;
    private EAplicacionOrigen aplicacionOrigen;
    private List<EIdentificacion> identificacions;
   // private EPersonaJuridica personaJuridica;
   // private EPersonaNatural personaNatural;

    @Id
    @Column(name = "id", nullable = false, length = 36)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "creacion_fch")
    public Date getCreacionFch() {
        return creacionFch;
    }

    public void setCreacionFch(Date creacionFch) {
        this.creacionFch = creacionFch;
    }

    @Basic
    @Column(name = "observaciones", length = 200)
    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EPersona ePersona = (EPersona) o;

        return (id != null ? id.equals(ePersona.id) : ePersona.id == null)
                && (creacionFch != null ? creacionFch.equals(ePersona.creacionFch) : ePersona.creacionFch == null)
                && (observaciones != null ? observaciones.equals(ePersona.observaciones) : ePersona.observaciones == null);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (creacionFch != null ? creacionFch.hashCode() : 0);
        result = 31 * result + (observaciones != null ? observaciones.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "tipo_persona_estado_id", referencedColumnName = "id", nullable = false)
    public ETipoPersonaEstado getTipoPersonaEstado() {
        return tipoPersonaEstado;
    }

    public void setTipoPersonaEstado(ETipoPersonaEstado tipoPersonaEstado) {
        this.tipoPersonaEstado = tipoPersonaEstado;
    }

    @ManyToOne
    @JoinColumn(name = "tipo_persona_id", referencedColumnName = "id")
    public ETipoPersona getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(ETipoPersona tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    @ManyToOne
    @JoinColumn(name = "aplicacion_origen_id", referencedColumnName = "id")
    public EAplicacionOrigen getAplicacionOrigen() {
        return aplicacionOrigen;
    }

    public void setAplicacionOrigen(EAplicacionOrigen aplicacionOrigen) {
        this.aplicacionOrigen = aplicacionOrigen;
    }

    //@OneToMany(mappedBy = "persona")
    //@OneToMany
    //@JoinColumn(name = "id", referencedColumnName = "id")

   /* @OneToMany
    public List<EIdentificacion> getIdentificacions() {
        return identificacions;
    }

    public void setIdentificacions(List<EIdentificacion> identificacions) {
        this.identificacions = identificacions;
    }
*/
 /*   @OneToOne//(mappedBy = "personaByPersonaId")
    public EPersonaJuridica getPersonaJuridica() {
        return personaJuridica;
    }

    public void setPersonaJuridica(EPersonaJuridica personaJuridica) {
        this.personaJuridica = personaJuridica;
    }

    @OneToOne//(mappedBy = "personaByPersonaId")
    public EPersonaNatural getPersonaNatural() {
        return personaNatural;
    }

    public void setPersonaNatural(EPersonaNatural personaNatural) {
        this.personaNatural = personaNatural;
    }
    */
}
