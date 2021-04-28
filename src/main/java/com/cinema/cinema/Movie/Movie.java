package com.cinema.cinema.Movie;

import com.cinema.cinema.Abstracts.Category;
import com.cinema.cinema.Abstracts.Status;

public class Movie {
    private Long movieId;
    private String title;
    private String synopsis;
    private Category category;
    private String duration;
    private Status status;

    public Movie() {
    }

    public Movie(Long movieId, String title, String synopsis, Category category, String duration, Status status) {
        this.movieId = movieId;
        this.title = title;
        this.synopsis = synopsis;
        this.category = category;
        this.duration = duration;
        this.status = status;
    }

    public Movie(String title, String synopsis, Category category, String duration, Status status) {
        this.title = title;
        this.synopsis = synopsis;
        this.category = category;
        this.duration = duration;
        this.status = status;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", title='" + title + '\'' +
                ", synopsis='" + synopsis + '\'' +
                ", category='" + category + '\'' +
                ", duration='" + duration + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
