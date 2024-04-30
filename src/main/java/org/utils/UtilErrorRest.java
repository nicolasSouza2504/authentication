package org.utils;

import com.google.gson.Gson;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

public class UtilErrorRest {

    public static void throwResponseError(String message) {
        throwResponseError(message, 400);
    }

    public static void throwResponseError(String message, Integer status) {
        throw new WebApplicationException(Response.status(status).entity(new Gson().toJson(new ResponseDto(message, true))).build());
    }

}
