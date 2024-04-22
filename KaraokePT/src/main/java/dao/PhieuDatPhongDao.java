package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entity.DichVu;
import entity.PhieuDatPhong;

public class PhieuDatPhongDao {
	private final SessionFactory sessionFactory;

    public PhieuDatPhongDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<PhieuDatPhong> getAllPhieuDatPhong() {
        List<PhieuDatPhong> dsPhieuDatPhong = new ArrayList<PhieuDatPhong>();
        try (Session session = sessionFactory.openSession()) {
        	dsPhieuDatPhong = session.createQuery("FROM PhieuDatPhong", PhieuDatPhong.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsPhieuDatPhong;
    }
    
    public boolean deletePhieuDatPhong(int maDV) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            PhieuDatPhong pdp = session.get(PhieuDatPhong.class, maDV);
            if (pdp != null) {
                session.delete(pdp);
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
