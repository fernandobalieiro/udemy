package org.acme.data;

import io.vertx.mutiny.sqlclient.Row;

public class Book {
    private final String title;
    private final Integer pages;

    public Book(String title, Integer pages) {
        this.title = title;
        this.pages = pages;
    }

    public static Book from(final Row row) {
        return new Book(row.getString("title"), row.getInteger("pages"));
    }

    public String getTitle() {
        return title;
    }

    public Integer getPages() {
        return pages;
    }
}
