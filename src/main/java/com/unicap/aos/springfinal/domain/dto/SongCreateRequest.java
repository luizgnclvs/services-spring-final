package com.unicap.aos.springfinal.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class SongCreateRequest {
    @NotBlank
    private String name;

    private int duration;

    @NotNull
    @JsonProperty("album_id")
    private long albumId;
}
