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
@NamedNativeQueries({ @NamedNativeQuery(name = "getDSLoaiDichVu", query = "{}", resultClass = LoaiDichVu.class), })
public class LoaiDichVu implements java.io.Serializable{
    @Id
    @Nationalized
    @Column(name = "loaiDV", nullable = false, length = 50)
    private String loaiDV;

    public LoaiDichVu() {
    }

    public LoaiDichVu(String loaiDV) {
        this.loaiDV = loaiDV;
    }


	public String getLoaiDV() {
		return loaiDV;
	}

	public void setLoaiDV(String loaiDV) {
		this.loaiDV = loaiDV;
	}

}