package com.gft.moviesapi.controllers;

import com.gft.moviesapi.entities.*;
import com.gft.moviesapi.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.io.IOException;
import java.util.List;

@RestController
public class ApiController {

    private static final String INSERT_USERS_SQL = "INSERT INTO user_movies" +
            "  (userid , movieid , favorite , personal_rating , notes ) VALUES " +
            " (?, ?, ?, ?, ?);";

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

    @PatchMapping("api/movie/{movie_id}")
    public void updateMovie(@PathVariable int movie_id) throws IOException {
        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/bdd4?useSSL=false", "root", "admin");
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setInt(1, 1);
            preparedStatement.setInt(2, movie_id);
            preparedStatement.setBoolean(3, true);
            preparedStatement.setInt(4, 7);
            preparedStatement.setString(5, "Mi opinion");
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
