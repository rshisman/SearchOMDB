package com.example.movielocator.dm;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class OmdbMoviesSearchDTO implements Serializable {

    @SerializedName("Search")
    private final List<OmdbMovieDTO> movies;

    public OmdbMoviesSearchDTO(List<OmdbMovieDTO> search) {
        this.movies = search;
    }

    public List<OmdbMovieDTO> getMovies() {
        return Collections.unmodifiableList(movies);
    }
}
