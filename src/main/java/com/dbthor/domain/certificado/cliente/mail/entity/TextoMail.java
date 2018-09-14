package com.dbthor.domain.certificado.cliente.mail.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class TextoMail {
    @Getter
    @Setter
    private String titulo;
    @Getter
    @Setter
    private String texto;
    @Getter
    @Setter
    private List<String> listVar;


    @Override
    public String toString() {
        return "TextoMail{" +
                "titulo='" + titulo + '\'' +
                ", texto='" + texto + '\'' +
                ", listVar=" + listVar +
                '}';
    }
}
