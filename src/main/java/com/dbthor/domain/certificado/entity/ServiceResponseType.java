package com.dbthor.domain.certificado.entity;

import com.dbthor.domain.certificado.exception.ServiceException;
import com.dbthor.domain.certificado.exception.ServiceExceptionCodes;
import com.dbthor.tools.ErrorTools;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.hal.Jackson2HalModule;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;


/**
 * Respuesta de los Servicios API
 *
 * Created by csattler on 11/04/2017.
 */
@SuppressWarnings("unused,SameParameterValue,WeakerAccess")
@NoArgsConstructor
public class ServiceResponseType<T> extends ResourceSupport {

    @Setter @Getter @JsonProperty("trxId")          private String trxId;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'z", locale = "es-CL", timezone = "America/Santiago")
    @Setter @Getter @JsonProperty("trxFchhr")       private Date trxFchhr;
    @Getter @JsonProperty("error")                  private Error error;
    @Setter @Getter @JsonProperty("datos")          private T datos;

    /*Constructor*/
    @JsonCreator
    public ServiceResponseType(@JsonProperty("trxId") String trxId, @JsonProperty("trxFchhr") Date trxFchhr) {
        setTrxId(trxId);
        setTrxFchhr(trxFchhr);
    }

    public ServiceResponseType(UUID trxId) {
        setTrxId(trxId.toString());
        setTrxFchhr(new Date());
    }

    @NoArgsConstructor
    public class Error {
        @Setter @Getter @JsonProperty("codigo")     public String codigo;
        @Setter @Getter @JsonProperty("mensaje")    public String mensaje;
        @Setter @Getter @JsonProperty("detalle")    public String detalle;
        @Setter @Getter @JsonProperty("path")       public String path;
    }

    /*Metodos de Error*/
    public void addError(String codigo, String mensaje) {
        error =  new Error();
        error.setCodigo(codigo);
        error.setMensaje(mensaje);
    }

    public void addError(String codigo, String mensaje, String detalle) {
        error =  new Error();
        error.setCodigo(codigo);
        error.setMensaje(mensaje);
        error.setDetalle(detalle);
    }

    public void addError(ServiceException exception) {
        error =  new Error();
        error.setCodigo(exception.getCode());
        error.setMensaje(exception.getMessage());
        error.setDetalle(exception.getDetalle() + (exception.getCause()!=null?" : "+ ErrorTools.getStackTrace(exception.getCause()):""));
        error.setPath(exception.getPath());
    }

    public void addError(ServiceExceptionCodes codigo ) {
        error =  new Error();
        error.setCodigo(codigo.getId());
        error.setMensaje(codigo.getMsg());
    }

    public void addError(ServiceExceptionCodes codigo, Throwable cause ) {
        error =  new Error();
        error.setCodigo(codigo.getId());
        error.setMensaje(codigo.getMsg());
        error.setDetalle(ErrorTools.getStackTrace(cause));
    }

    public void addError(ServiceExceptionCodes codigo, String detalle) {
        error =  new Error();
        error.setCodigo(codigo.getId());
        error.setMensaje(codigo.getMsg());
        error.setDetalle(detalle);
    }

    public static String getErrorMsg(ServiceResponseType.Error error) {
        return "ERR: "
                + (error.getCodigo()!=null?error.getCodigo():"UNKNOW")
                + (error.getMensaje()!=null?" - "+ error.getMensaje():"")
                + (error.getDetalle()!=null?" - "+ error.getDetalle():"");
    }

    public static HttpStatus getHttpStatus(ServiceResponseType.Error error, HttpStatus okEndStatus) {
        if (error == null || error.getCodigo() == null)
            return okEndStatus;
        else
            return (error.getCodigo().equals(ServiceExceptionCodes.ERROR_NO_ADMINISTRADO.getId())? HttpStatus.INTERNAL_SERVER_ERROR: HttpStatus.UNPROCESSABLE_ENTITY );
    }

    //------------------------------------------------------------------------------------------------------------------
    /**
     * Procesa la respuesta de error de los servicos web recibida
     *
     * @param json  json con el mensje de respuesta de error
     * @return
     */
    public static ServiceResponseType getfromJson(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new Jackson2HalModule());
        try {
            return objectMapper.readValue(json, ServiceResponseType.class);
        } catch (IOException e1) {
            ServiceResponseType resp =  new ServiceResponseType();
            try {
                SpringRestError restErr= objectMapper.readValue(json, SpringRestError.class);
                resp.addError(ServiceExceptionCodes.ERROR_SERVICIO_REQUERIDO,restErr.toString());
            } catch (IOException e) {
                resp.addError(ServiceExceptionCodes.JSON_RESPUESTA_INVALIDO,"JSON Data:"+json);
            }
            return resp;
        }
    }

}
