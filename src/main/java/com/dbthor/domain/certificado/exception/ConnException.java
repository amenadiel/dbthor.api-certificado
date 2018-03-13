package com.dbthor.domain.certificado.exception;

import java.sql.SQLException;

public class ConnException extends SQLException{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public ConnException() {
        super();
    }

    public ConnException(String message) {
        super(message);
    }

    public ConnException(Throwable cause) {
        super(cause);
    }

    public ConnException(String message, Throwable cause) {
        super(message, cause);
    }

}
