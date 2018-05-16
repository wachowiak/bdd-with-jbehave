package org.wachowiak.bdd.server.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

    private static final long serialVersionUID = -5362554398280413634L;

    public BadRequestException(String message) {
        super(message);
    }
}
