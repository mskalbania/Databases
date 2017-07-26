package com.matuesz.shop;

import com.matuesz.shop.Hibernate.DatabaseServer;
import com.matuesz.shop.Hibernate.HibernateItemSupplier;
import com.matuesz.shop.item.Item;
import com.matuesz.shop.item.ItemType;

public class Demo {
    public static void main(String[] args) throws InterruptedException {
        HibernateItemSupplier hs = new HibernateItemSupplier();

        Item item = new Item();
        item.setId(202);
        item.setName("Jakies szlugensy");
        item.setPrice(100.00);
        item.setQuantityAtStock(1);
        item.setItemType(new ItemType("CIGAR"));

        hs.updateItem(item);
        Thread.sleep(30000);
        hs.removeItem(item);

        DatabaseServer.getInstance().closeFactory();
    }
}
