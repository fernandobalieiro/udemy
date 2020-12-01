package org.acme;

import io.reactivex.Flowable;
import org.acme.data.Price;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;
import java.util.Random;

import static java.util.concurrent.TimeUnit.SECONDS;

@ApplicationScoped
public class PriceProducer {

    private final Random random = new Random();

    @Outgoing("price")
    public Flowable<Price> producePrice() {
        return Flowable.interval(5, SECONDS)
                .map(it -> new Price((long) random.nextInt(6000), "BTC"));
    }
}
