package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class ChiTietHoaDon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaChiTietHD", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "MaHD", nullable = false)
    private HoaDon maHD;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "maDV", nullable = false)
    private DichVu maDV;

    @Column(name = "maPDP", nullable = false)
    private Integer maPDP;

    @Column(name = "SoLuong", nullable = false)
    private Integer soLuong;

    @Column(name = "ThanhTien", nullable = false)
    private BigDecimal thanhTien;

    @Nationalized
    @Column(name = "GhiChu", length = 200)
    private String ghiChu;

}