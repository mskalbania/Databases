package com.matuesz.shop.item;

import java.util.List;

public interface ItemsSupplier {

    List<Item> getAllItems();

    List<ItemType> getItemTypes();

    void removeItem(Item item);

    void updateItem(Item item);

    void addItem(Item item);

    Integer addItemType(ItemType itemType);

}
