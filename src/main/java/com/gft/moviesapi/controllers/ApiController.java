package com.gft.moviesapi.controllers;

import com.gft.moviesapi.entities.Genre;
import com.gft.moviesapi.service.ApiService;
import io.netty.handler.codec.http.HttpMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
public class ApiController {

    @Autowired
    ApiService apiService;

    @GetMapping("api/genre/movie/list")
    public List<Genre> getAllGenres() throws IOException {
        return apiService.getAllGenres();
    }

}
