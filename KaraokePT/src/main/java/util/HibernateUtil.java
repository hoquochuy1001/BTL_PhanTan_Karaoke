package util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import entity.*;

public class HibernateUtil {
    public static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                // Tạo StandardServiceRegistry từ cấu hình trong file hibernate.cfg.xml
                StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                        .configure("hibernate.cfg.xml")
                        .build();

                // Tạo Metadata từ sources đã cấu hình và đăng ký với registry
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

                // Tạo SessionFactory từ Metadata
                sessionFactory = metadata.getSessionFactoryBuilder().build();
            } catch (Exception e) {
                System.err.println("Không thể khởi tạo SessionFactory: " + e);
                throw new ExceptionInInitializerError(e);
            }
        }
        return sessionFactory;
    }

    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
