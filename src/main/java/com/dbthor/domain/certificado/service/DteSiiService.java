package com.dbthor.domain.certificado.service;

import com.dbthor.domain.certificado.entity.RestCall;
import com.dbthor.domain.certificado.entity.persona.EIdentificacion;
import com.dbthor.domain.certificado.exception.ServiceException;
import com.dbthor.tools.JsonTools;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Log4j2
public class DteSiiService {

    @Value("${url.srv.dte.sii.token.get}")  private String getDteSiiTokenUrl;

    public String getSiiToken(UUID certId, String passwd, UUID trxId) throws ServiceException {
        log.debug("{} START", trxId);
        log.debug("{} PARAM certId : {}", trxId, certId);

        RestCall<String> restCall =  new RestCall<>();
        try {
            URIBuilder url = new URIBuilder();
            url.setPath(getDteSiiTokenUrl);
            url.addParameter("certId", certId.toString());
            url.addParameter("password", passwd);
            url.addParameter("trxId", trxId.toString());

            log.debug("{} WS URL: {}", trxId, url.toString());

            String jsonResponse = restCall.callGet(url,trxId);

            ObjectMapper objectMapper =  JsonTools.getObjectMapperOmit();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);

            String response = objectMapper.readValue(jsonResponse, new TypeReference<String>() {});

            log.debug("{} END", trxId);
            return response;
        } catch (Exception e) {
            ServiceException fex=ServiceException.assignException(e);
            log.error("{} {}", trxId, fex.getErrorLog());
            log.debug("{} END", trxId);
            throw fex;
        }
    }
}
