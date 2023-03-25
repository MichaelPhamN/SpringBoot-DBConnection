package com.example.jdbc.exception;

public class DataNotFoundException extends RuntimeException{
    public DataNotFoundException(String description) {
        super(description);
    }
}
