package com.springr.first.exceptions;

// ha van valami kivétel amit le kell kezelni, akkor azt így lehet
public class XlsProcessException extends RuntimeException {

    public XlsProcessException(String message) {
        super(message);
    }

    public XlsProcessException(String message, Throwable cause) {
        super(message, cause);
    }
}
