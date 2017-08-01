package com.dbthor.domain.certificado.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

/**
 * Configuracion Servicio RESTFul
 *
 * Created by CSATTLER on 20-02-2017.
 */
@Configuration
public class RestConfig {

    @Value("${restful.timeout.connect}")    private int connectTimeOut;
    @Value("${restful.timeout.read}")       private int readTimeOut;

    @Bean
    public RestOperations createRestTemplate(ClientHttpRequestFactory clientHttpRequestFactory) {
        return new RestTemplate(clientHttpRequestFactory);
    }

    @Bean
    public ClientHttpRequestFactory createClientHttpRequestFactory() {
        HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory =  new HttpComponentsClientHttpRequestFactory();
        httpComponentsClientHttpRequestFactory.setConnectionRequestTimeout(connectTimeOut);
        httpComponentsClientHttpRequestFactory.setReadTimeout(readTimeOut);

        return httpComponentsClientHttpRequestFactory;
    }


}