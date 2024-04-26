package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

@Getter
@Setter
@Entity
@NamedNativeQueries({ @NamedNativeQuery(name = "getDSTaiKhoan", query = "{}", resultClass = TaiKhoan.class), })
public class TaiKhoan {
    @Id
    @Nationalized
    @Column(name = "tenTK", nullable = false, length = 10)
    private String tenTK;

    @Nationalized
    @Column(name = "matKhau", length = 10)
    private String matKhau;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maNV")
    private NhanVien maNV;

    public TaiKhoan(String tenTK, String matKhau, NhanVien maNV) {
        this.tenTK = tenTK;
        this.matKhau = matKhau;
        this.maNV = maNV;
    }

    public TaiKhoan() {
    }

    public TaiKhoan(NhanVien maNV) {
        this.maNV = maNV;
    }

    public String getTenTK() {
        return tenTK;
    }

    public void setTenTK(String tenTK) {
        this.tenTK = tenTK;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public NhanVien getMaNV() {
        return maNV;
    }

    public void setMaNV(NhanVien maNV) {
        this.maNV = maNV;
    }
}