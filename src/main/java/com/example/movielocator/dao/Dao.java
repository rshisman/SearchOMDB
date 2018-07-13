package com.example.movielocator.dao;

import java.util.Optional;

public interface Dao<T> {

    Optional<T> findById(Long id);
    boolean create(T entity);
}
