package org.acme;

import io.quarkus.security.identity.SecurityIdentity;
import org.jboss.resteasy.annotations.cache.NoCache;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("users")
@Produces(APPLICATION_JSON)
public class UserResource {

    @Inject
    SecurityIdentity securityIdentity;

    @GET
    //@RolesAllowed("user")
    @NoCache
    public SecurityIdentity getUserInfo() {
        return securityIdentity;
    }
    @GET
    @Path("admin")
    @RolesAllowed("admin")
    @NoCache
    public SecurityIdentity getAdminSecret() {
        return securityIdentity;
    }

}
