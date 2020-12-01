package org.acme;

import org.acme.data.Price;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.jboss.resteasy.annotations.SseElementType;
import org.reactivestreams.Publisher;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.SERVER_SENT_EVENTS;

@Path("prices")
public class PriceResource {

    @Inject
    @Channel("converted-prices")
    Publisher<Price> publisher;

    @GET
    @Path("/stream")
    @Produces(SERVER_SENT_EVENTS)
    @SseElementType(APPLICATION_JSON)
    public Publisher<Price> getPrices() {
        return publisher;
    }
}
