package com.gft.moviesapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gft.moviesapi.entities.*;
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

    public List<Movie> getPopularMovies() throws IOException{
        mapper = new ObjectMapper();
        URL url = new URL(uri+"movie/popular?api_key="+apiKey);
        String jsonAString = stream(url);
        Movie[] lista = mapper.readValue(mapper.readTree(jsonAString).get("results").toString(),Movie[].class);
        return Arrays.asList(lista);
    }

    /*Comodidad de código, para evitar repetirlo en cada función*/
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

    public List<Movie> getTopRatedMovies() throws IOException{
        mapper = new ObjectMapper();
        URL url = new URL(uri+"movie/top_rated?api_key="+apiKey);
        String jsonAString = stream(url);
        Movie[] lista = mapper.readValue(mapper.readTree(jsonAString).get("results").toString(),Movie[].class);
        return Arrays.asList(lista);
    }

    public Movie getMovie(int movie_id) throws IOException{
        mapper = new ObjectMapper();
        URL url = new URL(uri+"/movie/"+movie_id+"?api_key="+apiKey);
        String jsonAString = stream(url);
        Movie pelicula = mapper.readValue(jsonAString,Movie.class);
        return pelicula;
    }

    /*public List<CastCrew> getMovieCredits(Integer movie_id) throws IOException {
        mapper = new ObjectMapper();
        URL url = new URL(uri+"/movie/"+movie_id+"?api_key="+apiKey);
        String jsonAString = stream(url);
        CastCrew[] castcrew  = mapper.readValue(mapper.readTree(jsonAString).get("cast").toString(),CastCrew[].class);
        return Arrays.asList(castcrew);
    }*/

    public List<Keyword> getKeyword(Integer movie_id) throws IOException {
        mapper = new ObjectMapper();
        URL url = new URL(uri+"/movie/"+movie_id+"/keywords?api_key="+apiKey);
        String jsonAString = stream(url);
        Keyword[] response  = mapper.readValue(mapper.readTree(jsonAString).get("keywords").toString(),Keyword[].class);
        return Arrays.asList(response);
    }

    /*public List<Movie> getRecommendations(Integer movie_id) throws IOException {
        StringBuilder jsonReq = getStringFromRequest("movie/"+movie_id.toString()+"/recommendations?api_key=");
        Movie[] response  = mapper.readValue(mapper.readTree(jsonReq.toString()).get("results").toString(),Movie[].class);
        return Arrays.asList(response);
    }*/



}
