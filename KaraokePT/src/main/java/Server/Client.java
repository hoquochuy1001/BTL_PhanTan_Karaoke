package Server;

import gui.DangNhap_GUI;
import gui.Menu_GUI;
import model.*;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client {
    private static final String URL = "rmi://LAPTOP-FK3M91LG:7878/";
      //public static final String SERVER_URL = "rmi://LAPTOP-2QBKN4EM:7878/";

    //TH1: áp dụng khi phòng trống (ko có khách đặt trước)
    // khách vô cửa hàng đặt
    // phòng hiện tại đang có khách - không được sử dụng chức năng đặt phòng trước
    // tại vì mình không biết là khi nào khách hát xong

    //TH2: phòng đã có khách đặt trước trong khoảng thời gian cụ thể - vd: 10h - 14h
    // nếu có khách mà đặt trước từ 14h - 18h: true
    // vd như hện tại đang là 6h - thì khách vào cửa hàng sẽ đặt được từ 6h - 10h







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

