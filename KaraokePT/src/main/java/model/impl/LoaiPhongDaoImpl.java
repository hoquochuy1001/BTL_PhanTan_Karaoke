package model.impl;

import entity.LoaiPhong;
import model.LoaiPhongDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class LoaiPhongDaoImpl extends UnicastRemoteObject implements LoaiPhongDao {

    private final SessionFactory sessionFactory;

    public LoaiPhongDaoImpl(SessionFactory sessionFactory) throws RemoteException {
        this.sessionFactory = sessionFactory;
    }
    public List<LoaiPhong> getAllLoaiPhong() {
        List<LoaiPhong> dsLoaiPhong = new ArrayList<LoaiPhong>();
        try (Session session = sessionFactory.openSession()) {
            dsLoaiPhong = session.createQuery("FROM LoaiPhong", LoaiPhong.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsLoaiPhong;
    }

    public boolean createLoaiPhong(LoaiPhong lp) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(lp);
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteLoaiPhong(String loaiP) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            LoaiPhong lp = session.get(LoaiPhong.class, loaiP);
            if (lp != null) {
                session.delete(lp);
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
}
