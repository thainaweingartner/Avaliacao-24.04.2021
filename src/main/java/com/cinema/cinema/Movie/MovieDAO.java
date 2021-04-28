package com.cinema.cinema.Movie;

import com.cinema.cinema.Abstracts.Category;
import com.cinema.cinema.Abstracts.Status;
import com.cinema.cinema.ConnectionFactory.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO {


    public void createTableMovie() {
        String sql = "CREATE TABLE IF NOT EXISTS movie (" +
                "movieId BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT," +
                "title VARCHAR(20) NOT NULL," +
                "category VARCHAR(20) NOT NULL," +
                "duration  VARCHAR(20) NOT NULL," +
                "status VARCHAR(20) NOT NULL" +
                ");";

        Connection connection = ConnectionFactory.getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.execute();
            stmt.close();

            System.out.println("Tabela de Filmes criada com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Movie save(Movie movie){
        String sql = "INSERT INTO movie (title, category, duration, status) VALUES (?,?,?,?,?)";
        Connection connection = ConnectionFactory.getConnection();

        try{
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, movie.getTitle());
            pst.setString(2, movie.getCategory().toString());
            pst.setString(3, movie.getDuration());
            pst.setString(4, movie.getStatus().toString());

            int executedSuccessfully = pst.executeUpdate();
            pst.close();
            if(executedSuccessfully > 0) {
                System.out.println("Filme criado com sucesso!");
                return movie;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public Movie update(Long movieId, Movie movie){
        if (movie == null || movie.getMovieId() == null) {
            System.out.println("Filme não encontrado");
            return null;
        }
        String sql = "UPDATE movie SET title=?, category=?, duration=?, status=? WHERE (movieId=?)";
        Connection connection = ConnectionFactory.getConnection();

        try {
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, movie.getTitle());
            pst.setString(2, movie.getCategory().toString());
            pst.setString(3, movie.getDuration());
            pst.setString(4, movie.getStatus().toString());
            pst.setString(5, String.valueOf(movieId));

            int executedSuccessfully = pst.executeUpdate();
            pst.close();

            if (executedSuccessfully > 0) {
                System.out.println("Filme atualizado: "+ movie.getTitle());
                return movie;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public void delete(Long movieId){
        if (movieId == null){
            System.out.println("Filme não encontrado");
            return;
        }
        String sql = "DELETE FROM movie WHERE (movieId=?)";
        Connection connection = ConnectionFactory.getConnection();

        try {
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, String.valueOf(movieId));

            int executedSuccessfully = pst.executeUpdate();
            pst.close();
            if(executedSuccessfully > 0){
                System.out.println("Filme deletado: "+ movieId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Movie> listAll(){
        String sql = "SELECT movieId, title, category, duration, status FROM movie";
        Connection connection = ConnectionFactory.getConnection();
        List<Movie> movieList = new ArrayList<>();

        try {
            PreparedStatement pst = connection.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while(rs.next()) {
                movieList.add(new Movie(rs.getString(1), rs.getString(2), Category.getCategory(rs.getString(3)), rs.getString(4), Status.getStatus(rs.getString(5))));
            }

            pst.close();
            return movieList;
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public  Movie findById(Long movieId) {
        String sql = "SELECT movieId, title, category, duration, status FROM movie WHERE (movieId=?)";
        Connection connection = ConnectionFactory.getConnection();
        Movie searched_movie = new Movie();

        try {
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, String.valueOf(movieId));
            ResultSet rs = pst.executeQuery();

            while(rs.next()) {
                searched_movie = new Movie(rs.getString(1), rs.getString(2), Category.getCategory(rs.getString(3)), rs.getString(4), Status.getStatus(rs.getString(5)));
            }
            pst.close();

            return searched_movie;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
