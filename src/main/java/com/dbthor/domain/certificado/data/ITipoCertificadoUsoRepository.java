package com.dbthor.domain.certificado.data;

import com.dbthor.domain.certificado.data.entity.ETipoCertificadoUso;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by CSATTLER on 07-02-2017.
 */
public interface ITipoCertificadoUsoRepository extends CrudRepository<ETipoCertificadoUso, String> {
}