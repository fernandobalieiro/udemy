package org.acme;

import io.quarkus.runtime.StartupEvent;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

import static org.eclipse.microprofile.health.HealthCheckResponse.down;
import static org.eclipse.microprofile.health.HealthCheckResponse.up;

@Readiness
@ApplicationScoped
public class ReadinessCheck implements HealthCheck {

    public static boolean READY = false;

    public void onStart(@Observes final StartupEvent event) throws InterruptedException {
        new Thread(() -> {
            try {
                Thread.sleep(15000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            READY = true;
        }).start();
    }

    @Override
    public HealthCheckResponse call() {
        return READY ? up("Ready") : down("Down");
    }
}
