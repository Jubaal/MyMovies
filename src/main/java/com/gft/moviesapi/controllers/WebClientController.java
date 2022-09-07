package com.gft.moviesapi.controllers;

import com.gft.moviesapi.service.WebClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

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
}
