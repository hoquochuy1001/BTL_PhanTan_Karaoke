package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.Instant;

@Getter
@Setter
@Entity
@NamedNativeQueries({ @NamedNativeQuery(name = "getDSPhieuDatPhong", query = "{}", resultClass = PhieuDatPhong.class), })
public class PhieuDatPhong {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "maPDP", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "maPhong", nullable = false)
    private Phong maPhong;

    @Column(name = "tgDatPhong", nullable = false)
    private Timestamp tgDatPhong;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "maKH", nullable = false)
    private KhachHang maKH;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "maNV", nullable = false)
    private NhanVien maNV;

    public PhieuDatPhong() {
    }

    public PhieuDatPhong(Integer id) {
        this.id = id;
    }

    public PhieuDatPhong(Integer id, Phong maPhong, Timestamp tgDatPhong, KhachHang maKH, NhanVien maNV) {
        this.id = id;
        this.maPhong = maPhong;
        this.tgDatPhong = tgDatPhong;
        this.maKH = maKH;
        this.maNV = maNV;
    }
}