package org.services.handler;

import com.google.gson.Gson;
import io.vertx.core.json.JsonObject;
import jakarta.annotation.Priority;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.client.ResponseProcessingException;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Cookie;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import org.dto.Session;
import org.services.redis.RedisService;

import java.util.Map;

@Provider
@Dependent
@Priority(Priorities.AUTHORIZATION)
public class RequestHandler implements ContainerRequestFilter {

    @Inject
    RedisService redisService;

    @Override
    public void filter(ContainerRequestContext requestContext) {

        String path = requestContext.getUriInfo().getPath();

        if (!publicPath(path)) {

            Session session = verifySession(requestContext);

            if (session == null) {
                throw new ResponseProcessingException(Response.serverError().status(404).build(), "Unauthorized");
            }

        }

    }

    public Session verifySession(ContainerRequestContext requestContext) {

        String authToken = getAuthToken(requestContext);

        JsonObject jsonSession = (JsonObject) redisService.get(authToken);

        return jsonSession != null ? new Gson().fromJson(jsonSession.toString(), Session.class) : null;

    }

    private String getAuthToken(ContainerRequestContext requestContext) {

        Map<String, Cookie> cookies = requestContext.getCookies();

        if (cookies.containsKey("authToken")) {

            Cookie authToken = cookies.get("authToken");

            if (authToken != null) {
                return authToken.getValue();
            }

        }

        return null;

    }

    public Boolean publicPath(String path) {
        return path != null && path.equals("/login");
    }

}
