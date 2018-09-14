package com.dbthor.domain.certificado.entity;

import com.dbthor.domain.certificado.cliente.mail.entity.TextoMail;
import com.dbthor.domain.certificado.database.DBConnector;
import com.dbthor.domain.certificado.service.Services;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
@Log4j2
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class IMailRepo extends Services {
    @Getter
    @Setter
    DBConnector connDb;

    @Value("${spring.datasource.url.dbthor}")
    public String _defaultDB;


    public TextoMail getTexto(Long idTexto, UUID trxId) {

        TextoMail texto = new TextoMail();
        String strsql = "";
        try {
            log.debug("{} START", trxId);
            log.debug("{} PARAM idTexto: {}", trxId, idTexto);

            strsql = "select titulo, texto " +
                    "from " + _defaultDB + ".legal_textosociedad_generico l " +
                    "WHERE " +
                    "l.id= ?";


            PreparedStatement statement = connDb.prepareStatement(strsql);
            int i = 1;
            connDb.setValue(statement, i++, idTexto);
            ResultSet rs = statement.executeQuery();
            log.debug("{} SQL SELECT: {}", trxId, strsql);
            while (rs.next()) {
                texto.setTitulo(rs.getString("titulo"));
                texto.setTexto(rs.getString("texto"));

                List<String> allMatches = new ArrayList<String>();
                Matcher m = Pattern.compile("(--)(.+?)(--)")
                        .matcher(texto.getTexto());
                while (m.find()) {
                    allMatches.add(m.group());
                }
                Matcher mm = Pattern.compile("(--)(.+?)(--)")
                        .matcher(texto.getTitulo());
                while (mm.find()) {
                    allMatches.add(mm.group());
                }
                texto.setListVar(allMatches.stream().distinct().collect(Collectors.toList()));
            }
            log.debug("{} listVar.count(): {}", trxId, (texto != null && texto.getListVar() != null ? texto.getListVar().size() : null));
            rs.close();
            statement.close();
            log.debug("END", trxId);
            return texto;
        } catch (Exception e) {
            log.error("{} ERR: {}", trxId, e.getMessage());
            log.error("{} ERR: {}", trxId, e);
            log.debug("{} END", trxId);
        }
        return texto;
    }


    public String getNombreFactoring(long rut_factoring) throws SQLException {
        String nombreFactoring = "";
        String query = "SELECT nombre FROM " + _defaultDB + ".empresa WHERE rutempresa= ? LIMIT 1";
        log.trace(query);
        PreparedStatement statement = connDb.prepareStatement(query);
        int i = 1;
        connDb.setValue(statement, i++, rut_factoring);
        ResultSet rs = statement.executeQuery();
        String db = null;
        if (rs.next()) {
            nombreFactoring = rs.getString("nombre");
        }
        rs.close();
        statement.close();
        return nombreFactoring;
    }

}
