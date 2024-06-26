package bll;


import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.Date;

public class ChuyenDoi {

    //Hàm đổi từ số sang chữ có định dạng 
    public static String DinhDangTien(double tien) {
        return NumberFormat.getNumberInstance().format(tien);
    }

    //Hàm chuyển từ chữ sang số để tính toán
    public static double ChuyenTien(String tien) {
        try {
            return NumberFormat.getNumberInstance().parse(tien).doubleValue();
        } catch (ParseException ex) {
//            GUI.frmHeThong.ThongBao("Thông báo", "Lỗi chuyển dữ liệu");

        }
        return 0;
    }

    //Hàm chuyển ngày tháng sang chữ 
    public static String DinhDangNgay(java.util.Date ngay ) {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        
        return dateFormat.format(ngay);
    }

}
