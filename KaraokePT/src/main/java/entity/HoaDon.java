package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class HoaDon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaHD", nullable = false)
    private Integer id;

    @Column(name = "SoHoaDon", nullable = false, length = 15)
    private String soHoaDon;

    @Column(name = "NgayTaoHD", nullable = false)
    private LocalDate ngayTaoHD;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "maNV", nullable = false)
    private NhanVien maNV;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "maKH", nullable = false)
    private KhachHang maKH;

    @Column(name = "TongTien", nullable = false)
    private BigDecimal tongTien;

    @Nationalized
    @Column(name = "GhiChu", length = 250)
    private String ghiChu;

    @Nationalized
    @Column(name = "IDCode", length = 25)
    private String iDCode;

}