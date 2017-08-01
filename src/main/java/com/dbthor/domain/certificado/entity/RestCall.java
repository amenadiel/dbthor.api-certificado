package com.dbthor.domain.certificado.entity;

import com.dbthor.domain.certificado.exception.ServiceException;
import com.dbthor.domain.certificado.exception.ServiceExceptionCodes;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.hateoas.hal.Jackson2HalModule;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

/**
 * Classe de llamada de servicios de DBThor
 *
 * @author csattler, created on 20-06-2017.
 */
@Log4j2
public class RestCall<T> {
    //------------------------------------------------------------------------------------------------------------------
    /**
     * Llama a un servicio DBThor metodo GET
     *
     * @param url               URL del servicio
     * @param trxId             Identificacion Transaccion (UUID)
     * @return                  JSON respuesta
     * @throws ServiceException documento
     */
    public String callGet(URIBuilder url, UUID trxId) throws ServiceException {
        log.debug("{} START", trxId);
        RestTemplate restTemplate= new RestTemplate();
        try {

            log.debug("{} WS URL: {}", trxId, url.toString());
            String json;

            String resultBody = restTemplate.getForObject(url.build(), String.class);
            json =  procesarRespuesta(resultBody);

            log.debug("{} WS Response: {}", trxId, json);
            log.debug("{} END", trxId);
            return json;
        } catch (Exception e) {
            ServiceException fex = processWebCallException(e);
            log.error("{} {}", trxId, fex.getErrorLog());
            log.debug("{} END", trxId);
            throw fex;
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    /**
     * Llama a un servicio DBThor metodo POST
     *
     * @param url               URL del servicio
     * @param postData          Objeto del body post
     * @param trxId             Identificacion Transaccion (UUID)
     * @return                  JSON respuesta
     * @throws ServiceException documento
     */
    public String callPost(URIBuilder url, Object postData, UUID trxId) throws ServiceException {
        log.debug("{} START", trxId);
        RestTemplate restTemplate= new RestTemplate();
        try {

            log.debug("{} WS URL: {}", trxId, url.toString());
            String json;

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<Object> entity = new HttpEntity<>(postData, headers);
            ResponseEntity<String> response = restTemplate.exchange(url.build(), HttpMethod.POST, entity, String.class);

            if (response.getStatusCode() != HttpStatus.CREATED)
                throw new ServiceException(ServiceExceptionCodes.RESPUESTA_SERVICIO_INVALIDA,"Esperando respuesta HttpStatus:"+ HttpStatus.CREATED.toString()+" y se obtuvo HttpStatus:"+response.getStatusCode().toString());

            String resultBody= response.getBody();
            json =  procesarRespuesta(resultBody);
            log.debug("{} WS Response: {}", trxId, json);
            log.debug("{} END", trxId);
            return json;
        } catch (Exception e) {
            ServiceException fex = processWebCallException(e);
            log.error("{} {}", trxId, fex.getErrorLog());
            log.debug("{} END", trxId);
            throw fex;
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    /**
     * Llama a un servicio DBThor metodo PUT
     *
     * @param url               URL del servicio
     * @param postData          Objeto del body post
     * @param trxId             Identificacion Transaccion (UUID)
     * @return                  JSON respuesta
     * @throws ServiceException documento
     */
    public String callPut(URIBuilder url, Object postData, UUID trxId) throws ServiceException {
        log.debug("{} START", trxId);
        RestTemplate restTemplate= new RestTemplate();
        try {

            log.debug("{} WS URL: {}", trxId, url.toString());
            String json;

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Object> entity = new HttpEntity<>(postData, headers);

            ResponseEntity<String> response = restTemplate.exchange(url.build(), HttpMethod.PUT, entity, String.class);

            if (response.getStatusCode() != HttpStatus.CREATED)
                throw new ServiceException(ServiceExceptionCodes.RESPUESTA_SERVICIO_INVALIDA,"Esperando respuesta HttpStatus:"+ HttpStatus.CREATED.toString()+" y se obtuvo HttpStatus:"+response.getStatusCode().toString());


            String resultBody= response.getBody();
            json =  procesarRespuesta(resultBody);
            log.debug("{} WS Response: {}", trxId, json);
            log.debug("{} END", trxId);
            return json;
        } catch (Exception e) {
            ServiceException fex = processWebCallException(e);
            log.error("{} {}", trxId, fex.getErrorLog());
            log.debug("{} END", trxId);
            throw fex;
        }
    }


    //------------------------------------------------------------------------------------------------------------------
    /**
     * Servicio de pocesa la respuesta de un servicio de DBThor.
     *
     * @param resultBody        Json de respuesta
     * @return                  Json con la data
     * @throws ServiceException ServiceException
     */
    private String procesarRespuesta(String resultBody) throws ServiceException {
        String json;
        try {
            ObjectMapper mapper = new ObjectMapper();
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new Jackson2HalModule());
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); //Don't fail if additional fields in incoming JSON, just ignore
            objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false); //Don't fail on incoming JSON missing fields
            if (resultBody.contains("\"trxId\"")) {
                ServiceResponseType resp = objectMapper.readValue(resultBody, ServiceResponseType.class);
                if (resp == null) //Si respuesta es null error
                    throw new ServiceException(ServiceExceptionCodes.RESPUESTA_SERVICIO_INVALIDA);

                json = mapper.writeValueAsString(resp.getDatos());
            } else {
                WebSrvResponse respWebOld = objectMapper.readValue(resultBody, WebSrvResponse.class);
                if (respWebOld == null) //Si respuesta es null error
                    throw new ServiceException(ServiceExceptionCodes.RESPUESTA_SERVICIO_INVALIDA);

                json = mapper.writeValueAsString(respWebOld.getDatos());
            }
            return json;
        } catch (Exception e) {
            throw ServiceException.assignException(e);
        }
    }

    //------------------------------------------------------------------------------------------------------------------

    /**
     * Procesa la excepcion de la respuesta de un llamado a un servicio GET, POST
     *
     * @param e Excepcion producida
     * @return  ServiceException
     */
    private ServiceException processWebCallException(Exception e) {
        ServiceException fex;
        if ( e instanceof HttpClientErrorException) {
            ServiceResponseType resp = ServiceResponseType.getfromJson( ((HttpClientErrorException) e).getResponseBodyAsString());
            fex = new ServiceException(resp.getError().getCodigo(), resp.getError().getMensaje());
        } else {
            fex=(e instanceof ServiceException ?(ServiceException) e: new ServiceException(ServiceExceptionCodes.ERROR_NO_ADMINISTRADO, e));
        }
        return fex;
    }
}
