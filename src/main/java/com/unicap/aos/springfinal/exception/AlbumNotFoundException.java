package com.unicap.aos.springfinal.exception;

import com.unicap.aos.springfinal.exception.model.NotFoundException;

public class AlbumNotFoundException extends NotFoundException {
    public AlbumNotFoundException(long id) {
        super("Álbum", id);
    }
}
