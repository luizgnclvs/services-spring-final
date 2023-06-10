package com.unicap.aos.springfinal.exception;

import com.unicap.aos.springfinal.exception.model.InvalidRequestException;

public class InvalidAlbumException extends InvalidRequestException {
    public InvalidAlbumException(String message) {
        super(message);
    }
}
