package org.acme

import org.acme.data.MultipartBody
import org.acme.service.CurrencyService
import org.acme.service.FileService
import org.eclipse.microprofile.rest.client.inject.RestClient
import org.jboss.resteasy.annotations.jaxrs.QueryParam
import java.util.Currency
import javax.inject.Inject
import javax.ws.rs.Consumes
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType.APPLICATION_JSON
import javax.ws.rs.core.MediaType.MULTIPART_FORM_DATA
import javax.ws.rs.core.MediaType.TEXT_PLAIN
import kotlin.text.Charsets.UTF_8

@Path("/crypto")
class CryptoCurrencyResource {

    @Inject
    @RestClient
    lateinit var currencyService: CurrencyService

    @Inject
    @RestClient
    lateinit var fileService: FileService

    @GET
    @Produces(APPLICATION_JSON)
    fun getCurrencyById(@QueryParam id: String): List<Currency> {
        return currencyService.getCurrencyById(id)
    }

    @POST
    @Path("echo")
    @Consumes(MULTIPART_FORM_DATA)
    @Produces(TEXT_PLAIN)
    fun echoFile(body: String) = body

    @GET
    @Path("echo_test")
    @Produces(TEXT_PLAIN)
    fun callEcho(): String {
        val multipartBody = MultipartBody(
                file = "Hello from file!".byteInputStream(UTF_8),
                name = "hello.txt"
        )
        return fileService.sendFile(multipartBody)
    }
}
