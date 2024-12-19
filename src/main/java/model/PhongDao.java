package model;

import entity.Phong;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface PhongDao extends Remote {
    List<Phong> getAllPhong() throws RemoteException;
    Phong getPhongByLoaiPhong(String loaiP) throws RemoteException;
    Phong getPhongByMaPhong(String maP) throws RemoteException;
    boolean createPhong(Phong p) throws RemoteException;
    boolean updatePhong(Phong p) throws RemoteException;
    boolean deletePhong(String maP) throws RemoteException;
    boolean updateTrangThaiDP(String maP) throws RemoteException;
    boolean updateTrangThaiTP(String maP) throws RemoteException;
}
