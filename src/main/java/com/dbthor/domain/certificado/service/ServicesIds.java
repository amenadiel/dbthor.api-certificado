package com.dbthor.domain.certificado.service;

public enum ServicesIds {

    UNDEFINED(0L),
    TestService(1L),
    t2(2L);
    /*id correspondiente al servicio*/
    Long id;
    ServicesIds(Long id){
        this.id=id;
    }

    public Long getId() {
        return id;
    }
}
