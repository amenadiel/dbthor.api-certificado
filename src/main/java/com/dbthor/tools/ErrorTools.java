package com.dbthor.tools;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;

/**
 * Libreria de metodos para el manejo de errores
 *
 * Created by CSATTLER on 17-01-2017.
 */
@SuppressWarnings("unused")
public class ErrorTools {

    public static String getStackTrace(Throwable t) {
        if (t ==null) return null;
        StringWriter sw = new StringWriter();
        t.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }

    public static String getErrorCode(Throwable t) {
        if (t instanceof SQLException) {
            return "SQL"+String.valueOf (((SQLException) t).getErrorCode());
        } else {
            return "UNKNOW";
        }
    }
    
    public static String getErrorShortDesc(Throwable t) {
        return t.toString();
    }

    public static String getCurrentClassAndMethodNames() {
        final StackTraceElement e = Thread.currentThread().getStackTrace()[2];
        final String s = e.getClassName();
        return s.substring(s.lastIndexOf('.') + 1, s.length()) + "." + e.getMethodName();
    }
}
