package com.unicap.aos.springfinal.exception;

import com.unicap.aos.springfinal.exception.model.NotFoundException;

public class RatingNotFoundException extends NotFoundException {
    public RatingNotFoundException(long id) {
        super("Avaliação", id);
    }
}
