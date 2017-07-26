package com.matuesz.shop.Hibernate;

import com.matuesz.shop.user.Gender;
import com.matuesz.shop.user.User;
import com.matuesz.shop.user.UserExtraInfo;
import com.matuesz.shop.user.UserSupplier;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

@SuppressWarnings({"unchecked", "Duplicates"})
public class HibernateUserSupplier implements UserSupplier {

    public void updateExtraInfo(UserExtraInfo info, String gender) {
        Session session = DatabaseServer.getInstance().getSession();

        Transaction transaction = session.beginTransaction();
        Gender temp = ((Gender) session.createQuery("FROM Gender g where g.gender = '"
                + gender.toUpperCase() + "'").uniqueResult());
        transaction.commit();

        if(temp == null){
            temp = new Gender();
            temp.setGender(gender);
        }
        info.setGender(temp);

        transaction = session.beginTransaction();
        session.saveOrUpdate(info);
        transaction.commit();
        session.close();
    }

    public void removeExtraInfo(UserExtraInfo info){
        Session session = DatabaseServer.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.delete(info);

        transaction.commit();
        session.close();
    }

    //BOTH BASIC AND EXTENDED HAVE SAME IMPLEMENTATIONS IN HIBERNATE
    @Override
    public List<User> getAllBasicInfo(String sortBy, String sortType) {
        Session session = DatabaseServer.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        List<User> output = session
                .createQuery("FROM User u " + " ORDER BY u." + sortBy.toLowerCase() + " " + sortType)
                .list();

        //imitation of getInstance basic info
        output.forEach(user -> user.setUserExtraInfo(null));
        transaction.commit();
        session.close();
        return output;
    }

    @Override
    public List<User> getAllExtendedInfo(String sortBy, String sortType) {
        Session session = DatabaseServer.getInstance().getSession();
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
        Session session = DatabaseServer.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(User.build().withId(id).get());
        transaction.commit();
        session.close();
    }

    @Override
    public void addUser(User user) {
        Session session = DatabaseServer.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

    @Override
    public void updateUser(User user) {
        Session session = DatabaseServer.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(user);
        transaction.commit();
        session.close();
    }
}
