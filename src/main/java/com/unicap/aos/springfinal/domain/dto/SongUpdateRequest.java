package com.unicap.aos.springfinal.domain.dto;

import lombok.Data;

@Data
public class SongUpdateRequest {
    private String name;
    private int duration;
}
