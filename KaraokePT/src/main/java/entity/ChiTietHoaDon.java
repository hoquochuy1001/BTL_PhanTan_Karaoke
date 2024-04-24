package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@NamedNativeQueries({ @NamedNativeQuery(name = "getDSChiTietHoaDon", query = "{}", resultClass = ChiTietHoaDon.class), })
public class ChiTietHoaDon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaChiTietHD", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "MaHD", nullable = false)
    private HoaDon maHD;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "maDV", nullable = false)
    private DichVu maDV;

    @Column(name = "maPDP", nullable = false)
    private Integer maPDP;

    @Column(name = "SoLuong", nullable = false)
    private Integer soLuong;

    @Column(name = "ThanhTien", nullable = false)
    private BigDecimal thanhTien;

    @Nationalized
    @Column(name = "GhiChu", length = 200)
    private String ghiChu;

    public ChiTietHoaDon() {
    }

    public ChiTietHoaDon(Integer id) {
        this.id = id;
    }

    public ChiTietHoaDon(Integer id, HoaDon maHD, DichVu maDV, Integer maPDP, Integer soLuong, BigDecimal thanhTien, String ghiChu) {
        this.id = id;
        this.maHD = maHD;
        this.maDV = maDV;
        this.maPDP = maPDP;
        this.soLuong = soLuong;
        this.thanhTien = thanhTien;
        this.ghiChu = ghiChu;
    }
<<<<<<< HEAD
=======

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public HoaDon getMaHD() {
		return maHD;
	}

	public void setMaHD(HoaDon maHD) {
		this.maHD = maHD;
	}

	public DichVu getMaDV() {
		return maDV;
	}

	public void setMaDV(DichVu maDV) {
		this.maDV = maDV;
	}

	public Integer getMaPDP() {
		return maPDP;
	}

	public void setMaPDP(Integer maPDP) {
		this.maPDP = maPDP;
	}

	public Integer getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(Integer soLuong) {
		this.soLuong = soLuong;
	}

	public BigDecimal getThanhTien() {
		return thanhTien;
	}

	public void setThanhTien(BigDecimal thanhTien) {
		this.thanhTien = thanhTien;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}
    
    
>>>>>>> Khai-branch
}