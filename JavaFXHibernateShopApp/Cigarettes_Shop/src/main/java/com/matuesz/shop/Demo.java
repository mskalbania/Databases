package com.matuesz.shop;

import com.matuesz.shop.Hibernate.HibernateUserSupplier;

import java.util.List;

public class Demo {
    public static void main(String[] args) {

        HibernateUserSupplier supplier = new HibernateUserSupplier();

        List<User> users = supplier.getAllBasicInfo("id","DESC");

        users.forEach(System.out::println);
    }
}
