package org.acme;

import org.acme.data.Book;
import org.neo4j.driver.Driver;
import org.neo4j.driver.async.ResultCursor;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import java.util.concurrent.CompletionStage;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.neo4j.driver.Values.parameters;

@Path("/books")
@Produces(APPLICATION_JSON)
public class BookResource {

    @Inject
    Driver driver;

    @GET
    public CompletionStage<Response> list() {
        final var session = driver.asyncSession();
        return session.runAsync("MATCH (b:Book) RETURN b ORDER BY b.title")
                .thenCompose(cursor -> cursor.listAsync(record -> Book.from(record.get("b").asNode())))
                .thenCompose(books -> session.closeAsync().thenApply(it -> books))
                .thenApply(Response::ok)
                .thenApply(ResponseBuilder::build);
    }

    @POST
    @Consumes(APPLICATION_JSON)
    public CompletionStage<Response> create(final Book book) {
        final var session = driver.asyncSession();
        return session.writeTransactionAsync(tx ->
                tx.runAsync("CREATE (b:Book {title: $title, pages: $pages}) RETURN b",
                        parameters("title", book.getTitle(), "pages", book.getPages()))
                        .thenCompose(ResultCursor::singleAsync))
                .thenApply(record -> Book.from(record.get("b").asNode()))
                .thenCompose(createdBook -> session.closeAsync().thenApply(it -> createdBook))
                .thenApply(Response::ok)
                .thenApply(ResponseBuilder::build);
    }
}
