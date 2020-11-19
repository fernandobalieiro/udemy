package org.acme

import org.acme.service.CurrencyService
import org.eclipse.microprofile.rest.client.inject.RestClient
import org.jboss.resteasy.annotations.jaxrs.QueryParam
import java.util.Currency
import javax.inject.Inject
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType.APPLICATION_JSON

@Path("/crypto")
class CryptoCurrencyResource {

    @Inject
    @RestClient
    lateinit var currencyService: CurrencyService

    @GET
    @Produces(APPLICATION_JSON)
    fun getCurrencyById(@QueryParam id: String): List<Currency> {
        return currencyService.getCurrencyById(id)
    }
}
