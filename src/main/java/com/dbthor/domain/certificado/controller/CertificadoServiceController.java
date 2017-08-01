package com.dbthor.domain.certificado.controller;

import com.dbthor.domain.certificado.data.entity.ECertificadoDigital;
import com.dbthor.domain.certificado.entity.CertificadoCreateRequest;
import com.dbthor.domain.certificado.entity.ServiceResponseType;
import com.dbthor.domain.certificado.exception.ServiceException;
import com.dbthor.domain.certificado.exception.ServiceExceptionCodes;
import com.dbthor.domain.certificado.service.CertificadoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


/**
 * Controlador de servicio RestFull para el certificado
 *
 * Created by CSATTLER on 31-01-2017.
 */
@Api
@RestController
@RequestMapping(CertificadoServiceController.BASE_URI)
@Log4j2
@SuppressWarnings("unused,WeakerAccess")
public class CertificadoServiceController {
    static final String BASE_URI="/certificado";

    @Autowired private CertificadoService certSrv;

    /*----------------------------------------------------------------------------------------------------------------*/
    /**
     * Cargar Certificado
     *
     * @param bodyData  JSON con datos del certificado
     * @param trxId     Identificación de la transacción
     * @return          ECertificadoDigital
     */
    @RequestMapping(method = RequestMethod.POST, value = "", produces = "application/json")
    @ApiOperation(value = "Cargar Certificado", notes = "Servicio que carga uncertificado a una persona")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ServiceResponseType<ECertificadoDigital>> postCertificado(
            @ApiParam(value = "Objeto on el requermiento de creacion") @RequestBody CertificadoCreateRequest bodyData
            , @ApiParam(value = "Identificador de transacción") @RequestParam(required = false) UUID trxId
    ) {
        if (trxId==null) trxId= UUID.randomUUID();
        log.debug("{} ----------------------------------------------------------------------------", trxId);
        ControllerLinkBuilder link = linkTo(methodOn(CertificadoServiceController.class).postCertificado(bodyData, trxId ));
        ServiceResponseType<ECertificadoDigital> resp=  new ServiceResponseType<>(trxId);
        try {
            log.debug("{} START", trxId);
            log.debug("{} POST  {}", trxId, link.toUri().toString() );
            //log.debug("{} PARAM personaId:                  {}", trxId, (identificacion==null?"null":identificacion.getPersonaId()));
            resp.add(link.withSelfRel());

            if (bodyData==null)
                throw new ServiceException(ServiceExceptionCodes.POST_BODY_REQUEST_NULO);

            ECertificadoDigital cert = certSrv.loadCertificado(bodyData, trxId);

            resp.setDatos(cert);
        } catch (Exception e) {
            ServiceException se= ServiceException.assignException(e);
            resp.addError(se);
            log.error("{} {}", trxId, ServiceResponseType.getErrorMsg(resp.getError()));
        }

        log.debug("{} END", trxId );
        log.debug("{} ----------------------------------------------------------------------------", trxId);
        return new ResponseEntity<>(resp, ServiceResponseType.getHttpStatus(resp.getError(),  HttpStatus.CREATED));
    }


    /*----------------------------------------------------------------------------------------------------------------*/
    /**
     * Obtiene el certificado
     *
     * @param certificadoId Identificado del certificado
     * @param trxId     Identificación de la transacción
     * @return              CertificadoResponse con los datos del certificado
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{certificadoId}", produces = "application/json")
    @ApiOperation(value = "Obtiene datos de un Certificado", notes = "Servicio que obtiene datos de un Certificado")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ServiceResponseType<ECertificadoDigital>> getCertificado(
            @ApiParam(value = "Identificado del certificado")   @PathVariable UUID certificadoId
            , @ApiParam(value = "Identificador de transacción") @RequestParam(required = false) UUID trxId
    ) {
        if (trxId==null) trxId= UUID.randomUUID();
        log.debug("{} ----------------------------------------------------------------------------", trxId);
        ControllerLinkBuilder link = linkTo(methodOn(CertificadoServiceController.class).getCertificado(certificadoId, trxId ));
        ServiceResponseType<ECertificadoDigital> resp=  new ServiceResponseType<>(trxId);
        try {
            log.debug("{} START", trxId);
            log.debug("{} POST  {}", trxId, link.toUri().toString() );
            log.debug("{} PARAM certificadoId: {}", trxId, certificadoId);
            resp.add(link.withSelfRel());

            ECertificadoDigital cert = certSrv.getCertificadoEntity(certificadoId, trxId);

            resp.setDatos(cert);
        } catch (Exception e) {
            ServiceException se= ServiceException.assignException(e);
            resp.addError(se);
            log.error("{} {}", trxId, ServiceResponseType.getErrorMsg(resp.getError()));
        }
        log.debug("{} END", trxId );
        log.debug("{} ----------------------------------------------------------------------------", trxId);
        return new ResponseEntity<>(resp, ServiceResponseType.getHttpStatus(resp.getError(),  HttpStatus.OK));
    }


    /*----------------------------------------------------------------------------------------------------------------*/
    /**
     * Obtiene el certificado asociado a una persona
     *
     * @param personaId Identificados Internos de la Persona
     * @param usoId     Tipo de Uso de Certificado
     * @return          List<ECertificadoDigital> Listados de certificados
     */
    @RequestMapping(method = RequestMethod.GET, value = "/persona/{personaId}", produces = "application/json")
    @ApiOperation(value = "Obtiene el certificado asociado a una persona",
            notes = "Servicio que oObtiene el certificado asociado a una persona")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ServiceResponseType<List<ECertificadoDigital>>> getCertificadoPersonaUso(
            @ApiParam(value = "Identificado de la persona")     @PathVariable UUID personaId
            , @ApiParam(value = "Identificado de la persona")   @RequestParam(value = "usoId" , required = false) String usoId
            , @ApiParam(value = "Identificador de transacción") @RequestParam(required = false) UUID trxId
    ) {
        if (trxId==null) trxId= UUID.randomUUID();
        log.debug("{} ----------------------------------------------------------------------------", trxId);
        ControllerLinkBuilder link = linkTo(methodOn(CertificadoServiceController.class).getCertificadoPersonaUso(personaId,usoId,trxId ));
        ServiceResponseType<List<ECertificadoDigital>> resp=  new ServiceResponseType<>(trxId);
        try {
            log.debug("{} START", trxId);
            log.debug("{} POST  {}", trxId, link.toUri().toString() );
            log.debug("{} PARAM personaId: {}", trxId, personaId);
            log.debug("{} PARAM usoId:     {}", trxId, usoId);
            resp.add(link.withSelfRel());

            List<ECertificadoDigital> listCert = certSrv.getCertificadoUso(personaId, usoId, trxId);

            resp.setDatos(listCert);
        } catch (Exception e) {
            ServiceException se= ServiceException.assignException(e);
            resp.addError(se);
            log.error("{} {}", trxId, ServiceResponseType.getErrorMsg(resp.getError()));
        }
        log.debug("{} END", trxId );
        log.debug("{} ----------------------------------------------------------------------------", trxId);
        return new ResponseEntity<>(resp, ServiceResponseType.getHttpStatus(resp.getError(),  HttpStatus.OK));
    }
}


