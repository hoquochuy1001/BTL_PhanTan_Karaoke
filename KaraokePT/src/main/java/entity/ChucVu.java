package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class ChucVu {
    @Id
    @Nationalized
    @Column(name = "maCV", nullable = false, length = 10)
    private String maCV;

    @Nationalized
    @Column(name = "tenCV", length = 10)
    private String tenCV;

    @OneToMany(mappedBy = "maCV")
    private Set<NhanVien> nhanViens = new LinkedHashSet<>();

}