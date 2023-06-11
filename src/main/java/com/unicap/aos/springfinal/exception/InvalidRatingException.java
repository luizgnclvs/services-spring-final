package com.unicap.aos.springfinal.exception;

import com.unicap.aos.springfinal.exception.model.InvalidRequestException;

public class InvalidRatingException extends InvalidRequestException {
    public InvalidRatingException(String message) {
        super(message);
    }
}
