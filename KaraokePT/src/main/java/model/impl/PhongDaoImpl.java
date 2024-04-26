package model.impl;

import entity.Phong;
import model.PhongDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class PhongDaoImpl extends UnicastRemoteObject implements PhongDao {
    private final SessionFactory sessionFactory;

    public PhongDaoImpl(SessionFactory sessionFactory) throws RemoteException {
        this.sessionFactory = sessionFactory;
    }
    public List<Phong> getAllPhong() {
        List<Phong> dsPhong = new ArrayList<Phong>();
        try (Session session = sessionFactory.openSession()) {
            dsPhong = session.createQuery("FROM Phong", Phong.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsPhong;
    }

    public Phong getPhongByLoaiPhong(String loaiP) {
        Transaction transaction = null;
        Phong p = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            p = session.get(Phong.class, loaiP);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return p;
    }

    public boolean createPhong(Phong p) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(p);
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


    public boolean updatePhong(Phong p) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(p);
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

    public boolean deletePhong(String maP) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Phong p = session.get(Phong.class, maP);
            if (p != null) {
                session.delete(p);
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

    //function to update status of room(Phong) to "Phòng có Khách"
    public boolean updateTrangThaiDP(String maP) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Phong p = session.get(Phong.class, maP);
            if (p != null) {
                p.setTinhTrang("Phòng có Khách");
                session.update(p);
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

    //function to update status of room(Phong) to "Phòng trống"
    public boolean updateTrangThaiTP(String maP) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Phong p = session.get(Phong.class, maP);
            if (p != null) {
                p.setTinhTrang("Phòng trống");
                session.update(p);
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
