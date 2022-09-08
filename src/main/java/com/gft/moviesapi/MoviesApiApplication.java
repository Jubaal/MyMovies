package com.gft.moviesapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
@EnableCaching
@Configuration
public class MoviesApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoviesApiApplication.class, args);
    }

}
