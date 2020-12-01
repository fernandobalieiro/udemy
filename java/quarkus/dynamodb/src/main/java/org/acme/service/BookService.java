package org.acme.service;

import org.acme.data.Book;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import software.amazon.awssdk.services.dynamodb.model.ScanRequest;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;

import static java.util.stream.Collectors.toList;

@ApplicationScoped
public class BookService {
    public static final String BOOKS_TABLE = "Books";
    public static final String BOOK_TITLE_COL = "title";
    public static final String BOOK_PAGES_COL = "pages";

    @Inject
    DynamoDbClient client;

    public void add(final Book book) {
        client.putItem(putItemRequest(book));
    }

    public List<Book> findAll() {
        return client.scanPaginator(scanRequest()).items().stream()
                .map(Book::from)
                .collect(toList());
    }

    public Book get(final String title) {
        return Book.from(client.getItem(getItemRequest(title)).item());
    }

    private ScanRequest scanRequest() {
        return ScanRequest.builder()
                .tableName(BOOKS_TABLE)
                .attributesToGet(BOOK_TITLE_COL, BOOK_PAGES_COL)
                .build();
    }

    private GetItemRequest getItemRequest(final String title) {
        final var item = new HashMap<String, AttributeValue>();
        item.put(BOOK_TITLE_COL, AttributeValue.builder().s(title).build());
        return GetItemRequest.builder()
                .tableName(BOOKS_TABLE)
                .key(item)
                .attributesToGet(BOOK_TITLE_COL, BOOK_PAGES_COL)
                .build();
    }

    private PutItemRequest putItemRequest(final Book book) {
        final var item = new HashMap<String, AttributeValue>();
        item.put(BOOK_TITLE_COL, AttributeValue.builder().s(book.getTitle()).build());
        item.put(BOOK_PAGES_COL, AttributeValue.builder().n(book.getPages().toString()).build());
        return PutItemRequest.builder()
                .tableName(BOOKS_TABLE)
                .item(item)
                .build();
    }
}
