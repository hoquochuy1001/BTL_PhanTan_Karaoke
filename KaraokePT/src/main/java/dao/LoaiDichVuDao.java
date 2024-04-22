package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entity.DichVu;
import entity.LoaiDichVu;

public class LoaiDichVuDao {
	private final SessionFactory sessionFactory;

    public LoaiDichVuDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<LoaiDichVu> getAllLoaiDichVu() {
        List<LoaiDichVu> dsLoaiDichVu = new ArrayList<LoaiDichVu>();
        try (Session session = sessionFactory.openSession()) {
            dsLoaiDichVu = session.createQuery("FROM LoaiDichVu", LoaiDichVu.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsLoaiDichVu;
    }

    public boolean createLoaiDichVu(LoaiDichVu loaidv) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(loaidv);
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

    public boolean deleteLoaiDichVu(String loaiDV) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            LoaiDichVu ldv = session.get(LoaiDichVu.class, loaiDV);
            if (ldv != null) {
                session.delete(ldv);
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
