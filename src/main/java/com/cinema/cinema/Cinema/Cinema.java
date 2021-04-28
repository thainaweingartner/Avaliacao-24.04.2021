package com.cinema.cinema.Cinema;

public class Cinema {
    private Long cineId;
    private String name;
    private String open_time;
    private String close_time;
    private String phone;

    public Cinema() {}

    public Cinema(Long cineId, String name, String open_time, String close_time, String phone) {
        this.cineId = cineId;
        this.name = name;
        this.open_time = open_time;
        this.close_time = close_time;
        this.phone = phone;
    }

    public Cinema(String name, String open_time, String close_time, String phone) {
        this.name = name;
        this.open_time = open_time;
        this.close_time = close_time;
        this.phone = phone;
    }

    public Long getCineId() {
        return cineId;
    }

    public void setCineId(Long cineId) {
        this.cineId = cineId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOpen_time() {
        return open_time;
    }

    public void setOpen_time(String open_time) {
        this.open_time = open_time;
    }

    public String getClose_time() {
        return close_time;
    }

    public void setClose_time(String close_time) {
        this.close_time = close_time;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Cinema{" +
                "cineId=" + cineId +
                ", name='" + name + '\'' +
                ", open_time='" + open_time + '\'' +
                ", close_time='" + close_time + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
