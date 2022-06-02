package io.swagger.domain;

public class Order {
    private final int id;
    private final String name;

    private Order(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Order of(int id, String name) {
        return new Order(id, name);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
