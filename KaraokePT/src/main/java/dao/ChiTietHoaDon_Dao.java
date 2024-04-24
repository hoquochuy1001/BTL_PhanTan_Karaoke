package dao;

import entity.ChiTietHoaDon;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class ChiTietHoaDon_Dao {
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public boolean createChiTietHoaDon(ChiTietHoaDon cthd) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(cthd);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null && session.isOpen() && transaction.isActive()){
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}

