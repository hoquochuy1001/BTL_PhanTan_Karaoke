package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entity.DichVu;
import entity.PhieuDatPhong;
import org.hibernate.query.Query;
import util.HibernateUtil;

public class PhieuDatPhongDao {
	private final SessionFactory sessionFactory;

    public PhieuDatPhongDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<PhieuDatPhong> getAllPhieuDatPhong() {
        List<PhieuDatPhong> dsPhieuDatPhong = new ArrayList<PhieuDatPhong>();
        try (Session session = sessionFactory.openSession()) {
            String hql = "SELECT pdp FROM PhieuDatPhong pdp JOIN FETCH pdp.maPhong";
            dsPhieuDatPhong = session.createQuery(hql, PhieuDatPhong.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsPhieuDatPhong;
    }


    public boolean deletePhieuDatPhong(int maDV) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            PhieuDatPhong pdp = session.get(PhieuDatPhong.class, maDV);
            if (pdp != null) {
                session.delete(pdp);
                transaction.commit();
                return true;
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return false;
    }

    public boolean createPhieuDatPhong(PhieuDatPhong pdp) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(pdp);
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
    public PhieuDatPhong getPhieuDatPhongByID(int id) {
        Transaction transaction = null;
        PhieuDatPhong pdp = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            pdp = session.get(PhieuDatPhong.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return pdp;
    }
}

