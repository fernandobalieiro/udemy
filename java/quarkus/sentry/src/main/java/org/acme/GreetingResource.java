package org.acme;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import static javax.ws.rs.core.MediaType.TEXT_PLAIN;

@Path("/hello-resteasy")
public class GreetingResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(GreetingResource.class);

    @GET
    @Produces(TEXT_PLAIN)
    public String hello() {
        LOGGER.info("Hello with Info!");
        LOGGER.error("Hello with Error!");
        LOGGER.warn("Hello with Warning!");
        return "Hello RESTEasy";
    }
}
