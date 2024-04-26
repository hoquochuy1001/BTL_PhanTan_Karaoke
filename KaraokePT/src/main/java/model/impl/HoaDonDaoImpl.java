package model.impl;

import entity.HoaDon;
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

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.util.Hashtable;

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
}
