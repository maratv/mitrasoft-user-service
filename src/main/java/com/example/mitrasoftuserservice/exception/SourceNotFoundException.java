package com.example.mitrasoftuserservice.exception;

public class SourceNotFoundException extends RuntimeException{
    public SourceNotFoundException(String message) {
        super(message);
    }
}
