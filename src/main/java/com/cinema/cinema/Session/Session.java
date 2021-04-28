package com.cinema.cinema.Session;

import com.cinema.cinema.Abstracts.Room;
import com.cinema.cinema.Cinema.Cinema;
import com.cinema.cinema.Movie.Movie;

public class Session {
    private Long sessionId;
    private String time;
    private Movie movie;
    private Cinema cinema;
    private Room room;
    private String price;

    public Session(long aLong, String string, long rsLong, long l, long aLong1, String rsString) {
    }

    public Session(Long sessionId, String time, Movie movie, Cinema cinema, Room room, String price) {
        this.sessionId = sessionId;
        this.time = time;
        this.movie = movie;
        this.cinema = cinema;
        this.room = room;
        this.price = price;
    }

    public Session(String time, Movie movie, Room room, Cinema cinema, String price) {
        this.time = time;
        this.movie = movie;
        this.cinema = cinema;
        this.room = room;
        this.price = price;
    }

    public Session() {

    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Session{" +
                "sessionId=" + sessionId +
                ", time='" + time + '\'' +
                ", movie=" + movie +
                ", cinema=" + cinema +
                ", room=" + room +
                ", price='" + price + '\'' +
                '}';
    }
}
