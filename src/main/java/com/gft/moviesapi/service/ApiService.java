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

    public List<Movie> getTopRatedMovies() throws IOException{
        mapper = new ObjectMapper();
        URL url = new URL(uri+"movie/top_rated?api_key="+apiKey);
        String jsonAString = stream(url);
        Movie[] lista = mapper.readValue(mapper.readTree(jsonAString).get("results").toString(),Movie[].class);
        return Arrays.asList(lista);
    }

    public Movie getMovie(int movie_id) throws IOException{
        mapper = new ObjectMapper();
        URL url = new URL(uri+"movie/"+movie_id+"?api_key="+apiKey);
        String jsonAString = stream(url);
        Movie pelicula = mapper.readValue(jsonAString,Movie.class);
        return pelicula;
    }

    public CastCrew getMovieCredits(int movie_id) throws IOException {
        mapper = new ObjectMapper();
        URL url = new URL(uri+"movie/"+movie_id+"/credits?api_key="+apiKey);
        String jsonAString = stream(url);
        Cast[] cast = mapper.readValue(mapper.readTree(jsonAString).get("cast").toString(),Cast[].class);
        Crew[] crew = mapper.readValue(mapper.readTree(jsonAString).get("crew").toString(),Crew[].class);
        CastCrew castCrew = new CastCrew(Arrays.asList(cast),Arrays.asList(crew));
        return castCrew;
    }

    public List<Keyword> getKeyword(Integer movie_id) throws IOException {
        mapper = new ObjectMapper();
        URL url = new URL(uri+"movie/"+movie_id+"/keywords?api_key="+apiKey);
        String jsonAString = stream(url);
        Keyword[] response  = mapper.readValue(mapper.readTree(jsonAString).get("keywords").toString(),Keyword[].class);
        return Arrays.asList(response);
    }

    public List<Movie> getRecommendations(int movie_id) throws IOException {
        mapper = new ObjectMapper();
        URL url = new URL(uri+"movie/"+movie_id+"/recommendations?api_key="+apiKey);
        String jsonAString = stream(url);
        Movie[] response  = mapper.readValue(mapper.readTree(jsonAString.toString()).get("results").toString(),Movie[].class);
        return Arrays.asList(response);
    }

    public List<Movie> getSimilar(int movie_id) throws IOException {
        mapper = new ObjectMapper();
        URL url = new URL(uri+"movie/"+movie_id+"/similar?api_key="+apiKey);
        String jsonAString = stream(url);
        Movie[] response  = mapper.readValue(mapper.readTree(jsonAString.toString()).get("results").toString(),Movie[].class);
        return Arrays.asList(response);
    }

    /*Comodidad de código, para evitar repetirlo en cada función*/
    public String stream(URL url) throws IOException {
        mapper = new ObjectMapper();
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
