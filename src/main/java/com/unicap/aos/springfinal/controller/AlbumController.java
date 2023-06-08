package com.unicap.aos.springfinal.controller;

import com.unicap.aos.springfinal.domain.dto.AlbumCreateRequest;
import com.unicap.aos.springfinal.domain.dto.AlbumResponse;
import com.unicap.aos.springfinal.domain.dto.AlbumUpdateRequest;
import com.unicap.aos.springfinal.domain.dto.DeletionResponse;
import com.unicap.aos.springfinal.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "api/album")
public class AlbumController {
    @Autowired
    AlbumService albumService;

    @GetMapping
    public ResponseEntity<List<AlbumResponse>> getAlbums() {
        return ResponseEntity.ok(albumService.listAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AlbumResponse> getAlbumById(@PathVariable int id) {
        return ResponseEntity.ok(albumService.searchById(id));
    }

    @PostMapping
    public ResponseEntity<AlbumResponse> postAlbum(@RequestBody @Valid AlbumCreateRequest request) {
        return ResponseEntity.ok(albumService.create(request));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<AlbumResponse> putAlbum(@PathVariable int id, @RequestBody AlbumUpdateRequest request) {
        return ResponseEntity.ok(albumService.update(id, request));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<DeletionResponse> deleteAlbum(@PathVariable int id) {
        return ResponseEntity.ok(albumService.delete(id));
    }
}
