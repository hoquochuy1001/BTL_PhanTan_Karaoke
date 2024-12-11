package model;

import entity.Phong;
import entity.TaiKhoan;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface TaiKhoanDao extends Remote {
    List<TaiKhoan> getAllTaiKhoan() throws RemoteException;
    TaiKhoan getTaiKhoanById(String id) throws RemoteException;
    boolean createTaiKhoan(TaiKhoan t) throws RemoteException;
    boolean updateTaiKhoan(TaiKhoan t) throws RemoteException;
    boolean deleteTaiKhoan(String id) throws RemoteException;
    public String getLastTenTK() throws RemoteException;
    public String generateNextTenTK() throws RemoteException;
    public boolean checkLogin(String username, String password) throws RemoteException;
}
