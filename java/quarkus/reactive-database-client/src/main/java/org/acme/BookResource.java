package org.acme;

import io.quarkus.runtime.StartupEvent;
import io.vertx.mutiny.mysqlclient.MySQLPool;
import org.acme.data.Book;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletionStage;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/book")
public class BookResource {

    @Inject
    MySQLPool pool;

    public void onStart(@Observes final StartupEvent event) {
        pool.query("DROP TABLE IF EXISTS books").execute()
                .stage(it -> pool.query("CREATE TABLE books(title text, pages int)").execute())
                .stage(it -> pool.query("INSERT INTO books VALUES('test book', 200)").execute())
                .subscribeAsCompletionStage()
                .join();
    }

    @GET
    @Produces(APPLICATION_JSON)
    public CompletionStage<List<Book>> list() {
        return pool.query("SELECT * FROM books").execute()
                .subscribeAsCompletionStage().thenApply(rows -> {
                            final var list = new ArrayList<Book>();
                            rows.forEach(row -> list.add(Book.from(row)));
                            return list;
                        }
                );
    }
}
