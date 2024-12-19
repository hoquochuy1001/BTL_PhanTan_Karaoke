package model.impl;

import entity.DatPhongTruoc;
import entity.Phong;
import entity.TaiKhoan;
import org.hibernate.query.Query;
import jakarta.persistence.TypedQuery;
import model.DatPhongTruocDao;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DatPhongTruocDaoImpl extends UnicastRemoteObject implements DatPhongTruocDao {
    private final SessionFactory sessionFactory;

    public DatPhongTruocDaoImpl(SessionFactory sessionFactory) throws RemoteException {
        this.sessionFactory = sessionFactory;
    }

    private List<DatPhongTruoc> datPhongList = new ArrayList<>();
    @Override
    public List<DatPhongTruoc> findPhongTrungThoiGian(String maPhong, Timestamp start, Timestamp end) throws RemoteException {
        List<DatPhongTruoc> result = new ArrayList<>();

        // Iterate over all booking records
        for (DatPhongTruoc datPhong : datPhongList) {
            // Check if the room ID matches and if the time periods overlap
            if (datPhong.getPhong().getMaPhong().equals(maPhong) &&
                    !(datPhong.getThoiGianKetThuc().before(start) || datPhong.getThoiGianBatDau().after(end))) {
                result.add(datPhong);
            }
        }
        return result;
    }


    @Override
    public List<DatPhongTruoc> getAllDatPhong() throws RemoteException {
        try (Session session = sessionFactory.openSession()) {
            List<DatPhongTruoc> list = session.createQuery("FROM DatPhongTruoc", DatPhongTruoc.class).getResultList();

            if (list == null) {
                System.out.println("Dữ liệu trả về là null.");
            } else if (list.isEmpty()) {
                System.out.println("Dữ liệu trả về là danh sách rỗng.");
            } else {
                System.out.println("Dữ liệu trả về có " + list.size() + " phần tử.");
            }

            if (list != null) {
                Collections.sort(list, (o1, o2) -> o2.getThoiGianBatDau().compareTo(o1.getThoiGianBatDau())); // So sánh giảm dần
            }

            return list;  // Trả về danh sách đã sắp xếp hoặc null
        } catch (Exception e) {
            e.printStackTrace(); // In ra thông tin lỗi
            return null;
        }
    }
    public List<DatPhongTruoc> getBookingsForRoom(String maPhong) throws RemoteException {
        List<DatPhongTruoc> bookings = new ArrayList<>();

        try (Session session = sessionFactory.openSession()) {
            // Truy vấn HQL, tham chiếu tới đối tượng Phong thông qua mối quan hệ ManyToOne
            String hql = "FROM DatPhongTruoc dp WHERE dp.phong.maPhong = :maPhong";

            // Tạo truy vấn với kiểu trả về là DatPhongTruoc
            TypedQuery<DatPhongTruoc> query = session.createQuery(hql, DatPhongTruoc.class);

            // Gán tham số
            query.setParameter("maPhong", maPhong);

            // Thực thi truy vấn và lấy kết quả
            bookings = query.getResultList();
        } catch (HibernateException e) {
            e.printStackTrace();
            throw new RemoteException("Lỗi khi lấy thông tin đặt phòng", e);
        }

        return bookings;
    }
    @Override
    public boolean addDatPhongTruoc(DatPhongTruoc dpt) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(dpt);
            transaction.commit(); // Commit transaction

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


    @Override
    public boolean saveDatPhong(DatPhongTruoc datPhongTruoc) throws RemoteException {
        return datPhongList.add(datPhongTruoc);
    }

    @Override
    public boolean deleteDatPhong(int id) throws RemoteException {
        return datPhongList.removeIf(datPhong -> datPhong.getId() == id);
    }
    @Override
    public boolean updatePhong(DatPhongTruoc p) {
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
    @Override
    public DatPhongTruoc getDatPhongTruocById(int id) throws RemoteException {
        DatPhongTruoc datPhongTruoc = null;

        try (Session session = sessionFactory.openSession()) {
            // Sử dụng HQL để truy vấn theo mã ID
            String hql = "FROM DatPhongTruoc WHERE id = :id";
            Query<DatPhongTruoc> query = session.createQuery(hql, DatPhongTruoc.class);
            query.setParameter("id", id);

            // Lấy kết quả truy vấn
            datPhongTruoc = query.uniqueResult();
        } catch (HibernateException e) {
            e.printStackTrace();
            throw new RemoteException("Lỗi khi lấy thông tin đặt phòng trước theo ID", e);
        }

        return datPhongTruoc;
    }

}
