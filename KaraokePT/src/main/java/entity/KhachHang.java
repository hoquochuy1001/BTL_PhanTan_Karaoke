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
public class KhachHang implements java.io.Serializable{
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

    public KhachHang() {
    }

    public KhachHang(String maKH, String tenKH, String cmnd, String sdt) {
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.cmnd = cmnd;
        this.sdt = sdt;
    }

    public KhachHang(String khachhang) {
        this.maKH = khachhang;
    }


	public String getMaKH() {
		return maKH;
	}

	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}

	public String getTenKH() {
		return tenKH;
	}

	public void setTenKH(String tenKH) {
		this.tenKH = tenKH;
	}

	public String getCmnd() {
		return cmnd;
	}

	public void setCmnd(String cmnd) {
		this.cmnd = cmnd;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
    
    

}