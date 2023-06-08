package com.unicap.aos.springfinal.domain.dto;

import com.unicap.aos.springfinal.domain.entity.Album;
import lombok.Data;

@Data
public class AlbumResponse {
    private long id;
    private String name;
    private String artist;
    private String coverURL;
    private int releaseYear;

    public AlbumResponse (Album album) {
        this.id = album.getId();
        this.name = album.getName();
        this.artist = album.getArtist();
        this.coverURL = album.getCoverURL();
        this.releaseYear = album.getReleaseYear();
    }
}
