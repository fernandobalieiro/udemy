package org.acme

import org.acme.data.Book
import org.eclipse.microprofile.faulttolerance.CircuitBreaker
import org.eclipse.microprofile.faulttolerance.Fallback
import org.eclipse.microprofile.faulttolerance.Retry
import org.eclipse.microprofile.faulttolerance.Timeout
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.Random
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType.APPLICATION_JSON

@Path("/book")
class BookResource {

    @GET
    @Produces(APPLICATION_JSON)
    @Timeout(3000)
    fun getBooks(): List<Book> {
        Thread.sleep(2000)
        return getBooksList()
    }

    @Retry(maxRetries = 4, delay = 1000L)
    fun getBooksList(): List<Book> {
        val fail = Random().nextBoolean()
        if (fail) {
            logger.info("Couldn't connect to database")
            throw RuntimeException("Couldn't connect to database")
        }
        return listOf(
                Book("1", "My first Book", "My first author"),
                Book("2", "My second Book", "My second author"),
                Book("3", "My third Book", "My third author")
        )
    }

    @GET
    @Path("/fallback")
    @Produces(APPLICATION_JSON)
    @Fallback(fallbackMethod = "getFallbackBooksList")
    fun getFallbackBooks(): List<Book> {
        val fail = Random().nextBoolean()
        if (fail) {
            logger.info("Couldn't connect to database")
            throw RuntimeException("Couldn't connect to database")
        }
        return getBooksList()
    }

    @GET
    @Path("/circuit-breaker")
    @Produces(APPLICATION_JSON)
    @CircuitBreaker(failureRatio = 0.5, requestVolumeThreshold = 5, failOn = [RuntimeException::class], delay = 20000)
    fun getCircuitBreakerBooks(): List<Book> {
        val fail = Random().nextBoolean()
        if (fail) {
            logger.info("Couldn't connect to database")
            throw RuntimeException("Couldn't connect to database")
        }
        return getBooksList()
    }

    fun getFallbackBooksList(): List<Book> {
        logger.info("Fallback")
        return listOf(
                Book("1", "Fallback of My first Book", "Fallback of My first author"),
                Book("2", "Fallback of My second Book", "Fallback of My second author"),
                Book("3", "Fallback of My third Book", "Fallback of My third author")
        )
    }

    private companion object {
        val logger: Logger = LoggerFactory.getLogger(BookResource::class.java)
    }
}
