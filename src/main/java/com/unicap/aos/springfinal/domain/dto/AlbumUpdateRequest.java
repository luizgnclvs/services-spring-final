package com.unicap.aos.springfinal.domain.dto;

import lombok.Data;

@Data
public class AlbumUpdateRequest {
    private String name;
    private String artist;
    private String coverURL;
    private int releaseYear;
}
