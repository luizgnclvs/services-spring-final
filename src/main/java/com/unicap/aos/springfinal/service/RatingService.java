package com.unicap.aos.springfinal.service;

import com.unicap.aos.springfinal.domain.dto.*;
import com.unicap.aos.springfinal.domain.entity.Album;
import com.unicap.aos.springfinal.domain.entity.Rating;
import com.unicap.aos.springfinal.domain.entity.Song;
import com.unicap.aos.springfinal.exception.*;
import com.unicap.aos.springfinal.repository.AlbumRepository;
import com.unicap.aos.springfinal.repository.RatingRepository;
import com.unicap.aos.springfinal.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RatingService {
    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private SongRepository songRepository;

    public List<RatingResponse> listAll() {
        List<Rating> ratings = ratingRepository.findAll();

        return ratings.stream().map(rating -> new RatingResponse(rating)).collect(Collectors.toList());
    }

    public RatingResponse searchById(long id) {
        Optional<Rating> searchedRating = ratingRepository.findById(id);
        if (searchedRating.isEmpty()) throw new RatingNotFoundException(id);

        return new RatingResponse(searchedRating.get());
    }

    public RatingResponse create(RatingCreateRequest request) {
        if (!isRelationValid(request))
            throw new InvalidRatingException("Avaliação deve possuir um 'album_id' ou 'song_id' e nunca ambos simultaneamente.");

        if (!isDateValid(request.getDate()))
            throw new InvalidRatingException("Data da avaliação não pode ser anterior à 12 de Nov. de 1955 ou posterior ao dia atual.");

        Optional<Album> album = albumRepository.findById(request.getAlbumId());
        Optional<Song> song = songRepository.findById(request.getSongId());

        if (request.getAlbumId() != 0 && album.isEmpty()) throw new AlbumNotFoundException(request.getAlbumId());
        if (request.getSongId() != 0 && song.isEmpty()) throw new SongNotFoundException(request.getSongId());

        Rating newRating = new Rating();

        newRating.setScore(request.getScore());
        newRating.setCommentary(request.getCommentary().trim());
        newRating.setDate(request.getDate());
        newRating.setAlbum(album.isEmpty() ? null : album.get());
        newRating.setSong(song.isEmpty() ? null : song.get());

        Rating createdRating = ratingRepository.save(newRating);

        return new RatingResponse(createdRating);
    }

    public RatingResponse update(long id, RatingUpdateRequest request) {
        if (!isUpdateFilled(request)) throw new InvalidRatingException("Ao menos um dos campos deve ser preenchido.");

        Optional<Rating> searchedRating = ratingRepository.findById(id);
        if (searchedRating.isEmpty()) throw new RatingNotFoundException(id);

        Rating foundRating = searchedRating.get();

        if (request.getScore() > 0 && request.getScore() <= 10) foundRating.setScore(request.getScore());
        if (request.getCommentary() != null)
            if (!request.getCommentary().isBlank()) foundRating.setCommentary(request.getCommentary().trim());
        if (request.getDate() != null) {
            if (!isDateValid(request.getDate()))
                throw new InvalidRatingException("Data da avaliação não pode ser anterior à 12 de Nov. de 1955 ou posterior ao dia atual.");
            else
                foundRating.setDate(request.getDate());
        }

        Rating updatedRating = ratingRepository.save(foundRating);

        return new RatingResponse(updatedRating);
    }

    public void delete(long id) {
        Optional<Rating> searchedRating = ratingRepository.findById(id);
        if (searchedRating.isEmpty()) throw new RatingNotFoundException(id);

        ratingRepository.delete(searchedRating.get());
    }

    private boolean isRelationValid(RatingCreateRequest request) {
        if (request.getAlbumId() != 0 && request.getSongId() != 0) return false;
        if (request.getAlbumId() == 0 && request.getSongId() == 0) return false;

        return true;
    }

    private boolean isUpdateFilled(RatingUpdateRequest request) {
        if (request.getScore() != 0) return true;
        if (request.getCommentary() != null)
            if (!request.getCommentary().isBlank()) return true;
        if (request.getDate() != null) return true;

        return false;
    }

    private boolean isDateValid(LocalDate date) {
        if (date.isBefore(LocalDate.of(1955, 11, 12))) return false;
        if (date.isAfter(LocalDate.now())) return false;

        return true;
    }
}
