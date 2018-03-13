package com.dbthor.domain.certificado.data;

import com.dbthor.domain.certificado.data.entity.ECertificadoLog;
import com.dbthor.domain.certificado.data.entity.ECertificadoLogPK;
import org.springframework.data.repository.CrudRepository;

public interface ICertificadoLogRepository extends CrudRepository<ECertificadoLog, ECertificadoLogPK> {


}
