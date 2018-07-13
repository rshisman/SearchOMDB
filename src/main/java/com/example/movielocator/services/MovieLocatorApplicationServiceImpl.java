package com.example.movielocator.services;

import com.example.movielocator.dao.MovieLocatorDao;
import com.example.movielocator.dm.Movie;
import com.example.movielocator.dm.MovieLocatorConstants;
import com.example.movielocator.dm.MoviesSearchDTO;
import com.example.movielocator.util.RESTApiResponse;
import com.example.movielocator.util.RestApiInvoker;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Component
public class MovieLocatorApplicationServiceImpl implements MovieLocatorApplicationService {

    private MovieLocatorDao movieLocatorDao;


    @Autowired
    public MovieLocatorApplicationServiceImpl(MovieLocatorDao movieLocatorDao) {
        this.movieLocatorDao = movieLocatorDao;
    }

    @Override
    public List<Movie> findMoviesByTitle(String title) {
        return searchMoviesInOmdb(title);
    }

    public Optional<Movie> findNetworkByID(Long id) {
        return movieLocatorDao.findById(id);
    }

    private List<Movie> searchMoviesInOmdb(String title){

        RestApiInvoker restApiInvoker = new RestApiInvoker();

        //for example http://www.omdbapi.com/?apikey=bb182d9e&s=bat*&type=movie
        String requestUrl = String.format(MovieLocatorConstants.OMDB_SERACH_MOVIE_BY_TITLE_URL, title);

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        RESTApiResponse<MoviesSearchDTO> restApiResponse = null;

        try {
            restApiResponse = restApiInvoker.httpGetForJson(requestUrl, headers, new TypeToken<MoviesSearchDTO>(){}.getType());
            if(!restApiResponse.isSuccess()){
                return Collections.emptyList();
            }

            return restApiResponse.getResultData().isPresent() ? restApiResponse.getResultData().get().getMovies() : Collections.emptyList();

        } catch (Exception e) {
            return Collections.emptyList();
        }
    }
}
