package com.unicap.aos.springfinal.repository;

import com.unicap.aos.springfinal.domain.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {}
