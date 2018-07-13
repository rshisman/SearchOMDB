package com.example.movielocator.controller;

import com.example.movielocator.dm.Movie;
import com.example.movielocator.services.MovieLocatorApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value="api/movies")
public class MovieLocatorController {

    private MovieLocatorApplicationService movieLocatorApplicationService;

    @Autowired
    public MovieLocatorController(MovieLocatorApplicationService movieLocatorApplicationService) {
        this.movieLocatorApplicationService = movieLocatorApplicationService;
    }

    @GetMapping(value = "", produces = "application/json")
    public ResponseEntity<List<Movie>> getMoviesByTitle(@RequestParam("title") String title){

        List<Movie> moviesJson = movieLocatorApplicationService.findMoviesByTitle(title);
        if(!moviesJson.isEmpty()){
            return new ResponseEntity<>(moviesJson, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.NOT_FOUND);
        }
    }

}