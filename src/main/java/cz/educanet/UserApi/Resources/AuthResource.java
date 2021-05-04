package cz.educanet.UserApi.Resources;


import cz.educanet.UserApi.Managers.AuthManager;
import cz.educanet.UserApi.Wrappers.Classes.User;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
public class AuthResource {

    @Inject
    private AuthManager manager;

    /**
     *
     * @return usertoken, used to authenticate the user later in the app
     */
    @POST
    public Response login(User user) {
        Optional<String> token = manager.loginUser(user.getUsername(), user.getPassword());
        if (token.isPresent())
            return Response.ok(token).build();
        return Response.status(400).build();
    }

    @GET
    public Response getTokens(){
        return Response.ok(manager.getTokens()).build();
    }

    /**
     * @param token unique UUID string to identify users
     * @return 200 if token was there, thus deleting it, 404 if it doesnt exist, 400 if no token is passed
     */
    @DELETE
    public Response logout(@HeaderParam("token") String token) {
        if (manager.logoutUser(token))
            return Response.ok().build();
        return Response.status(404).build();
    }

}
