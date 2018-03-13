package com.dbthor.domain.certificado.entity.persona;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Clase que representa la entidad Identificacion
 *
 * Created by CSATTLER on 01-02-2017.
 */
@SuppressWarnings("unused")
public class EIdentificacionPK implements Serializable {
    private String personaId;
    private Short tipoIdentificacionId;
    private Timestamp vigenciaInicioFch;

    @Column(name = "persona_id", nullable = false, length = 36)
    @Id
    public String getPersonaId() {
        return personaId;
    }

    public void setPersonaId(String personaId) {
        this.personaId = personaId;
    }

    @Column(name = "tipo_identificacion_id", nullable = false)
    @Id
    public Short getTipoIdentificacionId() {
        return tipoIdentificacionId;
    }

    public void setTipoIdentificacionId(Short tipoIdentificacionId) {
        this.tipoIdentificacionId = tipoIdentificacionId;
    }

    @Column(name = "vigencia_inicio_fch", nullable = false)
    @Id
    public Timestamp getVigenciaInicioFch() {
        return vigenciaInicioFch;
    }

    public void setVigenciaInicioFch(Timestamp vigenciaInicioFch) {
        this.vigenciaInicioFch = vigenciaInicioFch;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EIdentificacionPK that = (EIdentificacionPK) o;

        return (personaId != null ? personaId.equals(that.personaId) : that.personaId == null)
                && (tipoIdentificacionId != null ? tipoIdentificacionId.equals(that.tipoIdentificacionId) : that.tipoIdentificacionId == null)
                && (vigenciaInicioFch != null ? vigenciaInicioFch.equals(that.vigenciaInicioFch) : that.vigenciaInicioFch == null);
    }

    @Override
    public int hashCode() {
        int result = personaId != null ? personaId.hashCode() : 0;
        result = 31 * result + (tipoIdentificacionId != null ? tipoIdentificacionId.hashCode() : 0);
        result = 31 * result + (vigenciaInicioFch != null ? vigenciaInicioFch.hashCode() : 0);
        return result;
    }
}
