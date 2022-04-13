package com.revature.project2.exception;

public class FailedLoginException extends Exception{
    public FailedLoginException() {
    }

    public FailedLoginException(String message) {
        super(message);
    }
}
