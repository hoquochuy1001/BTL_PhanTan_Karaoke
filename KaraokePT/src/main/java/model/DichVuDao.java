package model;

import entity.DichVu;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface DichVuDao extends Remote {
    List<DichVu> getAllDichVu() throws RemoteException;
    DichVu getDichVuByLoaiDichVu(String loaiDV) throws RemoteException;
    boolean createDichVu(DichVu dv) throws RemoteException;
    boolean updateDichVu(DichVu dv) throws RemoteException;
    boolean deleteDichVu(String maDV) throws RemoteException;
}
