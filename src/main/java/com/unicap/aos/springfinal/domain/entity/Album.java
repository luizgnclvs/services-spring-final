package com.unicap.aos.springfinal.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String artist;

    @NotBlank
    @Column(name = "cover_url")
    private String coverURL;

    @NotNull
    @Min(1955)
    @Max(9999)
    @Column(name = "release_year")
    private Integer releaseYear;
}
