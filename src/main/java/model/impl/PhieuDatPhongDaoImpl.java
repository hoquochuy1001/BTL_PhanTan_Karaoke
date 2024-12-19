package model.impl;

import entity.PhieuDatPhong;
import model.PhieuDatPhongDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class PhieuDatPhongDaoImpl extends UnicastRemoteObject implements PhieuDatPhongDao {
    private final SessionFactory sessionFactory;

    public PhieuDatPhongDaoImpl(SessionFactory sessionFactory) throws RemoteException {
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
