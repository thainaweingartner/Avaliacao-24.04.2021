package com.cinema.cinema.Movie;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Path("/movie")
public class MovieController {

    private final MovieDAO movieDAO = new MovieDAO();

    @GET
    @Path("/createtable")
    @Produces("application/json")
    public Response createTableMovie() {
        movieDAO.createTableMovie();
        return Response.status(202).entity("Tabela de Filmes criada com sucesso").build();
    }

    @GET
    @Produces("application/json")
    public List<Movie> listMovies(){

        return MovieDAO.listAll();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response createMovie(Movie movie) throws URISyntaxException {
        Movie new_movie = movieDAO.save(movie);

        GenericEntity<Movie> entity = new GenericEntity<>(movie, Movie.class);
        return Response.created(new URI("/api/movie/"+new_movie.getMovieId())).entity(entity).build();
    }

    @PUT
    @Path("/{movieId}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response editCinema(@PathParam("movieId")  Long movieId, Movie movie){
        Movie new_movie = movieDAO.update(movieId, movie);

        GenericEntity<Movie> entity = new GenericEntity<>(new_movie, Movie.class);
        return Response.ok().entity(entity).build();
    }

    @DELETE
    @Path("/{movieId}")
    public Response delete(@PathParam("movieId") Long movieId) {
        movieDAO.delete(movieId);

        return Response.status(202).entity("Deleted movie"+movieId).build();
    }

    @GET
    @Path("/{movieId}")
    @Produces("application/json")
    public Response findCinemaById(@PathParam("movieId") Long movieId) {
        Movie movie = movieDAO.findById(movieId);

        GenericEntity<Movie> entity = new GenericEntity<>(movie, Movie.class);
        return Response.ok().entity(entity).build();
    }
}
