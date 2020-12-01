package org.acme;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.SECONDS;
import static javax.jms.Session.AUTO_ACKNOWLEDGE;

@ApplicationScoped
public class PriceProducer implements Runnable {
    private final Random random = new Random();
    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    @Inject
    ConnectionFactory factory;

    public void onStart(@Observes final StartupEvent event) {
        executorService.scheduleWithFixedDelay(this, 0L, 5L, SECONDS);
    }

    public void onStop(@Observes final ShutdownEvent event) {
        executorService.shutdown();
    }

    @Override
    public void run() {
        try (final var context = factory.createContext(AUTO_ACKNOWLEDGE)) {
            context.createProducer().send(context.createQueue("prices"), Integer.toString(random.nextInt(100)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
