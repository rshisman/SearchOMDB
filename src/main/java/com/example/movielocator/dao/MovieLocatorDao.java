package com.example.movielocator.dao;

import com.example.movielocator.dm.Movie;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MovieLocatorDao implements Dao<Movie> {

    @Override
    public Optional<Movie> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean create(Movie entity) {

        return true;
    }
}
