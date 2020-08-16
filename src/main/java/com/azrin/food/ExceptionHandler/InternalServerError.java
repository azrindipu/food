package com.azrin.food.ExceptionHandler;

public class InternalServerError extends RuntimeException {
    public InternalServerError(String message){
        super(message);
    }
}
