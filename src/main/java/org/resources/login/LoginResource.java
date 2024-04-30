package org.resources.login;

import com.google.gson.Gson;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.dto.Session;
import org.services.login.LoginService;

@Path("/login")
public class LoginResource {

    @Inject
    LoginService loginService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(String jsonUser) {

        Session session = loginService.login(jsonUser);

        return Response.ok(new Gson().toJson(session)).build();

    }

}
