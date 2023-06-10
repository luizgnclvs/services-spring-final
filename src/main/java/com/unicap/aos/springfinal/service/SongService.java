package com.unicap.aos.springfinal.service;

import com.unicap.aos.springfinal.domain.dto.*;
import com.unicap.aos.springfinal.domain.entity.Album;
import com.unicap.aos.springfinal.domain.entity.Song;
import com.unicap.aos.springfinal.exception.AlbumNotFoundException;
import com.unicap.aos.springfinal.exception.InvalidSongException;
import com.unicap.aos.springfinal.exception.SongNotFoundException;
import com.unicap.aos.springfinal.repository.AlbumRepository;
import com.unicap.aos.springfinal.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SongService {
    @Autowired
    private SongRepository songRepository;

    @Autowired
    private AlbumRepository albumRepository;

    public List<SongResponse> listAll() {
        List<Song> songs = songRepository.findAll();

        return songs.stream().map(song -> new SongResponse(song)).collect(Collectors.toList());
    }

    public SongResponse searchById(long id) {
        Optional<Song> searchedSong = songRepository.findById(id);
        if (searchedSong.isEmpty()) throw new SongNotFoundException(id);

        return new SongResponse(searchedSong.get());
    }

    public SongResponse create(SongCreateRequest request) {
        Optional<Album> album = albumRepository.findById(request.getAlbumId());
        if (album.isEmpty()) throw new AlbumNotFoundException(request.getAlbumId());

        Song newSong = new Song();

        newSong.setName(request.getName().trim());
        newSong.setDuration(request.getDuration());
        newSong.setAlbum(album.get());

        Song createdSong = songRepository.save(newSong);

        return new SongResponse(createdSong);
    }

    public SongResponse update(long id, SongUpdateRequest request) {
        if (!isUpdateFilled(request)) throw new InvalidSongException("Ao menos um dos campos deve ser preenchido.");

        Optional<Song> searchedSong = songRepository.findById(id);
        if (searchedSong.isEmpty()) throw new SongNotFoundException(id);

        Song foundSong = searchedSong.get();

        if (request.getName() != null)
            if (!request.getName().isBlank()) foundSong.setName(request.getName().trim());
        if (request.getDuration() != 0) foundSong.setDuration(request.getDuration());

        Song updatedSong = songRepository.save(foundSong);

        return new SongResponse(updatedSong);
    }

    public void delete(long id) {
        Optional<Song> searchedSong = songRepository.findById(id);
        if (searchedSong.isEmpty()) throw new SongNotFoundException(id);

        songRepository.delete(searchedSong.get());
    }

    private boolean isUpdateFilled(SongUpdateRequest request) {
        if (request.getName() != null)
            if (!request.getName().isBlank()) return true;
        if (request.getDuration() != 0) return true;

        return false;
    }
}
