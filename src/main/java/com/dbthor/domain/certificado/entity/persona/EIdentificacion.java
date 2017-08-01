package com.dbthor.domain.certificado.entity.persona;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Clase que representa la entidad Identificacion
 *
 * Created by CSATTLER on 01-02-2017.
 */
@SuppressWarnings("unused")
@Entity
@Table(name = "identificacion", schema = "persona_schema", catalog = "persona_catalog")
@IdClass(EIdentificacionPK.class)
public class EIdentificacion {
    private String personaId;
    private Short tipoIdentificacionId;
    private Timestamp vigenciaInicioFch;
    private String identificacionVal;
    private String identificacionValidadorVal;
    private String identificacionSerieNum;
    private Timestamp vigenciaFinFch;
    private EPais paisByPaisEmisionId;

    @Id
    @Column(name = "persona_id", nullable = false, length = 36)
    public String getPersonaId() {
        return personaId;
    }

    public void setPersonaId(String personaId) {
        this.personaId = personaId;
    }

    @Id
    @Column(name = "tipo_identificacion_id", nullable = false)
    public Short getTipoIdentificacionId() {
        return tipoIdentificacionId;
    }

    public void setTipoIdentificacionId(Short tipoIdentificacionId) {
        this.tipoIdentificacionId = tipoIdentificacionId;
    }

    @Id
    @Column(name = "vigencia_inicio_fch", nullable = false)
    public Timestamp getVigenciaInicioFch() {
        return vigenciaInicioFch;
    }

    public void setVigenciaInicioFch(Timestamp vigenciaInicioFch) {
        this.vigenciaInicioFch = vigenciaInicioFch;
    }

    @Basic
    @Column(name = "identificacion_val", nullable = false, length = 50)
    public String getIdentificacionVal() {
        return identificacionVal;
    }

    public void setIdentificacionVal(String identificacionVal) {
        this.identificacionVal = identificacionVal;
    }

    @Basic
    @Column(name = "identificacion_validador_val",  length = 20)
    public String getIdentificacionValidadorVal() {
        return identificacionValidadorVal;
    }

    public void setIdentificacionValidadorVal(String identificacionValidadorVal) {
        this.identificacionValidadorVal = identificacionValidadorVal;
    }

    @Basic
    @Column(name = "identificacion_serie_num",  length = 50)
    public String getIdentificacionSerieNum() {
        return identificacionSerieNum;
    }

    public void setIdentificacionSerieNum(String identificacionSerieNum) {
        this.identificacionSerieNum = identificacionSerieNum;
    }

    @Basic
    @Column(name = "vigencia_fin_fch")
    public Timestamp getVigenciaFinFch() {
        return vigenciaFinFch;
    }

    public void setVigenciaFinFch(Timestamp vigenciaFinFch) {
        this.vigenciaFinFch = vigenciaFinFch;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EIdentificacion that = (EIdentificacion) o;

        return (personaId != null ? personaId.equals(that.personaId) : that.personaId == null)
                && (tipoIdentificacionId != null ? tipoIdentificacionId.equals(that.tipoIdentificacionId) : that.tipoIdentificacionId == null)
                && (vigenciaInicioFch != null ? vigenciaInicioFch.equals(that.vigenciaInicioFch) : that.vigenciaInicioFch == null)
                && (identificacionVal != null ? identificacionVal.equals(that.identificacionVal) : that.identificacionVal == null)
                && (identificacionValidadorVal != null ? identificacionValidadorVal.equals(that.identificacionValidadorVal) : that.identificacionValidadorVal == null)
                && (identificacionSerieNum != null ? identificacionSerieNum.equals(that.identificacionSerieNum) : that.identificacionSerieNum == null)
                && (vigenciaFinFch != null ? vigenciaFinFch.equals(that.vigenciaFinFch) : that.vigenciaFinFch == null);
    }

    @Override
    public int hashCode() {
        int result = personaId != null ? personaId.hashCode() : 0;
        result = 31 * result + (tipoIdentificacionId != null ? tipoIdentificacionId.hashCode() : 0);
        result = 31 * result + (vigenciaInicioFch != null ? vigenciaInicioFch.hashCode() : 0);
        result = 31 * result + (identificacionVal != null ? identificacionVal.hashCode() : 0);
        result = 31 * result + (identificacionValidadorVal != null ? identificacionValidadorVal.hashCode() : 0);
        result = 31 * result + (identificacionSerieNum != null ? identificacionSerieNum.hashCode() : 0);
        result = 31 * result + (vigenciaFinFch != null ? vigenciaFinFch.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "pais_emision_id", referencedColumnName = "id", nullable = false)
    public EPais getPaisByPaisEmisionId() {
        return paisByPaisEmisionId;
    }

    public void setPaisByPaisEmisionId(EPais paisByPaisEmisionId) {
        this.paisByPaisEmisionId = paisByPaisEmisionId;
    }
}
