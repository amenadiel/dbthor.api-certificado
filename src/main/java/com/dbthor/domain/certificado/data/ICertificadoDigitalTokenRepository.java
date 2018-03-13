package com.dbthor.domain.certificado.data;

import com.dbthor.domain.certificado.data.entity.ECertificadoDigitalToken;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by CSATTLER on 15-02-2017.
 */
public interface ICertificadoDigitalTokenRepository extends CrudRepository<ECertificadoDigitalToken, String> {

}
