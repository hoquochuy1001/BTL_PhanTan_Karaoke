package Server;

import gui.Menu_GUI;
import model.*;
import model.impl.*;
import util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import static util.HibernateUtil.sessionFactory;

public class Server {
    private static final String URL = Config.SERVER_URL;

    public static void main(String[] args) throws RemoteException, NamingException {
        Context context = new InitialContext();
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        NhanVienDao nhanVienDao = new NhanVienDaoImpl(sessionFactory);
        PhieuDatPhongDao phieuDatPhongDao = new PhieuDatPhongDaoImpl(sessionFactory);
        PhongDao phongDao = new PhongDaoImpl(sessionFactory);
        ChiTietHoaDonDao chiTietHoaDonDao = new ChiTietHoaDonDaoImpl(sessionFactory);
        ChucVuDao chucVuDao = new ChucVuDaoImpl(sessionFactory);
        DichVuDao dichVuDao = new DichVuDaoImpl(sessionFactory);
        HoaDonDao hoaDonDao = new HoaDonDaoImpl(sessionFactory);
        KhachHangDao khachHangDao = new KhachHangDaoImpl(sessionFactory);
        LoaiPhongDao loaiPhongDao = new LoaiPhongDaoImpl(sessionFactory);
        LoaiDichVuDao loaiDichVuDao = new LoaiDichVuDaoImpl(sessionFactory);
        TaiKhoanDao taiKhoanDao = new TaiKhoanDaoImpl(sessionFactory);

        LocateRegistry.createRegistry(7878);

        context.bind(URL + "nhanVienDao", nhanVienDao);
        context.bind(URL + "phieuDatPhongDao", phieuDatPhongDao);
        context.bind(URL + "phongDao", phongDao);
        context.bind(URL + "chiTietHoaDonDao", chiTietHoaDonDao);
        context.bind(URL + "chucVuDao", chucVuDao);
        context.bind(URL + "dichVuDao", dichVuDao);
        context.bind(URL + "hoaDonDao", hoaDonDao);
        context.bind(URL + "khachHangDao", khachHangDao);
        context.bind(URL + "loaiPhongDao", loaiPhongDao);
        context.bind(URL + "loaiDichVuDao", loaiDichVuDao);
        context.bind(URL + "taiKhoanDao", taiKhoanDao);

        System.out.println("Server is running...");
        //new Menu_GUI();
    }
}
