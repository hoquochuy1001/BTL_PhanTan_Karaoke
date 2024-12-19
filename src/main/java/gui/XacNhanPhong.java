package gui;

import java.awt.BorderLayout;
import java.awt.event.*;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.sql.*;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.awt.TextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.Component;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


import Server.Config;
import bll.SessionManager;
import entity.*;
import model.*;
import org.hibernate.LazyInitializationException;
import util.HibernateUtil;

public class XacNhanPhong extends JFrame implements ActionListener, MouseListener{

    private JPanel contentPane;
    private JTable table_1;
    private JTable table_2;
    private JTable table_3;
    private JTable table_4;
    private KhachHangDao kh_dao;
    private NhanVienDao nv_dao;
    private PhongDao phong_dao;
    private DefaultTableModel tableModel1;
    private DefaultTableModel tableModel2;
    private DefaultTableModel tableModel3;
    private DefaultTableModel tableModel4;
    private JButton btnNewButton_DP;
    private JTextField textField;
    private JTextField textField_1;
    private PhieuDatPhongDao pdp_dao;
    private DatPhongTruocDao dpt_dao;
    private JButton btnNewButton_load,btnNewButton_Confirm;
    private TaiKhoan user;
    private TaiKhoanDao tk_dao;
    private SessionManager currentUser = SessionManager.getInstance();
    private JTextField txt_timkiemKH;
    private JTextField txt_timkiemnNV;
    private JTextField txt_timkiemP;
    private String maKH, maPhong;
    private int id;


    public XacNhanPhong(int id,String maKH, String maPhong) throws RemoteException {

        try {
            kh_dao = (KhachHangDao) Naming.lookup(Config.SERVER_URL+"khachHangDao");
            nv_dao = (NhanVienDao) Naming.lookup(Config.SERVER_URL+"nhanVienDao");
            phong_dao = (PhongDao) Naming.lookup(Config.SERVER_URL+"phongDao");
            pdp_dao = (PhieuDatPhongDao) Naming.lookup(Config.SERVER_URL+"phieuDatPhongDao");
            tk_dao = (TaiKhoanDao) Naming.lookup(Config.SERVER_URL + "taiKhoanDao");
            dpt_dao = (DatPhongTruocDao) Naming.lookup(Config.SERVER_URL + "datPhongTruocDao");

        } catch (Exception e) {
            e.printStackTrace();
        }

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    //        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setSize(600, 600);
        setLocationRelativeTo(null);

        setTitle("Xác nhận phòng");
        this.maKH = maKH;
        this.maPhong = maPhong;
        this.id=id;
        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);


        JLabel lblNewLabel_maNV_1 = new JLabel("Tìm Kiếm Nhân viên");
        lblNewLabel_maNV_1.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNewLabel_maNV_1.setBounds(10, 27, 188, 29);
        contentPane.add(lblNewLabel_maNV_1);

        txt_timkiemnNV = new JTextField("Tìm kiếm tên/mã...");
        txt_timkiemnNV.setForeground(Color.GRAY);
        txt_timkiemnNV.setColumns(10);
        txt_timkiemnNV.setBounds(10, 52, 236, 26);
        contentPane.add(txt_timkiemnNV);
        txt_timkiemnNV.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (txt_timkiemnNV.getText().equals("Tìm kiếm tên/mã...")) {
                    txt_timkiemnNV.setText("");
                    txt_timkiemnNV.setForeground(Color.BLACK); // Đổi màu chữ khi có input
                }
            }

            public void focusLost(FocusEvent e) {
                if (txt_timkiemnNV.getText().equals("")) {
                    txt_timkiemnNV.setText("Tìm kiếm tên/mã...");
                    txt_timkiemnNV.setForeground(Color.GRAY); // Đổi màu lại khi không có input
                }
            }
        });

        Box horizontalBox_3 = Box.createHorizontalBox();
        horizontalBox_3.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Danh s\u00E1ch nh\u00E2n vi\u00EAn ph\u1EE5c v\u1EE5", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        horizontalBox_3.setBounds(10, 84, 522, 315);
        contentPane.add(horizontalBox_3);

        String [] headers3 = {"Mã Nhân Viên","Tên Nhân Viên", "SĐT"};
        tableModel3=new DefaultTableModel(headers3,0);
        JScrollPane scroll3 = new JScrollPane();
        scroll3.setViewportView(table_3 = new JTable(tableModel3));
        table_3.setRowHeight(25);
        table_3.setAutoCreateRowSorter(true);
        table_3.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);

        JScrollPane scrollPane_3 = new JScrollPane(table_3);
        horizontalBox_3.add(scrollPane_3);


        btnNewButton_Confirm = new JButton("Xác nhận phòng");
        btnNewButton_Confirm.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnNewButton_Confirm.setBounds(200, 410, 157, 65);
        contentPane.add(btnNewButton_Confirm);


        btnNewButton_Confirm.addActionListener(this);
        table_3.addMouseListener(this);


        DocDuLieuDatabaseVaoTable3();

        txt_timkiemnNV.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String searchQuery = txt_timkiemnNV.getText().trim().toLowerCase();
                updateTableWithFilterForNV(searchQuery);
            }
        });





        this.setVisible(true);
    }



    private void DocDuLieuDatabaseVaoTable4() throws RemoteException {
        // TODO Auto-generated method stub
        tableModel4.setRowCount(0);

        List<DatPhongTruoc> list = dpt_dao.getAllDatPhong();

        for (DatPhongTruoc dpt : list) {
            String[] rowData = {
                    dpt.getId() + "",
                    dpt.getPhong().getMaPhong(),
                    dpt.getMaKH(),
                    dpt.getThoiGianBatDau()+ "",
                    dpt.getThoiGianKetThuc().toString(),
                    dpt.getTrangThai(),
            };

            tableModel4.addRow(rowData);
        }

        table_4.setModel(tableModel4);
    }



    private void DocDuLieuDatabaseVaoTable3() throws RemoteException {
        tableModel3.setRowCount(0);

        List<NhanVien> list = nv_dao.getNhanVienExceptAdmin();
        for(NhanVien s : list) {
            String[] rowData = {s.getMaNV(), s.getTenNV(), s.getSdt()};
            tableModel3.addRow(rowData);
        }
        table_3.setModel(tableModel3);
    }

    private void DocDuLieuDatabaseVaoTable2() throws RemoteException {
        // TODO Auto-generated method stub
        tableModel2.setRowCount(0);

        List<Phong> list = phong_dao.getAllPhong();

        // Xóa dữ liệu cũ trong bảng nếu có

        for (Phong s : list) {
            String[] rowData = {
                    s.getMaPhong(),
                    s.getTenPhong(),
                    s.getLoaiPhong().getLoaiPhong(),
                    s.getTinhTrang()
            };
            tableModel2.addRow(rowData);
        }

        table_2.setModel(tableModel2);

        // Áp dụng renderer tùy chỉnh để tô màu các hàng
        table_2.setDefaultRenderer(Object.class, new CustomTableCellRenderer());
    }
    class CustomTableCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            String tinhTrang = table.getValueAt(row, 3).toString();
            System.out.println("tinhTrang"+tinhTrang);
            if ("Phòng có Khách".equalsIgnoreCase(tinhTrang != null ? tinhTrang.trim() : "")) {
                System.out.println("yes");

                cell.setBackground(Color.ORANGE);
            } else {
                cell.setBackground(Color.WHITE);
            }

            if (isSelected) {
                cell.setBackground(table.getSelectionBackground());
                cell.setForeground(table.getSelectionForeground());
            } else {
                cell.setForeground(Color.BLACK);
            }

            return cell;
        }
    }


    private void DocDuLieuDatabaseVaoTable1() throws RemoteException {
        // TODO Auto-generated method stub

        List<KhachHang> list = kh_dao.getAllKhachHang();
        for(KhachHang s : list) {
            String[] rowData = {s.getMaKH(), s.getTenKH(),s.getSdt()};
            tableModel1.addRow(rowData);
        }
        table_1.setModel(tableModel1);
    }

    private void updateTableWithFilter(String searchQuery) {
        tableModel2.setRowCount(0);

        try {
            List<Phong> list = phong_dao.getAllPhong();
            for (Phong s : list) {
                String maPhong = s.getMaPhong().toLowerCase();
                String tenPhong = s.getTenPhong().toLowerCase();


                if (maPhong.contains(searchQuery) || tenPhong.contains(searchQuery)) {
                    String[] rowData = {
                            s.getMaPhong(),
                            s.getTenPhong(),
                            s.getLoaiPhong().getLoaiPhong(),
                            s.getTinhTrang()
                    };
                    tableModel2.addRow(rowData);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        table_2.setModel(tableModel2);
        table_2.setDefaultRenderer(Object.class, new CustomTableCellRenderer());
    }
    private void updateTableWithFilterForKH(String searchQuery) {
        tableModel1.setRowCount(0);

        try {
            List<KhachHang> list = kh_dao.getAllKhachHang();
            for (KhachHang s : list) {
                String maKH = s.getMaKH().toLowerCase();
                String tenKH = s.getTenKH().toLowerCase();

                if (maKH.contains(searchQuery) || tenKH.contains(searchQuery)) {
                    String[] rowData = {
                            s.getMaKH(),
                            s.getTenKH(),
                            s.getSdt()
                    };
                    tableModel1.addRow(rowData);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        table_1.setModel(tableModel1);
    }
    private void updateTableWithFilterForNV(String searchQuery) {
        tableModel3.setRowCount(0);

        try {
            List<NhanVien> list = nv_dao.getNhanVienExceptAdmin();
            for (NhanVien s : list) {
                String maNV = s.getMaNV().toLowerCase();
                String tenNV = s.getTenNV().toLowerCase();

                if (maNV.contains(searchQuery) || tenNV.contains(searchQuery)) {
                    String[] rowData = {
                            s.getMaNV(),
                            s.getTenNV(),
                            s.getSdt()
                    };
                    tableModel3.addRow(rowData);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        table_3.setModel(tableModel3);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        Object o = e.getSource();
        if(o.equals(btnNewButton_Confirm)) {
            try {
                confirm(id,maKH, maPhong);
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    private void tailai() throws RemoteException{
        DocDuLieuDatabaseVaoTable4();
        DocDuLieuDatabaseVaoTable2();

    }
    private void datPhong() throws RemoteException {
        // TODO Auto-generated method stub
        int row1 = table_1.getSelectedRow();
        int row2 = table_2.getSelectedRow();
        int row3 = table_3.getSelectedRow();
        if(row1==-1 || row2==-1 || row3==-1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn khách hàng, phòng và nhân viên phục vụ");
            return;
        }

        String maKH = tableModel1.getValueAt(row1, 0).toString();
        String maPhong = tableModel2.getValueAt(row2, 0).toString();
        String maNV = tableModel3.getValueAt(row3, 0).toString();
        String tinhTrangPhong = phong_dao.getPhongByLoaiPhong(maPhong).getTinhTrang();

        if ("Phòng có Khách".equalsIgnoreCase(tinhTrangPhong != null ? tinhTrangPhong.trim() : "")) {
            JOptionPane.showMessageDialog(this, "Phòng đã có khách, không thể đặt phòng");
            return;
        }
        PhieuDatPhong pdp = new PhieuDatPhong();
        pdp.setMaKH(kh_dao.getKhachHangByMaKhachHang(maKH));
        pdp.setMaPhong(phong_dao.getPhongByLoaiPhong(maPhong));
        pdp.setMaNV(nv_dao.getNhanVienByChucVu(maNV));
        pdp.setTgDatPhong(Timestamp.from(Instant.now()));
        if(pdp_dao.createPhieuDatPhong(pdp)) {
            phong_dao.updateTrangThaiDP(maPhong);
            JOptionPane.showMessageDialog(this, "Đặt phòng thành công");

        }
        else {
            JOptionPane.showMessageDialog(this, "Đặt phòng thất bại");
        }
        tailai();

    }

    private void confirm(int id,String maKH, String maPhong) throws RemoteException {
        // TODO Auto-generated method stub
        int row1 = table_3.getSelectedRow();
        String maNV = tableModel3.getValueAt(row1, 0).toString();

        String tinhTrangPhong = phong_dao.getPhongByLoaiPhong(maPhong).getTinhTrang();

        if ("Phòng có Khách".equalsIgnoreCase(tinhTrangPhong != null ? tinhTrangPhong.trim() : "")) {
            JOptionPane.showMessageDialog(this, "Phòng đã có khách, không thể đặt phòng");
            return;
        }
        PhieuDatPhong pdp = new PhieuDatPhong();
        DatPhongTruoc dp = dpt_dao.getDatPhongTruocById(id);
        dp.setTrangThai("Đã nhận phòng");
        pdp.setMaKH(kh_dao.getKhachHangByMaKhachHang(maKH));
        pdp.setMaPhong(phong_dao.getPhongByLoaiPhong(maPhong));
        pdp.setMaNV(nv_dao.getNhanVienByChucVu(maNV));
        pdp.setTgDatPhong(Timestamp.from(Instant.now()));
        if(pdp_dao.createPhieuDatPhong(pdp)) {
            phong_dao.updateTrangThaiDP(maPhong);
            dpt_dao.updatePhong(dp);
            JOptionPane.showMessageDialog(this, "Đặt phòng thành công");
            this.dispose();

        }
        else {
            JOptionPane.showMessageDialog(this, "Đặt phòng thất bại");
        }
        tailai();

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }
}
