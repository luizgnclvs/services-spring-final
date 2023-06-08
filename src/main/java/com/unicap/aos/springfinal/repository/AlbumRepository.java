package com.unicap.aos.springfinal.repository;

import com.unicap.aos.springfinal.domain.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {}
