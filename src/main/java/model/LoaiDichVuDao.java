package model;

import entity.LoaiDichVu;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface LoaiDichVuDao extends Remote {
    List<LoaiDichVu> getAllLoaiDichVu() throws RemoteException;
    boolean createLoaiDichVu(LoaiDichVu loaidv) throws RemoteException;
    boolean deleteLoaiDichVu(String loaiDV) throws RemoteException;
}
