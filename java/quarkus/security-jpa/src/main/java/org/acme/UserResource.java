package org.acme;

import io.quarkus.runtime.StartupEvent;
import org.acme.data.User;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.event.Observes;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/greeting")
@Produces(APPLICATION_JSON)
public class UserResource {

    @Transactional
    public void onStart(@Observes final StartupEvent event) {
        User.add("admin", "admin", "Admin,User");
        User.add("user", "user", "User");
    }

    @GET
    @RolesAllowed("User")
    public String getInfo(@Context final SecurityContext context) {
        // final var user = User.find("username", context.getUserPrincipal().getName());
        return "Hello " + context.getUserPrincipal().getName();
    }

    @GET
    @Path("admin")
    @RolesAllowed("Admin")
    public String admin(@Context final SecurityContext context) {
        return "Hello " + context.getUserPrincipal().getName();
    }
}
