package com.example.movielocator.services;

import com.example.movielocator.dao.MovieRepository;
import com.example.movielocator.dm.Movie;
import com.example.movielocator.dm.MovieLocatorConstants;
import com.example.movielocator.dm.OmdbMovieDTO;
import com.example.movielocator.dm.OmdbMoviesSearchDTO;
import com.example.movielocator.util.RESTApiResponse;
import com.example.movielocator.util.RestApiInvoker;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class MovieLocatorApplicationServiceImpl implements MovieLocatorApplicationService {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public List<Movie> findMoviesByTitle(String title) {

        List<Movie> moviesByTitle = movieRepository.findByTitleContaining(title);
        if(moviesByTitle != null && !moviesByTitle.isEmpty()){
           return moviesByTitle;
        }

        List<OmdbMovieDTO> omdbMovieDTOS = searchMoviesInOmdb(title);
        moviesByTitle = omdbMovieDTOS.stream().map(x-> getMovieFromOmdbMovie(x)).collect(Collectors.toList());
        movieRepository.saveAll(moviesByTitle);
        return moviesByTitle;
    }

    private List<OmdbMovieDTO> searchMoviesInOmdb(String title){

        RestApiInvoker restApiInvoker = new RestApiInvoker();

        //for example http://www.omdbapi.com/?apikey=bb182d9e&s=bat*&type=movie
        String requestUrl = String.format(MovieLocatorConstants.OMDB_SERACH_MOVIE_BY_TITLE_URL, title);

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        RESTApiResponse<OmdbMoviesSearchDTO> restApiResponse = null;

        try {
            restApiResponse = restApiInvoker.httpGetForJson(requestUrl, headers, new TypeToken<OmdbMoviesSearchDTO>(){}.getType());
            if(!restApiResponse.isSuccess()){
                return Collections.emptyList();
            }

            return restApiResponse.getResultData().isPresent() ? restApiResponse.getResultData().get().getMovies() : Collections.emptyList();

        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    private Movie getMovieFromOmdbMovie(OmdbMovieDTO omdbMovie){

        Movie movie = new Movie(omdbMovie.getImdbID(), omdbMovie.getTitle(), omdbMovie.getYear(), omdbMovie.getPoster());
        movie.setFromDB(false);
        return movie;
    }
}
