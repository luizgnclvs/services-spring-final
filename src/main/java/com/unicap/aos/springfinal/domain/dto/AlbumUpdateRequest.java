package com.unicap.aos.springfinal.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AlbumUpdateRequest {
    private String name;
    private String artist;

    @JsonProperty("cover_url")
    private String coverURL;

    @JsonProperty("release_year")
    private int releaseYear;
}
