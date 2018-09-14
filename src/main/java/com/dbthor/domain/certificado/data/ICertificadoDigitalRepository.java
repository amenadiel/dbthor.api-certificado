package com.dbthor.domain.certificado.data;

import com.dbthor.domain.certificado.data.entity.ECertificadoDigital;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Entidad que representa al certificado digital
 *
 * Created by CSATTLER on 07-02-2017.
 */
@Repository
public interface ICertificadoDigitalRepository extends CrudRepository<ECertificadoDigital, String> {
}