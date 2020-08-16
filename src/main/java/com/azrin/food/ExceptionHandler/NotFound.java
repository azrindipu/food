package com.azrin.food.ExceptionHandler;

public class NotFound extends RuntimeException {
    public NotFound(String message) {
        super(message);
    }
}
