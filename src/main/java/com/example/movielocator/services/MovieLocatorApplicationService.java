package com.example.movielocator.services;


import com.example.movielocator.dm.Movie;
import com.example.movielocator.dm.MovieDetails;

import java.util.List;
import java.util.Optional;

public interface MovieLocatorApplicationService {

    List<Movie> findMoviesByTitle(String title);
    Optional<MovieDetails> findMovieById(String id);
}
