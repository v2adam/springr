package com.springr.first.exceptions;

// ha van valami kivétel amit le kell kezelni, akkor azt így lehet
public class MyRuntimeException extends RuntimeException {


    public MyRuntimeException(){

    }

    public MyRuntimeException(String message) {
        super(message);
    }

    public MyRuntimeException(Throwable cause) {
        super(cause);
    }

    public MyRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }




}
