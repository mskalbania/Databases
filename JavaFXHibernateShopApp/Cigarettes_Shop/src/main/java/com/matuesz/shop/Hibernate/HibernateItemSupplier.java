package com.matuesz.shop.Hibernate;

import com.matuesz.shop.item.Item;
import com.matuesz.shop.item.ItemType;
import com.matuesz.shop.item.ItemsSupplier;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

@SuppressWarnings({"unchecked", "Duplicates"})
public class HibernateItemSupplier implements ItemsSupplier {

    @Override
    public List<Item> getAllItems() {
        List<Item> items;
        Session session = DatabaseServer.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        items = session.createQuery("from Item").list();
        tx.commit();
        session.close();
        return items;
    }

    @Override
    public void removeItem(Item item) {
        Session session = DatabaseServer.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        session.delete(item);
        tx.commit();
        session.close();
    }

    @Override
    public void updateItem(Item item) {
        addOrUpdateItem(item);
    }

    @Override
    public void addItem(Item item) {
        addOrUpdateItem(item);
    }

    private void addOrUpdateItem(Item item) {
        String itemType = item.getItemType().getType();
        int typeId = getItemTypeId(itemType);

        //Indicates that specified type is not in db
        if (typeId == -1) {
            int obtainedId = addNewItemType(item.getItemType());
            item.getItemType().setId(obtainedId);
        } else {
            item.setItemType(new ItemType(typeId));
        }

        Session session = DatabaseServer.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        session.saveOrUpdate(item);
        tx.commit();
        session.close();
    }

    private int getItemTypeId(String itemType) {
        String categoryUpper = itemType.toUpperCase();
        String query = "FROM ItemType i where i.type = '" + categoryUpper + "'";

        Session session = DatabaseServer.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        ItemType type = ((ItemType) session.createQuery(query).uniqueResult());

        tx.commit();
        session.close();

        if (type != null) {
            return type.getId();
        } else {
            return -1;
        }
    }

    private Integer addNewItemType(ItemType itemType) {
        Session session = DatabaseServer.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        Integer obtainedId = ((Integer) session.save(itemType));
        tx.commit();
        session.close();
        return obtainedId;
    }
}
