package com.azrin.food.ExceptionHandler;

public class BadRequest extends RuntimeException {
    public BadRequest(String message){
        super(message);
    }
}
