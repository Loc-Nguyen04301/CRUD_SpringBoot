package com.example.CRUD_Post.exception;

public class ResouceNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ResouceNotFoundException(String message) {
        super(message);
    }
}
