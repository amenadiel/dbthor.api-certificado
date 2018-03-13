package com.dbthor.domain.certificado.response.Token;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by CMIRANDA on 01-08-2017.
 */
public class EToken {
    public EToken(String token) {
        this.token = token;
    }

    public EToken() {

    }

    @Setter
    @Getter
    @JsonProperty("token")
    private String token;







}
