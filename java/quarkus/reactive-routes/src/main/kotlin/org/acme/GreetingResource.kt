package org.acme

import io.quarkus.vertx.web.Route
import io.quarkus.vertx.web.RouteBase
import io.quarkus.vertx.web.RouteFilter
import io.quarkus.vertx.web.RoutingExchange
import io.vertx.core.http.HttpMethod.GET
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.event.Observes
import javax.ws.rs.core.MediaType

@ApplicationScoped
@RouteBase(path = "test", produces = [MediaType.APPLICATION_JSON])
class GreetingResource {

    @Route(path = "/hello", methods = [GET])
    fun hello(routingContext: RoutingContext) = routingContext.response().end("hello")

    @Route(path = "/hello-exchange", methods = [GET])
    fun helloExchange(routingExchange: RoutingExchange) = routingExchange.ok("hello")

    fun init(@Observes router: Router) {
        router.get("/book").method(GET).handler { rc ->
            rc.response().end("Book1,Book2")
        }
    }

    @RouteFilter
    fun myFilter(routingContext: RoutingContext) {
        routingContext.response().putHeader("test", "test")
        routingContext.next()
    }
}
