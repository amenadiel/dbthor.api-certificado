package com.dbthor.domain.certificado.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by CSATTLER on 07-02-2017.
 */
public class CertificadoCreateRequest {
    public class ClienteIdent {
        @Setter @Getter @JsonProperty("id") String personaId;
        @Setter @Getter @JsonProperty("tipoIdentificadorId") Short tipoIdentificadorId;
        @Setter @Getter @JsonProperty("identificador") String identificador;
        @Setter @Getter @JsonProperty("validador") String validador;

        public ClienteIdent() { }
    }

    public class Certificado {
        @Setter @Getter @JsonProperty("id") public String certId;
        @Setter @Getter @JsonProperty("data") public String data;
        @Setter @Getter @JsonProperty("password") public String password;
        @Setter @Getter @JsonProperty("userEMail") public String userEmail;
        @Setter @Getter @JsonProperty("archivoNombre") public String archivoNombre;
        @Setter @Getter @JsonProperty("certRut") long certRut;

        public Certificado() {}
    }
    @Setter @Getter @JsonProperty("persona") public ClienteIdent cliente;
    @Setter @Getter @JsonProperty("certificado") public Certificado certificado;
}
