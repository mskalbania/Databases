package com.matuesz.shop.item;

import java.util.List;

public interface ItemsSupplier {

    List<Item> getAllItems();

    void removeItem(Item item);

    void updateItem(Item item);

    void addItem(Item item);
}
