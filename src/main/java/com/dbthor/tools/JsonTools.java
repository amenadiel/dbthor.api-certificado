package com.dbthor.tools;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.hateoas.hal.Jackson2HalModule;

/**
 * Clase JsonTools
 *
 * @author Claudio Miranda Pizarro
 * @version 1.0
 * @since 05-10-2017 16:25
 */
public class JsonTools {

    public static ObjectMapper getObjectMapperOmit() {
        ObjectMapper objectMapper = new /*Para que no nos encuentre este metodo cuando refactorice*/ObjectMapper();
        objectMapper.registerModule(new Jackson2HalModule());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }

}
