package org.acme.service;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import org.acme.data.Book;
import org.bson.Document;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

@ApplicationScoped
public class BookService {
    @Inject
    MongoClient mongoClient;

    public void create(final Book book) {
        final var collection = getCollection();
        final var document = new Document();
        document.put("title", book.getTitle());
        document.put("pages", book.getPages());
        collection.insertOne(document);
    }

    public List<Book> listAll() {
        final var documents = getCollection().find();

        return StreamSupport.stream(documents.spliterator(), true)
                .map(document ->
                        new Book(
                                document.getString("title"),
                                document.getInteger("pages")
                        )
                )
                .collect(toList());
    }

    private MongoCollection<Document> getCollection() {
        return mongoClient.getDatabase("books").getCollection("books");
    }
}
