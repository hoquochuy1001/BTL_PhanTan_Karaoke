package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@NamedNativeQuery(
		name = "getDSHoaDon",
		query = "SELECT * FROM HoaDon",
		resultClass = HoaDon.class
)

@NamedNativeQuery(
		name = "getDoanhThuTheoThang",
		query = "SELECT MONTH(NgayTaoHD) AS month, SUM(TongTien) AS totalRevenue FROM HoaDon GROUP BY MONTH(NgayTaoHD) ORDER BY month ASC",
		resultSetMapping = "HoaDonRevenueMapping"
)

@SqlResultSetMapping(
		name = "HoaDonRevenueMapping",
		classes = @ConstructorResult(
				targetClass = HoaDonRevenue.class,
				columns = {
						@ColumnResult(name = "month", type = Integer.class),
						@ColumnResult(name = "totalRevenue", type = BigDecimal.class)
				}
		)
)

public class HoaDon implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaHD", nullable = false)
    private Integer id;

    @Column(name = "SoHoaDon", nullable = false, length = 15)
    private String soHoaDon;

    @Column(name = "NgayTaoHD", nullable = false)
    private LocalDate ngayTaoHD;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "maNV", nullable = false)
    private NhanVien maNV;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "maKH", nullable = false)
    private KhachHang maKH;

    @Column(name = "TongTien", nullable = false)
    private BigDecimal tongTien;

    @Nationalized
    @Column(name = "GhiChu", length = 250)
    private String ghiChu;

    @Nationalized
    @Column(name = "IDCode", length = 25)
    private String iDCode;


    public HoaDon() {
    }

    public HoaDon(Integer id) {
        this.id = id;
    }

    public HoaDon(Integer id, String soHoaDon, LocalDate ngayTaoHD, NhanVien maNV, KhachHang maKH, BigDecimal tongTien, String ghiChu, String iDCode) {
        this.id = id;
        this.soHoaDon = soHoaDon;
        this.ngayTaoHD = ngayTaoHD;
        this.maNV = maNV;
        this.maKH = maKH;
        this.tongTien = tongTien;
        this.ghiChu = ghiChu;
        this.iDCode = iDCode;
    }


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSoHoaDon() {
		return soHoaDon;
	}

	public void setSoHoaDon(String soHoaDon) {
		this.soHoaDon = soHoaDon;
	}

	public LocalDate getNgayTaoHD() {
		return ngayTaoHD;
	}

	public void setNgayTaoHD(LocalDate ngayTaoHD) {
		this.ngayTaoHD = ngayTaoHD;
	}

	public NhanVien getMaNV() {
		return maNV;
	}

	public void setMaNV(NhanVien maNV) {
		this.maNV = maNV;
	}

	public KhachHang getMaKH() {
		return maKH;
	}

	public void setMaKH(KhachHang maKH) {
		this.maKH = maKH;
	}

	public BigDecimal getTongTien() {
		return tongTien;
	}

	public void setTongTien(BigDecimal tongTien) {
		this.tongTien = tongTien;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	public String getiDCode() {
		return iDCode;
	}

	public void setiDCode(String iDCode) {
		this.iDCode = iDCode;
	}
    
    

}