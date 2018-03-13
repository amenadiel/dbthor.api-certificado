package com.dbthor.domain.certificado.response.CargarCertificado;

public enum CargarCertificadoResponseCodes {

    CGR(1, "CERTIFICADO GUARDADO"),
    ERR_0(0, "SIN RESPUESTA"),
    BNU(00, "BODY NULO"),
    EGC(10,"ERROR - AL GUARDAR CERTIFICADO"),
    ECI(20,"ERROR - CLAVE INVALIDA"),
    ECC(30,"ERROR - CERTIFICADO CADUCADO"),
    ENT(40,"ERROR - NO SE PUDO OBTENER TOKEN SII");

    private String msg;
    private int id;

    CargarCertificadoResponseCodes(int id, String msg) {
        this.msg = msg;
        this.id = id;
    }

    CargarCertificadoResponseCodes(String msg) {
        this.msg = msg;
        this.id = 0;
    }

    //<editor-fold defaultstate="collapsed" desc="Codigos OK">

    public CargarCertificadoResponseCodes CGR() {
        return CGR;
    }


    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Codigos Error">

    public CargarCertificadoResponseCodes BNU() {
        return BNU;
    }

    public CargarCertificadoResponseCodes EGC() { return EGC; }

    public CargarCertificadoResponseCodes ECI() { return ECI; }

    public CargarCertificadoResponseCodes ECC() {return ECC;}

    public CargarCertificadoResponseCodes ENT() {return ENT;}
    //</editor-fold>

    public String msg() {
        return msg;
    }


    public int id() {
        return id;
    }


    }
