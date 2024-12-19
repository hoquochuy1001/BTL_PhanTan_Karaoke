package model.impl;

import entity.HoaDon;

import entity.HoaDonRevenue;
import model.HoaDonDao;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.jdbc.ReturningWork;
import org.hibernate.query.Query;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;


public class HoaDonDaoImpl extends UnicastRemoteObject implements HoaDonDao {
    private final SessionFactory sessionFactory;

    public HoaDonDaoImpl(SessionFactory sessionFactory) throws RemoteException {
        this.sessionFactory = sessionFactory;
    }
    public Long countSoHoaDon(String soHoaDon) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "SELECT COUNT(*) FROM HoaDon WHERE soHoaDon like :soHoaDon";
            Query query = session.createQuery(hql);
            query.setParameter("soHoaDon", "%" + soHoaDon + "%");
            return (Long) query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public HoaDon getBySoHoaDon(String soHoaDon) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM HoaDon WHERE soHoaDon = :soHoaDon";
            Query query = session.createQuery(hql);
            query.setParameter("soHoaDon", soHoaDon);
            return (HoaDon) query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int createHoaDon(HoaDon hd) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.persist(hd);
            transaction.commit();
            return hd.getId();
        } catch (Exception e) {
            if (transaction != null && session.isOpen() && transaction.isActive()){
                transaction.rollback();
            }
            e.printStackTrace();
            return 0;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
    public void xuatHoaDon(int idhd) {
        Session session = null;
        try {
            Hashtable map = new Hashtable();
            JasperReport report = JasperCompileManager.compileReport("src/report/Blank_A4.jrxml");

            map.put("sMaHD", idhd);

            session = sessionFactory.openSession();
            Connection connection = session.doReturningWork(new ReturningWork<Connection>() {
                @Override
                public Connection execute(Connection conn) {
                    return conn;
                }
            });
            JasperPrint p = JasperFillManager.fillReport(report, map, connection);
            JasperViewer.viewReport(p, false);
            JasperExportManager.exportReportToPdfFile(p, "src/report/test.pdf");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
    @Override
    public List<HoaDonRevenue> getDoanhThuTheoThang() {
        List<HoaDonRevenue> revenueList = null;
        try (Session session = sessionFactory.openSession()) {
            Query<HoaDonRevenue> query = session.createNamedQuery("getDoanhThuTheoThang", HoaDonRevenue.class);
            revenueList = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace(); // Log errors
        }
        return revenueList; // Return the list or null if an error occurred
    }

    @Override
    public List<HoaDon> getAllHoaDon() throws RemoteException {
        List<HoaDon> hoaDonList = null;
        try (Session session = sessionFactory.openSession()) {
            Query<HoaDon> query = session.createQuery("FROM HoaDon", HoaDon.class);
            hoaDonList = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace(); // Ghi lại lỗi nếu có
        }
        return hoaDonList; // Trả về danh sách hoặc null nếu có lỗi
    }

    @Override
    public List<HoaDonRevenue> getDoanhThuTheoNgayTrongTuan() {
        List<HoaDonRevenue> revenueList = null;
        try (Session session = sessionFactory.openSession()) {
            // Truy vấn SQL trả về tên ngày và doanh thu
            String sql = "SET DATEFIRST 1;" +
                    "WITH DaysOfWeek AS (" +
                    "    SELECT 1 AS ngayTrongTuan, N'Thứ hai' AS tenNgay" +
                    "    UNION ALL SELECT 2, N'Thứ ba'" +
                    "    UNION ALL SELECT 3, N'Thứ tư'" +
                    "    UNION ALL SELECT 4, N'Thứ năm'" +
                    "    UNION ALL SELECT 5, N'Thứ sáu'" +
                    "    UNION ALL SELECT 6, N'Thứ bảy'" +
                    "    UNION ALL SELECT 7, N'Chủ nhật'" +
                    ")" +
                    "SELECT d.ngayTrongTuan AS dayWeek, d.tenNgay AS dayOfWeek, ISNULL(SUM(hd.TongTien), 0) AS totalRevenue " +
                    "FROM DaysOfWeek d " +
                    "LEFT JOIN HoaDon hd " +
                    "    ON DATEPART(WEEKDAY, hd.NgayTaoHD) = d.ngayTrongTuan " +
                    "    AND hd.NgayTaoHD >= DATEADD(DAY, 1 - DATEPART(WEEKDAY, GETDATE()), CAST(GETDATE() AS DATE)) " +
                    "    AND hd.NgayTaoHD <= CAST(GETDATE() AS DATE) " +
                    "GROUP BY d.ngayTrongTuan, d.tenNgay " +
                    "ORDER BY d.ngayTrongTuan ASC";

            // Sử dụng SQL query để ánh xạ trực tiếp vào đối tượng HoaDonRevenue
            Query<Object[]> query = session.createNativeQuery(sql);

            // Sử dụng vòng lặp để ánh xạ kết quả về đối tượng HoaDonRevenue
            revenueList = new ArrayList<>();
            List<Object[]> result = query.getResultList();
            for (Object[] row : result) {
                int dayWeek = (int) row[0];
                String dayOfWeek = (String) row[1];  // Ngày trong tuần (String)
                BigDecimal totalRevenue = (BigDecimal) row[2];  // Tổng doanh thu (BigDecimal)

                HoaDonRevenue revenue = new HoaDonRevenue(dayWeek,dayOfWeek, totalRevenue);
                revenueList.add(revenue);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log errors
        }
        return revenueList;
    }




}
