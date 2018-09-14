package com.dbthor.domain.certificado.data;

import com.dbthor.domain.certificado.data.entity.ECertificadoDigitalHistorico;
import com.dbthor.domain.certificado.data.entity.ECertificadoDigitalHistoricoPK;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICertificadoDigitalHistoricoRepository extends CrudRepository<ECertificadoDigitalHistorico,ECertificadoDigitalHistoricoPK> {
}
