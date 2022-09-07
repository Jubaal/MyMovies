package com.gft.moviesapi.service;

import com.gft.moviesapi.entities.Genre;
import io.netty.handler.codec.serialization.ObjectEncoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.List;

@Service
@Profile("webclient")
public class WebClientService {
    WebClient webClient = WebClient.create("https://api.themoviedb.org/3/");
    @Value("${themoviedatabase.api_key}")
    private String api_key;

    public HashMap<String, Object> getConfig() {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("configuration")
                        .queryParam("api_key",api_key)
                        .build()
                )
                .retrieve()
                .bodyToMono(HashMap.class)
                .block();
    }


    public HashMap<String,Object> getAllGenres() {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("genre/movie/list")
                .queryParam("api_key",api_key)
                .build()
                )
                .retrieve()
                .bodyToMono(HashMap.class)
                .block();
    }


    public Object getPopularMovies() {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("movie/popular")
                        .queryParam("api_key",api_key)
                        .build()
                )
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    /*public Object getMovie(int movie_id) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("movie/popular")
                        .queryParam("api_key",api_key)
                        .build()
                )
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }*/
}
