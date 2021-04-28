package com.cinema.cinema.Session;

import com.cinema.cinema.ConnectionFactory.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SessionDAO {

    public void createTableSession() {
        String sql = "CREATE TABLE IF NOT EXISTS session (" +
                "sessionId BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT," +
                "time VARCHAR(20) NOT NULL," +
                "movieId BIGINT NOT NULL," +
                "cinemaId BIGINT NOT NULL," +
                "room VARCHAR(20) NOT NULL," +
                "chairs INT NOT NULL,," +
                "price VARCHAR(20) NOT NULL," +
                "CONSTRAINT fk_idMovie FOREIGN KEY (movieId)" +
                "REFERENCES movie(movieId)," +
                "CONSTRAINT fk_idCinema FOREIGN KEY (cinemaId)" +
                "REFERENCES cinema(cinemaId)" +
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

    public Session save(Session session){
        String sql = "INSERT INTO session (time, movieId, cinemaId, roomId, price) VALUES (?,?,?,?)";
        Connection connection = ConnectionFactory.getConnection();

        try{
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, session.getTime());
            pst.setLong(2, session.getMovie().getMovieId());
            pst.setLong(3, session.getCinema().getCineId());
            pst.setLong(4, session.getRoom().getRoomId());
            pst.setString(5, session.getPrice());

            int executedSuccessfully = pst.executeUpdate();
            pst.close();
            if(executedSuccessfully > 0) {
                System.out.println("Sessão criada com sucesso!");
                return session;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public Session update(Long sessionId, Session session){
        if (session == null || session.getSessionId() == null) {
            System.out.println("Sessão não encontrada");
            return null;
        }
        String sql = "UPDATE session SET time=?, movieId=?, cinemaId=?, roomId=?, price=?  WHERE (sessionId=?)";
        Connection connection = ConnectionFactory.getConnection();

        try {
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, session.getTime());
            pst.setLong(2, session.getMovie().getMovieId());
            pst.setLong(3, session.getCinema().getCineId());
            pst.setLong(4, session.getRoom().getRoomId());
            pst.setString(5, session.getPrice());
            pst.setString(6, String.valueOf(sessionId));

            int executedSuccessfully = pst.executeUpdate();
            pst.close();

            if (executedSuccessfully > 0) {
                System.out.println("Sessão atualizada: "+ session.getSessionId());
                return session;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public void delete(Long sessionId){
        if (sessionId == null){
            System.out.println("Sessão não encontrada");
            return;
        }
        String sql = "DELETE FROM session WHERE (sessionId=?)";
        Connection connection = ConnectionFactory.getConnection();

        try {
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, String.valueOf(sessionId));

            int executedSuccessfully = pst.executeUpdate();
            pst.close();
            if(executedSuccessfully > 0){
                System.out.println("Sessão deletada: "+ sessionId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Session> listAll(){
        String sql = "SELECT sessionId, time, movieId, cinemaId, roomId, price FROM session";
        Connection connection = ConnectionFactory.getConnection();
        List<Session> sessionList = new ArrayList<>();

        try {
            PreparedStatement pst = connection.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while(rs.next()) {
                sessionList.add(new Session(rs.getLong(1), rs.getString(2), rs.getLong(3), rs.getLong(4), rs.getLong(5), rs.getString(6)));
            }

            pst.close();
            return sessionList;
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public  Session findById(Long sessionId) {
        String sql = "SELECT sessionId, time, movieId, cinemaId, roomId, price FROM session WHERE (sessionId=?)";
        Connection connection = ConnectionFactory.getConnection();
        Session searched_session = new Session();

        try {
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1,String.valueOf(sessionId));
            ResultSet rs = pst.executeQuery();

            while(rs.next()) {
                searched_session = new Session(rs.getLong(1), rs.getString(2), rs.getLong(3), rs.getLong(4), rs.getLong(5), rs.getString(6));
            }
            pst.close();

            return searched_session;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
