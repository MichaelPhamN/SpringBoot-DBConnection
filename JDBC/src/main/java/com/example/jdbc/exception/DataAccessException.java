package com.example.jdbc.exception;

public class DataAccessException extends RuntimeException{
    public DataAccessException(String description) {
        super(description);
    }
}
