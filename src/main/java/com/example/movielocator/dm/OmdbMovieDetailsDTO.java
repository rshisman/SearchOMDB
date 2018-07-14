package com.example.movielocator.dm;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class OmdbMovieDetailsDTO implements Serializable {

    private static final long serialVersionUID = 6528409389808374366L;

    private String imdbID;

    @SerializedName("Title")
    private String title;

    @SerializedName("Poster")
    private String poster;

    @SerializedName("Released")
    private String releaseDate;

    @SerializedName("Genre")
    private String genre;

    @SerializedName("Director")
    private String director;

    @SerializedName("Actors")
    private String actors;

    @SerializedName("Plot")
    private String plot;

    private String imdbRating;

    @SerializedName("Website")
    private String website;

    public String getImdbID() {
        return imdbID;
    }

    public String getTitle() {
        return title;
    }

    public String getPoster() {
        return poster;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getGenre() {
        return genre;
    }

    public String getDirector() {
        return director;
    }

    public String getActors() {
        return actors;
    }

    public String getPlot() {
        return plot;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public String getWebsite() {
        return website;
    }
}
