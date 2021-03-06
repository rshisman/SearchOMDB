package com.example.movielocator.dm;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="movies")
public class Movie implements Serializable {

    private static final long serialVersionUID = 436933635106433534L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @Column(name="imdb_id")
    private String imdbID;

    @Column(name="title")
    private String title;

    @Column(name="release_year")
    private Integer year;

    @Column(name="poster")
    private String poster;

    private transient Boolean isFromDB = Boolean.TRUE;

    public Movie(){}

    public Movie(String imdbId, String title, Integer year, String poster) {
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

    public Boolean getFromDB() {
        return isFromDB;
    }

    public void setFromDB(Boolean fromDB) {
        isFromDB = fromDB;
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
