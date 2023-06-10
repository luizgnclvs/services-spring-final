package com.unicap.aos.springfinal.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.unicap.aos.springfinal.domain.entity.Song;
import lombok.Data;

@Data
public class SongResponse {
    private long id;
    private String name;
    private int duration;

    @JsonProperty("album_id")
    private long albumId;

    public SongResponse(Song song) {
        this.id = song.getId();
        this.name = song.getName();
        this.duration = song.getDuration();
        this.albumId = song.getAlbum().getId();
    }
}
