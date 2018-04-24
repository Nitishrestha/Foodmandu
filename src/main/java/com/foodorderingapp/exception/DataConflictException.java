package com.foodorderingapp.exception;

public class DataConflictException extends RuntimeException{
    public DataConflictException(String message) {
        super(message);
    }
}
