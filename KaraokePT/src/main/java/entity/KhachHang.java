package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedNativeQueries;
import jakarta.persistence.NamedNativeQuery;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

@Getter
@Setter
@Entity
@NamedNativeQueries({ @NamedNativeQuery(name = "getDSKhachHang", query = "{}", resultClass = KhachHang.class), })
public class KhachHang {
    @Id
    @Nationalized
    @Column(name = "maKH", nullable = false, length = 50)
    private String maKH;

    @Nationalized
    @Column(name = "tenKH", length = 20)
    private String tenKH;

    @Nationalized
    @Column(name = "cmnd", length = 20)
    private String cmnd;

    @Nationalized
    @Column(name = "sdt", length = 10)
    private String sdt;

}