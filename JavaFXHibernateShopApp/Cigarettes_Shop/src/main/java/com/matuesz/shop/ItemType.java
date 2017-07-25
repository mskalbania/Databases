package com.matuesz.shop;

public class ItemType {

    private String type;

    public ItemType(String type) {
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
