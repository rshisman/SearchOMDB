package com.example.movielocator.dm;

public class MovieLocatorConstants {

    //OMDB constants
    public static final String OMDB_BASE_URL = "www.omdbapi.com";
    public static final String OMDB_API_KEY = "bb182d9e";
    public static final String OMDB_SERACH_MOVIE_BASE_URL = String.format("http://%s/?apikey=%s", OMDB_BASE_URL, OMDB_API_KEY);
    public static final String OMDB_SERACH_MOVIE_BY_TITLE_URL = OMDB_SERACH_MOVIE_BASE_URL + "&type=movie&s=%s";
    public static final String OMDB_SERACH_MOVIE_BY_ID_URL = OMDB_SERACH_MOVIE_BASE_URL + "&i=%s";

}
