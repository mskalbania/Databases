package com.matuesz.shop.JDBC;/*
Hardcoding connection parameters (always connecting to this db)
*/

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@SuppressWarnings("FieldCanBeLocal")
public class DatabaseServer {

    private final String JDBCRequest = "jdbc:mysql://localhost:3306/shop?";
    private final String user = "shop_admin";
    private final String password = "admin";

    private Connection connection;

    public DatabaseServer() throws SQLException {
        DriverManager.registerDriver(new Driver());
    }

    public void connect() {
        try {
            this.connection = DriverManager.getConnection(JDBCRequest + "user="
                    + user + "&password=" + password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Statement getStatement() throws SQLException {
        return connection.createStatement();
    }

    public void disconnect() throws SQLException {
        connection.close();
    }
}
