package com.springr.first.exceptions;

// ha van valami kivétel amit le kell kezelni, akkor azt így lehet
public class StorageException extends RuntimeException {

    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
