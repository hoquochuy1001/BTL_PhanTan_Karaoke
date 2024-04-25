package client;

import gui.Menu_GUI;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import util.HibernateUtil;

public class client {
    public static void main(String[] args) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        System.out.println("Success!!!");

        new Menu_GUI();
    }
}