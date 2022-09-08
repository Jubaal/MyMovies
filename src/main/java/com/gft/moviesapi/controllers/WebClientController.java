package com.gft.moviesapi.controllers;

import com.gft.moviesapi.UserMovieRepository;
import com.gft.moviesapi.entities.Genre;
import com.gft.moviesapi.entities.Movie;
import com.gft.moviesapi.entities.UserMovie;
import com.gft.moviesapi.service.WebClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

@RestController
@Profile("webclient")
public class WebClientController {

    @Autowired
    WebClientService webClientService;
    @Autowired
    UserMovieRepository userMovieRepository;

    @GetMapping("/api/configuration")
    public HashMap<String, Object> getConfiguration() {
        HashMap<String, Object> config =
        webClientService.getConfig();
        config.put("hola","kease");
        return config;
    }

    @GetMapping("api/genre/movie/list")
    public HashMap<String, Object> getAllGenres() throws IOException {
        return webClientService.getAllGenres();
    }

    @GetMapping("api/movie/popular")
    public Object getPopularMovies() throws IOException {
        return webClientService.getPopularMovies();
    }

    @GetMapping("/api/movie/{id}")
    public Object findMovieById(@AuthenticationPrincipal UserDetails user, @PathVariable int id) {
        System.out.println(id);
        String movieid = Integer.toString(id);
        UserMovie userMovie = userMovieRepository.findByUsernameAndMovie(user.getUsername(), movieid ).orElse(null);

        HashMap<String, Object> movie = webClientService.findMovieById(id);
        if(userMovie != null) {
            movie.put("favorite", userMovie.getFavorite());
            movie.put("personal_rating", userMovie.getPersonal_rating());
            movie.put("notes", userMovie.getNotes());
        }
        return movie;
    }

    @GetMapping("/user")
    public String helloUser() {
        return "Hello, user!";
    }

    @GetMapping("/admin")
    public String helloAdmin() {
        return "Hello, admin!";
    }

    @GetMapping("/hello")
    public String helloAnyone(@AuthenticationPrincipal UserDetails user) {
        if (user != null) {
            return "Hello, " + user.toString();
        } else {
            return "Who are you?";
        }
    }

    @PatchMapping("/api/movie/{id}")
    public ResponseEntity<UserMovie> patchUserMovie(@PathVariable int id, @RequestBody UserMovie userMovie, @AuthenticationPrincipal UserDetails user){
        String movieid = Integer.toString(id);
        UserMovie updatedMovie = userMovieRepository.findByUsernameAndMovie((user.getUsername()),movieid).orElse(null);
        if(updatedMovie == null){
            updatedMovie = new UserMovie();
        }
        updatedMovie.setUsername(user.getUsername());
        updatedMovie.setMovie(movieid);
        updatedMovie.setFavorite(userMovie.getFavorite());
        updatedMovie.setPersonal_rating(userMovie.getPersonal_rating());
        updatedMovie.setNotes((userMovie.getNotes()));

        userMovieRepository.save(updatedMovie);

        return new ResponseEntity<UserMovie>(updatedMovie, HttpStatus.OK);
    }
}
