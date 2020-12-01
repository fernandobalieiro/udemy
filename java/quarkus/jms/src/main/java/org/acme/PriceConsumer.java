package org.acme;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static javax.jms.Session.AUTO_ACKNOWLEDGE;

@ApplicationScoped
public class PriceConsumer implements Runnable {
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    private String lastPrice;

    @Inject
    ConnectionFactory factory;

    public String getLastPrice() {
        return lastPrice;
    }

    public void onStart(@Observes final StartupEvent event) {
        executorService.execute(this);
    }

    public void onStop(@Observes final ShutdownEvent event) {
        executorService.shutdown();
    }

    @Override
    public void run() {
        try (final var context = factory.createContext(AUTO_ACKNOWLEDGE)) {
            final var consumer = context.createConsumer(context.createQueue("prices"));
            while (true) {
                final var message = consumer.receive();
                if (message == null) {
                    return;
                }
                lastPrice = message.getBody(String.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
