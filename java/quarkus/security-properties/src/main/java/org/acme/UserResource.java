package org.acme;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import static javax.ws.rs.core.MediaType.TEXT_PLAIN;

@Path("/users")
public class UserResource {

    @GET
    @RolesAllowed("User")
    @Produces(TEXT_PLAIN)
    public String hello() {
        return "Hello User";
    }

    @GET
    @Path("admin")
    @RolesAllowed("Admin")
    @Produces(TEXT_PLAIN)
    public String admin() {
        return "Hello Admin";
    }

    @GET
    @Path("dev")
    @RolesAllowed("Dev")
    @Produces(TEXT_PLAIN)
    public String dev() {
        return "Hello Dev";
    }
}
