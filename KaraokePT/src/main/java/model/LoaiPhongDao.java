package model;

import entity.LoaiPhong;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface LoaiPhongDao extends Remote {
    List<LoaiPhong> getAllLoaiPhong() throws RemoteException;
    boolean createLoaiPhong(LoaiPhong lp) throws RemoteException;
    boolean deleteLoaiPhong(String loaiP) throws RemoteException;
}
