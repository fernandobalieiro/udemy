package org.acme;

import io.fabric8.kubernetes.client.KubernetesClient;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.stream.Collectors;

@Path("/weather")
public class WeatherResource {

    @Inject
    KubernetesClient client;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return client.pods().list().getItems().stream().map(it -> it.getStatus().getPodIP()).collect(Collectors.joining(", "));
    }
}
