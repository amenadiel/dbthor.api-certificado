package com.dbthor.domain.certificado.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by CSATTLER on 02-02-2017.
 */
public class CertificadoResponse {

    public class Error {
        @Setter @Getter @JsonProperty("codigo") public String codigo;
        @Setter @Getter @JsonProperty("mensaje") public String mensaje;
        @Setter @Getter @JsonProperty("detalle") public String detalle;
        public Error() {}
    }

    @Getter @JsonProperty("error") public Error error;
    @Setter @Getter @JsonProperty("datos") public Object datos;

    /*Constructores*/
    public CertificadoResponse() { }

    public CertificadoResponse(Object dato) {
        setDatos(dato);
    }

    /*Metodos*/
    public void setError(String codigo, String mensaje) {
        error =  new Error();
        error.setCodigo(codigo);
        error.setMensaje(mensaje);
    }

    public void setError(String codigo, String mensaje, String detalle) {
        error =  new Error();
        error.setCodigo(codigo);
        error.setMensaje(mensaje);
        error.setDetalle(detalle);
    }
}
