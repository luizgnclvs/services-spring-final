package com.unicap.aos.springfinal.controller;

import com.unicap.aos.springfinal.domain.dto.*;
import com.unicap.aos.springfinal.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "api/song")
public class SongController {
    @Autowired
    private SongService songService;

    @GetMapping
    public ResponseEntity<List<SongResponse>> getSongs() {
        return ResponseEntity.ok(songService.listAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<SongResponse> getSongById(@PathVariable long id) {
        return ResponseEntity.ok(songService.searchById(id));
    }

    @PostMapping
    public ResponseEntity<SongResponse> createSong(@RequestBody @Valid SongCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(songService.create(request));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<SongResponse> updateSong(@PathVariable long id, @RequestBody SongUpdateRequest request) {
        return ResponseEntity.ok(songService.update(id, request));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteSong(@PathVariable long id) {
        songService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
