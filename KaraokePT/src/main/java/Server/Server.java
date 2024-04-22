package Server;

import gui.Menu_GUI;
import util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Server {
    public static void main(String[] args) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        System.out.println("Success!!!");
        
        new Menu_GUI();
    }
}
