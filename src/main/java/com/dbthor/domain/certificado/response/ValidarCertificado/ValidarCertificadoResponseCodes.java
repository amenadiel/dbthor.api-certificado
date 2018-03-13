package com.dbthor.domain.certificado.response.ValidarCertificado;

public enum ValidarCertificadoResponseCodes {

    CVA(1, "CERTIFICADO VALIDO"),
    ERR_0(0, "SIN RESPUESTA"),
    BNU(00, "BODY NULO"),
    ECI(10,"ERROR - CLAVE INVALIDA"),
    ECC(20,"ERROR - CERTIFICADO CADUCADO"),
    ENT(30,"ERROR - NO SE PUDO OBTENER TOKEN SII"),
    ENE(40,"ERROR - NO EXISTE CERTIFICADO");

    private String msg;
    private int id;

    ValidarCertificadoResponseCodes(int id, String msg) {
        this.msg = msg;
        this.id = id;
    }

    ValidarCertificadoResponseCodes(String msg) {
        this.msg = msg;
        this.id = 0;
    }

    //<editor-fold defaultstate="collapsed" desc="Codigos OK">

    public ValidarCertificadoResponseCodes CVA() {
        return CVA;
    }


    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Codigos Error">

    public ValidarCertificadoResponseCodes BNU() {
        return BNU;
    }


    public ValidarCertificadoResponseCodes ECI() { return ECI; }

    public ValidarCertificadoResponseCodes ECC() {return ECC;}

    public ValidarCertificadoResponseCodes ENT() {return ENT;}
    public ValidarCertificadoResponseCodes ENE() {return ENE;}
    //</editor-fold>

    public String msg() {
        return msg;
    }


    public int id() {
        return id;
    }


    }
