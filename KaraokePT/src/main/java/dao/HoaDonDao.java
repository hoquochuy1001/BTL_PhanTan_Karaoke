package dao;



import entity.HoaDon;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

public class HoaDonDao {
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public Long countSoHoaDon(String soHoaDon) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "SELECT COUNT(*) FROM HoaDon WHERE soHoaDon like :soHoaDon";
            Query query = session.createQuery(hql);
            query.setParameter("soHoaDon", "%" + soHoaDon + "%");
            return (Long) query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public HoaDon getBySoHoaDon(String soHoaDon) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM HoaDon WHERE soHoaDon = :soHoaDon";
            Query query = session.createQuery(hql);
            query.setParameter("soHoaDon", soHoaDon);
            return (HoaDon) query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int createHoaDon(HoaDon hd) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.persist(hd);
            transaction.commit();
            return hd.getId();
        } catch (Exception e) {
            if (transaction != null && session.isOpen() && transaction.isActive()){
                transaction.rollback();
            }
            e.printStackTrace();
            return 0;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}

