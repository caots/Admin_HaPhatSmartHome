package com.bksoftware.sellingweb.exception;

public class BadRequestEx extends RuntimeException {

    private String message;

    public BadRequestEx(String message) {
        super(message);
        this.message = message;
    }
}
