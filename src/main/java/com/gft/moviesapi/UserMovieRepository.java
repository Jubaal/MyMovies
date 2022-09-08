package com.gft.moviesapi;

import com.gft.moviesapi.entities.UserMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserMovieRepository extends JpaRepository<UserMovie,Integer> {
    Optional<UserMovie> findByUsernameAndMovie(String username,String movie);
}
