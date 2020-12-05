package org.acme;

import org.acme.service.TokenService;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.HashMap;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;
import static org.acme.jwt.utils.TokenUtils.ROLE_USER;

@Path("/profile")
@RequestScoped
@Produces(APPLICATION_JSON)
public class ProfileResource {

    @Inject
    TokenService tokenService;

    @GET
    @Produces(TEXT_PLAIN)
    @RolesAllowed(ROLE_USER)
    public String hello() {
        return "hello";
    }

    @POST
    @PermitAll
    public HashMap<String, String> register(@QueryParam final String username, @QueryParam final String email, @QueryParam final String birthdate) {
        final var token = tokenService.generateToken(username, email, birthdate);

        return new HashMap<>() {{
            put("token", token);
        }};
    }
}
