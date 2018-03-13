package com.dbthor.domain.certificado.config;

import lombok.extern.log4j.Log4j2;
import org.hibernate.EmptyInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Permite la intercecion del codigo SQL
 *
 * Created by csattler on 25/04/2017.
 */
@Log4j2
@Component
public class HibernateInterceptor extends EmptyInterceptor {

    @Value("${db.domain.certificado.schemaCatalog}") String certificadoSchemaCatalog;

    @Override
    public String onPrepareStatement(String sql) {
        String prepedStatement = super.onPrepareStatement(sql);
        log.trace("HIBERNATE BEFORE: {}",prepedStatement );

        //schemaCatalogCertificado
        prepedStatement = replaceCatalogSchema(prepedStatement, "certificado",certificadoSchemaCatalog );

        log.trace("HIBERNATE AFTHER: {}",prepedStatement );
        return prepedStatement;
    }

    @SuppressWarnings("SameParameterValue")
    private static String replaceCatalogSchema(String statement, String key, String value) {
        if (statement.contains(" "+key+"_catalog."))
            statement = statement.replaceAll(" "+key+"_catalog.", " "+value+".");
        else if (statement.contains(" "+key+"_catalog."+key+"_schema."))
            statement = statement.replaceAll(" "+key+"_catalog."+key+"_schema.", " "+value+".");
        return statement;
    }
}
