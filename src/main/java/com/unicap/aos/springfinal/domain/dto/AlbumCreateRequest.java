package com.unicap.aos.springfinal.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class AlbumCreateRequest {
    @NotBlank(message = "Nome não pode ser nulo, vazio ou em branco.")
    private String name;

    @NotBlank(message = "Artista não pode ser nulo, vazio ou em branco.")
    private String artist;

    @NotBlank(message = "URL da capa não pode ser nula, vazia ou em branco.")
    private String coverURL;

    @NotNull(message = "Ano de lançamento não pode ser nulo.")
    private int releaseYear;
}
