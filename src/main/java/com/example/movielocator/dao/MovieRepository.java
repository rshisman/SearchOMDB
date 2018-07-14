package com.example.movielocator.dao;

import com.example.movielocator.dm.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository extends CrudRepository<Movie, Long> {

    List<Movie> findByTitleContaining(@Param("title") String title);
}
