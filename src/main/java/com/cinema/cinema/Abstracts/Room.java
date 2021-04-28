package com.cinema.cinema.Abstracts;

import java.io.Serializable;

public class Room {
    private Long roomId;
    private String room;
    private int chairs;

    public Room() {
    }

    public Room(Long roomId, String room, int chairs) {
        this.roomId = roomId;
        this.room = room;
        this.chairs = chairs;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public int getChairs() {
        return chairs;
    }

    public void setChairs(int chairs) {
        this.chairs = chairs;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomId=" + roomId +
                ", room='" + room + '\'' +
                ", chairs=" + chairs +
                '}';
    }
}
