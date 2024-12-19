package model.impl;

import entity.KhachHang;
import entity.NhanVien;
import entity.TaiKhoan;
import model.NhanVienDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class NhanVienDaoImpl extends UnicastRemoteObject implements NhanVienDao {

    private final SessionFactory sessionFactory;
    private static final Logger logger
            = LoggerFactory.getLogger(NhanVienDaoImpl.class);
    public NhanVienDaoImpl(SessionFactory sessionFactory) throws RemoteException {
        this.sessionFactory = sessionFactory;
    }
    public List<NhanVien> getAllNhanVien() {
        List<NhanVien> dsNhanVien = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            dsNhanVien = session.createQuery("FROM NhanVien nv WHERE nv.isDelete = false", NhanVien.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsNhanVien;
    }

    public NhanVien getNhanVienByChucVu(String macv) {
        Transaction transaction = null;
        NhanVien nv = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            nv = session.get(NhanVien.class, macv);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return nv;
    }
    public boolean createNhanVien(NhanVien nv) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(nv);
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
    public boolean updateNhanVien(NhanVien nv) throws  RemoteException{
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(nv);
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
    public boolean deleteNhanVien(String maNV) {
        System.out.println("nv delete: " + maNV);
        Transaction transaction = null;

        if (sessionFactory == null) {
            System.out.println("SessionFactory is null!");
            return false;
        }

        try (Session session = sessionFactory.openSession()) {
            if (session == null) {
                System.out.println("Failed to open session!");
                return false;
            }

            transaction = session.beginTransaction();
            if (transaction == null) {
                System.out.println("Failed to begin transaction!");
                return false;
            }

            System.out.println("Session and Transaction started successfully!");
            NhanVien nv = session.get(NhanVien.class, maNV);
            System.out.println("nv delete: " + nv);

            if (nv != null) {
                System.out.println("Found NhanVien with maNV: " + maNV);

                // Đánh dấu là đã xóa thay vì thực sự xóa
                nv.setDelete(true);
                session.update(nv);
                transaction.commit();
                return true;

            } else {
                System.out.println("NhanVien with maNV: " + maNV + " not found!");
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return false;
    }

    public List<NhanVien> getNhanVienExceptAdmin() {
        List<NhanVien> dsNhanVien = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            // Giả sử có quan hệ với TaiKhoan thông qua trường taiKhoan
            dsNhanVien = session.createQuery("FROM NhanVien nv JOIN TaiKhoan tk ON nv.maNV = tk.maNV WHERE tk.role != 'admin' AND nv.isDelete = false", NhanVien.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsNhanVien;
    }

    public NhanVien getNhanVienByMaNhanVien(String maNV) {
        Transaction transaction = null;
        NhanVien nv = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            nv = session.get(NhanVien.class, maNV);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return nv;
    }
    @Override
    public List<String> getAllMaNhanVien() {
        Transaction transaction = null;
        List<String> maNhanVienList = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            maNhanVienList = session.createQuery("SELECT nv.maNV FROM NhanVien nv", String.class).getResultList();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return maNhanVienList;
    }

}
