package com.unicap.aos.springfinal.domain.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RatingUpdateRequest {
    private int score;
    private String commentary;
    private LocalDate date;
}
