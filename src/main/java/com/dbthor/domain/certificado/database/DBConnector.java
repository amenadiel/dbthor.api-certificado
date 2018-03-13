package com.dbthor.domain.certificado.database;

/**
 * Created by CSATTLER on 17-12-2015.
 */
import com.dbthor.domain.certificado.exception.ConnException;

import com.dbthor.tools.DateTools;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.*;
import java.util.ArrayList;

@Log4j2
@Component
//@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class DBConnector {

    @PersistenceContext
    private EntityManager em;
    @Autowired
    private PlatformTransactionManager platformTransactionManager;
    private TransactionStatus status = null;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Connection conn = null;
    private String _PortStr = "";
    @Value("${spring.datasource.url}")
    public String _Server;
    @Value("${spring.datasource.username}")
    public String _User;
    @Value("${spring.datasource.password}")
    public String _Pwd;
    @Value("${spring.datasource.url.dbthor}")
    public String _defaultDB;
    @Value("${spring.datasource.url.dte}")
    public String _defaultDBDte;
    public String actDb;
    private String _ConnUrl;
    public static String dbThorDataBase;


    @Value("${spring.datasource.url.dbthor}")
    public void setDbThorDataBase(String dbThorDataBase) {
        this.dbThorDataBase = dbThorDataBase;
    }

    public DBConnector() throws ConnException {

    }

    @PostConstruct
    private void init() throws ConnException {
        DBConnectorInit(_Server, _User, _Pwd);
    }

    private void DBConnectorInit(String Server, String User, String Pwd) throws ConnException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            if (!_Server.contains(":3306")) {
                _PortStr = ":3306";
            }
            if (_Server.indexOf(":3306") > 0) {
                _ConnUrl = _Server.substring(0, _Server.indexOf(":3306") + 5) + _PortStr + "/{db}?autoReconnect=true&zeroDateTimeBehavior=convertToNull";
            } else {
                _ConnUrl = _Server + _PortStr + "/{db}?autoReconnect=true&zeroDateTimeBehavior=convertToNull";
            }

        } catch (Exception e) {
            log.error("ERROR AL INICIAR LA CONFIGURACION DE LA BASE DE DATOS  :- {}", e.getMessage());
            throw new ConnException(e);
        }
    }


    /**
     * buscaremos en tabla dbthor.empresas  la base de datos correspondiente al factoring
     *
     * @param rut_factoring
     * @throws ConnException
     */
    public void open(Long rut_factoring) throws ConnException {
        try {
            log.trace("OPEN CON FACTORING {}", rut_factoring);
            String facFb = getDbFactoring(rut_factoring);
            open(facFb);
        } catch (SQLException e) {
            throw new ConnException(e);
        }
    }

    @Deprecated
    private String getDbFactoringConn(long rut_factoring) throws SQLException {
        open(_defaultDB);
        String query = "SELECT rutempresa, basedatos, estado FROM " + _defaultDB + ".empresa WHERE rutempresa= " + rut_factoring + " LIMIT 1";
        log.trace(query);
        PreparedStatement stmt = prepareStatement(query);
//        stmt.setLong(1, rut_factoring);
        ResultSet rs = stmt.executeQuery();
        String db = null;
        if (rs.next()) {
            rs.getLong("rutempresa");
            if (rs.getInt("estado") == 1) {
                db = rs.getString("basedatos");
            } else {
                close();
                throw new ConnException("DATA BASE NO ACTIVA  :::::  " + rut_factoring + "  :::::::  " + rs.getString("basedatos"));
            }
        } else {
            close();
            throw new ConnException("DATA BASE NO FOUND  :::::  " + rut_factoring + "  :::");
        }
        close();
        return db;
    }

    public String getDbFactoring(long rut_factoring) throws SQLException {
        log.trace("Abriendo coneccion para el factoring {}", rut_factoring);
        Connection connec = jdbcTemplate.getDataSource().getConnection();

        log.trace("coneccion abiertapara el factoring {}", rut_factoring);
        try {
//            open(_defaultDB);
            String query = "SELECT rutempresa, basedatos, estado FROM " + _defaultDB + ".empresa WHERE rutempresa= " + rut_factoring + " LIMIT 1";
            log.trace(query);
            PreparedStatement stmt = connec.prepareStatement(query);
//        stmt.setLong(1, rut_factoring);
            ResultSet rs = stmt.executeQuery();
            String db = null;
            if (rs.next()) {
                rs.getLong("rutempresa");
                if (rs.getInt("estado") == 1) {
                    db = rs.getString("basedatos");
                } else {
                    connec.close();
                    throw new ConnException("DATA BASE NO ACTIVA  :::::  " + rut_factoring + "  :::::::  " + rs.getString("basedatos"));
                }
            } else {
                connec.close();
                throw new ConnException("DATA BASE NO FOUND  :::::  " + rut_factoring + "  :::");
            }
//            HibernateInterceptor.setTenantName(db);
            return db;
        } finally {
            if (connec != null && !connec.isClosed()) {
                connec.close();
            }
        }
    }

    public void open() throws ConnException {
        open(_defaultDB);
    }


    public void open(String _db) throws ConnException {
        try {
            if (isNotClosed()) {

                DefaultTransactionDefinition param = new DefaultTransactionDefinition();
                status = platformTransactionManager.getTransaction(param);
                actDb = _db;
                String urlconn = _ConnUrl.replace("{db}", _db);
                log.debug("DriverManager.getConnection [ConnUrl=" + urlconn + " , User=" + _User + "");
//                conn = DriverManager.getConnection(urlconn, _User, _Pwd);
                conn = jdbcTemplate.getDataSource().getConnection();
                conn.setCatalog(_db);
            } else {
                log.trace("Is allready open");
                conn.setCatalog(_db);
            }
            conn.setAutoCommit(false);

        } catch (Exception e) {
            log.error("ERROR AL INICIAR LA CONFIGURACION DE LA BASE DE DATOS  :- {}", e.getMessage());
            throw new ConnException(e);
        }
    }

    public void rolback() {
        try {
            if (!isNotClosed()) {
                log.warn("ROLLBACK");
                conn.rollback();
                if (!status.isCompleted()) {
                    platformTransactionManager.rollback(status);
                }

            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        } catch (org.springframework.transaction.IllegalTransactionStateException e) {
        }
    }

    public void close() {
        close(conn);
    }

    public void close(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) {
                log.trace("TRY CONN CLOSE");
                conn.commit();
                conn.close();
                if (!status.isCompleted() && !status.isRollbackOnly()) {
                    platformTransactionManager.commit(status);
                }
                log.trace("CONN CLOSE");
            } else {
                log.trace("CONN IS CLOSED");
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        } catch (org.springframework.transaction.IllegalTransactionStateException e) {
            e.printStackTrace();
        } finally {
            conn = null;
        }
    }

    /**
     * Metodo retorna true si la coneccion es nula o se encuentra cerrada
     *
     * @return
     * @throws SQLException
     */
    public boolean isNotClosed() throws SQLException {
        return !(conn != null && !conn.isClosed());
    }

    public PreparedStatement prepareStatement(String query) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(query);
        return stmt;
    }

    public PreparedStatement prepareStatement(String query, int returnGeneratedKeys) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(query, returnGeneratedKeys);
        return stmt;
    }

    public Boolean execute(String qry) throws SQLException {
        PreparedStatement stmt = prepareStatement(qry);
        stmt.execute();
        stmt.close();
        return true;
    }

    public CallableStatement prepareCall(String qry) throws SQLException {
        return conn.prepareCall(qry);
    }
/*
    public ArrayList<FactoringDB> getListadoFactoringDBThorActivos() throws SQLException {
        log.debug("getListadoFactoringDBThor() - Cargando listado de Factotings");
        PreparedStatement stmt = prepareStatement("SELECT rutempresa, basedatos,nombre FROM " + DBConnector.dbThorDataBase + ".empresa WHERE estado = 1");
        return getListadoFactoringDBThor(stmt);
    }

    public ArrayList<FactoringDB> getListadoFactoringDBThorActivosSeguimiento() throws SQLException {
        log.debug("getListadoFactoringDBThor() - Cargando listado de Factotings");
        PreparedStatement stmt = prepareStatement("SELECT rutempresa, basedatos,nombre FROM " + DBConnector.dbThorDataBase + ".empresa WHERE estado = 1 and activo_seguimiento = 1 ");
        return getListadoFactoringDBThor(stmt);
    }


    public ArrayList<FactoringDB> getListadoFactoringDBThor(PreparedStatement stmt) throws SQLException {
        log.trace("getListadoFactoringDBThor() - SQL:{}", stmt);
        ResultSet rst = stmt.executeQuery();

        ArrayList<FactoringDB> listado = new ArrayList();

        while (rst.next()) {
            FactoringDB db = new FactoringDB(
                    rst.getLong("rutEmpresa")
                    , rst.getString("basedatos")
                    , rst.getString("nombre")
            );
            log.trace("Factoring Item {}", db.toString());
            listado.add(db);
        }

        return listado;
    }
*/

    public String[] getEmailsNotificatios(Long notificacionId) throws SQLException, ConnException {
        ArrayList<String> correos = new ArrayList<String>();
        PreparedStatement pst = prepareStatement("select  *  from " + DBConnector.dbThorDataBase + ".sis_notificacion_services_email where id_service = ? and activo = 1");
        pst.setLong(1, notificacionId);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            correos.add(rs.getString("correo"));
        }
        return (String[]) correos.toArray(new String[0]);
    }

    public void setValue(PreparedStatement ps, int index, String value) throws SQLException {
        if (value == null) {
            ps.setNull(index, Types.VARCHAR);
        } else {
            ps.setString(index, value);
        }
    }

    public void setValue(PreparedStatement ps, int index, Double value) throws SQLException {
        ps.setDouble(index, value != null ? value : 0);
    }

    public void setValue(PreparedStatement ps, int index, Long value) throws SQLException {
        ps.setLong(index, value != null ? value : 0);
    }

    public void setValue(PreparedStatement ps, int index, Integer value) throws SQLException {
        ps.setInt(index, value != null ? value : 0);
    }

    public void setValue(PreparedStatement ps, int index, Short value) throws SQLException {
        ps.setShort(index, value != null ? value : 0);
    }

    public void setValue(PreparedStatement ps, int index, Date value) throws SQLException {
        if (value == null) {
            ps.setNull(index, Types.DATE);
        } else {
            ps.setDate(index, value);
        }
    }

    public void setValue(PreparedStatement ps, int index, java.util.Date value) throws SQLException {
        if (value == null) {
            ps.setNull(index, Types.DATE);
        } else {
            ps.setDate(index, DateTools.convertUtil2SqlDate(value));
        }
    }

}