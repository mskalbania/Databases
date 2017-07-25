package com.matuesz.shop.Hibernate;

import com.matuesz.shop.User;
import com.matuesz.shop.UserSupplier;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

@SuppressWarnings({"unchecked", "Duplicates"})
public class HibernateUserSupplier implements UserSupplier {

    //BOTH BASIC AND EXTENDED HAVE SAME IMPLEMENTATIONS IN HIBERNATE
    @Override
    public List<User> getAllBasicInfo(String sortBy, String sortType) {
        Session session = DatabaseServer.get().getSession();
        Transaction transaction = session.beginTransaction();

        List<User> output = session
                .createQuery("FROM User u " + " ORDER BY u." + sortBy.toLowerCase() + " " + sortType)
                .list();

        //imitation of get basic info
        output.forEach(user -> user.setUserExtraInfo(null));

        transaction.commit();
        session.close();
        return output;
    }

    @Override
    public List<User> getAllExtendedInfo(String sortBy, String sortType) {
        Session session = DatabaseServer.get().getSession();
        Transaction transaction = session.beginTransaction();
        List<User> output = session
                .createQuery("FROM User u " + " ORDER BY u." + sortBy.toLowerCase() + " " + sortType)
                .list();

        transaction.commit();
        session.close();
        return output;
    }

    @Override
    public void deleteUser(int id) {
        Session session = DatabaseServer.get().getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(User.build().withId(id).get());
        transaction.commit();
        session.close();
    }

    @Override
    public void addUser(User user) {

    }

    @Override
    public void updateUser(User user) {

    }
}
