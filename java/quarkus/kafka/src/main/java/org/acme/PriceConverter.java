package org.acme;

import io.smallrye.reactive.messaging.annotations.Broadcast;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PriceConverter {

    @Incoming("prices")
    @Outgoing("converted-prices")
    @Broadcast
    public double convert(long price) {
        return price * 1.5d;
    }
}
