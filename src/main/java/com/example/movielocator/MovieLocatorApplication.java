package com.example.movielocator;

import com.example.movielocator.dao.MovieRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses= {MovieRepository.class})
public class MovieLocatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieLocatorApplication.class, args);
    }
}
