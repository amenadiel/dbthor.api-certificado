package com.dbthor.domain.certificado.service;

import com.dbthor.domain.certificado.data.entity.EServUsuarios;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import java.util.UUID;


@Log4j2
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Services {


    @Getter
    @Setter
    protected ServicesIds servId;
    @Getter
    @Setter
    protected Long id;
    @Getter
    @Setter
    protected UUID trxId;

    @Setter
    @Getter
    @JsonProperty("usuarioServ")
    @JsonPropertyDescription("Representa al usuario que esta consumiendo el servicio")
    protected EServUsuarios usuarioServ;

    public Services(UUID trxId) {
        this.trxId = trxId;
    }

    public Services() {
        servId = ServicesIds.UNDEFINED;
    }
}
