package org.resources.user;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.model.UserLogin;
import org.services.user.UserService;

@Path("/register")
public class UserResource {

    @Inject
    UserService userService;

    @POST
    @Path("/user")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerUser(String jsonUser) {

        UserLogin user = userService.save(jsonUser);

        return Response.ok(user).status(200).build();

    }

}
