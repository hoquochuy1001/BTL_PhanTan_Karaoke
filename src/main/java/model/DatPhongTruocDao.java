package model;

import entity.DatPhongTruoc;
import entity.Phong;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public interface DatPhongTruocDao extends Remote {
    List<DatPhongTruoc> findPhongTrungThoiGian(String maPhong, Timestamp start, Timestamp end) throws RemoteException;

    // Lấy tất cả các đặt phòng trước
    List<DatPhongTruoc> getAllDatPhong() throws RemoteException;

    boolean addDatPhongTruoc(DatPhongTruoc datPhongTruoc) throws RemoteException;

    // Lưu thông tin đặt phòng trước
    boolean saveDatPhong(DatPhongTruoc datPhongTruoc) throws RemoteException;

    // Xóa thông tin đặt phòng trước
    boolean deleteDatPhong(int id) throws RemoteException;
    List<DatPhongTruoc> getBookingsForRoom(String maPhong) throws RemoteException;
    boolean updatePhong(DatPhongTruoc p) throws RemoteException;
    public DatPhongTruoc getDatPhongTruocById(int id) throws RemoteException;
}
