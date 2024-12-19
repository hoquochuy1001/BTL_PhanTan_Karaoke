package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity
@NamedNativeQueries({ @NamedNativeQuery(name = "getDSPhong", query = "{}", resultClass = Phong.class), })
public class Phong implements java.io.Serializable{
    @Id
    @Nationalized
    @Column(name = "maPhong", nullable = false, length = 10)
    private String maPhong;

	@OneToMany(mappedBy = "phong")
	private List<DatPhongTruoc> datPhongTruocList;
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

    public Phong() {
    }

    public Phong(String maPhong) {
        this.maPhong = maPhong;
    }

    public Phong(String maPhong, String tenPhong, BigDecimal giaPhong, LoaiPhong loaiPhong, String tinhTrang) {
        this.maPhong = maPhong;
        this.tenPhong = tenPhong;
        this.giaPhong = giaPhong;
        this.loaiPhong = loaiPhong;
        this.tinhTrang = tinhTrang;
    }


	public String getMaPhong() {
		return maPhong;
	}

	public void setMaPhong(String maPhong) {
		this.maPhong = maPhong;
	}

	public String getTenPhong() {
		return tenPhong;
	}

	public void setTenPhong(String tenPhong) {
		this.tenPhong = tenPhong;
	}

	public BigDecimal getGiaPhong() {
		return giaPhong;
	}

	public void setGiaPhong(BigDecimal giaPhong) {
		this.giaPhong = giaPhong;
	}

	public LoaiPhong getLoaiPhong() {
		return loaiPhong;
	}

	public void setLoaiPhong(LoaiPhong loaiPhong) {
		this.loaiPhong = loaiPhong;
	}

	public String getTinhTrang() {
		return tinhTrang;
	}

	public void setTinhTrang(String tinhTrang) {
		this.tinhTrang = tinhTrang;
	}
    
    

}