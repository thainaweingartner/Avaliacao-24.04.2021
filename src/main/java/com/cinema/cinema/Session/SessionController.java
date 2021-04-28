package com.cinema.cinema.Session;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Path("/session")
public class SessionController {

    private final SessionDAO sessionDAO = new SessionDAO();

    @GET
    @Path("/createtable")
    @Produces("application/json")
    public Response createTableSession() {
        sessionDAO.createTableSession();
        return Response.status(202).entity("Tabela de Sessões criada com sucesso").build();
    }

    @GET
    @Produces("application/json")
    public List<Session> listSessions(){

        return SessionDAO.listAll();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response createSession(Session session) throws URISyntaxException {
        Session new_cine = sessionDAO.save(session);

        GenericEntity<Session> entity = new GenericEntity<>(session, Session.class);
        return Response.created(new URI("/api/session/"+new_cine.getSessionId())).entity(entity).build();
    }

    @PUT
    @Path("/{sessionId}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response editCinema(@PathParam("sessionId")  Long sessionId, Session session){
        Session new_session = sessionDAO.update(sessionId, session);

        GenericEntity<Session> entity = new GenericEntity<>(new_session, Session.class);
        return Response.ok().entity(entity).build();
    }

    @DELETE
    @Path("/{sessionId}")
    public Response delete(@PathParam("sessionId") Long sessionId) {
        sessionDAO.delete(sessionId);

        return Response.status(202).entity("Deleted session"+sessionId).build();
    }

    @GET
    @Path("/{sessionId}")
    @Produces("application/json")
    public Response findCinemaById(@PathParam("sessionId") Long sessionId) {
        Session session = sessionDAO.findById(sessionId);

        GenericEntity<Session> entity = new GenericEntity<>(session, Session.class);
        return Response.ok().entity(entity).build();
    }
}
