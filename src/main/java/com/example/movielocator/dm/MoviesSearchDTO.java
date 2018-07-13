package com.example.movielocator.dm;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class MoviesSearchDTO implements Serializable {

    @SerializedName("Search")
    private final List<Movie> movies;

    public MoviesSearchDTO(List<Movie> search) {
        this.movies = search;
    }

    public List<Movie> getMovies() {
        return Collections.unmodifiableList(movies);
    }
}
