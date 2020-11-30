package org.acme;

import org.acme.data.Weather;
import org.acme.service.WeatherService;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/weather")
public class WeatherResource {

    @Inject
    WeatherService weatherService;

    @GET
    @Produces(APPLICATION_JSON)
    public Weather getWeather(@QueryParam final String city) {
        return weatherService.provideWeather(city);
    }

    @DELETE
    @Produces(APPLICATION_JSON)
    public Response invalidate(@QueryParam final String city) {
        if (city != null && !city.isBlank()) {
            weatherService.invalidate(city);
        } else {
            weatherService.invalidateAll();
        }
        return Response.accepted().build();
    }
}
