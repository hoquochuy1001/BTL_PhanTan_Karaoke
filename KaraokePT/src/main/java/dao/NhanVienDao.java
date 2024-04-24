package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entity.DichVu;
import entity.NhanVien;

public class NhanVienDao {
	private final SessionFactory sessionFactory;

    public NhanVienDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<NhanVien> getAllNhanVien() {
        List<NhanVien> dsNhanVien = new ArrayList<NhanVien>();
        try (Session session = sessionFactory.openSession()) {
            dsNhanVien = session.createQuery("FROM NhanVien", NhanVien.class).getResultList();
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


    public boolean updateNhanVien(NhanVien nv) {
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
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            NhanVien nv = session.get(NhanVien.class, maNV);
            if (nv != null) {
                session.delete(nv);
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

    public List<NhanVien> getNhanVienExceptAdmin() {
        List<NhanVien> dsNhanVien = new ArrayList<NhanVien>();
        try (Session session = sessionFactory.openSession()) {
            dsNhanVien = session.createQuery("FROM NhanVien WHERE maNV != 'GD'", NhanVien.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsNhanVien;
    }

}
