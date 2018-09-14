package com.dbthor.domain.certificado.cliente.mail.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class MailMessage {
    @Getter
    @Setter
    List<String> listaCorreoTO =  new ArrayList<>();
    @Getter @Setter List<String> listaCorreoCC =  new ArrayList<>();
    @Getter @Setter List<String> listaCorreoBCC =  new ArrayList<>();
    @Getter @Setter String tema;
    @Getter @Setter String mensaje;
    @Getter @Setter List<MailAttachment> listAttachment =  new ArrayList<>();

    public MailMessage() {
    }

    public MailMessage(List<String> correoDestinos, String mensaje ) {
        this.listaCorreoTO=correoDestinos;
        this.mensaje = mensaje;
    }

    public void addCorreoTO(String correo) {
        listaCorreoTO.add(correo);
    }

    public void addCorreoCC(String correo) {
        listaCorreoCC.add(correo);
    }

    public void addAttchament(String archivo, String dataEncodeB64, String tipo) {
        MailAttachment attch =  new MailAttachment();

        attch.setArchivo(archivo);
        attch.setDataEncodeB64(dataEncodeB64);
        attch.setTipo(tipo);

        listAttachment.add(attch);
    }



}
