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

public class DatPhongTruoc_GUI extends JFrame implements ActionListener, MouseListener{

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


    public DatPhongTruoc_GUI() throws RemoteException {

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

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setBounds(0, 0, 1650, 1080);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        user = tk_dao.getTaiKhoanById(currentUser.getCurrentUser());
        if (user.getRole().trim().equals("admin")) {

            JMenu mnNewMenu_menu = new JMenu("Nhân Viên");

            mnNewMenu_menu.setIcon(new ImageIcon(Menu_GUI.class.getResource("/image/user.png")));
            mnNewMenu_menu.setIcon(new ImageIcon(Menu_GUI.class.getResource("../image/user.png")));


            mnNewMenu_menu.setIcon(new ImageIcon(Menu_GUI.class.getResource("/image/user.png")));

            mnNewMenu_menu.setIcon(new ImageIcon(Menu_GUI.class.getResource("../image/user.png")));


            mnNewMenu_menu.setFont(new Font("Segoe UI", Font.PLAIN, 30));
            mnNewMenu_menu.addActionListener(this);
            menuBar.add(mnNewMenu_menu);

            JMenuItem mntmNewMenuItem_upNV = new JMenuItem("Cập Nhập Nhân Viên");

            mntmNewMenuItem_upNV.setIcon(new ImageIcon(Menu_GUI.class.getResource("/image/plus.png")));

            mntmNewMenuItem_upNV.setIcon(new ImageIcon(Menu_GUI.class.getResource("../image/plus.png")));


            mntmNewMenuItem_upNV.setIcon(new ImageIcon(Menu_GUI.class.getResource("/image/plus.png")));

            mntmNewMenuItem_upNV.setIcon(new ImageIcon(Menu_GUI.class.getResource("../image/plus.png")));


            mntmNewMenuItem_upNV.setFont(new Font("Segoe UI", Font.PLAIN, 15));
            mntmNewMenuItem_upNV.addActionListener(this);
            mnNewMenu_menu.add(mntmNewMenuItem_upNV);
            JMenuItem mntmNewMenuItem_findNV = new JMenuItem("Tìm Kiếm Nhân Viên");

            mntmNewMenuItem_findNV.setIcon(new ImageIcon(Menu_GUI.class.getResource("/image/search.png")));

            mntmNewMenuItem_findNV.setIcon(new ImageIcon(Menu_GUI.class.getResource("../image/search.png")));


            mntmNewMenuItem_findNV.setIcon(new ImageIcon(Menu_GUI.class.getResource("/image/search.png")));

            mntmNewMenuItem_findNV.setIcon(new ImageIcon(Menu_GUI.class.getResource("../image/search.png")));


            mntmNewMenuItem_findNV.setFont(new Font("Segoe UI", Font.PLAIN, 15));
            mntmNewMenuItem_findNV.addActionListener(this);
            mnNewMenu_menu.add(mntmNewMenuItem_findNV);

            JMenuItem mntmNewMenuItem_tkNV = new JMenuItem("Tài Khoản");

            mntmNewMenuItem_tkNV.setIcon(new ImageIcon(Menu_GUI.class.getResource("/image/user4.png")));

            mntmNewMenuItem_tkNV.setIcon(new ImageIcon(Menu_GUI.class.getResource("../image/user4.png")));


            mntmNewMenuItem_tkNV.setIcon(new ImageIcon(Menu_GUI.class.getResource("/image/user4.png")));

            mntmNewMenuItem_tkNV.setIcon(new ImageIcon(Menu_GUI.class.getResource("../image/user4.png")));


            mntmNewMenuItem_tkNV.setFont(new Font("Segoe UI", Font.PLAIN, 15));
            mntmNewMenuItem_tkNV.addActionListener(this);
            mnNewMenu_menu.add(mntmNewMenuItem_tkNV);

            JMenuItem mntmNewMenuItem_cvNV = new JMenuItem("Chức Vụ");

            mntmNewMenuItem_cvNV.setIcon(new ImageIcon(Menu_GUI.class.getResource("/images/ic_KH.png")));

            mntmNewMenuItem_cvNV.setIcon(new ImageIcon(Menu_GUI.class.getResource("../images/ic_KH.png")));

            mntmNewMenuItem_cvNV.setIcon(new ImageIcon(Menu_GUI.class.getResource("/images/ic_KH.png")));

            mntmNewMenuItem_cvNV.setIcon(new ImageIcon(Menu_GUI.class.getResource("../images/ic_KH.png")));


            mntmNewMenuItem_cvNV.setFont(new Font("Segoe UI", Font.PLAIN, 15));
            mntmNewMenuItem_cvNV.addActionListener(this);
            mnNewMenu_menu.add(mntmNewMenuItem_cvNV);
        }

        JMenu mnNewMenu_kh = new JMenu("Khách Hàng");

        mnNewMenu_kh.setIcon(new ImageIcon(Menu_GUI.class.getResource("/image/users.png")));

        mnNewMenu_kh.setIcon(new ImageIcon(Menu_GUI.class.getResource("../image/users.png")));


        mnNewMenu_kh.setIcon(new ImageIcon(Menu_GUI.class.getResource("/image/users.png")));

        mnNewMenu_kh.setIcon(new ImageIcon(Menu_GUI.class.getResource("../image/users.png")));


        mnNewMenu_kh.setFont(new Font("Segoe UI", Font.PLAIN, 30));
        mnNewMenu_kh.addActionListener(this);
        menuBar.add(mnNewMenu_kh);

        JMenuItem mntmCpNhp_upKH = new JMenuItem("Cập Nhập Khách Hàng");

        mntmCpNhp_upKH.setIcon(new ImageIcon(Menu_GUI.class.getResource("/image/plus.png")));

        mntmCpNhp_upKH.setIcon(new ImageIcon(Menu_GUI.class.getResource("../image/plus.png")));



        mntmCpNhp_upKH.setIcon(new ImageIcon(Menu_GUI.class.getResource("/image/plus.png")));

        mntmCpNhp_upKH.setIcon(new ImageIcon(Menu_GUI.class.getResource("../image/plus.png")));


        mntmCpNhp_upKH.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        mntmCpNhp_upKH.addActionListener(this);
        mnNewMenu_kh.add(mntmCpNhp_upKH);

        JMenuItem mntmNewMenuItem_findKH = new JMenuItem("Tìm Kiếm Khách Hàng");

        mntmNewMenuItem_findKH.setIcon(new ImageIcon(Menu_GUI.class.getResource("/image/search.png")));

        mntmNewMenuItem_findKH.setIcon(new ImageIcon(Menu_GUI.class.getResource("../image/search.png")));


        mntmNewMenuItem_findKH.setIcon(new ImageIcon(Menu_GUI.class.getResource("/image/search.png")));

        mntmNewMenuItem_findKH.setIcon(new ImageIcon(Menu_GUI.class.getResource("../image/search.png")));


        mntmNewMenuItem_findKH.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        mntmNewMenuItem_findKH.addActionListener(this);
        mnNewMenu_kh.add(mntmNewMenuItem_findKH);
        if (user.getRole().trim().equals("admin")) {
            JMenu mnNewMenu_dv = new JMenu("Dịch Vụ");

            mnNewMenu_dv.setIcon(new ImageIcon(Menu_GUI.class.getResource("/images/ic_DV (5).png")));

            mnNewMenu_dv.setIcon(new ImageIcon(Menu_GUI.class.getResource("../images/ic_DV (5).png")));


            mnNewMenu_dv.setIcon(new ImageIcon(Menu_GUI.class.getResource("/images/ic_DV (5).png")));

            mnNewMenu_dv.setIcon(new ImageIcon(Menu_GUI.class.getResource("../images/ic_DV (5).png")));


            mnNewMenu_dv.setFont(new Font("Segoe UI", Font.PLAIN, 30));
            mnNewMenu_dv.addActionListener(this);
            menuBar.add(mnNewMenu_dv);

            JMenuItem mntmNewMenuItem_upDV = new JMenuItem("Cập Nhập Dịch Vụ");

            mntmNewMenuItem_upDV.setIcon(new ImageIcon(Menu_GUI.class.getResource("/image/plus.png")));

            mntmNewMenuItem_upDV.setIcon(new ImageIcon(Menu_GUI.class.getResource("../image/plus.png")));


            mntmNewMenuItem_upDV.setIcon(new ImageIcon(Menu_GUI.class.getResource("/image/plus.png")));

            mntmNewMenuItem_upDV.setIcon(new ImageIcon(Menu_GUI.class.getResource("../image/plus.png")));


            mntmNewMenuItem_upDV.setFont(new Font("Segoe UI", Font.PLAIN, 15));
            mntmNewMenuItem_upDV.addActionListener(this);
            mnNewMenu_dv.add(mntmNewMenuItem_upDV);

            JMenuItem mntmNewMenuItem_findDV = new JMenuItem("Tìm Kiếm Dịch Vụ");

            mntmNewMenuItem_findDV.setIcon(new ImageIcon(Menu_GUI.class.getResource("/image/search.png")));

            mntmNewMenuItem_findDV.setIcon(new ImageIcon(Menu_GUI.class.getResource("../image/search.png")));


            mntmNewMenuItem_findDV.setIcon(new ImageIcon(Menu_GUI.class.getResource("/image/search.png")));

            mntmNewMenuItem_findDV.setIcon(new ImageIcon(Menu_GUI.class.getResource("../image/search.png")));


            mntmNewMenuItem_findDV.setFont(new Font("Segoe UI", Font.PLAIN, 15));
            mntmNewMenuItem_findDV.addActionListener(this);
            mnNewMenu_dv.add(mntmNewMenuItem_findDV);

            JMenuItem mntmNewMenuItem_loaiDV = new JMenuItem("Loại Dịch Vụ");

            mntmNewMenuItem_loaiDV.setIcon(new ImageIcon(Menu_GUI.class.getResource("/image/food.png")));

            mntmNewMenuItem_loaiDV.setIcon(new ImageIcon(Menu_GUI.class.getResource("../image/food.png")));


            mntmNewMenuItem_loaiDV.setIcon(new ImageIcon(Menu_GUI.class.getResource("/image/food.png")));

            mntmNewMenuItem_loaiDV.setIcon(new ImageIcon(Menu_GUI.class.getResource("../image/food.png")));


            mntmNewMenuItem_loaiDV.setFont(new Font("Segoe UI", Font.PLAIN, 15));
            mntmNewMenuItem_loaiDV.addActionListener(this);
            mnNewMenu_dv.add(mntmNewMenuItem_loaiDV);
        }

        JMenu mnNewMenu_phong = new JMenu("Phòng");

        mnNewMenu_phong.setIcon(new ImageIcon(Menu_GUI.class.getResource("/image/essentials-08.png")));

        mnNewMenu_phong.setIcon(new ImageIcon(Menu_GUI.class.getResource("../image/essentials-08.png")));


        mnNewMenu_phong.setIcon(new ImageIcon(Menu_GUI.class.getResource("/image/essentials-08.png")));

        mnNewMenu_phong.setIcon(new ImageIcon(Menu_GUI.class.getResource("../image/essentials-08.png")));


        mnNewMenu_phong.setFont(new Font("Segoe UI", Font.PLAIN, 30));
        mnNewMenu_phong.addActionListener(this);
        menuBar.add(mnNewMenu_phong);
        if (user.getRole().trim().equals("admin")) {
            JMenuItem mntmNewMenuItem_upPhong = new JMenuItem("Cập Nhập Phòng");

            mntmNewMenuItem_upPhong.setIcon(new ImageIcon(Menu_GUI.class.getResource("/image/plus.png")));

            mntmNewMenuItem_upPhong.setIcon(new ImageIcon(Menu_GUI.class.getResource("../image/plus.png")));


            mntmNewMenuItem_upPhong.setIcon(new ImageIcon(Menu_GUI.class.getResource("/image/plus.png")));

            mntmNewMenuItem_upPhong.setIcon(new ImageIcon(Menu_GUI.class.getResource("../image/plus.png")));


            mntmNewMenuItem_upPhong.setFont(new Font("Segoe UI", Font.PLAIN, 15));
            mntmNewMenuItem_upPhong.addActionListener(this);
            mnNewMenu_phong.add(mntmNewMenuItem_upPhong);

            JMenuItem mntmNewMenuItem_upLP = new JMenuItem("Cập Nhập Loại Phòng");

            mntmNewMenuItem_upLP.setIcon(new ImageIcon(Menu_GUI.class.getResource("/image/plus.png")));

            mntmNewMenuItem_upLP.setIcon(new ImageIcon(Menu_GUI.class.getResource("../image/plus.png")));

            mntmNewMenuItem_upLP.setIcon(new ImageIcon(Menu_GUI.class.getResource("/image/plus.png")));

            mntmNewMenuItem_upLP.setIcon(new ImageIcon(Menu_GUI.class.getResource("../image/plus.png")));


            mntmNewMenuItem_upLP.setFont(new Font("Segoe UI", Font.PLAIN, 15));
            mntmNewMenuItem_upLP.addActionListener(this);
            mnNewMenu_phong.add(mntmNewMenuItem_upLP);
        }

        JMenuItem mntmNewMenuItem_findPhong = new JMenuItem("Tìm Kiếm Phòng");

        mntmNewMenuItem_findPhong.setIcon(new ImageIcon(Menu_GUI.class.getResource("/image/search.png")));

        mntmNewMenuItem_findPhong.setIcon(new ImageIcon(Menu_GUI.class.getResource("../image/search.png")));


        mntmNewMenuItem_findPhong.setIcon(new ImageIcon(Menu_GUI.class.getResource("/image/search.png")));

        mntmNewMenuItem_findPhong.setIcon(new ImageIcon(Menu_GUI.class.getResource("../image/search.png")));


        mntmNewMenuItem_findPhong.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        mntmNewMenuItem_findPhong.addActionListener(this);
        mnNewMenu_phong.add(mntmNewMenuItem_findPhong);

        JMenuItem mntmNewMenuItem_datPhong = new JMenuItem("Đặt Phòng");

        mntmNewMenuItem_datPhong.setIcon(new ImageIcon(Menu_GUI.class.getResource("/image/bell.png")));

        mntmNewMenuItem_datPhong.setIcon(new ImageIcon(Menu_GUI.class.getResource("../image/bell.png")));


        mntmNewMenuItem_datPhong.setIcon(new ImageIcon(Menu_GUI.class.getResource("/image/bell.png")));

        mntmNewMenuItem_datPhong.setIcon(new ImageIcon(Menu_GUI.class.getResource("../image/bell.png")));


        mntmNewMenuItem_datPhong.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        mntmNewMenuItem_datPhong.addActionListener(this);
        mnNewMenu_phong.add(mntmNewMenuItem_datPhong);

        JMenuItem mntmNewMenuItem_datPhongTruoc = new JMenuItem("Đặt Phòng Trước");

        mntmNewMenuItem_datPhongTruoc.setIcon(new ImageIcon(Menu_GUI.class.getResource("/images/ic_ThanhToan.png")));


        mntmNewMenuItem_datPhongTruoc.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        mntmNewMenuItem_datPhongTruoc.addActionListener(this);
        mnNewMenu_phong.add(mntmNewMenuItem_datPhongTruoc);

        JMenu mnNewMenu_hd = new JMenu("Hoá Đơn");

        mnNewMenu_hd.setIcon(new ImageIcon(Menu_GUI.class.getResource("/images/ic_HD.png")));

        mnNewMenu_hd.setIcon(new ImageIcon(Menu_GUI.class.getResource("../images/ic_HD.png")));


        mnNewMenu_hd.setIcon(new ImageIcon(Menu_GUI.class.getResource("/images/ic_HD.png")));

        mnNewMenu_hd.setIcon(new ImageIcon(Menu_GUI.class.getResource("../images/ic_HD.png")));


        mnNewMenu_hd.setFont(new Font("Segoe UI", Font.PLAIN, 30));
        mnNewMenu_hd.addActionListener(this);
        menuBar.add(mnNewMenu_hd);

        JMenuItem mntmNewMenuItem_lapHD = new JMenuItem("Lập Hoá Đơn");
        mntmNewMenuItem_lapHD.setIcon(new ImageIcon(Menu_GUI.class.getResource("/images/ic_ThanhToan.png")));
        mntmNewMenuItem_lapHD.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        mntmNewMenuItem_lapHD.addActionListener(this);
        mnNewMenu_hd.add(mntmNewMenuItem_lapHD);
        if (user.getRole().trim().equals("admin")) {
            JMenu mnNewMenu_thongKe = new JMenu("Thống Kê");

            mnNewMenu_thongKe.setIcon(new ImageIcon(Menu_GUI.class.getResource("/images/ic_TK.png")));

            mnNewMenu_thongKe.setIcon(new ImageIcon(Menu_GUI.class.getResource("../images/ic_TK.png")));

            mnNewMenu_thongKe.setIcon(new ImageIcon(Menu_GUI.class.getResource("/images/ic_TK.png")));

            mnNewMenu_thongKe.setIcon(new ImageIcon(Menu_GUI.class.getResource("../images/ic_TK.png")));


            mnNewMenu_thongKe.setFont(new Font("Segoe UI", Font.PLAIN, 30));
            mnNewMenu_thongKe.addActionListener(this);
            menuBar.add(mnNewMenu_thongKe);

            JMenuItem mntmNewMenuItem_tkDoanhThu = new JMenuItem("Thống Kê Doanh Thu");

            mntmNewMenuItem_tkDoanhThu.setIcon(new ImageIcon(Menu_GUI.class.getResource("/images/ic_TK.png")));

            mntmNewMenuItem_tkDoanhThu.setIcon(new ImageIcon(Menu_GUI.class.getResource("../images/ic_TK.png")));

            mntmNewMenuItem_tkDoanhThu.setIcon(new ImageIcon(Menu_GUI.class.getResource("/images/ic_TK.png")));

            mntmNewMenuItem_tkDoanhThu.setIcon(new ImageIcon(Menu_GUI.class.getResource("../images/ic_TK.png")));

            mntmNewMenuItem_tkDoanhThu.setFont(new Font("Segoe UI", Font.PLAIN, 15));
            mntmNewMenuItem_tkDoanhThu.addActionListener(this);
            mnNewMenu_thongKe.add(mntmNewMenuItem_tkDoanhThu);

            JMenuItem mntmNewMenuItem_tkDongHang = new JMenuItem("Thống Kê Đơn hàng");
            mntmNewMenuItem_tkDongHang.setIcon(new ImageIcon(Menu_GUI.class.getResource("/images/ic_HD.png")));
            mntmNewMenuItem_tkDongHang.setFont(new Font("Segoe UI", Font.PLAIN, 15));
            mntmNewMenuItem_tkDongHang.addActionListener(this);
            mnNewMenu_thongKe.add(mntmNewMenuItem_tkDongHang);
        }


        JMenu mnOtherMenu = new JMenu();
        menuBar.add(mnOtherMenu);
        menuBar.add(Box.createHorizontalStrut(user.getRole().equals("admin") ? 300: 800));

        JMenu mnNewMenu_Admin = new JMenu(user.getRole().equals("admin") ? "Admin" : "NV_" + user.getTenTK());
        mnNewMenu_Admin.setIcon(new ImageIcon(Menu_GUI.class.getResource("/image/admin.png")));
        mnNewMenu_Admin.setFont(new Font("Segoe UI", Font.PLAIN, 30));
        mnNewMenu_Admin.addActionListener(this);
        menuBar.add(mnNewMenu_Admin);

        JMenuItem mntmNewMenuItem_info = new JMenuItem("Thông tin cá nhân");
        mntmNewMenuItem_info.setIcon(new ImageIcon(Menu_GUI.class.getResource("/image/user.png")));
        mntmNewMenuItem_info.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        mntmNewMenuItem_info.addActionListener(this);
        mnNewMenu_Admin.add(mntmNewMenuItem_info);

        JMenuItem mntmNewMenuItem_LOGOUT = new JMenuItem("Đăng xuất");
        mntmNewMenuItem_LOGOUT.setIcon(new ImageIcon(Menu_GUI.class.getResource("/image/exit.png")));
        mntmNewMenuItem_LOGOUT.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        mntmNewMenuItem_LOGOUT.addActionListener(this);
        mnNewMenu_Admin.add(mntmNewMenuItem_LOGOUT);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("ĐẶT PHÒNG TRƯỚC");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
        lblNewLabel.setBounds(415, 0, 650, 74);
        contentPane.add(lblNewLabel);

        Box horizontalBox_1 = Box.createHorizontalBox();
        horizontalBox_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Danh s\u00E1ch kh\u00E1ch h\u00E0ng ch\u1EDD", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        horizontalBox_1.setBounds(10, 84, 503, 315);
        contentPane.add(horizontalBox_1);
        //
        String [] headers1 = {"Mã Khách Hàng","Tên Khách Hàng", "SĐT"};
        tableModel1=new DefaultTableModel(headers1,0);
        JScrollPane scroll = new JScrollPane();
        scroll.setViewportView(table_1 = new JTable(tableModel1));
        table_1.setRowHeight(25);
        table_1.setAutoCreateRowSorter(true);
        table_1.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
        //
        JScrollPane scrollPane_1 = new JScrollPane(table_1);
        horizontalBox_1.add(scrollPane_1);


        Box horizontalBox_2 = Box.createHorizontalBox();
        horizontalBox_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Danh s\u00E1ch ph\u00F2ng tr\u1ED1ng", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        horizontalBox_2.setBounds(10, 465, 520, 255);
        contentPane.add(horizontalBox_2);
        //
        String [] headers2 = {"Mã Phòng","Tên Phòng", "Loại Phòng", "Trạng Thái"};
        tableModel2=new DefaultTableModel(headers2,0);
        JScrollPane scroll2 = new JScrollPane();
        scroll2.setViewportView(table_2 = new JTable(tableModel2));
        table_2.setRowHeight(25);
        table_2.setAutoCreateRowSorter(true);
        table_2.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
        //
        JScrollPane scrollPane_2 = new JScrollPane(table_2);
        horizontalBox_2.add(scrollPane_2);

        JLabel lblNewLabel_maNV_1 = new JLabel("Tìm Kiếm Nhân viên");
        lblNewLabel_maNV_1.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNewLabel_maNV_1.setBounds(1008, 27, 188, 29);
        //contentPane.add(lblNewLabel_maNV_1);

        txt_timkiemnNV = new JTextField("Tìm kiếm tên/mã...");
        txt_timkiemnNV.setForeground(Color.GRAY);
        txt_timkiemnNV.setColumns(10);
        txt_timkiemnNV.setBounds(1008, 52, 236, 26);
        //contentPane.add(txt_timkiemnNV);
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
        horizontalBox_3.setBounds(1008, 84, 522, 315);
        //contentPane.add(horizontalBox_3);
        //
        String [] headers3 = {"Mã Nhân Viên","Tên Nhân Viên", "SĐT"};
        tableModel3=new DefaultTableModel(headers3,0);
        JScrollPane scroll3 = new JScrollPane();
        scroll3.setViewportView(table_3 = new JTable(tableModel3));
        table_3.setRowHeight(25);
        table_3.setAutoCreateRowSorter(true);
        table_3.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
        //
        JScrollPane scrollPane_3 = new JScrollPane(table_3);
        horizontalBox_3.add(scrollPane_3);


        btnNewButton_DP = new JButton("Đặt Phòng Trước");
        btnNewButton_DP.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnNewButton_DP.setBounds(529, 650, 157, 65);
        contentPane.add(btnNewButton_DP);

        JLabel lblNewLabel_maKH = new JLabel("Tìm Kiếm Khách hàng");
        lblNewLabel_maKH.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNewLabel_maKH.setBounds(10, 27, 188, 29);
        contentPane.add(lblNewLabel_maKH);

        txt_timkiemKH = new JTextField("Tìm kiếm tên/mã...");
        txt_timkiemKH.setForeground(Color.GRAY);
        txt_timkiemKH.setColumns(10);
        txt_timkiemKH.setBounds(10, 52, 236, 26);
        contentPane.add(txt_timkiemKH);
        txt_timkiemKH.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (txt_timkiemKH.getText().equals("Tìm kiếm tên/mã...")) {
                    txt_timkiemKH.setText("");
                    txt_timkiemKH.setForeground(Color.BLACK); // Đổi màu chữ khi có input
                }
            }

            public void focusLost(FocusEvent e) {
                if (txt_timkiemKH.getText().equals("")) {
                    txt_timkiemKH.setText("Tìm kiếm tên/mã...");
                    txt_timkiemKH.setForeground(Color.GRAY); // Đổi màu lại khi không có input
                }
            }
        });

        Box horizontalBox_4 = Box.createHorizontalBox();
        horizontalBox_4.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Danh s\u00E1ch ph\u00F2ng \u0111\u00E3 \u0111\u1EB7t", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        horizontalBox_4.setBounds(529, 84, 1020, 555);
        contentPane.add(horizontalBox_4);

        String [] headers4 = {"ID","Mã Phòng", "Mã Khách hàng", "Thời gian đặt phòng", "Thời gian trả phòng","Trạng thái"};
        tableModel4=new DefaultTableModel(headers4,0);
        JScrollPane scroll4 = new JScrollPane();
        scroll4.setViewportView(table_4 = new JTable(tableModel4));
        table_4.setRowHeight(25);
        table_4.setAutoCreateRowSorter(true);
        table_4.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
        //
        JScrollPane scrollPane_4 = new JScrollPane(table_4);
        horizontalBox_4.add(scrollPane_4);

        btnNewButton_load = new JButton("Load Phòng");
        btnNewButton_load.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnNewButton_load.setBounds(855, 650, 157, 65);
        contentPane.add(btnNewButton_load);
        JLabel lblNewLabel_maP = new JLabel("Tìm Kiếm Phòng");
        lblNewLabel_maP.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNewLabel_maP.setBounds(10, 400, 188, 29);
        contentPane.add(lblNewLabel_maP);

        btnNewButton_Confirm = new JButton("Xác nhận phòng");
        btnNewButton_Confirm.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnNewButton_Confirm.setBounds(690, 650, 157, 65);
        contentPane.add(btnNewButton_Confirm);

        txt_timkiemP = new JTextField("Tìm kiếm tên/mã...");
        txt_timkiemP.setForeground(Color.GRAY);
        txt_timkiemP.setColumns(10);
        txt_timkiemP.setBounds(10, 425, 236, 26);
        contentPane.add(txt_timkiemP);
        txt_timkiemP.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (txt_timkiemP.getText().equals("Tìm kiếm tên/mã...")) {
                    txt_timkiemP.setText("");
                    txt_timkiemP.setForeground(Color.BLACK); // Đổi màu chữ khi có input
                }
            }

            public void focusLost(FocusEvent e) {
                if (txt_timkiemP.getText().equals("")) {
                    txt_timkiemP.setText("Tìm kiếm tên/mã...");
                    txt_timkiemP.setForeground(Color.GRAY); // Đổi màu lại khi không có input
                }
            }
        });

        btnNewButton_DP.addActionListener(this);
        btnNewButton_load.addActionListener(this);
        btnNewButton_Confirm.addActionListener(this);
        table_1.addMouseListener(this);
        table_2.addMouseListener(this);
        table_3.addMouseListener(this);
        table_4.addMouseListener(this);

        DocDuLieuDatabaseVaoTable1();
        DocDuLieuDatabaseVaoTable2();
        DocDuLieuDatabaseVaoTable3();
        DocDuLieuDatabaseVaoTable4();
        // tim kiem
        txt_timkiemP.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String searchQuery = txt_timkiemP.getText().trim().toLowerCase();
                updateTableWithFilter(searchQuery);
            }
        });
        txt_timkiemKH.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String searchQuery = txt_timkiemKH.getText().trim().toLowerCase();
                updateTableWithFilterForKH(searchQuery);
            }
        });
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
        if (e.getActionCommand().equals("Cập Nhập Nhân Viên")) {
            dispose();
            try {
                new CapNhapNV_GUI();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        }
//		if (e.getActionCommand().equals("Tìm Kiếm Nhân Viên")) {
//			dispose();
//            new TimKiemNV_GUI();
//        }
        if (e.getActionCommand().equals("Tài Khoản")) {
            dispose();
            try {
                new TaiKhoan_GUI();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        }
        if (e.getActionCommand().equals("Chức Vụ")) {
            dispose();
            try {
                new ChucVu_GUI();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        }
        ////////////////////////////////////////////////////////////////////////////
        if (e.getActionCommand().equals("Cập Nhập Khách Hàng")) {
            dispose();
            try {
                new CapNhapKH_GUI();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        }
//		if (e.getActionCommand().equals("Tìm Kiếm Khách Hàng")) {
//			dispose();
//            new TimKiemKH_GUI();
//        }
        ///////////////////////////////////////////////////////////////////////////
        if (e.getActionCommand().equals("Cập Nhập Dịch Vụ")) {
            dispose();
            try {
                new CapNhapDV_GUI();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        }
//		if (e.getActionCommand().equals("Tìm Kiếm Dịch Vụ")) {
//			dispose();
//            new TimKiemDV_GUI();
//        }
        if (e.getActionCommand().equals("Loại Dịch Vụ")) {
            dispose();
            try {
                new LoaiDichVu_GUI();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        }
        ///////////////////////////////////////////////////////////////////////////
        if (e.getActionCommand().equals("Cập Nhập Phòng")) {
            dispose();
            try {
                new CapNhapPhong_GUI();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        }
        if (e.getActionCommand().equals("Cập Nhập Loại Phòng")) {
            dispose();
            try {
                new LoaiPhong_GUI();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        }
//		if (e.getActionCommand().equals("Tìm Kiếm Phòng")) {
//			dispose();
//            new TimKiemPhong_GUI();
//        }
        if (e.getActionCommand().equals("Đặt Phòng")) {
            dispose();
            try {
                new DatPhong_GUI();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        }
        ///////////////////////////////////////////////////////////////////////////
        if (e.getActionCommand().equals("Lập Hoá Đơn")) {
            dispose();
            try {
                new LapHoaDon_GUI();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        }
        if (e.getActionCommand().equals("Thống Kê Doanh Thu")) {
            dispose();
            try {
                new THONGKE_GUI();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        }
        if (e.getActionCommand().equals("Thống Kê Đơn hàng")) {
            dispose();
            try {
                new DONHANG_GUI();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        }
        if (e.getActionCommand().equals("Thông tin cá nhân")) {

            try {
//				TaiKhoan user = tk_dao.getTaiKhoanById(currentUser.getCurrentUser());
                String maNV = user.getMaNV().getMaNV();
                System.out.println("maNV: "+maNV);
                NhanVien current = nv_dao.getNhanVienByMaNhanVien(maNV);
                System.out.println("curentUser: "+current);

                new ThongTinCaNhan_GUI(current);

            } catch (LazyInitializationException | RemoteException ex) {
                System.err.println("Session đã đóng trước khi truy cập dữ liệu!");
                ex.printStackTrace();
            }
        }
        if (e.getActionCommand().equals("Đăng xuất")) {
            dispose();
            try {
//				System.out.println("curentUser: "+currentUser.getCurrentUser());
                currentUser.clearSession();
//				System.out.println("curentUser: "+currentUser.getCurrentUser());
                new DangNhap_GUI();

            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        if (e.getActionCommand().equals("Đặt Phòng Trước")) {
            dispose();
            try {
                new DatPhongTruoc_GUI();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        }
        //////////////////////////////////////////////////////////////////////////
        Object o = e.getSource();
        if(o.equals(btnNewButton_DP)) {
            int row1 = table_1.getSelectedRow();
            int row2 = table_2.getSelectedRow();
            String maKH = tableModel1.getValueAt(row1, 0).toString().trim();
            String maPhong = tableModel2.getValueAt(row2, 0).toString().trim();
            new formDatPhongTruoc(maKH,maPhong);
            try {
                DocDuLieuDatabaseVaoTable4();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        }
        if(o.equals(btnNewButton_load)) {
            try {
                tailai();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        }
        if(o.equals(btnNewButton_Confirm)) {
            try {
                confirm();
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

    private void confirm() throws RemoteException {
        // TODO Auto-generated method stub
        int row1 =table_4.getSelectedRow();
        if(row1==-1 ) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn phòng đặt trước");
            return;
        }

        String maKH = tableModel4.getValueAt(row1, 2).toString();
        String maPhong = tableModel4.getValueAt(row1, 1).toString();
        String tinhTrang =tableModel4.getValueAt(row1, 5).toString().trim();
        int id =Integer.parseInt(tableModel4.getValueAt(row1, 0).toString()) ;
        if(tinhTrang.equals("Đã nhận phòng") ) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn phòng chưa xác nhận");
            return;
        }
        new XacNhanPhong(id,maKH,maPhong);


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
