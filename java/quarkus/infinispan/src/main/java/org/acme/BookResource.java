package org.acme;

import io.quarkus.infinispan.client.Remote;
import org.acme.data.Book;
import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/book")
public class BookResource {

    @Inject
    @Remote("books")
    RemoteCache<String, Book> remoteCache;

    @Inject
    RemoteCacheManager cacheManager;

    @GET
    @Path("/{id}")
    @Produces(APPLICATION_JSON)
    public Book getById(@PathParam final String id) {
        return remoteCache.get(id);
    }

//    @POST
//    @Produces(APPLICATION_JSON)
//    @Consumes(APPLICATION_JSON)
//    public Book create(Book book) {
//        remoteCache.put(book.getId(), book);
//        return book;
//    }

    @POST
    @Produces(APPLICATION_JSON)
    @Consumes(APPLICATION_JSON)
    public Book create(@QueryParam final String id, @QueryParam final String title, @QueryParam final String author) {
        var book = new Book(id, title, author);
        remoteCache.put(book.getId(), book);
        return book;
    }

}
