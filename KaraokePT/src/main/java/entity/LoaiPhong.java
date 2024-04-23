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
@NamedNativeQueries({ @NamedNativeQuery(name = "getDSLoaiPhong", query = "{}", resultClass = LoaiPhong.class), })
public class LoaiPhong {
    @Id
    @Nationalized
    @Column(name = "loaiPhong", nullable = false, length = 50)
    private String loaiPhong;

    public LoaiPhong() {
    }
    public LoaiPhong(String loaiPhong) {
        this.loaiPhong = loaiPhong;
    }

    //TODO [JPA Buddy] generate columns from DB
}