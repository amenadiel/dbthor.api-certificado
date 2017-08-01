package com.dbthor.domain.certificado.entity.persona;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Clase que representa la entidad persona_aplicacion_rel
 *
 * Created by chris on 09-07-2017.
 */
@SuppressWarnings("unused")
@Entity
@Table(name = "persona_aplicacion_rel", schema = "persona_schema", catalog = "persona_catalog")
@IdClass(EPersonaAplicacionRelPK.class)
public class EPersonaAplicacionRel {
    private String personaId;
    private String aplicacionSistemaId;
    private Integer tipoAplicacionRelId;
    private Short activoInd;
    private Timestamp activoFchhr;
    private Timestamp relacionIniFchhr;
    private Timestamp relacionFinFchhr;
    private Timestamp regCreacionFchhr;
    private ETipoAplicacionRel tipoAplicacionRel;

    @Id
    @Column(name = "persona_id")
    public String getPersonaId() {
        return personaId;
    }

    public void setPersonaId(String personaId) {
        this.personaId = personaId;
    }

    @Id
    @Column(name = "aplicacion_sistema_id")
    public String getAplicacionSistemaId() {
        return aplicacionSistemaId;
    }

    public void setAplicacionSistemaId(String aplicacionSistemaId) {
        this.aplicacionSistemaId = aplicacionSistemaId;
    }

    @Basic
    @Column(name = "tipo_aplicacion_rel_id")
    public Integer getTipoAplicacionRelId() {
        return tipoAplicacionRelId;
    }

    public void setTipoAplicacionRelId(Integer tipoAplicacionRelId) {
        this.tipoAplicacionRelId = tipoAplicacionRelId;
    }

    @Basic
    @Column(name = "activo_ind")
    public Short getActivoInd() {
        return activoInd;
    }

    public void setActivoInd(Short activoInd) {
        this.activoInd = activoInd;
    }

    @Basic
    @Column(name = "activo_fchhr")
    public Timestamp getActivoFchhr() {
        return activoFchhr;
    }

    public void setActivoFchhr(Timestamp activoFchhr) {
        this.activoFchhr = activoFchhr;
    }

    @Basic
    @Column(name = "relacion_ini_fchhr")
    public Timestamp getRelacionIniFchhr() {
        return relacionIniFchhr;
    }

    public void setRelacionIniFchhr(Timestamp relacionIniFchhr) {
        this.relacionIniFchhr = relacionIniFchhr;
    }

    @Basic
    @Column(name = "relacion_fin_fchhr")
    public Timestamp getRelacionFinFchhr() {
        return relacionFinFchhr;
    }

    public void setRelacionFinFchhr(Timestamp relacionFinFchhr) {
        this.relacionFinFchhr = relacionFinFchhr;
    }

    @Basic
    @Column(name = "reg_creacion_fchhr")
    public Timestamp getRegCreacionFchhr() {
        return regCreacionFchhr;
    }

    public void setRegCreacionFchhr(Timestamp regCreacionFchhr) {
        this.regCreacionFchhr = regCreacionFchhr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EPersonaAplicacionRel that = (EPersonaAplicacionRel) o;

        return (personaId != null ? personaId.equals(that.personaId) : that.personaId == null)
                && (aplicacionSistemaId != null ? aplicacionSistemaId.equals(that.aplicacionSistemaId) : that.aplicacionSistemaId == null)
                && (tipoAplicacionRelId != null ? tipoAplicacionRelId.equals(that.tipoAplicacionRelId) : that.tipoAplicacionRelId == null)
                && (activoInd != null ? activoInd.equals(that.activoInd) : that.activoInd == null)
                && (activoFchhr != null ? activoFchhr.equals(that.activoFchhr) : that.activoFchhr == null)
                && (relacionIniFchhr != null ? relacionIniFchhr.equals(that.relacionIniFchhr) : that.relacionIniFchhr == null)
                && (relacionFinFchhr != null ? relacionFinFchhr.equals(that.relacionFinFchhr) : that.relacionFinFchhr == null)
                && (regCreacionFchhr != null ? regCreacionFchhr.equals(that.regCreacionFchhr) : that.regCreacionFchhr == null);
    }

    @Override
    public int hashCode() {
        int result = personaId != null ? personaId.hashCode() : 0;
        result = 31 * result + (aplicacionSistemaId != null ? aplicacionSistemaId.hashCode() : 0);
        result = 31 * result + (tipoAplicacionRelId != null ? tipoAplicacionRelId.hashCode() : 0);
        result = 31 * result + (activoInd != null ? activoInd.hashCode() : 0);
        result = 31 * result + (activoFchhr != null ? activoFchhr.hashCode() : 0);
        result = 31 * result + (relacionIniFchhr != null ? relacionIniFchhr.hashCode() : 0);
        result = 31 * result + (relacionFinFchhr != null ? relacionFinFchhr.hashCode() : 0);
        result = 31 * result + (regCreacionFchhr != null ? regCreacionFchhr.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "tipo_aplicacion_rel_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public ETipoAplicacionRel getTipoAplicacionRel() {
        return tipoAplicacionRel;
    }

    public void setTipoAplicacionRel(ETipoAplicacionRel tipoAplicacionRel) {
        this.tipoAplicacionRel = tipoAplicacionRel;
    }
}
