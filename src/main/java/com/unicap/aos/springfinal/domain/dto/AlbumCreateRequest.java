package com.unicap.aos.springfinal.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class AlbumCreateRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String artist;

    @NotBlank
    @JsonProperty("cover_url")
    private String coverURL;

    @NotNull
    @JsonProperty("release_year")
    private int releaseYear;
}
