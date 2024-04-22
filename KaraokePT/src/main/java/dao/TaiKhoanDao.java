package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entity.DichVu;
import entity.TaiKhoan;

public class TaiKhoanDao {
	private final SessionFactory sessionFactory;

    public TaiKhoanDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<TaiKhoan> getAllDichVu() {
        List<TaiKhoan> dsTaiKhoan = new ArrayList<TaiKhoan>();
        try (Session session = sessionFactory.openSession()) {
            dsTaiKhoan = session.createQuery("FROM TaiKhoan", TaiKhoan.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsTaiKhoan;
    }
    
    public TaiKhoan getTaiKhoanByNhanVien(String maNV) {
        Transaction transaction = null;
        TaiKhoan tk = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            tk = session.get(TaiKhoan.class, maNV);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return tk;
    }

    public boolean createTaiKhoan(TaiKhoan tk) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(tk);
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


    public boolean updateTaiKhoan(TaiKhoan tk) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(tk);
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

    public boolean deleteTaiKhoan(String maNV) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            TaiKhoan tk = session.get(TaiKhoan.class, maNV);
            if (tk != null) {
                session.delete(tk);
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
