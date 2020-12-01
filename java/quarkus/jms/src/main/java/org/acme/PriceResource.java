package org.acme;

import org.jboss.resteasy.annotations.SseElementType;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import static javax.ws.rs.core.MediaType.TEXT_PLAIN;

@Path("prices")
public class PriceResource {

    @Inject
    PriceConsumer consumer;

    @GET
    @SseElementType(TEXT_PLAIN)
    public String getPrice() {
        return consumer.getLastPrice();
    }
}
