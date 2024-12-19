package model.impl;

import entity.ChucVu;
import model.ChucVuDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ChucVuDaoImpl extends UnicastRemoteObject implements ChucVuDao {

    private final SessionFactory sessionFactory;

    public ChucVuDaoImpl(SessionFactory sessionFactory) throws RemoteException {
        this.sessionFactory = sessionFactory;
    }
    public List<ChucVu> getAllChucVu() {
        List<ChucVu> dsChucVu = new ArrayList<ChucVu>();
        try (Session session = sessionFactory.openSession()) {
            dsChucVu = session.createQuery("FROM ChucVu", ChucVu.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsChucVu;
    }

    public boolean createChucVu(ChucVu cv) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(cv);
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


    public boolean updateChucVu(ChucVu cv) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(cv);
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

    public boolean deleteChucVu(String maCV) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            ChucVu cv = session.get(ChucVu.class, maCV);
            if (cv != null) {
                session.delete(cv);
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
    public ChucVu getChucVuByMa(String ma) {
        Transaction transaction = null;
        ChucVu cv = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            cv = session.get(ChucVu.class, ma);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return cv;
    }
}
