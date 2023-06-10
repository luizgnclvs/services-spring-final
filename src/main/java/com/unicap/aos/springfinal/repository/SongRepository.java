package com.unicap.aos.springfinal.repository;

import com.unicap.aos.springfinal.domain.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {}
