package com.springr.first.exceptions;

// unckecked
// nem kiszámítható, pl túl nagy a file, és nem fér el a mappában

// checked exception
// akkor, ha van valamilyen scenario a hiba esetén pl. nem jó jelszó -> PasswordNotValidMyException


// If a client can reasonably be expected to recover from an exception, make it a checked exception.
// If a client cannot do anything to recover from the exception, make it an unchecked exception
public class StorageException extends RuntimeException {

    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
