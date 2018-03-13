package com.dbthor.domain.certificado.request;

import lombok.Getter;
import lombok.Setter;

public class ValidarCertificadoRequest {
    @Getter
    @Setter
    private String certificadoId;
    @Getter
    @Setter
    private String contrasenna;


}
