package model.impl;

import entity.ChiTietHoaDon;
import model.ChiTietHoaDonDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ChiTietHoaDonDaoImpl extends UnicastRemoteObject implements ChiTietHoaDonDao {
    private final SessionFactory sessionFactory;

    public ChiTietHoaDonDaoImpl(SessionFactory sessionFactory) throws RemoteException {
        this.sessionFactory = sessionFactory;
    }

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
