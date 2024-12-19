package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@NamedNativeQueries({ @NamedNativeQuery(name = "getDSDichVu", query = "{}", resultClass = DichVu.class), })
public class DichVu implements java.io.Serializable{
    @Id
    @Nationalized
    @Column(name = "maDV", nullable = false, length = 10)
    private String maDV;

    @Nationalized
    @Column(name = "tenDV", length = 50)
    private String tenDV;

    @Nationalized
    @Column(name = "donViTinh", length = 50)
    private String donViTinh;

    @Column(name = "giaDV", nullable = false)
    private BigDecimal giaDV;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loaiDV")
    private LoaiDichVu loaiDV;

    @OneToMany(mappedBy = "maDV")
    private Set<ChiTietHoaDon> chiTietHoaDons = new LinkedHashSet<>();

    public DichVu() {
    }

    public DichVu(String maDV) {
        this.maDV = maDV;
    }

    public DichVu(String maDV, String tenDV, String donViTinh, BigDecimal giaDV, LoaiDichVu loaiDV) {
        this.maDV = maDV;
        this.tenDV = tenDV;
        this.donViTinh = donViTinh;
        this.giaDV = giaDV;
        this.loaiDV = loaiDV;
    }


	public String getMaDV() {
		return maDV;
	}

	public void setMaDV(String maDV) {
		this.maDV = maDV;
	}

	public String getTenDV() {
		return tenDV;
	}

	public void setTenDV(String tenDV) {
		this.tenDV = tenDV;
	}

	public String getDonViTinh() {
		return donViTinh;
	}

	public void setDonViTinh(String donViTinh) {
		this.donViTinh = donViTinh;
	}

	public BigDecimal getGiaDV() {
		return giaDV;
	}

	public void setGiaDV(BigDecimal giaDV) {
		this.giaDV = giaDV;
	}

	public LoaiDichVu getLoaiDV() {
		return loaiDV;
	}

	public void setLoaiDV(LoaiDichVu loaiDV) {
		this.loaiDV = loaiDV;
	}

	public Set<ChiTietHoaDon> getChiTietHoaDons() {
		return chiTietHoaDons;
	}

	public void setChiTietHoaDons(Set<ChiTietHoaDon> chiTietHoaDons) {
		this.chiTietHoaDons = chiTietHoaDons;
	}




}