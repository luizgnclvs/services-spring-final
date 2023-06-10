package com.unicap.aos.springfinal.exception;

import com.unicap.aos.springfinal.exception.model.NotFoundException;

public class SongNotFoundException extends NotFoundException {
    public SongNotFoundException(long id) {
        super("Música", id);
    }
}
