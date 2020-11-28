package org.acme

import org.jboss.resteasy.annotations.jaxrs.PathParam
import javax.inject.Inject
import javax.persistence.EntityManager
import javax.transaction.Transactional
import javax.ws.rs.Consumes
import javax.ws.rs.DELETE
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.PUT
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType.APPLICATION_JSON

@Path("/book")
class BookResource {

    @Inject
    lateinit var entityManager: EntityManager

    @GET
    @Produces(APPLICATION_JSON)
    fun list(): List<Book> {
        return entityManager.createQuery("select b from Book b", Book::class.java).resultList
    }

    @POST
    @Produces(APPLICATION_JSON)
    @Consumes(APPLICATION_JSON)
    @Transactional
    fun create(book: Book): Book {
        entityManager.persist(book)
        return book
    }

    @PUT
    @Path("/{id}")
    @Produces(APPLICATION_JSON)
    @Consumes(APPLICATION_JSON)
    @Transactional
    fun update(@PathParam id: Int, book: Book): Book {
        entityManager.merge(book)
        return book
    }

    @DELETE
    @Path("/{id}")
    @Produces(APPLICATION_JSON)
    @Consumes(APPLICATION_JSON)
    @Transactional
    fun delete(@PathParam id: Int): Book {
        val book = entityManager.createQuery("select b from Book b where id = $id", Book::class.java).singleResult
        entityManager.remove(book)
        return book
    }
}
