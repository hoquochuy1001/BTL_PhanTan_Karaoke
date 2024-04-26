package model;

import entity.ChiTietHoaDon;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChiTietHoaDonDao extends Remote {
    boolean createChiTietHoaDon(ChiTietHoaDon cthd) throws RemoteException;
}
