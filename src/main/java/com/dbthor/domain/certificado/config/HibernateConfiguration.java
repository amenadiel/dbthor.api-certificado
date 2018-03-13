package com.dbthor.domain.certificado.config;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.autoconfigure.transaction.TransactionManagerCustomizers;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.sql.DataSource;
import java.util.Map;

/**
 * Configuración Hibernate, pata la intercepción de codigo SQL
 *
 * Created by csattler on 25/04/2017.
 */
@Configuration
public class HibernateConfiguration extends HibernateJpaAutoConfiguration {


    private final HibernateInterceptor userInterceptor;


    @Autowired
    public HibernateConfiguration(DataSource dataSource, JpaProperties jpaProperties, ObjectProvider<JtaTransactionManager> jtaTransactionManager, ObjectProvider<TransactionManagerCustomizers> transactionManagerCustomizers, HibernateInterceptor userInterceptor) {
        super(dataSource, jpaProperties, jtaTransactionManager, transactionManagerCustomizers);
        this.userInterceptor = userInterceptor;
    }


    @Override
    protected void customizeVendorProperties(Map<String, Object> vendorProperties) {
        vendorProperties.put("hibernate.ejb.interceptor",userInterceptor);
        //vendorProperties.put("hibernate.ejb.interceptor",HibernateInterceptor.class);
    }
}