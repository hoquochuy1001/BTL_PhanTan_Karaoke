package model.impl;

import entity.KhachHang;
import model.KhachHangDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class KhachHangDaoImpl extends UnicastRemoteObject implements KhachHangDao {
    private final SessionFactory sessionFactory;

    public KhachHangDaoImpl(SessionFactory sessionFactory) throws RemoteException {
        this.sessionFactory = sessionFactory;
    }
    public List<KhachHang> getAllKhachHang() {
        List<KhachHang> dsKhachHang = new ArrayList<KhachHang>();
        try (Session session = sessionFactory.openSession()) {
            dsKhachHang = session.createQuery("FROM KhachHang", KhachHang.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsKhachHang;
    }

    public KhachHang getKhachHangByMaKhachHang(String maKH) {
        Transaction transaction = null;
        KhachHang kh = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            kh = session.get(KhachHang.class, maKH);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return kh;
    }

    public boolean createKhachHang(KhachHang kh) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(kh);
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


    public boolean updateKhachHang(KhachHang kh) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(kh);
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

    public boolean deleteKhachHang(String maKH) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            KhachHang kh = session.get(KhachHang.class, maKH);
            if (kh != null) {
                session.delete(kh);
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
