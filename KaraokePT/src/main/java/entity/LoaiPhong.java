package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

@Getter
@Setter
@Entity
public class LoaiPhong {
    @Id
    @Nationalized
    @Column(name = "loaiPhong", nullable = false, length = 50)
    private String loaiPhong;

    //TODO [JPA Buddy] generate columns from DB
}