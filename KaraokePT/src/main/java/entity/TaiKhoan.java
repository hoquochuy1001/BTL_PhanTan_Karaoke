package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

@Getter
@Setter
@Entity
@NamedNativeQueries({ @NamedNativeQuery(name = "getDSTaiKhoan", query = "{}", resultClass = TaiKhoan.class), })
public class TaiKhoan {
    @Id
    @Nationalized
    @Column(name = "tenTK", nullable = false, length = 10)
    private String tenTK;

    @Nationalized
    @Column(name = "matKhau", length = 10)
    private String matKhau;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maNV")
    private NhanVien maNV;

}