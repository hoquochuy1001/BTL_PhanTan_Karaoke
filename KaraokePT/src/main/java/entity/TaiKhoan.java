package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.io.Serializable;

@Getter
@Setter
@Entity
@NamedNativeQueries({ @NamedNativeQuery(name = "getDSTaiKhoan", query = "{}", resultClass = TaiKhoan.class), })
public class TaiKhoan implements Serializable {
    private static final long serialVersionUID = 1L;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Nationalized
    @Column(name = "role", nullable = false, length = 20)
    private String role;

    public TaiKhoan(String tenTK, String matKhau, NhanVien maNV, String role) {
        this.tenTK = tenTK;
        this.matKhau = matKhau;
        this.maNV = maNV;
        this.role = role;

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