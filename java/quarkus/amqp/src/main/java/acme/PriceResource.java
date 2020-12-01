package acme;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.jboss.resteasy.annotations.SseElementType;
import org.reactivestreams.Publisher;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import static javax.ws.rs.core.MediaType.SERVER_SENT_EVENTS;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;

@Path("prices")
public class PriceResource {

    @Inject
    @Channel("converted-prices")
    Publisher<Double> publisher;

    @GET
    @Path("/stream")
    @Produces(SERVER_SENT_EVENTS)
    @SseElementType(TEXT_PLAIN)
    public Publisher<Double> getPrices() {
        return publisher;
    }
}
