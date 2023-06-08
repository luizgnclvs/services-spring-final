package com.unicap.aos.springfinal.service;

import com.unicap.aos.springfinal.domain.dto.AlbumResponse;
import com.unicap.aos.springfinal.domain.entity.Album;
import com.unicap.aos.springfinal.exception.AlbumNotFoundException;
import com.unicap.aos.springfinal.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlbumService {
    @Autowired
    private AlbumRepository repository;

    public List<AlbumResponse> listAll() {
        List<Album> albums = repository.findAll();

        return albums.stream().map(album -> new AlbumResponse(album)).collect(Collectors.toList());
    }

    public AlbumResponse searchById(long id) {
        Optional<Album> searchedAlbum = repository.findById(id);

        if (searchedAlbum.isEmpty()) throw new AlbumNotFoundException(id);

        return new AlbumResponse(searchedAlbum.get());
    }
}
