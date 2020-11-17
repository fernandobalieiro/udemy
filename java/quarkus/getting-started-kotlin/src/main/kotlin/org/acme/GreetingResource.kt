package org.acme

import org.eclipse.microprofile.config.inject.ConfigProperty
import javax.inject.Inject
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType.TEXT_PLAIN

@Path("/hello")
class GreetingResource {

    @Inject
    lateinit var greetingConfig: GreetingConfig

    @ConfigProperty(name = "greeting.name", defaultValue = "abc")
    lateinit var name: String

    @ConfigProperty(name = "greeting.base64Name", defaultValue = "abc")
    lateinit var base64Name: Base64Value

    @GET
    @Produces(TEXT_PLAIN)
    fun hello(): String = "hello $name"

    @GET
    @Path("/base64")
    @Produces(TEXT_PLAIN)
    fun helloBase64() = "hello $base64Name"

    @GET
    @Path("/config")
    @Produces(TEXT_PLAIN)
    fun helloGreetingConfig() = "hello ${greetingConfig.prefix} ${greetingConfig.suffix}"
}
