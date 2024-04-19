package org.resources.teste;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/testes")
public class Test {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response authorized() {
        return Response.ok("Authorized").status(200).build();
    }

}
