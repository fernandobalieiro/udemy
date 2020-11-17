package org.acme.book

import org.jboss.resteasy.annotations.jaxrs.PathParam
import java.net.URI
import java.util.UUID
import javax.ws.rs.DELETE
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.PUT
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType.APPLICATION_JSON
import javax.ws.rs.core.Response
import javax.ws.rs.core.Response.Status.NOT_FOUND

@Produces(APPLICATION_JSON)
@Path("/book")
class BookResource {
    private var books: MutableList<Book> = mutableListOf(Book(title = "Foundation", author = "Isaac Asimov", year = 1951))

    @GET
    fun list(): List<Book> = this.books

    @GET
    @Path("/{id}")
    fun get(@PathParam id: String): Response {
        val book = this.books.find { item -> item.id == UUID.fromString(id) } ?: return Response.status(NOT_FOUND).build()
        return Response.ok().entity(book).build()
    }

    @POST
    fun add(book: Book): Response {
        this.books.add(book)
        return Response.created(URI("/book/${book.id}")).entity(book).build()
    }

    @PUT
    @Path("/{id}")
    fun update(@PathParam id: String, book: Book): Response {
        val bookToUpdate = this.books.find { item -> item.id == UUID.fromString(id) } ?: return Response.status(NOT_FOUND).build()

        bookToUpdate.title = book.title
        bookToUpdate.author = book.author
        bookToUpdate.year = book.year

        return Response.ok().entity(bookToUpdate).build()
    }

    @DELETE
    @Path("/{id}")
    fun delete(@PathParam id: String): Response {
        val bookToDelete = this.books.find { item -> item.id == UUID.fromString(id) } ?: return Response.status(NOT_FOUND).build()

        this.books.remove(bookToDelete)

        return Response.ok().entity(bookToDelete).build()
    }
}
