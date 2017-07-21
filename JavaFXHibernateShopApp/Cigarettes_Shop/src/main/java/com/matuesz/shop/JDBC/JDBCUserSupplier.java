package com.matuesz.shop.JDBC;

import com.matuesz.shop.User;
import com.matuesz.shop.UserSupplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JDBCUserSupplier implements UserSupplier {

    private final String selectQuery = "SELECT * FROM users";

    private DatabaseServer server;
    private Statement actualStatement;


    public JDBCUserSupplier() {
        try {
            this.server = new DatabaseServer();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private ResultSet getUserResultSet(String sortBy, String sortType, boolean withExtended) {
        ResultSet usersSet = null;
        try {
            server.connect();
            actualStatement = server.getStatement();
            switch (sortBy) {
                case "ID":
                    if (withExtended) {

                    } else {
                        usersSet = actualStatement.executeQuery(selectQuery + " ORDER BY user_id " + sortType);
                    }
                    break;
                case "NICK":
                    if (withExtended) {

                    } else {
                        usersSet = actualStatement.executeQuery(selectQuery + " ORDER BY nick " + sortType);
                    }
                    break;
                case "TIME":
                    if(withExtended){

                    }else {
                        usersSet = actualStatement.executeQuery(selectQuery + " ORDER BY time_joined " + sortType);
                    }
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usersSet;
    }


    @Override
    public List<User> getAllBasicInfo(String sortBy, String sortType) {
        List<User> usersList = new ArrayList<>();
        ResultSet usersSet = getUserResultSet(sortBy, sortType, false);
        if (usersSet != null) {
            try {
                while (usersSet.next()) {
                    User currentUser = User.build()
                            .withId(usersSet.getString("user_id"))
                            .withNick(usersSet.getString("nick"))
                            .withTimeJoined(usersSet.getString("time_joined"))
                            .withEmail(usersSet.getString("email"))
                            .get();
                    usersList.add(currentUser);
                }
                actualStatement.close();
                server.disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return usersList;
    }


    @Override
    public List<User> getAllExtendedInfo(String sortBy, String sortType) {
        List<User> usersList = new ArrayList<>();
        ResultSet usersSet = getUserResultSet(sortBy, sortType, true);

        return usersList;
    }
}
