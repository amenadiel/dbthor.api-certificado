package com.dbthor.domain.certificado.service;


import com.dbthor.domain.certificado.entity.RestCall;
import com.dbthor.domain.certificado.entity.persona.EIdentificacion;
import com.dbthor.domain.certificado.entity.persona.EPersona;
import com.dbthor.domain.certificado.exception.ServiceException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Servicio Cliente del Dominio Persona
 *
 * Created by chris on 19-06-2017.
 */
@SuppressWarnings("unused")
@Service
@Log4j2
public class PersonaClienteService {

    @Value("${url.srv.persona.get}")                private String getPersonaUrl;
    @Value("${url.srv.persona.ident.buscar.get}")   private String getIdentBuscarUrl;
    @Value("${url.srv.persona.buscar.get}")         private String getPersonaByIdentUrl;

    //------------------------------------------------------------------------------------------------------------------
    /**
     * Servicio que obtiene el identificador de una persona. Se busca por personaId y tipo identificador
     *
     * @param personaId             Identificacion persona (UUID)
     * @param tipoIdent             Tipo de Identificador
     * @param trxId                 Identificacion de Transaccion (UUID)
     * @return                      EIdentificacion
     * @throws ServiceException     ServiceException
     */
    public EIdentificacion getIdentificacion(UUID personaId, Integer tipoIdent, UUID trxId) throws ServiceException {
        log.debug("{} START", trxId);
        log.debug("{} PARAM personaId : {}", trxId, personaId);

        RestCall<EIdentificacion> restCall =  new RestCall<>();
        try {
            URIBuilder url = new URIBuilder();
            url.setPath(getIdentBuscarUrl);
            url.addParameter("personaId", personaId.toString());
            url.addParameter("tipoIdentificacionId", tipoIdent.toString());
            url.addParameter("trxId", trxId.toString());

            log.debug("{} WS URL: {}", trxId, url.toString());

            String jsonResponse = restCall.callGet(url,trxId);

            EIdentificacion response = (new ObjectMapper()).readValue(jsonResponse, new TypeReference<EIdentificacion>() {});

            log.debug("{} END", trxId);
            return response;
        } catch (Exception e) {
            ServiceException fex=ServiceException.assignException(e);
            log.error("{} {}", trxId, fex.getErrorLog());
            log.debug("{} END", trxId);
            throw fex;
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    /**
     * Busca una persona segun su identificador, si no existe la crea (solo si createNotExist = true)
     *
     * @param identTypeId           Tipo de identificador
     * @param identVal              Valor Identificador
     * @param createNotExist        Crear persona si no existe (true)
     * @param trxId                 Identificacion de Transaccion (UUID)
     * @return                      EPersona
     * @throws ServiceException    ContratoException
     */
    public EPersona getPersonaByIdent(Short identTypeId, String identVal, Boolean createNotExist, UUID trxId) throws ServiceException {
        log.debug("{} START", trxId);
        log.debug("{} PARAM identTypeId:    {}", trxId, identTypeId);
        log.debug("{} PARAM identVal:       {}", trxId, identVal);
        log.debug("{} PARAM createNotExist: {}", trxId, createNotExist);

        RestCall<EPersona> restCall =  new RestCall<>();
        try {
            URIBuilder url = new URIBuilder();
            url.setPath(getPersonaByIdentUrl
                    .replace("{tipo}", identTypeId.toString())
                    .replace("{identificador}", identVal));

            url.addParameter("tipoIdentificacionId", (createNotExist?"true":"false"));
            url.addParameter("trxId", trxId.toString());

            log.debug("{} WS URL: {}", trxId, url.toString());

            String jsonResponse = restCall.callGet(url,trxId);

            EPersona response = (new ObjectMapper()).readValue(jsonResponse, new TypeReference<EPersona>() {});

            log.debug("{} END", trxId);
            return response;
        } catch (Exception e) {
            ServiceException fex=ServiceException.assignException(e);
            log.error("{} {}", trxId, fex.getErrorLog());
            log.debug("{} END", trxId);
            throw fex;
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    /**
     * Servicio que obtene lso das base de una persona
     *
     * @param personId              Identificacion de la persona (UUID)
     * @param trxId                 Identificacion de la transaccion (UUID)
     * @return                      EPersona
     * @throws ServiceException    ServiceException
     */
    public EPersona getPersona(UUID personId, UUID trxId) throws ServiceException {
        log.debug("{} START", trxId);
        log.debug("{} PARAM personId:    {}", trxId, personId);

        RestCall<EPersona> restCall = new RestCall<>();
        try {
            URIBuilder url = new URIBuilder();
            url.setPath(getPersonaUrl
                    .replace("{personaId}", personId.toString()));
            url.addParameter("trxId", trxId.toString());

            log.debug("{} WS URL: {}", trxId, url.toString());
            String jsonResponse = restCall.callGet(url, trxId);

            EPersona response = (new ObjectMapper()).readValue(jsonResponse, new TypeReference<EPersona>() {});

            log.debug("{} END", trxId);
            return response;
        } catch (Exception e) {
            ServiceException fex = ServiceException.assignException(e);
            log.error("{} {}", trxId, fex.getErrorLog());
            log.debug("{} END", trxId);
            throw fex;
        }
    }
}
