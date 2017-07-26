package com.matuesz.shop.JDBC;

import item.Item;
import item.ItemType;
import item.ItemsSupplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JDBCItemsSupplier implements ItemsSupplier {

    private final String SELECT_ALL = "SELECT * FROM";

    private DatabaseServer server;
    private Statement actualStatement;


    public JDBCItemsSupplier(){
        try {
            server = new DatabaseServer();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Item> getAllItems() {
        List<Item> output = new ArrayList<>();
        try {
            server.connect();
            actualStatement = server.getStatement();
            ResultSet resultSet = actualStatement.executeQuery(SELECT_ALL + " selectAllWithType");
            while (resultSet.next()) {
                Item item = Item.build()
                        .setId(Integer.toString(resultSet.getInt("id")))
                        .setName(resultSet.getString("name"))
                        .setPrice(resultSet.getDouble("price"))
                        .setQuantityAtStock(resultSet.getInt("quantity_at_stock"))
                        .setType(new ItemType(resultSet.getString("type")))
                        .createItem();
                output.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return output;
    }
}
