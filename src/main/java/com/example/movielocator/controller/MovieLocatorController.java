package com.example.movielocator.controller;

import com.example.movielocator.dm.Movie;
import com.example.movielocator.dm.MovieDetails;
import com.example.movielocator.services.MovieLocatorApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="api/movies")
public class MovieLocatorController {

    private MovieLocatorApplicationService movieLocatorApplicationService;

    @Autowired
    public MovieLocatorController(MovieLocatorApplicationService movieLocatorApplicationService) {
        this.movieLocatorApplicationService = movieLocatorApplicationService;
    }

    @GetMapping(value = "/title/{title}", produces = "application/json")
    public ResponseEntity<List<Movie>> getMoviesByTitle(@PathVariable("title") String title){

        List<Movie> moviesJson = movieLocatorApplicationService.findMoviesByTitle(title);
        if(!moviesJson.isEmpty()){
            return new ResponseEntity<>(moviesJson, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<MovieDetails> getMovieByID(@PathVariable("id") String id){

        Optional<MovieDetails> movieDetails = movieLocatorApplicationService.findMovieById(id);
        if(movieDetails.isPresent()){
            return new ResponseEntity<>(movieDetails.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new MovieDetails(), HttpStatus.NOT_FOUND);
        }
    }

}