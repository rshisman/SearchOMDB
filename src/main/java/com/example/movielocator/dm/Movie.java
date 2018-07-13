package com.example.movielocator.dm;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Objects;

public class Movie implements Serializable {

    private static final long serialVersionUID = 436933635106433534L;

    private Long id;
    private String imdbID;

    @SerializedName("Title")
    private String title;

    @SerializedName("Year")
    private Integer year;

    @SerializedName("Poster")
    private String poster;

    public Movie(Long id, String imdbId, String title, Integer year, String poster) {
        this.id = id;
        this.imdbID = imdbId;
        this.title = title;
        this.year = year;
        this.poster = poster;
    }

    public Long getId() {
        return id;
    }

    public String getImdbID() {
        return imdbID;
    }

    public String getTitle() {
        return title;
    }

    public Integer getYear() {
        return year;
    }

    public String getPoster() {
        return poster;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(id, movie.id) &&
                Objects.equals(imdbID, movie.imdbID) &&
                Objects.equals(title, movie.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, imdbID, title);
    }
}
