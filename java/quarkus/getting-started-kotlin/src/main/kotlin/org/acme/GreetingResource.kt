package org.acme

import org.eclipse.microprofile.config.inject.ConfigProperty
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("/hello")
class GreetingResource {

    @ConfigProperty(name = "greeting.name", defaultValue = "abc")
    lateinit var name: String

    @ConfigProperty(name = "greeting.base64Name", defaultValue = "abc")
    lateinit var base64Name: Base64Value

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    fun hello() = "hello $name"

    @GET
    @Path("/base64")
    @Produces(MediaType.TEXT_PLAIN)
    fun helloBase64() = "hello $base64Name"
}
