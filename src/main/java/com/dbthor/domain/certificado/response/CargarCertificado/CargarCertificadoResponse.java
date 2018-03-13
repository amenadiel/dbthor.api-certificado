package com.dbthor.domain.certificado.response.CargarCertificado;

import com.dbthor.domain.certificado.data.entity.ECertificadoDigital;
import lombok.Getter;
import lombok.Setter;

public class CargarCertificadoResponse extends  CargarCertificadoServiceResponse<CargarCertificadoResponseCodes>{

    @Getter
    @Setter
    ECertificadoDigital certificadoDigital;


    public CargarCertificadoResponse(CargarCertificadoResponseCodes code) {
        super(code);
    }
    public CargarCertificadoResponse() {
        super(CargarCertificadoResponseCodes.ERR_0);
    }
}
