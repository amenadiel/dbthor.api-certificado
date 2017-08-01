package com.dbthor.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Libreria de metodos para el manejo del fechas
 *
 * Created by CSATTLER on 12-01-2017.
 */
@SuppressWarnings("unused")
public class DateTools {

    public static Date String2Date(String valor, String formato)  {
        SimpleDateFormat formatter = new SimpleDateFormat(formato);

        try {
            return formatter.parse(valor);
        } catch (ParseException e) {
            return null;
        }
    }

    public static String Date2String(Date valor, String formato) {
        SimpleDateFormat formatter = new SimpleDateFormat(formato);
        return formatter.format(valor);
    }

    public static java.util.Date convertSql2UtilDate(
            java.sql.Date sqlDate) {
        java.util.Date javaDate = null;
        if (sqlDate != null) {
            javaDate = new Date(sqlDate.getTime());
        }
        return javaDate;
    }

    public static java.sql.Date convertUtil2SqlDate(
            java.util.Date utilDate) {

        java.sql.Date sqlDate = null;
        if (utilDate != null) {
            sqlDate = new java.sql.Date(utilDate.getTime());
        }
        return sqlDate;
    }

    public static java.sql.Timestamp convertUtil2SqlTimestamp(
            java.util.Date utilDate) {
        return  new java.sql.Timestamp(utilDate.getTime());
    }

    public static java.sql.Date convertSqlTimestamp2Date(
            java.sql.Timestamp stamp) {
        return  new java.sql.Date(stamp.getTime());
    }
}
