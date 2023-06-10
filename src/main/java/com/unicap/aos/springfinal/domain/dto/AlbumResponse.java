package com.unicap.aos.springfinal.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.unicap.aos.springfinal.domain.entity.Album;
import lombok.Data;

@Data
public class AlbumResponse {
    private long id;
    private String name;
    private String artist;

    @JsonProperty("cover_url")
    private String coverURL;

    @JsonProperty("release_year")
    private int releaseYear;

    public AlbumResponse (Album album) {
        this.id = album.getId();
        this.name = album.getName();
        this.artist = album.getArtist();
        this.coverURL = album.getCoverURL();
        this.releaseYear = album.getReleaseYear();
    }
}
