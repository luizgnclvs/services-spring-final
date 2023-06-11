package com.unicap.aos.springfinal.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.unicap.aos.springfinal.domain.entity.Rating;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RatingResponse {
    private long id;
    private int score;
    private String commentary;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @JsonProperty("album_id")
    private Long albumId;

    @JsonProperty("song_id")
    private Long songId;

    public RatingResponse(Rating rating) {
        this.id = rating.getId();
        this.score = rating.getScore();
        this.commentary = rating.getCommentary();
        this.date = rating.getDate();
        this.albumId = rating.getAlbum() != null ? rating.getAlbum().getId() : null;
        this.songId = rating.getSong() != null ? rating.getSong().getId() : null;
    }
}
