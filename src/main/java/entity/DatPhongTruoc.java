package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Table(name = "DatPhongTruoc")
public class DatPhongTruoc implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "maPhong", nullable = false)
    private Phong phong; // Liên kết đến bảng Phong

    @Column(name = "maKH", nullable = false)
    private String maKH; // Mã khách hàng

    @Column(name = "thoiGianBatDau", nullable = false)
    private Timestamp thoiGianBatDau;

    @Column(name = "thoiGianKetThuc", nullable = false)
    private Timestamp thoiGianKetThuc;

    @Column(name = "trangThai", nullable = false)
    private String trangThai; // 'Đã đặt', 'Có khách', 'Trống'

    // Constructors
    public DatPhongTruoc() {
    }

    public DatPhongTruoc(Phong phong, String maKH, Timestamp thoiGianBatDau, Timestamp thoiGianKetThuc, String trangThai) {
        this.phong = phong;
        this.maKH = maKH;
        this.thoiGianBatDau = thoiGianBatDau;
        this.thoiGianKetThuc = thoiGianKetThuc;
        this.trangThai = trangThai;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Phong getPhong() {
        return phong;
    }

    public void setPhong(Phong phong) {
        this.phong = phong;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public Timestamp getThoiGianBatDau() {
        return thoiGianBatDau;
    }

    public void setThoiGianBatDau(Timestamp thoiGianBatDau) {
        this.thoiGianBatDau = thoiGianBatDau;
    }

    public Timestamp getThoiGianKetThuc() {
        return thoiGianKetThuc;
    }

    public void setThoiGianKetThuc(Timestamp thoiGianKetThuc) {
        this.thoiGianKetThuc = thoiGianKetThuc;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}
