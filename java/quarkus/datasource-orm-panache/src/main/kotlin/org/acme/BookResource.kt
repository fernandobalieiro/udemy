package org.acme

import org.jboss.resteasy.annotations.jaxrs.PathParam
import javax.inject.Inject
import javax.persistence.LockModeType.PESSIMISTIC_WRITE
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
    lateinit var bookRepository: BookRepository

    @GET
    @Produces(APPLICATION_JSON)
    fun list() = Book.findAllBooks()

    @POST
    @Produces(APPLICATION_JSON)
    @Consumes(APPLICATION_JSON)
    @Transactional
    fun create(book: Book) {

        // Pagination Example
        // val query = Book.find("author", "test")
        // query.page(Page.of(2, 50))
        // val books = query.list()
        // query.nextPage()
        // query.pageCount()
        // Book.listAll(Sort.by("name").and("author"))

        return Book.persist(book)
    }

    @PUT
    @Path("/{id}")
    @Produces(APPLICATION_JSON)
    @Consumes(APPLICATION_JSON)
    @Transactional
    fun update(@PathParam id: Long, book: Book): Book {
        val bookToUpdate = Book.findById(id, PESSIMISTIC_WRITE)
        bookToUpdate!!.name = book.name
        bookToUpdate.author = book.author
        bookToUpdate.pages = book.pages
        Book.persist(bookToUpdate)
        return bookToUpdate
    }

    @DELETE
    @Path("/{id}")
    @Produces(APPLICATION_JSON)
    @Consumes(APPLICATION_JSON)
    @Transactional
    fun delete(@PathParam id: Long) {
        // Book.deleteById(id)
        bookRepository.deleteById(id)
    }
}
