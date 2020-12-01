package org.acme;

import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.core.eventbus.EventBus;
import io.vertx.mutiny.core.eventbus.Message;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import static javax.ws.rs.core.MediaType.TEXT_PLAIN;

@Path("/greeting")
public class GreetingResource {

    @Inject
    EventBus eventBus;

    @GET
    @Produces(TEXT_PLAIN)
    public Uni<String> hello(@QueryParam final String name) {
        return eventBus.<String>request("greeting", name)
                .onItem().transform(Message::body);
    }
}
