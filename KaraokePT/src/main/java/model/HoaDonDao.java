package model;

import entity.HoaDon;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface HoaDonDao extends Remote {
    Long countSoHoaDon(String soHoaDon) throws RemoteException;
    HoaDon getBySoHoaDon(String soHoaDon) throws RemoteException;
    int createHoaDon(HoaDon hd) throws RemoteException;
    void xuatHoaDon(int idhd) throws RemoteException;
}
