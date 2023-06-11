package com.unicap.aos.springfinal.controller;

import com.unicap.aos.springfinal.domain.dto.*;
import com.unicap.aos.springfinal.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "api/rating")
public class RatingController {
    @Autowired
    private RatingService ratingService;

    @GetMapping
    public ResponseEntity<List<RatingResponse>> getRatings() {
        return ResponseEntity.ok(ratingService.listAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<RatingResponse> getRatingById(@PathVariable long id) {
        return ResponseEntity.ok(ratingService.searchById(id));
    }

    @PostMapping
    public ResponseEntity<RatingResponse> createRating(@RequestBody @Valid RatingCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.create(request));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<RatingResponse> updateRating(@PathVariable long id, @RequestBody RatingUpdateRequest request) {
        return ResponseEntity.ok(ratingService.update(id, request));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteRating(@PathVariable long id) {
        ratingService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
