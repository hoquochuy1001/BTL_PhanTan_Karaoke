package Server;

import gui.DangNhap_GUI;
import gui.Menu_GUI;
import model.*;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client {
//    private static final String URL = "rmi://LAPTOP-FK3M91LG:7878/";
      public static final String SERVER_URL = "rmi://LAPTOP-2QBKN4EM:7878/";

    public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
//        NhanVienDao nhanVienDao = (NhanVienDao) Naming.lookup(URL + "nhanVienDao");
//        PhieuDatPhongDao phieuDatPhongDao = (PhieuDatPhongDao) Naming.lookup(URL + "phieuDatPhongDao");
//        PhongDao phongDao = (PhongDao) Naming.lookup(URL + "phongDao");
//        LoaiPhongDao loaiPhongDao = (LoaiPhongDao) Naming.lookup(URL + "loaiPhongDao");
//        LoaiDichVuDao loaiDichVuDao = (LoaiDichVuDao) Naming.lookup(URL + "loaiDichVuDao");
//        DichVuDao dichVuDao = (DichVuDao) Naming.lookup(URL + "dichVuDao");
//        KhachHangDao khachHangDao = (KhachHangDao) Naming.lookup(URL + "khachHangDao");
//        HoaDonDao hoaDonDao = (HoaDonDao) Naming.lookup(URL + "hoaDonDao");
//        ChiTietHoaDonDao chiTietHoaDonDao = (ChiTietHoaDonDao) Naming.lookup(URL + "chiTietHoaDonDao");
//        ChucVuDao chucVuDao = (ChucVuDao) Naming.lookup(URL + "chucVuDao");

        new DangNhap_GUI();
    }
}

