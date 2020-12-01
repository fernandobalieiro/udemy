package org.acme.data;

public class Book {
    private String title;
    private Integer pages;

    public Book(String title, Integer pages) {
        this.title = title;
        this.pages = pages;
    }

    public String getTitle() {
        return title;
    }

    public Integer getPages() {
        return pages;
    }
}
