package com.unicap.aos.springfinal.domain.dto;

public class InvalidAlbumException extends RuntimeException {
    public InvalidAlbumException(String message) {
        super(message);
    }
}
