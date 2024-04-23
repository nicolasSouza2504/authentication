package org.resources.login;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/login")
public class LoginResource {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response login() {
        return Response.ok("{\"message\": \"LOGED IN \"}").build();
    }

}
