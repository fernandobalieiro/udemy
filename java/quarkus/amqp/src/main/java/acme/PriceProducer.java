package acme;

import io.reactivex.Flowable;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;
import java.util.Random;

import static java.util.concurrent.TimeUnit.SECONDS;

@ApplicationScoped
public class PriceProducer {

    private final Random random = new Random();

    @Outgoing("price")
    public Flowable<Integer> producePrice() {
        return Flowable.interval(1, SECONDS)
                .map(it -> random.nextInt(6000));
    }
}
