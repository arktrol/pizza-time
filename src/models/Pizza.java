package models;

import models.interfaces.IPizza;

public class Pizza implements IPizza {

    private final String name;
    private final double price;

    public Pizza( String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
