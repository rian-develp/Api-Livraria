package com.example.bookstoreapi.exceptions;

public class DataExistsException extends Exception{
    public DataExistsException(String message) {
        super(message);
    }
}
