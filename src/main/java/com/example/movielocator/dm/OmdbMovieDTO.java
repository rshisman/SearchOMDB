package com.example.movielocator.dm;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Objects;

public class OmdbMovieDTO implements Serializable {

    private static final long serialVersionUID = 436933635106433534L;

    private String imdbID;

    @SerializedName("Title")
    private String title;

    @SerializedName("Year")
    private Integer year;

    @SerializedName("Poster")
    private String poster;


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
        OmdbMovieDTO that = (OmdbMovieDTO) o;
        return Objects.equals(imdbID, that.imdbID) &&
                Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imdbID, title);
    }
}
