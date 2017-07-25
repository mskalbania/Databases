package com.matuesz.shop.JDBC;

import com.matuesz.shop.User;
import com.matuesz.shop.UserExtendedInfo;
import com.matuesz.shop.UserSupplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JDBCUserSupplier implements UserSupplier {

    private final String SELECT_BASIC = "SELECT * FROM users";
    private final String SELECT_EXTENDED = "SELECT * FROM usersExtended";
    private final String DELETE = "DELETE FROM users WHERE";
    private final String ADD = "INSERT INTO users (nick,email) values";
    private final String UPDATE = "UPDATE users SET";

    private DatabaseServer server;
    private Statement actualStatement;


    public JDBCUserSupplier() {
        try {
            this.server = new DatabaseServer();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //QUERIES FOR ALL USERS
    private ResultSet getUsersResultSet(String sortBy, String sortType, boolean withExtended) {
        ResultSet usersSet = null;
        try {
            server.connect();
            actualStatement = server.getStatement();
            switch (sortBy) {
                case "ID":
                    if (withExtended) {
                        usersSet = actualStatement.executeQuery
                                (SELECT_EXTENDED + " ORDER BY user_id " + sortType);
                    } else {
                        usersSet = actualStatement.executeQuery
                                (SELECT_BASIC + " ORDER BY user_id " + sortType);
                    }
                    break;
                case "NICK":
                    if (withExtended) {
                        usersSet = actualStatement.executeQuery
                                (SELECT_EXTENDED + " ORDER BY nick " + sortType);
                    } else {
                        usersSet = actualStatement.executeQuery
                                (SELECT_BASIC + " ORDER BY nick " + sortType);
                    }
                    break;
                case "TIME":
                    if (withExtended) {
                        usersSet = actualStatement.executeQuery
                                (SELECT_EXTENDED + " ORDER BY time_joined " + sortType);

                    } else {
                        usersSet = actualStatement.executeQuery
                                (SELECT_BASIC + " ORDER BY time_joined " + sortType);
                    }
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usersSet;
    }

    private void parseUser(ResultSet userResult, List<User> usersList) {
        try {
            while (userResult.next()) {
                User currentUser = User.build()
                        .withId(userResult.getString("user_id"))
                        .withNick(userResult.getString("nick"))
                        .withTimeJoined(userResult.getString("time_joined"))
                        .withEmail(userResult.getString("email"))
                        .get();
                usersList.add(currentUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void parseUserExtendedInfo(ResultSet userResult, List<User> userList) {
        try {
            userResult.beforeFirst();
            while (userResult.next()) {
                int isAdmin = userResult.getInt("isAdmin");
                String address = userResult.getString("address");
                String phoneNumber = userResult.getString("phone_number");
                String gender = userResult.getString("gender");
                userList.forEach(user -> user.setExtendedInfo
                        (new UserExtendedInfo(isAdmin, address, phoneNumber, gender)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllBasicInfo(String sortBy, String sortType) {
        ResultSet usersResultSet = getUsersResultSet(sortBy, sortType, false);
        List<User> usersList = new ArrayList<>();
        parseUser(usersResultSet, usersList);
        try {
            actualStatement.close();
            server.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usersList;
    }

    @Override
    public List<User> getAllExtendedInfo(String sortBy, String sortType) {
        List<User> usersList = new ArrayList<>();
        ResultSet usersSet = getUsersResultSet(sortBy, sortType, true);

        parseUser(usersSet, usersList);
        parseUserExtendedInfo(usersSet, usersList);

        try {
            actualStatement.close();
            server.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usersList;
    }

    @Override
    public void deleteUser(String id) {
        try {
            server.connect();
            actualStatement = server.getStatement();
            actualStatement.executeUpdate(DELETE + " user_id = " + id);
            actualStatement.close();
            server.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addUser(User user) {
        try {
            String nick = user.getNick();
            String email = user.getEmail();
            server.connect();
            actualStatement = server.getStatement();
            actualStatement.executeUpdate(ADD + " ('" + nick + "','" + email + "')");
            actualStatement.close();
            server.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(User user) {
        String nick = user.getNick();
        String email = user.getEmail();
        String id = user.getId();
        try {
            server.connect();
            actualStatement = server.getStatement();
            actualStatement.executeUpdate(UPDATE + " nick = '" + nick + "',email = '"
                    + email + "' WHERE user_id = " + id);
            actualStatement.close();
            server.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
