package com.unicap.aos.springfinal.service;

import com.unicap.aos.springfinal.domain.dto.*;
import com.unicap.aos.springfinal.domain.entity.Album;
import com.unicap.aos.springfinal.exception.AlbumNotFoundException;
import com.unicap.aos.springfinal.exception.InvalidAlbumException;
import com.unicap.aos.springfinal.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlbumService {
    @Autowired
    private AlbumRepository albumRepository;

    public List<AlbumResponse> listAll() {
        List<Album> albums = albumRepository.findAll();

        return albums.stream().map(album -> new AlbumResponse(album)).collect(Collectors.toList());
    }

    public AlbumResponse searchById(long id) {
        Optional<Album> searchedAlbum = albumRepository.findById(id);
        if (searchedAlbum.isEmpty()) throw new AlbumNotFoundException(id);

        return new AlbumResponse(searchedAlbum.get());
    }

    public AlbumResponse create(AlbumCreateRequest request) {
        if (request.getReleaseYear() < 1950 || request.getReleaseYear() > LocalDate.now().getYear()) {
            throw new InvalidAlbumException("Ano de lançamento deve estar entre 1955 e o ano atual.");
        }

        Album newAlbum = new Album();

        newAlbum.setName(request.getName());
        newAlbum.setArtist(request.getArtist());
        newAlbum.setCoverURL(request.getCoverURL());
        newAlbum.setReleaseYear(request.getReleaseYear());

        Album createdAlbum = albumRepository.save(newAlbum);

        return new AlbumResponse(createdAlbum);
    }

    public AlbumResponse update(long id, AlbumUpdateRequest request) {
        if (request.getName().isBlank() &&
            request.getArtist().isBlank() &&
            request.getCoverURL().isBlank() &&
            (Integer)request.getReleaseYear() == null) {
            throw new InvalidAlbumException("Ao menos um dos campos deve ser preenchido.");
        }

        Optional<Album> searchedAlbum = albumRepository.findById(id);
        if (searchedAlbum.isEmpty()) throw new AlbumNotFoundException(id);

        Album foundAlbum = searchedAlbum.get();

        if (!request.getName().isBlank()) foundAlbum.setName(request.getName());
        if (!request.getArtist().isBlank()) foundAlbum.setArtist(request.getArtist());
        if (!request.getCoverURL().isBlank()) foundAlbum.setCoverURL(request.getCoverURL());
        if (request.getReleaseYear() < 1950 || request.getReleaseYear() > LocalDate.now().getYear()) {
            throw new InvalidAlbumException("Ano de lançamento deve estar entre 1955 e o ano atual.");
        } else {
            foundAlbum.setReleaseYear(request.getReleaseYear());
        }

        Album updatedAlbum = albumRepository.save(foundAlbum);

        return new AlbumResponse(updatedAlbum);
    }

    public DeletionResponse delete(long id) {
        Optional<Album> searchedAlbum = albumRepository.findById(id);
        if (searchedAlbum.isEmpty()) throw new AlbumNotFoundException(id);

        Album deletedAlbum = searchedAlbum.get();
        albumRepository.delete(deletedAlbum);

        return new DeletionResponse("Álbum de ID: {{ " + id + " }} foi deletado com sucesso.");
    }
}
