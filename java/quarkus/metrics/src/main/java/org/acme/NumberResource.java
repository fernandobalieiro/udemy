package org.acme;

import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Gauge;
import org.eclipse.microprofile.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.Random;

import static javax.ws.rs.core.MediaType.TEXT_PLAIN;
import static org.eclipse.microprofile.metrics.MetricUnits.MILLISECONDS;
import static org.eclipse.microprofile.metrics.MetricUnits.NONE;

@Path("/number")
public class NumberResource {

    private int lastNumber = 0;

    @GET
    @Timed(
            name = "exec_time",
            description = "How long it takes to a Random Number to return",
            unit = MILLISECONDS
    )
    @Counted(
            name = "exec_count",
            description = "How many times the Random Number was returned"
    )
    @Produces(TEXT_PLAIN)
    public String getRandomNumber() throws InterruptedException {
        final int randomInteger = new Random().nextInt(100);
        Thread.sleep(randomInteger * 100);
        lastNumber = randomInteger;
        return Integer.toString(randomInteger);
    }

    @Gauge(
            name = "last_number",
            description = "description",
            unit = NONE
    )
    public int getLastNumber() {
        return lastNumber;
    }
}
