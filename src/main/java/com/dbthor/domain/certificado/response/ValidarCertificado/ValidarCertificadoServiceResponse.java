package com.dbthor.domain.certificado.response.ValidarCertificado;

import com.dbthor.domain.certificado.response.CargarCertificado.CargarCertificadoResponseCodes;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import lombok.Getter;
import lombok.Setter;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE,
        setterVisibility = JsonAutoDetect.Visibility.NONE, creatorVisibility = JsonAutoDetect.Visibility.NONE)

public abstract class ValidarCertificadoServiceResponse<E extends ValidarCertificadoResponseCodes> {
    @Getter
    @JsonProperty("code")
    @JsonPropertyDescription("Codigo identificador")
    protected String code;
    @Getter
    @JsonProperty("idCode")
    @JsonPropertyDescription("Codigo id")
    protected int idCode;
    @Getter
    @Setter
    @JsonProperty("mensaje")
    @JsonPropertyDescription("Descripcion del mensaje")
    protected String mensaje;

    @JsonIgnore
    protected E actCode;

    public ValidarCertificadoServiceResponse(E code) {
        asignarCodigo(code);
    }


    public void asignarCodigo(E code) {
        this.actCode = code;
        this.code = code.name();
        this.mensaje = code.msg();
        this.idCode = code.id();
    }

    public E codes() {
        return actCode;
    }

}

