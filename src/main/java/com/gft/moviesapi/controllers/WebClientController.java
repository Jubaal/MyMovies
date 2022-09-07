package com.gft.moviesapi.controllers;

import com.gft.moviesapi.entities.Genre;
import com.gft.moviesapi.entities.Movie;
import com.gft.moviesapi.service.WebClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@RestController
@Profile("webclient")
public class WebClientController {

    @Autowired
    WebClientService webClientService;

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

    /*@GetMapping("api/movie/{movie_id}")
    public Object getMovie(@PathVariable int movie_id) throws IOException {
        return webClientService.getMovie(movie_id);
    }*/

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
}
