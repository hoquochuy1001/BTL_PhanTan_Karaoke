import entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;

public class Main {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml") // sử dụng cấu hình từ file hibernate.cfg.xml
                .build();

        Metadata metadata = new MetadataSources(registry)
                .addAnnotatedClass(ChiTietHoaDon.class)
                .addAnnotatedClass(ChucVu.class)
                .addAnnotatedClass(DichVu.class)
                .addAnnotatedClass(HoaDon.class)
                .addAnnotatedClass(KhachHang.class)
                .addAnnotatedClass(LoaiDichVu.class)
                .addAnnotatedClass(NhanVien.class)
                .addAnnotatedClass(PhieuDatPhong.class)
                .addAnnotatedClass(Phong.class)
                .addAnnotatedClass(LoaiPhong.class)
                .addAnnotatedClass(TaiKhoan.class)
                .getMetadataBuilder()
                .build();

        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();

        session.close();
        sessionFactory.close();
    }
}
