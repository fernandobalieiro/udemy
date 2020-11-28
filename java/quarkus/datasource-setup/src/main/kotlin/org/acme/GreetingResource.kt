package org.acme

import io.agroal.api.AgroalDataSource
import io.quarkus.agroal.DataSource
import javax.inject.Inject
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType.TEXT_PLAIN

@Path("/hello")
class GreetingResource {

    @Inject
    @DataSource("mysql")
    lateinit var mySqlDataSource: AgroalDataSource

    @Inject
    @DataSource("h2")
    lateinit var h2DataSource: AgroalDataSource

    @GET
    @Produces(TEXT_PLAIN)
    fun hello(): String {
        val call = mySqlDataSource.connection.prepareCall("select * from greeting")
        call.execute()
        val resultSet = call.resultSet
        resultSet.next()
        resultSet.next()
        return resultSet.getString(2)
    }
}
