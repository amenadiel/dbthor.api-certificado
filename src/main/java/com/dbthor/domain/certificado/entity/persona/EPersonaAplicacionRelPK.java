package com.dbthor.domain.certificado.entity.persona;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Clase que representa la PK la entidad persona_aplicacion_rel
 *
 * Created by chris on 09-07-2017.
 */
@SuppressWarnings("unused")
public class EPersonaAplicacionRelPK implements Serializable {
    private String personaId;
    private String aplicacionSistemaId;

    @Column(name = "persona_id")
    @Id
    public String getPersonaId() {
        return personaId;
    }

    public void setPersonaId(String personaId) {
        this.personaId = personaId;
    }

    @Column(name = "aplicacion_sistema_id")
    @Id
    public String getAplicacionSistemaId() {
        return aplicacionSistemaId;
    }

    public void setAplicacionSistemaId(String aplicacionSistemaId) {
        this.aplicacionSistemaId = aplicacionSistemaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EPersonaAplicacionRelPK that = (EPersonaAplicacionRelPK) o;

        return (personaId != null ? personaId.equals(that.personaId) : that.personaId == null)
                && (aplicacionSistemaId != null ? aplicacionSistemaId.equals(that.aplicacionSistemaId) : that.aplicacionSistemaId == null);
    }

    @Override
    public int hashCode() {
        int result = personaId != null ? personaId.hashCode() : 0;
        result = 31 * result + (aplicacionSistemaId != null ? aplicacionSistemaId.hashCode() : 0);
        return result;
    }
}
