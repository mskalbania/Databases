package com.matuesz.shop.user;

import java.util.List;

public interface UserSupplier {

    List<User> getAllBasicInfo(String sortBy, String sortType);

    List<User> getAllExtendedInfo(String sortBy, String sortType);

    void deleteUser(int id);

    void addUser(User user);

    void updateUser(User user);
}
