package com.dbthor.domain.certificado.entity;

import com.dbthor.domain.certificado.database.DBConnector;
import com.dbthor.domain.certificado.service.Services;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Component
@Log4j2
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class IConsultaAutorizacion extends Services {


    @Getter
    @Setter
    DBConnector connDb;

    @Value("${spring.datasource.url.dbthor}")
    public String _defaultDB;


    public List<EClienteAutorizacion> selectAutorizacion(String rutCliente) {
        String sql = "";
        List<EClienteAutorizacion> list = new ArrayList<>();
        EClienteAutorizacion eClienteAutorizacion = new EClienteAutorizacion();
        try {
            sql = "select * from " + _defaultDB + ".cliente_autorizacion \n" +
                    "where rut_cliente = ?";

            PreparedStatement preparedStatement = connDb.prepareStatement(sql);
            int i = 1;
            connDb.setValue(preparedStatement, i++, rutCliente);
            log.debug("{} SQL SELECT AUTORIZACION: {}", trxId, preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                eClienteAutorizacion = new EClienteAutorizacion();
                eClienteAutorizacion.setRutEmpresa(resultSet.getLong("rut_empresa"));
                eClienteAutorizacion.setRutCliente(resultSet.getLong("rut_cliente"));
                eClienteAutorizacion.setTipoAplicationAutorizacionId(resultSet.getInt("tipo_aplication_autorizacion_id"));
                eClienteAutorizacion.setRutUsuario(resultSet.getLong("rut_usuario"));
                eClienteAutorizacion.setActivoInd(resultSet.getShort("activo_ind"));
                list.add(eClienteAutorizacion);
            }
            preparedStatement.close();
        } catch (SQLException e) {
            log.error("{} ERR {} {}", trxId, e.getErrorCode(), e.getMessage());
            log.debug("{} END", trxId);
        } catch (Exception e) {
            log.error("{} ERR {}", trxId, e.getMessage());
            log.debug("{} END", trxId);

        }
        return list;
    }


}
