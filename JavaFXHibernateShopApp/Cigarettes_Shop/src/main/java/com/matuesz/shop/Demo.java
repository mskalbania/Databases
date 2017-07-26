package com.matuesz.shop;

import com.matuesz.shop.Hibernate.DatabaseServer;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Demo {
    public static void main(String[] args) {

        User user = new User();
        user.setNick("CZESC");
        user.setEmail("EEEE");

        UserExtraInfo extraInfo = new UserExtraInfo();
        extraInfo.setId(255);
        extraInfo.setPhoneNumber("1232131");
        extraInfo.setAddress("asdasd");
        extraInfo.setIsAdmin(1);







        Session session = DatabaseServer.getInstance().getSession();
        Transaction transaction = session.beginTransaction();



        Gender g = ((Gender) session.createQuery("FROM Gender g where g.gender = '" + "XX" + "'").uniqueResult());

        System.out.println(g);

        transaction.commit();
        session.close();
        DatabaseServer.getInstance().closeFactory();
    }
}
