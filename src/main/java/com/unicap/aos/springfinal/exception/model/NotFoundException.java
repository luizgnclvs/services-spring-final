package com.unicap.aos.springfinal.exception.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
    public NotFoundException(String object, long id) {
        super(String.format("%s de ID: {{ %d }} não foi encontrado(a).", object, id));
    }
}
