package com.matuesz.shop.item;

public class ItemBuilder {
    private int id;
    private String name;
    private double price;
    private int quantityAtStock;
    private ItemType type;

    public ItemBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public ItemBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ItemBuilder setPrice(double price) {
        this.price = price;
        return this;
    }

    public ItemBuilder setQuantityAtStock(int quantityAtStock) {
        this.quantityAtStock = quantityAtStock;
        return this;
    }

    public ItemBuilder setType(ItemType type) {
        this.type = type;
        return this;
    }

    public Item createItem() {
        return new Item(id, name, price, quantityAtStock, type);
    }
}