package com.dbthor.domain.certificado.cliente.mail;

import com.dbthor.domain.certificado.cliente.mail.entity.MailMessage;
import com.dbthor.domain.certificado.entity.RestCall;
import com.dbthor.domain.certificado.exception.ServiceException;
import com.dbthor.domain.certificado.exception.ServiceExceptionCodes;
import com.dbthor.tools.JsonTools;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.log4j.Log4j2;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Log4j2
public class ClienteMailService {
    private static String urlEnviarMail;

    @Value("${url.srv.mail.enviar.post}")
    public void setUrlEnviarMail(String urlEnviarMail) {
        this.urlEnviarMail = urlEnviarMail;
    }



    //<editor-fold defaultstate="collapsed" desc="SERVICIO ENVIO MAIL">

    public static MailMessage enviarCorreo(String casilla, MailMessage message, UUID trxId) throws ServiceException {
        log.debug("{} START ENVIAR CORREO", trxId);
        RestCall<MailMessage> restCall = new RestCall<>();


        try {
            URIBuilder url = new URIBuilder();
            url.setPath(urlEnviarMail.replace("{casilla}", casilla));
            url.addParameter("trxId", trxId.toString());
            String jsonResponse = restCall.callPost(url, message, trxId);
//            log.trace("{} RESPONSE {} ", trxId, jsonResponse);
            MailMessage response = JsonTools.getObjectMapperOmit().readValue(jsonResponse, new TypeReference<MailMessage>() {
            });
            log.trace("{} END EXISTE CLIENTE", trxId);
            return response;
        } catch (Exception e) {
            ServiceException fex = (e instanceof ServiceException ? (ServiceException) e : new ServiceException(ServiceExceptionCodes.ERROR_NO_ADMINISTRADO, e));
            log.error("{} {}", trxId, fex.getErrorLog());
            log.debug("{} END EXISTE CLIENTE", trxId);
            throw fex;
        }
    }
    //</editor-fold>

}
