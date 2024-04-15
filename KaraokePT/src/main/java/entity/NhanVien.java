package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class NhanVien {
    @Id
    @Nationalized
    @Column(name = "maNV", nullable = false, length = 50)
    private String maNV;

    @Nationalized
    @Column(name = "tenNV", length = 20)
    private String tenNV;

    @Column(name = "ngaySinh")
    private LocalDate ngaySinh;

    @Nationalized
    @Column(name = "sdt", nullable = false, length = 10)
    private String sdt;

    @Nationalized
    @Column(name = "gioiTinh", length = 10)
    private String gioiTinh;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "maCV", nullable = false)
    private ChucVu maCV;

}