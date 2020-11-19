package org.acme.service

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient
import org.jboss.resteasy.annotations.jaxrs.QueryParam
import java.util.Currency
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType.APPLICATION_JSON

@RegisterRestClient(configKey = "api.crypto")
@Path("/ticker")
interface CurrencyService {

    @GET
    @Produces(APPLICATION_JSON)
    fun getCurrencyById(@QueryParam id: String): List<Currency>
}
