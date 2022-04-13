package com.revature.project2.exception;

public class MissingTokenHeaderException extends Exception {
    public MissingTokenHeaderException() {
    }

    public MissingTokenHeaderException(String message) {
        super(message);
    }
}
