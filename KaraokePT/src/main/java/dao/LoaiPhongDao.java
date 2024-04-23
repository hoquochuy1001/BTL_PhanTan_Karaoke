package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entity.DichVu;
import entity.LoaiPhong;

public class LoaiPhongDao {
	private final SessionFactory sessionFactory;

    public LoaiPhongDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<LoaiPhong> getAllLoaiPhong() {
        List<LoaiPhong> dsLoaiPhong = new ArrayList<LoaiPhong>();
        try (Session session = sessionFactory.openSession()) {
            dsLoaiPhong = session.createQuery("FROM LoaiPhong", LoaiPhong.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsLoaiPhong;
    }
    
    public boolean createLoaiPhong(LoaiPhong lp) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(lp);
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

    public boolean deleteLoaiPhong(String loaiP) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            LoaiPhong lp = session.get(LoaiPhong.class, loaiP);
            if (lp != null) {
                session.delete(lp);
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
