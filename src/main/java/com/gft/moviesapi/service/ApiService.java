package com.gft.moviesapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gft.moviesapi.entities.Genre;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

@Service
public class ApiService {
    final String uri = "https://api.themoviedb.org/3/";
    String apiKey= "525439ea4a0dda636770a72ffd63ba5e";
    private ObjectMapper mapper;
    private StringBuilder json;

    public List<Genre> getAllGenres() throws IOException{
        mapper = new ObjectMapper();
        URL url = new URL(uri+"genre/movie/list?api_key="+apiKey);
        String jsonAString = stream(url);
        Genre[] lista = mapper.readValue(mapper.readTree(jsonAString).get("genres").toString(),Genre[].class);
        return Arrays.asList(lista);
    }

    public String stream(URL url) throws IOException {
        InputStream input = url.openStream();
        InputStreamReader isr = new InputStreamReader(input);
        BufferedReader reader = new BufferedReader(isr);
        json = new StringBuilder();
        int c;
        while ((c = reader.read()) != -1) {
            json.append((char) c);
        }
        return json.toString();
    }
}
