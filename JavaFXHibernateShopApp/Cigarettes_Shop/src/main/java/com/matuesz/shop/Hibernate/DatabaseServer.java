package com.matuesz.shop.Hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DatabaseServer {

    private static DatabaseServer instance;
    private SessionFactory factory;
    private Session currentSession;

    private DatabaseServer() {
        factory = new Configuration()
                .configure("hibernate/hibernate.cfg.xml").buildSessionFactory();
    }

    public static synchronized DatabaseServer getInstance() {
        if (instance == null) {
            instance = new DatabaseServer();
            return instance;
        } else {
            return instance;
        }
    }

    public Session getSession() {
        currentSession = factory.openSession();
        return currentSession;
    }

    public void closeSession() {
        currentSession.close();
    }

    public void closeFactory(){
        factory.close();
    }
}
