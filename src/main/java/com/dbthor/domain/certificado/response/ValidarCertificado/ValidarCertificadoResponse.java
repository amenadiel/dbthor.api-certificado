package com.dbthor.domain.certificado.response.ValidarCertificado;

import com.dbthor.domain.certificado.data.entity.ECertificadoDigital;
import com.dbthor.domain.certificado.response.CargarCertificado.CargarCertificadoResponseCodes;
import com.dbthor.domain.certificado.response.CargarCertificado.CargarCertificadoServiceResponse;
import lombok.Getter;
import lombok.Setter;

public class ValidarCertificadoResponse extends  ValidarCertificadoServiceResponse<ValidarCertificadoResponseCodes>{

    @Getter
    @Setter
    Boolean respuesta;


    public ValidarCertificadoResponse(ValidarCertificadoResponseCodes code) {
        super(code);
    }
    public ValidarCertificadoResponse() {
        super(ValidarCertificadoResponseCodes.ERR_0);
    }
}
