package org.resources.privateapi;

import com.google.gson.Gson;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.dto.ServerSession;
import org.utils.ResponseDto;

@Path("/private-api")
public class PrivateApi {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response authorized() {
        return Response.ok(new Gson().toJson(new ResponseDto(ServerSession.getSession().getUserName() + " is authorized", false))).status(200).build();
    }

}
