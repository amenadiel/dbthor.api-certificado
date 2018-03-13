package com.dbthor.domain.certificado.data;

import com.dbthor.domain.certificado.data.entity.ECertificadoDigital;
import org.springframework.data.repository.CrudRepository;

/**
 * Entidad que representa al certificado digital
 *
 * Created by CSATTLER on 07-02-2017.
 */
public interface ICertificadoDigitalRepository extends CrudRepository<ECertificadoDigital, String> {
}