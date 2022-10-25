package com.example.studentsapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EmailAlreadyTakenException extends ResponseStatusException {
    public EmailAlreadyTakenException(String message) {
        super(HttpStatus.CONFLICT, message);

    }
}
