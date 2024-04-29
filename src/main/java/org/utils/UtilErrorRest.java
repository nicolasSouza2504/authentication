package org.utils;

import com.google.gson.Gson;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

public class UtilErrorRest {

    public static void throwResponseError(String message) {
        throw new WebApplicationException(Response.status(400).entity(new Gson().toJson(new ResponseDto(message, true))).build());
    }
}
