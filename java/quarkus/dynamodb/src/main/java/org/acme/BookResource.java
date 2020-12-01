package org.acme;

import org.acme.data.Book;
import org.acme.service.BookService;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/books")
@Produces(APPLICATION_JSON)
public class BookResource {

    @Inject
    BookService bookService;

    @GET
    public List<Book> list() {
        return bookService.findAll();
    }

    @POST
    public Book addBook(final Book book) {
        bookService.add(book);
        return book;
    }

    @GET
    @Path("/search")
    public Book getByTitle(@QueryParam final String title) {
        return bookService.get(title);
    }
}
