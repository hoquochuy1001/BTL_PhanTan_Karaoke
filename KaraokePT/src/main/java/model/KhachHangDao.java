package model;

import entity.KhachHang;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface KhachHangDao extends Remote {
    List<KhachHang> getAllKhachHang() throws RemoteException;
    KhachHang getKhachHangByMaKhachHang(String maKH) throws RemoteException;
    boolean createKhachHang(KhachHang kh) throws RemoteException;
    boolean updateKhachHang(KhachHang kh) throws RemoteException;
    boolean deleteKhachHang(String maKH) throws RemoteException;
}
