package com.revature.project2.exception;

public class FailedDeleteException extends RuntimeException{
    public FailedDeleteException(String message) {
        super(message);
    }
}
