package com.dbthor.domain.certificado.service;


import com.dbthor.domain.certificado.data.ICertificadoDigitalRepository;
import com.dbthor.domain.certificado.data.ICertificadoDigitalTokenRepository;
import com.dbthor.domain.certificado.data.IPersonaCertificadoDigitalRepository;
import com.dbthor.domain.certificado.data.ITipoCertificadoUsoRepository;
import com.dbthor.domain.certificado.data.entity.*;
import com.dbthor.domain.certificado.entity.CertificadoCreateRequest;
import com.dbthor.domain.certificado.entity.CertificadoDigital;
import com.dbthor.domain.certificado.entity.persona.EPersona;
import com.dbthor.domain.certificado.exception.ServiceException;
import com.dbthor.domain.certificado.exception.ServiceExceptionCodes;
import com.dbthor.tools.DateTools;
import com.google.common.collect.Lists;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Capa de servicio para el DTE
 *
 * Created by CSATTLER on 02-02-2017.
 */
@Service
@Log4j2
public class CertificadoService {
    private static final Logger logger = LogManager.getLogger(CertificadoService.class.getName());

    @Autowired private ICertificadoDigitalRepository certRepo;
    @Autowired private ITipoCertificadoUsoRepository tipoUsoCertRepo;
    @Autowired private IPersonaCertificadoDigitalRepository personaCertRepo;
    @Autowired private PersonaClienteService persSrv;
    @Autowired private ICertificadoDigitalTokenRepository certTokenRepo;
    @Autowired private DteSiiService dteSiiSrv;

    //------------------------------------------------------------------------------------------------------------------
    /**
     * Carga el certificado en la plataforma y lo valida.
     *
     * @param data                  CertificadoCreateRequest estructura del request
     * @throws ServiceException Exception de la aplicacion.
     */
    @Transactional
    public ECertificadoDigital loadCertificado(CertificadoCreateRequest data, UUID trxId) throws ServiceException {
        Date now = new Date();
        ECertificadoDigital certificado = new ECertificadoDigital();

        //  Extrae data relevante de la data enviada
        String personaId= data.getCliente().getPersonaId();
        Short personaTipoIdent= data.getCliente().getTipoIdentificadorId();
        String personaIdentVal =  data.getCliente().getIdentificador();

        String dataCert =  data.getCertificado().getData();
        String mail =  data.getCertificado().getUserEmail();
        String password =  data.getCertificado().getPassword();

        CertificadoDigital cert =  new CertificadoDigital();
        try {
            //  Agregar logica para validar si existe la persona
            if (personaId != null && personaId.length() > 1) {
                EPersona persona =persSrv.getPersona(UUID.fromString(personaId), trxId);
                if (persona == null)
                    throw new ServiceException(ServiceExceptionCodes.NO_EXISTE_PERSONA, "No existe la persona para asociar el certificado");
            }

            if (personaId == null && personaTipoIdent != null && personaIdentVal != null) {

                EPersona persona =persSrv.getPersonaByIdent(personaTipoIdent,personaIdentVal, false, trxId);
                if (persona == null)
                    throw new ServiceException(ServiceExceptionCodes.NO_EXISTE_PERSONA, "No existe la persona (tipo, identificador) para asociar el certificado");

                personaId = persona.getId();
            }
            ETipoCertificadoUso usoCert = tipoUsoCertRepo.findOne("SII-USO");

            //Valida que el usuario no tengo un certificado asociado para un mismo uso
            List<ECertificadoDigital> listCert;
            try {
                listCert = getCertificadoUso(UUID.fromString(personaId), usoCert.getId(),trxId);
            } catch (ServiceException ce) {
                listCert = new ArrayList<>();
            }


            Boolean existCert = false;
            UUID certId = UUID.randomUUID();
            if (listCert.size() > 0) {
                certId = UUID.fromString(listCert.get(0).getId());
                existCert = true;
                //throw new ServiceException("CERT-ERR-PERSONA-USO-EXIST", "Ya existe un certificado con la relacion persona-uso");
            }

            // Abre el certificado y extra la infomación basica
            cert.loadCertificado(dataCert, mail, password, trxId.toString());

            //Se genera los datos de la entidad Certificado
            certificado.setId(certId.toString());
            certificado.setDataEncode64Val(dataCert);
            certificado.setCreacionFchhr(DateTools.convertUtil2SqlTimestamp(cert.getFechaCreacion()));
            certificado.setExpiracionFchhr(DateTools.convertUtil2SqlTimestamp(cert.getFechaExpiracion()));
            certificado.setUsuarioCorreoVal(mail);
            certificado.setSubjectDnVal(cert.getSubject());
            certificado.setIssuerDnVal(cert.getIssuer());
            certificado.setArchivoNombre(data.getCertificado().getArchivoNombre());

            //certRepo.save(certificado);


//            if (cert.getFechaExpiracion().after(now)) {
            if (true) {
                certRepo.save(certificado);

                //Se genera los datos de la entidad Persona Certificado y su uso
                if (!existCert) {
                    EPersonaCertificadoDigital persUsoCert = new EPersonaCertificadoDigital();
                    persUsoCert.setPersonaId(personaId);
                    persUsoCert.setCertificadoDigitalId(certId.toString());
                    persUsoCert.setTipoCertificadoUso(usoCert);
                    personaCertRepo.save(persUsoCert);
                }
                // Se graba la informacion en la base de datos
                logger.info("{} Certificado Cargado y almacenado en base de datos", trxId);

            } else {
                certificado=null;
                logger.info("{} Certificado no ha sido almacenado", trxId);
            }


        } catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException e) {
            log.error("{} {}", trxId, e);
            throw new ServiceException(ServiceExceptionCodes.ERROR_CARGA_CERT, e.getCause());
        } catch (Exception e) {
            ServiceException fex=ServiceException.assignException(e);
            log.error("{} {}", trxId, fex.getErrorLog());
            log.debug("{} END", trxId);
            throw fex;
        }

        return certificado;
    }

    //------------------------------------------------------------------------------------------------------------------
    /**
     * Obtiene el certificado a partir del ID y  su clave
     *
     * @param certId                Identificacion del Certificado Digital
     * @param password              Clave del Certificado Digital
     * @return                      CertificadoDigital
     * @throws ServiceException Exception Certificado
     */
    public CertificadoDigital getCertificado(UUID certId, String password, String trxId) throws ServiceException {
        log.debug("{} START", trxId);
        log.debug("{} PARAM certId : {}", trxId, certId);

        String passwordCert=password;
        CertificadoDigital cert =  new CertificadoDigital();
        try {
            ECertificadoDigital certData =  certRepo.findOne(certId.toString());
            //valida si existe el certificado
            if (certData==null)
                throw new ServiceException(ServiceExceptionCodes.NO_EXISTE_CERTIFICADO ,"CetificadoId: "+certId.toString()+"");

            if (passwordCert == null && certData.getPasswordVal()!=null)
                passwordCert =  certData.getPasswordVal();

            if (passwordCert == null )
                throw new ServiceException(ServiceExceptionCodes.PASSWORD_INVALIDO);

            cert.loadCertificado(certData.getDataEncode64Val(), certData.getUsuarioCorreoVal(),passwordCert, trxId );
            cert.setId(certId);
            log.debug("{} END", trxId);
            return cert;
        } catch (Exception e) {
            ServiceException fex=ServiceException.assignException(e);
            log.error("{} {}", trxId, fex.getErrorLog());
            log.debug("{} END", trxId);
            throw fex;
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    public CertificadoDigital getCertificado(UUID certId, String trxId) throws ServiceException {
        log.debug("{} START", trxId);
        log.debug("{} PARAM certId : {}", trxId, certId);
        try {
            CertificadoDigital certificadoDigital = getCertificado(certId, null, trxId);
            log.debug("{} END", trxId);
            return certificadoDigital;
        } catch (Exception e) {
            ServiceException fex=ServiceException.assignException(e);
            log.error("{} {}", trxId, fex.getErrorLog());
            log.debug("{} END", trxId);
            throw fex;
        }

    }

    //------------------------------------------------------------------------------------------------------------------
    /**
     * Ontien el ultimo Token usado por el certificado
     *
     * @param certId    Identificacion del Certificado Digital
     * @return          String Token
     */
    public String getCertificadoToken(UUID certId, UUID trxId) throws ServiceException {
        log.debug("{} START", trxId);
        log.debug("{} PARAM certId : {}", trxId, certId);
        try {
            ECertificadoDigitalToken certToken =  certTokenRepo.findOne(certId.toString());
            return certToken==null?null:certToken.getTokenVal();
        } catch (Exception e) {
            ServiceException fex=ServiceException.assignException(e);
            log.error("{} {}", trxId, fex.getErrorLog());
            log.debug("{} END", trxId);
            throw fex;
        }
    }


    /**
     * Actualiza el toke de un certificado
     *
     * @param certId    Identificacion del Certificado Digital
     * @param token     String Token
     */
    public void setCertificadoToken(UUID certId, String token, UUID trxId) throws ServiceException {
        log.debug("{} START", trxId);
        log.debug("{} PARAM certId: {}", trxId, certId);
        log.debug("{} PARAM token : {}", trxId, token);
        try {
            ECertificadoDigitalToken certTokenRec = certTokenRepo.findOne(certId.toString());

            if (certTokenRec==null) {
                ECertificadoDigitalToken certToken =  new ECertificadoDigitalToken();
                certToken.setCertificadoDigitalId(certId.toString());
                certToken.setTokenVal(token);
                certTokenRepo.save(certToken);
            } else {
                certTokenRec.setRegUpdateFchhr(DateTools.convertUtil2SqlTimestamp(new Date()));
                certTokenRec.setTokenVal(token);
                certTokenRepo.save(certTokenRec);
            }
        } catch (Exception e) {
            ServiceException fex=ServiceException.assignException(e);
            log.error("{} {}", trxId, fex.getErrorLog());
            log.debug("{} END", trxId);
            throw fex;
        }
    }

    public void delCertificadoToken(UUID certId, UUID trxId) throws ServiceException {
        log.debug("{} START", trxId);
        log.debug("{} PARAM certId: {}", trxId, certId);
        try {
            ECertificadoDigitalToken certTokenRec = certTokenRepo.findOne(certId.toString());
            if (certTokenRec!=null)
                certTokenRepo.delete(certTokenRec);
        } catch (Exception e) {
            ServiceException fex=ServiceException.assignException(e);
            log.error("{} {}", trxId, fex.getErrorLog());
            log.debug("{} END", trxId);
            throw fex;
        }
    }

    /**
     *
     * @param certId
     * @return
     * @throws ServiceException
     */
    public ECertificadoDigital getCertificadoEntity(UUID certId, UUID trxId) throws ServiceException {
        log.debug("{} START", trxId);
        log.debug("{} PARAM certId: {}", trxId, certId);
        ECertificadoDigital cert;
        try {
            cert = certRepo.findOne(certId.toString());

            if (cert == null )
                throw new ServiceException(ServiceExceptionCodes.NO_EXISTE_CERTIFICADO,"certificadoId: "+certId.toString());

            log.debug("{} END", trxId);
            return cert;
        } catch (Exception e) {
            ServiceException fex=ServiceException.assignException(e);
            log.error("{} {}", trxId, fex.getErrorLog());
            log.debug("{} END", trxId);
            throw fex;
        }

    }

    public List<ECertificadoDigital> getCertificadoUso(UUID personaId, String usoId, UUID trxId) throws ServiceException {
        log.debug("{} START", trxId);
        log.debug("{} PARAM personaId: {}", trxId, personaId);
        log.debug("{} PARAM usoId: {}", trxId, usoId);
        List<ECertificadoDigital> listCert = new ArrayList<>();
        try {

            if (usoId !=null && usoId.length()>0) {
                EPersonaCertificadoDigital persCert= personaCertRepo.getCertificadoByPersonaUso(personaId.toString(), usoId);
                if (persCert == null )
                    throw new ServiceException(ServiceExceptionCodes.NO_EXISTE_USO_CERT,
                            "No existe certificado con el uso ["+usoId+"] " +
                            "asociado a la persona [personaId:"+personaId.toString()+"]");

                ECertificadoDigital cert = getCertificadoEntity(UUID.fromString(persCert.getCertificadoDigitalId()),trxId);
                if (cert == null )
                    throw new ServiceException(ServiceExceptionCodes.NO_EXISTE_CERTIFICADO ,"certificadoId:"+persCert.getCertificadoDigitalId()+"");

                listCert.add(cert);
            } else {
                List<EPersonaCertificadoDigital> listPersCert= personaCertRepo.getCertificadoByPersona(personaId.toString());
                if (listPersCert == null )
                    throw new ServiceException(ServiceExceptionCodes.NO_EXISTE_USO_CERT,"No existe certificado con el uso asociado a la persona [personaId:"+personaId.toString()+"]");

                for (EPersonaCertificadoDigital persCert: listPersCert) {
                    ECertificadoDigital cert = getCertificadoEntity(UUID.fromString(persCert.getCertificadoDigitalId()), trxId);

                    if (cert == null )
                        throw new ServiceException(ServiceExceptionCodes.NO_EXISTE_CERTIFICADO,"No existe el certificado [certificadoId:"+persCert.getCertificadoDigitalId()+"]");
                    listCert.add(cert);
                }
            }

            log.debug("{} END", trxId);
            return listCert;
        } catch (Exception e) {
            ServiceException fex=ServiceException.assignException(e);
            log.error("{} {}", trxId, fex.getErrorLog());
            log.debug("{} END", trxId);
            throw fex;
        }

    }

    public Boolean verificaCertificado(UUID certificadoId, String password, UUID trxId) throws ServiceException {
        log.debug("{} START", trxId);
        log.debug("{} PARAM certificadoId: {}", trxId, certificadoId);
        log.trace("{} PARAM password     : {}", trxId, password);
        try {
            CertificadoDigital cert = new CertificadoDigital();

            log.debug("{} Validando que exsita el certificado", trxId);
            ECertificadoDigital certData = getCertificadoEntity(certificadoId, trxId);

            if (certData == null || certData.getDataEncode64Val() == null)
                throw new ServiceException(ServiceExceptionCodes.NO_EXISTE_CERTIFICADO);


            // Abre el certificado y extra la infomación basica
            cert.loadCertificado(certData.getDataEncode64Val(), "", password, trxId.toString());

            if (cert.getFechaExpiracion().after(new Date())) {
                throw new ServiceException(ServiceExceptionCodes.EXPIRADO);
            }

            String token= dteSiiSrv.getSiiToken(certificadoId,password, trxId);

            if (token==null)
                throw new ServiceException(ServiceExceptionCodes.INVALIDO);

            log.debug("{} END", trxId);
            return true;
        } catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException e) {
            log.warn("{} {}", trxId, e);
            return false;
        } catch (Exception e) {
            ServiceException se=ServiceException.assignException(e);
            log.error("{} {}", trxId, se.getErrorLog());
            log.debug("{} END", trxId);
            throw se;
        }
    }


    //------------------------------------------------------------------------------------------------------------------
    /**
     * Metodo que elimina un certificado
     *
     * @param certificadoId     Identificacion del cerdificado (UUID)
     * @param trxId             Identificacion de la transaccion (UUID)
     * @return                  Boolean
     * @throws ServiceException ServiceException
     */
    public Boolean delCertificado(UUID certificadoId, UUID trxId) throws ServiceException {
        log.debug("{} START", trxId);
        log.debug("{} PARAM certificadoId: {}", trxId, certificadoId);
        try {
            CertificadoDigital cert = new CertificadoDigital();

            log.debug("{} Validando que exsita el certificado", trxId);
            ECertificadoDigital certData = getCertificadoEntity(certificadoId, trxId);

            if (certData == null || certData.getDataEncode64Val() == null)
                throw new ServiceException(ServiceExceptionCodes.NO_EXISTE_CERTIFICADO);

            log.debug("{} Eliminando el certificado", trxId);

            List<EPersonaCertificadoDigital> listpersCert = personaCertRepo.getCertificadoById(certData.getId());

            for(EPersonaCertificadoDigital e: listpersCert){
                EPersonaCertificadoDigitalPK pk = new EPersonaCertificadoDigitalPK();
                pk.setPersonaId(e.getPersonaId());
                pk.setCertificadoDigitalId(e.getCertificadoDigitalId());
                personaCertRepo.delete(pk);
            }

            certRepo.delete(certData.getId());

            log.debug("{} END", trxId);
            return true;
        } catch (Exception e) {
            ServiceException se=ServiceException.assignException(e);
            log.error("{} {}", trxId, se.getErrorLog());
            log.debug("{} END", trxId);
            throw se;
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    /**
     * Metodo que buscar certificados
     *
     * @param trxId             Identificacion de la transaccion (UUID)
     * @return                  Boolean
     * @throws ServiceException ServiceException
     */
    public List<ECertificadoDigital> buscarCertificado(Boolean expirado, UUID trxId) throws ServiceException {
        log.debug("{} START", trxId);
        log.debug("{} expirado: {}", trxId, expirado);
        try {

//            List<ECertificadoDigital> listCert =  new ArrayList<>();
//            for (ECertificadoDigital c: certRepo.findAll()){
//                listCert.add(c);
//            }

            List<ECertificadoDigital> listcert= Lists.newArrayList(certRepo.findAll());
            log.debug("{} Encontrados: {}", trxId,listcert!=null?listcert.size():0 );
            log.debug("{} END", trxId);
            return listcert;
        } catch (Exception e) {
            ServiceException se=ServiceException.assignException(e);
            log.error("{} {}", trxId, se.getErrorLog());
            log.debug("{} END", trxId);
            throw se;
        }
    }

}
