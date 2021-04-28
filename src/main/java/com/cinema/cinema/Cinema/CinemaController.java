package com.cinema.cinema.Cinema;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Path("/cinema")
public class CinemaController {

    private final CinemaDAO cinemaDAO = new CinemaDAO();

    @GET
    @Path("/createtable")
    @Produces("application/json")
    public Response createTableCinema() {
        cinemaDAO.createTableCinema();
        return Response.status(202).entity("Tabela de Cinemas criada com sucesso").build();
    }

    @GET
    @Produces("application/json")
    public List<Cinema> listCinemas(){

        return CinemaDAO.listAll();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response createCinema(Cinema cinema) throws URISyntaxException {
        Cinema new_cine = cinemaDAO.save(cinema);

        GenericEntity<Cinema> entity = new GenericEntity<>(cinema, Cinema.class);
        return Response.created(new URI("/api/cinema/"+new_cine.getCineId())).entity(entity).build();
    }

    @PUT
    @Path("/{cineId}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response editCinema(@PathParam("cineId")  Long cineId, Cinema cinema){
        Cinema new_cine = cinemaDAO.update(cineId, cinema);

        GenericEntity<Cinema> entity = new GenericEntity<>(new_cine, Cinema.class);
        return Response.ok().entity(entity).build();
    }

    @DELETE
    @Path("/{cineId}")
    public Response delete(@PathParam("cineId") Long cineId) {
        cinemaDAO.delete(cineId);

        return Response.status(202).entity("Deleted cinema"+cineId).build();
    }

    @GET
    @Path("/{cineId}")
    @Produces("application/json")
    public Response findCinemaById(@PathParam("cineId") Long cineId) {
        Cinema cinema = cinemaDAO.findById(cineId);

        GenericEntity<Cinema> entity = new GenericEntity<>(cinema, Cinema.class);
        return Response.ok().entity(entity).build();
    }
}
