package model;

import entity.ChucVu;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ChucVuDao extends Remote {
    List<ChucVu> getAllChucVu() throws RemoteException;
    boolean createChucVu(ChucVu cv) throws RemoteException;
    boolean updateChucVu(ChucVu cv) throws RemoteException;
    boolean deleteChucVu(String maCV) throws RemoteException;
    ChucVu getChucVuByMa(String ma) throws RemoteException;
}