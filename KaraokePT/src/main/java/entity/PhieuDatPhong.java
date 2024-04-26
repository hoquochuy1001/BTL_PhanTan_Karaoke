package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.Instant;

@Getter
@Setter
@Entity
@NamedNativeQueries({ @NamedNativeQuery(name = "getDSPhieuDatPhong", query = "{}", resultClass = PhieuDatPhong.class), })
public class PhieuDatPhong implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "maPDP", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "maPhong", nullable = false)
    private Phong maPhong;

    @Column(name = "tgDatPhong", nullable = false)
    private Timestamp tgDatPhong;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "maKH", nullable = false)
    private KhachHang maKH;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "maNV", nullable = false)
    private NhanVien maNV;

    public PhieuDatPhong() {
    }

    public PhieuDatPhong(Integer id) {
        this.id = id;
    }

    public PhieuDatPhong(Integer id, Phong maPhong, Timestamp tgDatPhong, KhachHang maKH, NhanVien maNV) {
        this.id = id;
        this.maPhong = maPhong;
        this.tgDatPhong = tgDatPhong;
        this.maKH = maKH;
        this.maNV = maNV;
    }


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Phong getMaPhong() {
		return maPhong;
	}

	public void setMaPhong(Phong maPhong) {
		this.maPhong = maPhong;
	}

	public Timestamp getTgDatPhong() {
		return tgDatPhong;
	}

	public void setTgDatPhong(Timestamp tgDatPhong) {
		this.tgDatPhong = tgDatPhong;
	}

	public KhachHang getMaKH() {
		return maKH;
	}

	public void setMaKH(KhachHang maKH) {
		this.maKH = maKH;
	}

	public NhanVien getMaNV() {
		return maNV;
	}

	public void setMaNV(NhanVien maNV) {
		this.maNV = maNV;
	}
    
    

}