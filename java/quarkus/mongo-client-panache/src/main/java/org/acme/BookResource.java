package org.acme;

import org.acme.data.Book;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/books")
@Produces(APPLICATION_JSON)
public class BookResource {

    @GET
    public List<Book> list() {
        return Book.listAll();
    }

    @POST
    @Consumes(APPLICATION_JSON)
    public Book create(final Book book) {
        Book.persistOrUpdate(book);
        return book;
    }
}
