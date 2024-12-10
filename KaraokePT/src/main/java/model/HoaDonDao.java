package model;

import entity.HoaDon;
import entity.HoaDonRevenue;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedNativeQueries;
import jakarta.persistence.NamedNativeQuery;
import lombok.Getter;
import lombok.Setter;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;


public interface HoaDonDao extends Remote {
    Long countSoHoaDon(String soHoaDon) throws RemoteException;
    HoaDon getBySoHoaDon(String soHoaDon) throws RemoteException;
    int createHoaDon(HoaDon hd) throws RemoteException;
    void xuatHoaDon(int idhd) throws RemoteException;
    List<HoaDonRevenue> getDoanhThuTheoThang() throws RemoteException;
    public List<HoaDon> getAllHoaDon() throws RemoteException;
    public List<HoaDonRevenue> getDoanhThuTheoNgayTrongTuan() throws RemoteException;



}
