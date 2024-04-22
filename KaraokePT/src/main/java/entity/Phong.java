package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@NamedNativeQueries({ @NamedNativeQuery(name = "getDSPhong", query = "{}", resultClass = Phong.class), })
public class Phong {
    @Id
    @Nationalized
    @Column(name = "maPhong", nullable = false, length = 10)
    private String maPhong;

    @Nationalized
    @Column(name = "tenPhong", length = 50)
    private String tenPhong;

    @Column(name = "giaPhong", nullable = false)
    private BigDecimal giaPhong;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loaiPhong")
    private LoaiPhong loaiPhong;

    @Nationalized
    @Column(name = "tinhTrang", length = 50)
    private String tinhTrang;

}