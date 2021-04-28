package com.cinema.cinema.Cinema;

import com.cinema.cinema.ConnectionFactory.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CinemaDAO {


    public void createTableCinema() {
        String sql = "CREATE TABLE IF NOT EXISTS cinema (" +
                "cineId BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT," +
                "name VARCHAR(20) NOT NULL," +
                "open_time VARCHAR(20) NOT NULL," +
                "close_time  VARCHAR(20) NOT NULL," +
                "phone VARCHAR(20) NOT NULL" +
                ");";

        Connection connection = ConnectionFactory.getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.execute();
            stmt.close();

            System.out.println("Tabela Sessões criada com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Cinema save(Cinema cinema){
        String sql = "INSERT INTO cinema (name, open_time, close_time, phone) VALUES (?,?,?,?,?)";
        Connection connection = ConnectionFactory.getConnection();

        try{
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, cinema.getName());
            pst.setString(2, cinema.getOpen_time());
            pst.setString(3, cinema.getClose_time());
            pst.setString(4, cinema.getPhone());

            int executedSuccessfully = pst.executeUpdate();
            pst.close();
            if(executedSuccessfully > 0) {
                System.out.println("Cinema criado com sucesso!");
                return cinema;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public Cinema update(Long cineId, Cinema cinema){
        if (cinema == null || cinema.getCineId() == null) {
            System.out.println("Cinema não encontrado");
            return null;
        }
        String sql = "UPDATE cinema SET name=?, open_time=?, close_time=?, phone=? WHERE (cineId=?)";
        Connection connection = ConnectionFactory.getConnection();

        try {
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, cinema.getName());
            pst.setString(2, cinema.getOpen_time());
            pst.setString(3, cinema.getClose_time());
            pst.setString(4, cinema.getPhone());
            pst.setString(5, String.valueOf(cineId));

            int executedSuccessfully = pst.executeUpdate();
            pst.close();

            if (executedSuccessfully > 0) {
                System.out.println("Cinema atualizado: "+ cinema.getName());
                return cinema;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public void delete(Long cineId){
        if (cineId == null){
            System.out.println("Cinema não encontrado");
            return;
        }
        String sql = "DELETE FROM cinema WHERE (cineId=?)";
        Connection connection = ConnectionFactory.getConnection();

        try {
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, String.valueOf(cineId));

            int executedSuccessfully = pst.executeUpdate();
            pst.close();
            if(executedSuccessfully > 0){
                System.out.println("Cinema deletado: "+ cineId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Cinema> listAll(){
        String sql = "SELECT cineId, name, open_time, close_time, phone FROM cinema";
        Connection connection = ConnectionFactory.getConnection();
        List<Cinema> cinemaList = new ArrayList<>();

        try {
            PreparedStatement pst = connection.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while(rs.next()) {
                cinemaList.add(new Cinema(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }

            pst.close();
            return cinemaList;
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public  Cinema findById(Long cineId) {
        String sql = "SELECT cineId, name, open_time, close_time, phone FROM cinema WHERE (cineId=?)";
        Connection connection = ConnectionFactory.getConnection();
        Cinema searched_cinema = new Cinema();

        try {
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1,String.valueOf(cineId));
            ResultSet rs = pst.executeQuery();

            while(rs.next()) {
                searched_cinema = new Cinema(rs.getLong(1), rs.getString(2), rs.getString(3),  rs.getString(4), rs.getString(5));
            }
            pst.close();

            return searched_cinema;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
