package com.unicap.aos.springfinal.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class RatingCreateRequest {
    @Min(1)
    @Max(10)
    private int score;

    private String commentary;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @JsonProperty("album_id")
    private long albumId;

    @JsonProperty("song_id")
    private long songId;
}
