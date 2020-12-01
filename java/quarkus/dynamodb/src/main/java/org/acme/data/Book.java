package org.acme.data;

import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.Map;

import static org.acme.service.BookService.BOOK_PAGES_COL;
import static org.acme.service.BookService.BOOK_TITLE_COL;

public class Book {
    private String title;
    private Integer pages;

    public Book(String title, Integer pages) {
        this.title = title;
        this.pages = pages;
    }

    public static Book from(Map<String, AttributeValue> attributes) {
        return new Book(attributes.get(BOOK_TITLE_COL).s(), Integer.valueOf(attributes.get(BOOK_PAGES_COL).n()));
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }
}
