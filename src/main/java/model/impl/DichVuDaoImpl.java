package model.impl;

import entity.DichVu;
import model.DichVuDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class DichVuDaoImpl extends UnicastRemoteObject implements DichVuDao {
    private final SessionFactory sessionFactory;

    public DichVuDaoImpl(SessionFactory sessionFactory) throws RemoteException {
        this.sessionFactory = sessionFactory;
    }
    public List<DichVu> getAllDichVu() {
        List<DichVu> dsDichVu = new ArrayList<DichVu>();
        try (Session session = sessionFactory.openSession()) {
            dsDichVu = session.createQuery("FROM DichVu", DichVu.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsDichVu;
    }

    public DichVu getDichVuByLoaiDichVu(String loaiDV) {
        Transaction transaction = null;
        DichVu dv = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            dv = session.get(DichVu.class, loaiDV);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return dv;
    }

    public boolean createDichVu(DichVu dv) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(dv);
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


    public boolean updateDichVu(DichVu dv) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(dv);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteDichVu(String maDV) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            DichVu dv = session.get(DichVu.class, maDV);
            if (dv != null) {
                session.delete(dv);
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
