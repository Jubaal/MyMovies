package com.gft.moviesapi.controllers;

import com.gft.moviesapi.entities.*;
import com.gft.moviesapi.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import java.io.IOException;
import java.util.List;

@RestController
public class ApiController {

    @Autowired
    ApiService apiService;

    @GetMapping("api/genre/movie/list")
    public List<Genre> getAllGenres() throws IOException {
        return apiService.getAllGenres();
    }

    @GetMapping("api/movie/popular")
    public List<Movie> getPopularMovies() throws IOException {
        return apiService.getPopularMovies();
    }

    @GetMapping("api/movie/top_rated")
    public List<Movie> getTopRatedMovies() throws IOException {
        return apiService.getTopRatedMovies();
    }

    @GetMapping("api/movie/{movie_id}")
    public Movie getMovie(@PathVariable int movie_id) throws IOException {
        return apiService.getMovie(movie_id);
    }

    @GetMapping("api/movie/{movie_id}/credits")
    public CastCrew getMovieCredits(@PathVariable int movie_id) throws IOException {
        return apiService.getMovieCredits(movie_id);
    }

    /*@GetMapping("api/movie/{movie_id}/images")
    public BackgroundLogosPosters getImagesForMovieById(@PathVariable Integer movie_id) throws IOException {
        return middleManMovieDBService.findAllImagesForMovieById(movie_id);
    }*/

    @GetMapping("api/movie/{movie_id}/keywords")
    public List<Keyword> getKeyword(@PathVariable int movie_id) throws IOException {
        return apiService.getKeyword(movie_id);
    }

    @GetMapping("api/movie/{movie_id}/recommendations")
    public List<Movie> getRecommendations(@PathVariable int movie_id) throws IOException {
        return apiService.getRecommendations(movie_id);
    }

    @GetMapping("api/movie/{movie_id}/similar")
    public List<Movie> getSimilar(@PathVariable int movie_id) throws IOException {
        return apiService.getSimilar(movie_id);
    }


}
