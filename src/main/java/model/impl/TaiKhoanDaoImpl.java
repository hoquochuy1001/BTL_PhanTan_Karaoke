package model.impl;

import entity.HoaDon;
import entity.TaiKhoan;
import model.PhongDao;
import model.TaiKhoanDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class TaiKhoanDaoImpl extends UnicastRemoteObject implements TaiKhoanDao {
    private static final Logger logger = LoggerFactory.getLogger(TaiKhoanDaoImpl.class);
    private final SessionFactory sessionFactory;

    public TaiKhoanDaoImpl(SessionFactory sessionFactory) throws RemoteException {
        this.sessionFactory = sessionFactory;
    }
    @Override
    public List<TaiKhoan> getAllTaiKhoan() throws RemoteException {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM TaiKhoan", TaiKhoan.class).getResultList();
        } catch (Exception e) {
            logger.error("Error fetching all accounts", e);
            return null;
        }
    }

    @Override
    public TaiKhoan getTaiKhoanById(String id) throws RemoteException {
        try (Session session = sessionFactory.openSession()) {
            return session.get(TaiKhoan.class, id);
        } catch (Exception e) {
            logger.error("Error fetching account by ID", e);
            return null;
        }
    }

    @Override
    public boolean createTaiKhoan(TaiKhoan t) throws RemoteException {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(t);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            logger.error("Error creating account", e);
            return false;
        }
    }

    @Override
    public boolean updateTaiKhoan(TaiKhoan t) throws RemoteException {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(t);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            logger.error("Error updating account", e);
            return false;
        }
    }

    @Override
    public boolean deleteTaiKhoan(String id) throws RemoteException {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            TaiKhoan t = session.get(TaiKhoan.class, id);

            if (t != null) {
                session.delete(t);
                transaction.commit();
                return true;
            } else {
                System.out.println("Tài khoản không tồn tại!");
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Error deleting account", e);
        }
        return false;
    }


    @Override
    public String getLastTenTK() throws RemoteException {
        try (Session session = sessionFactory.openSession()) {
            String query = "SELECT MAX(t.tenTK) FROM TaiKhoan t";
            return session.createQuery(query.trim(), String.class).getSingleResult();
        } catch (Exception e) {
            logger.error("Error fetching last tenTK", e);
            return null;
        }
    }

    @Override
    public String generateNextTenTK() throws RemoteException {
        String lastTenTK = getLastTenTK().trim();
        if (lastTenTK != null && lastTenTK.startsWith("TK")) {
            int currentNumber = Integer.parseInt(lastTenTK.substring(2));
            return String.format("TK%03d", currentNumber + 1);
        }
        return "TK001";
    }

    @Override
    public boolean checkLogin(String username, String password) throws RemoteException {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM TaiKhoan WHERE tenTK = :username AND matKhau = :password";
            Query<TaiKhoan> query = session.createQuery(hql, TaiKhoan.class);
            query.setParameter("username", username);
            query.setParameter("password", password);

            TaiKhoan tk = query.uniqueResult();

            return tk != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
