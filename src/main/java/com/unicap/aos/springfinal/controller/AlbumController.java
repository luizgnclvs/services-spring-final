package com.unicap.aos.springfinal.controller;

import com.unicap.aos.springfinal.domain.dto.AlbumCreateRequest;
import com.unicap.aos.springfinal.domain.dto.AlbumResponse;
import com.unicap.aos.springfinal.domain.dto.AlbumUpdateRequest;
import com.unicap.aos.springfinal.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "api/album")
public class AlbumController {
    @Autowired
    private AlbumService albumService;

    @GetMapping
    public ResponseEntity<List<AlbumResponse>> getAlbums() {
        return ResponseEntity.ok(albumService.listAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AlbumResponse> getAlbumById(@PathVariable long id) {
        return ResponseEntity.ok(albumService.searchById(id));
    }

    @PostMapping
    public ResponseEntity<AlbumResponse> createAlbum(@RequestBody @Valid AlbumCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(albumService.create(request));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<AlbumResponse> updateAlbum(@PathVariable long id, @RequestBody AlbumUpdateRequest request) {
        return ResponseEntity.ok(albumService.update(id, request));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteAlbum(@PathVariable long id) {
        albumService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
