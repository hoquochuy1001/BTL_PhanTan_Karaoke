package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@NamedNativeQueries({ @NamedNativeQuery(name = "getDSDichVu", query = "{}", resultClass = DichVu.class), })
public class DichVu {
    @Id
    @Nationalized
    @Column(name = "maDV", nullable = false, length = 10)
    private String maDV;

    @Nationalized
    @Column(name = "tenDV", length = 50)
    private String tenDV;

    @Nationalized
    @Column(name = "donViTinh", length = 50)
    private String donViTinh;

    @Column(name = "giaDV", nullable = false)
    private BigDecimal giaDV;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loaiDV")
    private LoaiDichVu loaiDV;

    @OneToMany(mappedBy = "maDV")
    private Set<ChiTietHoaDon> chiTietHoaDons = new LinkedHashSet<>();

}