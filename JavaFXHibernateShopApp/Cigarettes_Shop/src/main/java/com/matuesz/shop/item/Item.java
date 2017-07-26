package com.matuesz.shop.item;

import javax.persistence.*;

@Entity
@Table(name = "cigarettes")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private double price;
    @Column(name = "quantity_at_stock")
    private int quantityAtStock;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private ItemType itemType;

    public Item() {

    }

    public Item(int id) {
        this.id = id;
    }

    public Item(int id, String name, double price, int quantityAtStock, ItemType itemType) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantityAtStock = quantityAtStock;
        this.itemType = itemType;
    }

    public int getId() {
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

    public ItemType getItemType() {
        return itemType;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantityAtStock(int quantityAtStock) {
        this.quantityAtStock = quantityAtStock;
    }

    public void setItemType(ItemType type) {
        this.itemType = type;
    }

    @Override
    public String toString() {
        return "ID: " + id + " NAME: " + name + " PRICE: " + price + " QUANTITY: "
                + quantityAtStock + " TYPE: " + itemType;
    }

    public static ItemBuilder build() {
        return new ItemBuilder();
    }

}
