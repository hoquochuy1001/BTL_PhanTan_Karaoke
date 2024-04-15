package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
public class PhieuDatPhong {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "maPDP", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "maPhong", nullable = false)
    private Phong maPhong;

    @Column(name = "tgDatPhong", nullable = false)
    private Instant tgDatPhong;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "maKH", nullable = false)
    private KhachHang maKH;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "maNV", nullable = false)
    private NhanVien maNV;

}