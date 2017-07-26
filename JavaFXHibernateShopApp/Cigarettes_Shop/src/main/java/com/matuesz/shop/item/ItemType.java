package com.matuesz.shop.item;

import javax.persistence.*;

@Entity
@Table(name = "cigarette_types")
public class ItemType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String type;

    public ItemType() {
    }

    public ItemType(int id) {
        this.id = id;
    }

    public ItemType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }

    public String getTypeName() {
        return type;
    }
}
