package com.foodorderingapp.exception;

public class UnauthorizedExceptionHandler extends  RuntimeException{
    public UnauthorizedExceptionHandler(String message) {
        super(message);
    }
}
