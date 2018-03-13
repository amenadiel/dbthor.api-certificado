package com.dbthor.domain.certificado.exception;

import lombok.Getter;

import java.util.HashMap;

/**
 * Clase que interpreta las exception para el usuario
 * <p>
 * Created by CSATTLER on 07-02-2017.
 */
@SuppressWarnings("unused,WeakerAccess")
public class ServiceException extends Exception {

    @Getter
    private final String code;
    @Getter
    private String detalle;
    @Getter
    private ServiceExceptionCodes enumCode;
    private HashMap<Integer, String> path = new HashMap<>();

    //------------------------------------------------------------------------------------------------------------------
    // CONSTRUCTORES
    //------------------------------------------------------------------------------------------------------------------
    public ServiceException(ServiceExceptionCodes code) {
        super(code.getMsg());
        this.code = code.getId();
        this.enumCode = code;
    }

    public ServiceException(ServiceExceptionCodes code, Throwable cause) {
        super(code.getMsg(), cause);
        this.code = code.getId();
        this.enumCode = code;
    }

    public ServiceException(ServiceExceptionCodes code, String detalle) {
        super(code.getMsg());
        this.detalle = detalle;
        this.code = code.getId();
        this.enumCode = code;
    }

    public ServiceException(ServiceExceptionCodes code, String detalle, Throwable cause) {
        super(code.getMsg(), cause);
        this.detalle = detalle;
        this.code = code.getId();
    }

    public ServiceException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public ServiceException(String code, String message) {
        super(message);
        this.code = code;
    }

    //------------------------------------------------------------------------------------------------------------------
    // METODOS
    //------------------------------------------------------------------------------------------------------------------

    //------------------------------------------------------------------------------------------------------------------

    /**
     * Agrega al path la clase y el metodo de invocacion
     */
    public void addPath() {
        final StackTraceElement e = Thread.currentThread().getStackTrace()[2];
        final String s = e.getClassName();
        String data = s.substring(s.lastIndexOf('.') + 1, s.length()) + "." + e.getMethodName();
        path.put(path.size(), data);
    }

    //------------------------------------------------------------------------------------------------------------------

    /**
     * Agrega al path la clase y el metodo de invocacion
     *
     * @param data Dato con el nombre de la clase y el metodo (clase.metodo)
     */
    public void addPath(String data) {
        path.put(path.size(), data);
    }

    //------------------------------------------------------------------------------------------------------------------

    /**
     * Obtiene un String con el path
     */
    public String getPath() {
        StringBuilder data = new StringBuilder();
        for (String d : path.values()) {
            data.insert(0, d + ">>>");
        }
        return data.substring(0, data.length() - 3) + "[ERR]";
    }

    //------------------------------------------------------------------------------------------------------------------

    /**
     * Genera String ccon el mensaje de error para el log
     */
    public String getErrorLog() {
        return "ERR: "
                + (getCode() != null ? getCode() : "UNKNOW")
                + (getMessage() != null ? " - " + getMessage() : "")
                + (getDetalle() != null ? " - " + getDetalle() : "")
                + (getCause() != null && getCause().getMessage() != null ? " [" + getCause().getMessage() + "]" : "");
    }

    //------------------------------------------------------------------------------------------------------------------

    /**
     * Metodo estatico que asigna la excepcion, si no no es un error administrado uso default
     */
    public static ServiceException assignException(Exception e) {
        ServiceException se = (e instanceof ServiceException ? (ServiceException) e : new ServiceException(ServiceExceptionCodes.ERROR_NO_ADMINISTRADO, e));

        final StackTraceElement te = Thread.currentThread().getStackTrace()[2];
        final String s = te.getClassName();
        String data = s.substring(s.lastIndexOf('.') + 1, s.length()) + "." + te.getMethodName();
        se.addPath(data);
        return se;
    }


    public static ServiceExceptionCodes assignExceptionEx(Exception e) {
        ServiceExceptionCodes se;

        System.out.println("Entre a controlar el Error" + e.getMessage());

        if (e.getMessage().equals("No existe certificado")) {
            se = ServiceExceptionCodes.NO_EXISTE_CERTIFICADO;
        } else if (e.getCause().getMessage().equals("Failed PKCS12 integrity checking")) {
            se = ServiceExceptionCodes.PASSWORD_INVALIDO;
        }
//        else if (e.getMessage().equals("No existe certificado")) {
//            se = ServiceExceptionCodes.NO_EXISTE_CERTIFICADO;
//        }
        else {
            se = ServiceExceptionCodes.ERROR_NO_ADMINISTRADO;
        }


        return se;
    }
}
