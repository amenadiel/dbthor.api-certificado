package com.dbthor.domain.certificado.entity;

import com.dbthor.tools.JsonTools;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.hal.Jackson2HalModule;

import java.io.IOException;

/**
 * Created by CSATTLER on 02-02-2017.
 */
@SuppressWarnings("SameParameterValue")
public class WebSrvResponse {

    public class Error {
        @Setter
        @Getter
        @JsonProperty("codigo")
        public String codigo;
        @Setter
        @Getter
        @JsonProperty("mensaje")
        public String mensaje;
        @Setter
        @Getter
        @JsonProperty("detalle")
        public String detalle;

        public Error() {
        }

    }

    @Getter
    @JsonProperty("error")
    public Error error;
    @Setter
    @Getter
    @JsonProperty("datos")
    public Object datos;

    /*Constructores*/
    public WebSrvResponse() {
    }

    public WebSrvResponse(Object dato) {
        setDatos(dato);
    }

    public void setError(String codigo, String mensaje) {
        error = new Error();
        error.setCodigo(codigo);
        error.setMensaje(mensaje);
    }

    public void setError(String codigo, String mensaje, String detalle) {
        error = new Error();
        error.setCodigo(codigo);
        error.setMensaje(mensaje);
        error.setDetalle(detalle);
    }

    public static WebSrvResponse getfromJson(String json) {
        try {
            ObjectMapper objectMapper = JsonTools.getObjectMapperOmit();
            objectMapper.registerModule(new Jackson2HalModule());
            return objectMapper.readValue(json, WebSrvResponse.class);
        } catch (IOException e1) {
            WebSrvResponse resp = new WebSrvResponse();
            resp.setError("JSON_RESPUESTA_INVALIDO", "JSON Data:" + json);
            return resp;
        }
    }

}
