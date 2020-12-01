package org.acme.service;

import io.quarkus.vertx.ConsumeEvent;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GreetingService {

    @ConsumeEvent(value = "greeting")
    public String onMessage(final String name)  {
        return "Hello, " + name;
    }

//    @ConsumeEvent(value = "greeting")
//    public Uni<String> onMessageAsync(final Message<String> name)  {
//        return Uni.createFrom().item("Hello, " + name.body());
//    }
}
