package com.gft.moviesapi.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;

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
}
