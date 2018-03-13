package com.dbthor.domain.certificado.request;

import lombok.Getter;
import lombok.Setter;

public class CargarCertificadoRequest {
    @Getter
    @Setter
    private String certificadoBase64;
    @Getter
    @Setter
    private String contrasenna;


}
