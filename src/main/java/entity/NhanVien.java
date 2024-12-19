package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@NamedNativeQueries({ @NamedNativeQuery(name = "getDSNhanVien", query = "{}", resultClass = NhanVien.class), })
public class NhanVien implements Serializable {
    @Id
    @Nationalized
    @Column(name = "maNV", nullable = false, length = 50)
    private String maNV;

    @Nationalized
    @Column(name = "tenNV", length = 20)
    private String tenNV;

    @Column(name = "ngaySinh")
    private LocalDate ngaySinh;

    @Nationalized
    @Column(name = "sdt", nullable = false, length = 10)
    private String sdt;

    @Nationalized
    @Column(name = "gioiTinh", length = 10)
    private String gioiTinh;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "maCV", nullable = false)
    private ChucVu maCV;

	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean delete) {
		isDelete = delete;
	}

	@Column(name = "isDelete", nullable = false)
	private boolean isDelete;
    public NhanVien(String maNV) {
        this.maNV = maNV;
    }

    public NhanVien() {
    }

    public NhanVien(String maNV, String tenNV, LocalDate ngaySinh, String sdt, String gioiTinh, ChucVu maCV) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.ngaySinh = ngaySinh;
        this.sdt = sdt;
        this.gioiTinh = gioiTinh;
        this.maCV = maCV;
    }


	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}

	public String getTenNV() {
		return tenNV;
	}

	public void setTenNV(String tenNV) {
		this.tenNV = tenNV;
	}

	public LocalDate getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(LocalDate ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public String getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public ChucVu getMaCV() {
		return maCV;
	}

	public void setMaCV(ChucVu maCV) {
		this.maCV = maCV;
	}



}