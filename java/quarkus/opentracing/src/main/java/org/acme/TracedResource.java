package org.acme;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class TracedResource {

    private static Logger LOGGER = LoggerFactory.getLogger(TracedResource.class);

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        LOGGER.info("Hello!");
        LOGGER.info("How");
        LOGGER.info("Are");
        LOGGER.info("You?");
        return "Hello RESTEasy";
    }
}
