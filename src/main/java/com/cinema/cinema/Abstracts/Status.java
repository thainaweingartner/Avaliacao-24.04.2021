package com.cinema.cinema.Abstracts;

public enum Status {
    SHOWING,
    SOON,
    OFF,
    PRESALE;

    public static Status getStatus(String status){
        for(Status movie_status:Status.values()){
            if(status.toString().equals(status.toUpperCase())){
                return movie_status;
            }
        }
        return null;
    }
}
