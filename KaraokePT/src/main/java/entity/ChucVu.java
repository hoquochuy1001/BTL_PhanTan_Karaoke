package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class ChucVu implements Serializable {
    @Id
    @Nationalized
    @Column(name = "maCV", nullable = false, length = 10)
    private String maCV;

    @Nationalized
    @Column(name = "tenCV", length = 10)
    private String tenCV;

    @OneToMany(mappedBy = "maCV")
    private Set<NhanVien> nhanViens = new LinkedHashSet<>();

	public ChucVu() {
		super();
	}

	public ChucVu(String maCV, String tenCV) {
		super();
		this.maCV = maCV;
		this.tenCV = tenCV;
	}

	public ChucVu(String chucvu) {
		this.maCV = chucvu;

	}




	public String getMaCV() {
		return maCV;
	}

	public void setMaCV(String maCV) {
		this.maCV = maCV;
	}

	public String getTenCV() {
		return tenCV;
	}

	public void setTenCV(String tenCV) {
		this.tenCV = tenCV;
	}

	public Set<NhanVien> getNhanViens() {
		return nhanViens;
	}

	public void setNhanViens(Set<NhanVien> nhanViens) {
		this.nhanViens = nhanViens;
	}


}