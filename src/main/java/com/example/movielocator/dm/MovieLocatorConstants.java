package com.example.movielocator.dm;

public class MovieLocatorConstants {

    //OMDB constants
    public static final String OMDB_BASE_URL = "www.omdbapi.com";
    public static final String OMDB_API_KEY = "bb182d9e";
    public static final String OMDB_TYPE_MOVIE = "movie";
    public static final String OMDB_SERACH_MOVIE_BASE_URL = String.format("http://%s/?apikey=%s&type=%s", OMDB_BASE_URL, OMDB_API_KEY, OMDB_TYPE_MOVIE);
    public static final String OMDB_SERACH_MOVIE_BY_TITLE_URL = OMDB_SERACH_MOVIE_BASE_URL + "&s=%s";

}
