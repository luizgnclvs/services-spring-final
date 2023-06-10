package com.unicap.aos.springfinal.exception;

import com.unicap.aos.springfinal.exception.model.InvalidRequestException;

public class InvalidSongException extends InvalidRequestException {
    public InvalidSongException(String message) {
        super(message);
    }
}
