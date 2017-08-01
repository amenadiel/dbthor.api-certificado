package com.dbthor.domain.certificado.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * Clase de registro de Error de Spring WebServices
 *
 * Created by chris on 18-07-2017.
 */
@NoArgsConstructor
@ToString
public class SpringRestError {
    @Getter @Setter private Date timestamp;
    @Getter @Setter private Integer status;
    @Getter @Setter private String error;
    @Getter @Setter private String message;
    @Getter @Setter private String path;
}
