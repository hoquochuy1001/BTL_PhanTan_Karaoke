package model;

import entity.NhanVien;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface NhanVienDao extends Remote {
    List<NhanVien> getAllNhanVien() throws RemoteException;
    NhanVien getNhanVienByChucVu(String macv) throws RemoteException;
    boolean createNhanVien(NhanVien nv) throws RemoteException;
    boolean updateNhanVien(NhanVien nv) throws RemoteException;
    boolean deleteNhanVien(String maNV) throws RemoteException;
    List<NhanVien> getNhanVienExceptAdmin() throws RemoteException;
    NhanVien getNhanVienByMaNhanVien(String maNV) throws RemoteException;
    public List<String> getAllMaNhanVien() throws RemoteException;

}
