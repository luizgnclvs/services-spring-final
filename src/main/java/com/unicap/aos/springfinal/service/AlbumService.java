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

        newAlbum.setName(request.getName().trim());
        newAlbum.setArtist(request.getArtist().trim());
        newAlbum.setCoverURL(request.getCoverURL().trim());
        newAlbum.setReleaseYear(request.getReleaseYear());

        Album createdAlbum = albumRepository.save(newAlbum);

        return new AlbumResponse(createdAlbum);
    }

    public AlbumResponse update(long id, AlbumUpdateRequest request) {
        if (!isUpdateFilled(request)) throw new InvalidAlbumException("Ao menos um dos campos deve ser preenchido.");

        Optional<Album> searchedAlbum = albumRepository.findById(id);
        if (searchedAlbum.isEmpty()) throw new AlbumNotFoundException(id);

        Album foundAlbum = searchedAlbum.get();

        if (request.getName() != null)
            if (!request.getName().isBlank()) foundAlbum.setName(request.getName().trim());
        if (request.getArtist() != null)
            if (!request.getArtist().isBlank()) foundAlbum.setArtist(request.getArtist().trim());
        if (request.getCoverURL() != null)
            if (!request.getCoverURL().isBlank()) foundAlbum.setCoverURL(request.getCoverURL().trim());
        if (request.getReleaseYear() < 1955 || request.getReleaseYear() > LocalDate.now().getYear()) {
            if (request.getReleaseYear() != 0)
                throw new InvalidAlbumException("Ano de lançamento deve estar entre 1955 e o ano atual.");
        } else {
            foundAlbum.setReleaseYear(request.getReleaseYear());
        }

        Album updatedAlbum = albumRepository.save(foundAlbum);

        return new AlbumResponse(updatedAlbum);
    }

    public void delete(long id) {
        Optional<Album> searchedAlbum = albumRepository.findById(id);
        if (searchedAlbum.isEmpty()) throw new AlbumNotFoundException(id);

        albumRepository.delete(searchedAlbum.get());
    }

    private boolean isUpdateFilled(AlbumUpdateRequest request) {
        if (request.getName() != null)
            if (!request.getName().isBlank()) return true;

        if (request.getArtist() != null)
            if (!request.getArtist().isBlank()) return true;

        if (request.getCoverURL() != null)
            if (!request.getCoverURL().isBlank()) return true;

        if (request.getReleaseYear() != 0) return true;

        return false;
    }
}
