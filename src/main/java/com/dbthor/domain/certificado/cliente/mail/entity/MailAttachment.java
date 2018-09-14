package com.dbthor.domain.certificado.cliente.mail.entity;

import lombok.Getter;
import lombok.Setter;

public class MailAttachment {
    @Getter
    @Setter
    private String archivo;
    @Getter @Setter private String dataEncodeB64;
    @Getter @Setter private String tipo;

    public MailAttachment() {}
}
