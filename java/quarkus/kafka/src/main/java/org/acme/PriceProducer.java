package org.acme;

import io.reactivex.Flowable;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;
import java.util.Random;

import static java.util.concurrent.TimeUnit.SECONDS;

@ApplicationScoped
public class PriceProducer {

    private Random random = new Random();

    @Outgoing("price")
    public Flowable<Long> producePrice() {
        return Flowable.interval(5, SECONDS)
                .map(it -> (long) random.nextInt(4000));
    }
}
