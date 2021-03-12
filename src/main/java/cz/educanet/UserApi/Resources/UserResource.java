package cz.educanet.UserApi.Resources;


import cz.educanet.UserApi.Managers.AuthManager;
import cz.educanet.UserApi.Managers.UserManager;
import cz.educanet.UserApi.ObjectClasses.User;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;



@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    UserManager manager;

    @Inject
    AuthManager auth;

    @GET
    public Response getUsers(@HeaderParam("token") String token){
       if (auth.validateToken(token))
           return Response.ok(manager.getUsers()).build();
       return Response.status(401).build();
    }

    @GET
    @Path("{id}")
    public Response getUser(@PathParam("id") String id, @HeaderParam("token") String token){
        if (auth.validateToken(token))
            return Response.ok(manager.specUser(Integer.parseInt(id))).build();
        return Response.status(401).build();
    }

    @POST
    public Response registerUser(User user){
        if (manager.createUser(user))
            return Response.ok().build();
        return Response.status(400).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteUser(@PathParam("id") int id, @HeaderParam("token") String token){
        if (auth.validateToken(token)) {
            if (manager.deleteUser(id))
                return Response.ok().build();
            else
                return Response.status(404).build();
        }
        return Response.status(401).build();
    }

}
