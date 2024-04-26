package model;

import entity.PhieuDatPhong;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface PhieuDatPhongDao extends Remote {
    List<PhieuDatPhong> getAllPhieuDatPhong() throws RemoteException;
    boolean deletePhieuDatPhong(int maDV) throws RemoteException;
    boolean createPhieuDatPhong(PhieuDatPhong pdp) throws RemoteException;
    PhieuDatPhong getPhieuDatPhongByID(int id) throws RemoteException;
}
