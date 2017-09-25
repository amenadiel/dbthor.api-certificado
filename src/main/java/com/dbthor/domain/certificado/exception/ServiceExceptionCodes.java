package com.dbthor.domain.certificado.exception;

/**
 * Enum de codigos de Error del dominio
 *
 * Created by csattler on 19/04/2017.
 */
public enum ServiceExceptionCodes {
    //Codigo Genericos
    ERROR_NO_ADMINISTRADO("ERROR_NO_ADMINISTRADO","Error no administrado"),
    ERROR_SERVICIO_REQUERIDO("ERROR_SERVICIO_REQUERIDO","Erroren uno de los servicios externos requeridos por el proceso"),
    JSON_RESPUESTA_INVALIDO("JSON_RESPUESTA_INVALIDO","Respuesta JSON invalida o mal formada"),
    REQUERIMIENTO_PARAMETROS_INVALIDOS("REQUERIMIENTO_PARAMETROS_INVALIDOS","Parametros de invocacion no cumplen regla"),
    RESPUESTA_SERVICIO_INVALIDA("RESPUESTA_SERVICIO_INVALIDA","Respuesta de servicio web invalido o nula."),
    POST_BODY_REQUEST_NULO("POST_REQUEST_NULO","El Body del POST request es nulo"),
    REQUERIMIENTO_INVALIDO("REQUERIMIENTO_INVALIDO","Error en la invocacion del Servicio"),
    NO_EXISTE("NO_EXISTE","No existe entidad/elemento/objeto"),
    MISSING_DATA("MISSING_DATA","Invalido o faltan datos"),
    BUSINESS_RULE_ERROR("BUSINESS_RULE_ERROR","Error regla de negocio"),

    //Codigos de Error de CERTIFICADO
    NO_EXISTE_CERTIFICADO("NO_EXISTE_CERTIFICADO","No existe certificado"),
    NO_EXISTE_USO_CERT("NO_EXISTE_USO_CERT","No existe uso del certificado"),
    PASSWORD_INVALIDO("PASSWORD_INVALIDO","Clave invalida"),
    ERROR_CARGA_CERT("ERROR_CARGA_CERT","Error de carga del certificado"),
    EXPIRADO("EXPIRADO","Certificado expirado"),
    INVALIDO("INVALIDO","Certificado invalido"),

    //Codigos de Error de PERSONA
    NO_EXISTE_PERSONA("NO_EXISTE_PERSONA","No existe persona"),
    NO_EXISTE_PERSONA_IDENT("NO_EXISTE_PERSONA_IDENT","No existe persona con el identificador"),

    //Codigos de Error de IDENTIFICACION
    TIPO_IDENTIFICADOR_INVALIDO("TIPO_IDENTIFICADOR_INVALIDO", "Tipo identificador de documento invalido"),
    INDENTIFICADOR_INVALIDO("INDENTIFICADOR_INVALIDO","Identificador invalido"),
    INDENTIFICADOR_DUPLICADO("INDENTIFICADOR_DUPLICADO","Identificador Duplicado."),
    VALIDADOR_IDENTIFICADOR_INVALIDO("VALIDADOR_IDENTIFICADOR_INVALIDO", "Digito validador del RUT invalido"),


    //Codigos de Error de PERSONA_APLICACION

    ;
    private final String id;
    private final String msg;

    ServiceExceptionCodes(String id, String msg) {
        this.id = id;
        this.msg = msg;
    }

    public String getId() {
        return this.id;
    }
    public String getMsg() {
        return this.msg;
    }
    public String toString() {
        return this.id+" - " + this.msg;
    }
}
