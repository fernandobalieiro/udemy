package org.acme;

import org.acme.data.Book;
import org.acme.service.BookService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/book")
public class BookResource {

    @Inject
    BookService bookService;

    @POST
    @Produces(APPLICATION_JSON)
    @Consumes(APPLICATION_JSON)
    public Book create(final Book book) {
        bookService.create(book);
        return book;
    }

    @GET
    @Produces(APPLICATION_JSON)
    public List<Book> list() {
        return bookService.listAll();
    }
}
