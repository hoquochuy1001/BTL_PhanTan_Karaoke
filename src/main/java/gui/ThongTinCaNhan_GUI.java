package gui;

import Server.Config;
import entity.NhanVien;
import model.NhanVienDao;
import model.TaiKhoanDao;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.time.LocalDate;

public class ThongTinCaNhan_GUI extends JFrame implements ActionListener {
    private JTextField tfMaNV, tfTenNV, tfNgaySinh, tfSDT, tfGioiTinh, tfMaCV;
    private JButton btnSave, btnCancel;
    private NhanVienDao nv_dao;

    public ThongTinCaNhan_GUI(NhanVien nhanVien) {
        super("Thông Tin Cá Nhân");
        try{
            nv_dao = (NhanVienDao) Naming.lookup(Config.SERVER_URL+"nhanVienDao");
        }catch (Exception e){
            JOptionPane.showMessageDialog(this, "Server chưa mở");
        }
        JPanel mainPanel = new JPanel(new GridLayout(7, 2, 10, 10));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(0, 255, 255));

        JLabel lblMaNV = new JLabel("Mã nhân viên:");
        tfMaNV = new JTextField(nhanVien.getMaNV().trim());
        tfMaNV.setEditable(false);

        JLabel lblTenNV = new JLabel("Tên nhân viên:");
        tfTenNV = new JTextField(nhanVien.getTenNV());

        JLabel lblNgaySinh = new JLabel("Ngày sinh:");
        tfNgaySinh = new JTextField(nhanVien.getNgaySinh().toString());

        JLabel lblSDT = new JLabel("Số điện thoại:");
        tfSDT = new JTextField(nhanVien.getSdt());

        JLabel lblGioiTinh = new JLabel("Giới tính:");
        tfGioiTinh = new JTextField(nhanVien.getGioiTinh());

        JLabel lblMaCV = new JLabel("Mã công việc:");
        tfMaCV = new JTextField(nhanVien.getMaNV().trim());
        tfMaCV.setEditable(false);

        btnSave = new JButton("Lưu");
        btnCancel = new JButton("Hủy");

        // Thêm các thành phần vào giao diện
        mainPanel.add(lblMaNV);
        mainPanel.add(tfMaNV);
        mainPanel.add(lblTenNV);
        mainPanel.add(tfTenNV);
        mainPanel.add(lblNgaySinh);
        mainPanel.add(tfNgaySinh);
        mainPanel.add(lblSDT);
        mainPanel.add(tfSDT);
        mainPanel.add(lblGioiTinh);
        mainPanel.add(tfGioiTinh);
        mainPanel.add(lblMaCV);
        mainPanel.add(tfMaCV);
        mainPanel.add(btnSave);
        mainPanel.add(btnCancel);

        // Thêm mainPanel vào JFrame
        add(mainPanel);
        btnSave.addActionListener(this);
        btnCancel.addActionListener(this);

        // Cài đặt kích thước và hiển thị
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btnSave)) {
            try {
                handleUpdateUser();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        }
        if (o.equals(btnCancel)) {
            dispose();
        }
    }

    public  void handleUpdateUser() throws RemoteException {
        String maNV = tfMaNV.getText();
        String tenNV = tfTenNV.getText();
        String ngaySinh = tfNgaySinh.getText();
        String sdt = tfSDT.getText();
        String gioiTinh = tfGioiTinh.getText();
        String maCV = tfMaCV.getText();
        NhanVien nhanVien = nv_dao.getNhanVienByMaNhanVien(maNV);
        // Cập nhật thông tin vào đối tượng NhanVien
        nhanVien.setTenNV(tenNV);
        nhanVien.setNgaySinh(LocalDate.parse(ngaySinh));
        nhanVien.setSdt(sdt);
        nhanVien.setGioiTinh(gioiTinh);

        // Gọi phương thức cập nhật vào cơ sở dữ liệu
        boolean isSuccess = nv_dao.updateNhanVien(nhanVien);

        // Hiển thị thông báo theo kết quả
        if (isSuccess) {
            JOptionPane.showMessageDialog(null, "Cập nhật thông tin thành công!");
            dispose(); // Đóng cửa sổ sau khi cập nhật
        } else {
            JOptionPane.showMessageDialog(null, "Cập nhật thông tin thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
}

