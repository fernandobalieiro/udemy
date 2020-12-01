package org.acme.data;

public class Price {
    private Long price;
    private String name;

    public Price(Long price, String name) {
        this.price = price;
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
