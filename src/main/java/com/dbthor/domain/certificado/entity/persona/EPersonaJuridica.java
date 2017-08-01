package com.dbthor.domain.certificado.entity.persona;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Clase que representa la entidad persona_juridica
 *
 * Created by CSATTLER on 01-02-2017.
 */
@SuppressWarnings("unused")
@Entity
@Table(name = "persona_juridica", schema = "persona_schema", catalog = "persona_catalog")
public class EPersonaJuridica {
    private String personaId;
    private String razonSocial;
    private Timestamp constitucionFch;
    private Timestamp inscripcionFch;
    private Timestamp publicacionFch;
    private Timestamp registroFch;
    private Timestamp vigenciaFch;
    private String glosaVal;
    private String repertorioVal;
    private Integer notariaId;

    @Id
    @Column(name = "persona_id", nullable = false, length = 36)
    public String getPersonaId() {
        return personaId;
    }

    public void setPersonaId(String personaId) {
        this.personaId = personaId;
    }

    @Basic
    @Column(name = "razon_social", nullable = false, length = 100)
    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    @Basic
    @Column(name = "constitucion_fch")
    public Timestamp getConstitucionFch() {
        return constitucionFch;
    }

    public void setConstitucionFch(Timestamp constitucionFch) {
        this.constitucionFch = constitucionFch;
    }

    @Basic
    @Column(name = "inscripcion_fch")
    public Timestamp getInscripcionFch() {
        return inscripcionFch;
    }

    public void setInscripcionFch(Timestamp inscripcionFch) {
        this.inscripcionFch = inscripcionFch;
    }

    @Basic
    @Column(name = "publicacion_fch")
    public Timestamp getPublicacionFch() {
        return publicacionFch;
    }

    public void setPublicacionFch(Timestamp publicacionFch) {
        this.publicacionFch = publicacionFch;
    }

    @Basic
    @Column(name = "registro_fch")
    public Timestamp getRegistroFch() {
        return registroFch;
    }

    public void setRegistroFch(Timestamp registroFch) {
        this.registroFch = registroFch;
    }

    @Basic
    @Column(name = "vigencia_fch")
    public Timestamp getVigenciaFch() {
        return vigenciaFch;
    }

    public void setVigenciaFch(Timestamp vigenciaFch) {
        this.vigenciaFch = vigenciaFch;
    }

    @Basic
    @Column(name = "glosa_val", length = 250)
    public String getGlosaVal() {
        return glosaVal;
    }

    public void setGlosaVal(String glosaVal) {
        this.glosaVal = glosaVal;
    }

    @Basic
    @Column(name = "repertorio_val", length = 100)
    public String getRepertorioVal() {
        return repertorioVal;
    }

    public void setRepertorioVal(String repertorioVal) {
        this.repertorioVal = repertorioVal;
    }

    @Basic
    @Column(name = "notaria_id")
    public Integer getNotariaId() {
        return notariaId;
    }

    public void setNotariaId(Integer notariaId) {
        this.notariaId = notariaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EPersonaJuridica that = (EPersonaJuridica) o;

        return (personaId != null ? personaId.equals(that.personaId) : that.personaId == null)
                && (razonSocial != null ? razonSocial.equals(that.razonSocial) : that.razonSocial == null)
                && (constitucionFch != null ? constitucionFch.equals(that.constitucionFch) : that.constitucionFch == null)
                && (inscripcionFch != null ? inscripcionFch.equals(that.inscripcionFch) : that.inscripcionFch == null)
                && (publicacionFch != null ? publicacionFch.equals(that.publicacionFch) : that.publicacionFch == null)
                && (registroFch != null ? registroFch.equals(that.registroFch) : that.registroFch == null)
                && (vigenciaFch != null ? vigenciaFch.equals(that.vigenciaFch) : that.vigenciaFch == null)
                && (glosaVal != null ? glosaVal.equals(that.glosaVal) : that.glosaVal == null)
                && (repertorioVal != null ? repertorioVal.equals(that.repertorioVal) : that.repertorioVal == null)
                && (notariaId != null ? notariaId.equals(that.notariaId) : that.notariaId == null);
    }

    @Override
    public int hashCode() {
        int result = personaId != null ? personaId.hashCode() : 0;
        result = 31 * result + (razonSocial != null ? razonSocial.hashCode() : 0);
        result = 31 * result + (constitucionFch != null ? constitucionFch.hashCode() : 0);
        result = 31 * result + (inscripcionFch != null ? inscripcionFch.hashCode() : 0);
        result = 31 * result + (publicacionFch != null ? publicacionFch.hashCode() : 0);
        result = 31 * result + (registroFch != null ? registroFch.hashCode() : 0);
        result = 31 * result + (vigenciaFch != null ? vigenciaFch.hashCode() : 0);
        result = 31 * result + (glosaVal != null ? glosaVal.hashCode() : 0);
        result = 31 * result + (repertorioVal != null ? repertorioVal.hashCode() : 0);
        result = 31 * result + (notariaId != null ? notariaId.hashCode() : 0);
        return result;
    }
}
