package  gui;
import Server.Config;
import com.toedter.calendar.JDateChooser;
import entity.DatPhongTruoc;
import entity.Phong;
import model.DatPhongTruocDao;
import model.PhongDao;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
public class formDatPhongTruoc extends JFrame implements ActionListener {
    private JTextField tfMaPhong, tfTenKH, tfTrangThai;
    private JButton btnSave, btnCancel;
    private JDateChooser dcThoiGianBatDau, dcThoiGianKetThuc;
    private JComboBox<String> cbGioBatDau, cbPhutBatDau, cbGioKetThuc, cbPhutKetThuc;
    private DatPhongTruocDao dp_dao;
    private PhongDao p_dao;

    public formDatPhongTruoc(String maKH, String maPhong) {
        super("Đặt phòng trước");

        try {
            dp_dao = (DatPhongTruocDao) Naming.lookup(Config.SERVER_URL + "datPhongTruocDao");
            p_dao = (PhongDao) Naming.lookup(Config.SERVER_URL + "phongDao");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Server chưa mở");
        }

        JPanel mainPanel = new JPanel(new GridLayout(8, 2, 10, 10));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(200, 230, 250));

        JLabel lblMaPhong = new JLabel("Mã phòng:");
        tfMaPhong = new JTextField(maPhong);

        JLabel lblTenKH = new JLabel("Tên khách hàng:");
        tfTenKH = new JTextField(maKH);

        JLabel lblThoiGianBatDau = new JLabel("Ngày bắt đầu:");
        dcThoiGianBatDau = new JDateChooser();
        dcThoiGianBatDau.setDateFormatString("yyyy-dd-MM");

        JLabel lblGioBatDau = new JLabel("Giờ bắt đầu:");
        cbGioBatDau = createComboBox(0, 23);
        cbPhutBatDau = createComboBox(0, 59);

        JLabel lblThoiGianKetThuc = new JLabel("Ngày kết thúc:");
        dcThoiGianKetThuc = new JDateChooser();
        dcThoiGianKetThuc.setDateFormatString("yyyy-MM-dd");

        JLabel lblGioKetThuc = new JLabel("Giờ kết thúc:");
        cbGioKetThuc = createComboBox(0, 23);
        cbPhutKetThuc = createComboBox(0, 59);


        JLabel lblTrangThai = new JLabel("Trạng thái:");
        tfTrangThai = new JTextField("Đã đặt");

        btnSave = new JButton("Lưu");
        btnCancel = new JButton("Hủy");

        mainPanel.add(lblMaPhong);
        mainPanel.add(tfMaPhong);
        mainPanel.add(lblTenKH);
        mainPanel.add(tfTenKH);
        mainPanel.add(lblThoiGianBatDau);
        mainPanel.add(dcThoiGianBatDau);
        mainPanel.add(lblGioBatDau);
        mainPanel.add(createTimePanel(cbGioBatDau, cbPhutBatDau));
        mainPanel.add(lblThoiGianKetThuc);
        mainPanel.add(dcThoiGianKetThuc);
        mainPanel.add(lblGioKetThuc);
        mainPanel.add(createTimePanel(cbGioKetThuc, cbPhutKetThuc));
        mainPanel.add(lblTrangThai);
        mainPanel.add(tfTrangThai);
        mainPanel.add(btnSave);
        mainPanel.add(btnCancel);

        add(mainPanel);

        btnSave.addActionListener(this);
        btnCancel.addActionListener(this);

        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btnSave)) {
            try {
                handleSaveBooking();
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        }
        if (o.equals(btnCancel)) {
            dispose();
        }
    }

    public void handleSaveBooking() throws RemoteException {
        String maPhong = tfMaPhong.getText().trim();
        Phong phong = p_dao.getPhongByMaPhong(maPhong);
        String tenKH = tfTenKH.getText().trim();

        Date thoiGianBatDauDate = dcThoiGianBatDau.getDate();
        Date thoiGianKetThucDate = dcThoiGianKetThuc.getDate();

        if (thoiGianBatDauDate == null || thoiGianKetThucDate == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String trangThai = tfTrangThai.getText();

        if (maPhong.isEmpty() || tenKH.isEmpty() || trangThai.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Kiểm tra trạng thái của phòng trước khi đặt
        if ("Phòng có Khách".equals(phong.getTinhTrang().trim())) {
            List<DatPhongTruoc> existingBookings = dp_dao.getBookingsForRoom(maPhong);

            if (!existingBookings.isEmpty()) {
                Timestamp lastEndTime = existingBookings.get(existingBookings.size() - 1).getThoiGianKetThuc();

                Timestamp newStartTime = getTimestampFromChooser(dcThoiGianBatDau, cbGioBatDau, cbPhutBatDau);

                if (newStartTime.before(lastEndTime)) {
                    JOptionPane.showMessageDialog(this, "Phòng này chỉ có thể đặt sau thời gian kết thúc của khách đã đặt trước.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } else {
                // Nếu phòng không có đặt phòng trước, không cho phép đặt
                JOptionPane.showMessageDialog(this, "Phòng này hiện không thể đặt vì không có đặt phòng trước.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        try {
            Timestamp startTimestamp = getTimestampFromChooser(dcThoiGianBatDau, cbGioBatDau, cbPhutBatDau);
            Timestamp endTimestamp = getTimestampFromChooser(dcThoiGianKetThuc, cbGioKetThuc, cbPhutKetThuc);

            // Xử lý trực tiếp với Timestamp
            System.out.println("Thời gian bắt đầu (Timestamp): " + startTimestamp);
            System.out.println("Thời gian kết thúc (Timestamp): " + endTimestamp);

            if (startTimestamp.after(endTimestamp)) {
                JOptionPane.showMessageDialog(this, "Thời gian bắt đầu không được sau thời gian kết thúc!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Check if the start time is before the current time
            Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
            if (startTimestamp.before(currentTimestamp)) {
                JOptionPane.showMessageDialog(this, "Thời gian bắt đầu không được trước thời gian hiện tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Check if the room is already booked in the given time range
            boolean isRoomAvailable = checkRoomAvailability(maPhong, startTimestamp, endTimestamp);
            if (!isRoomAvailable) {
                JOptionPane.showMessageDialog(this, "Phòng đã được đặt trong khoảng thời gian này!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            DatPhongTruoc datPhong = new DatPhongTruoc(phong, tenKH, startTimestamp, endTimestamp, trangThai);
            boolean isSuccess = dp_dao.addDatPhongTruoc(datPhong);

            if (isSuccess) {
                JOptionPane.showMessageDialog(this, "Đặt phòng thành công!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Đặt phòng thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method to check room availability (no overlapping bookings)
    private boolean checkRoomAvailability(String maPhong, Timestamp startTimestamp, Timestamp endTimestamp) throws RemoteException {
        List<DatPhongTruoc> existingBookings = dp_dao.getBookingsForRoom(maPhong);

        for (DatPhongTruoc booking : existingBookings) {
            // Check if the existing booking overlaps with the new booking
            if (startTimestamp.before(booking.getThoiGianKetThuc()) && endTimestamp.after(booking.getThoiGianBatDau())) {
                return false;  // Room is not available due to overlap
            }
        }

        return true;  // Room is available
    }

    private JComboBox<String> createComboBox(int start, int end) {
        String[] items = new String[end - start + 1];
        for (int i = start; i <= end; i++) {
            items[i - start] = String.format("%02d", i);
        }
        return new JComboBox<>(items);
    }
    public Timestamp getTimestampFromChooser(JDateChooser dateChooser, JComboBox<String> hourComboBox, JComboBox<String> minuteComboBox) {
        Date date = dateChooser.getDate();
        if (date == null) {
            throw new IllegalArgumentException("Ngày không được để trống.");
        }

        int hour = Integer.parseInt((String) hourComboBox.getSelectedItem());
        int minute = Integer.parseInt((String) minuteComboBox.getSelectedItem());

        // Chuyển Date thành Timestamp
        LocalDateTime localDateTime = date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate()
                .atTime(hour, minute);

        return Timestamp.valueOf(localDateTime); // Trả về Timestamp
    }

    private JPanel createTimePanel(JComboBox<String> cbHour, JComboBox<String> cbMinute) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(cbHour);
        panel.add(new JLabel("h"));
        panel.add(cbMinute);
        panel.add(new JLabel("m"));
        return panel;
    }
    public String formatDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTime.format(formatter); // Trả về chuỗi không có 'T'
    }
    public LocalDateTime getDateTimeFromChooser(JDateChooser dateChooser, JComboBox<String> hourComboBox, JComboBox<String> minuteComboBox) {
        Date date = dateChooser.getDate();
        if (date == null) {
            throw new IllegalArgumentException("Ngày không được để trống.");
        }

        int hour = Integer.parseInt((String) hourComboBox.getSelectedItem());
        int minute = Integer.parseInt((String) minuteComboBox.getSelectedItem());

        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate()
                .atTime(hour, minute); // Kết hợp ngày và giờ
    }

}
