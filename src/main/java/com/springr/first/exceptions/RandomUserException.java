package com.springr.first.exceptions;

public class RandomUserException extends RuntimeException {


    public RandomUserException() {

    }

    public RandomUserException(String message) {
        super(message);
    }

    public RandomUserException(Throwable cause) {
        super(cause);
    }

    public RandomUserException(String message, Throwable cause) {
        super(message, cause);
    }


}

