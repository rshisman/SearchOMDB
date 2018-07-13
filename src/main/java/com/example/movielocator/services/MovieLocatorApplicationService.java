package com.example.movielocator.services;


import com.example.movielocator.dm.Movie;

import java.util.List;

public interface MovieLocatorApplicationService {

    List<Movie> findMoviesByTitle(String title);
}
