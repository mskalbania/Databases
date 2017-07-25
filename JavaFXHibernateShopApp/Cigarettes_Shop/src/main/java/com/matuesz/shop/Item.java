package com.matuesz.shop;

public class Item {

    private String id;
    private String name;
    private double price;
    private int quantityAtStock;
    private ItemType type;

    public Item(String id, String name, double price, int quantityAtStock, ItemType type) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantityAtStock = quantityAtStock;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantityAtStock() {
        return quantityAtStock;
    }

    public ItemType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "ID: " + id +" NAME: " + name + " PRICE: " + price + " QUANTITY: "
                + quantityAtStock + " TYPE: " + type;
    }

    public static ItemBuilder build() {
        return new ItemBuilder();
    }

}
