package gui;

import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
//import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Server.Config;
import bll.ChuyenDoi;
import bll.SessionManager;
import bll.jdlAddDV;
import bll.jdlAddPDP;


import entity.*;
import model.*;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import org.eclipse.persistence.jpa.jpql.parser.MathDoubleExpression;
import org.hibernate.LazyInitializationException;
import org.hibernate.Session;
import org.hibernate.jdbc.ReturningWork;

import static java.sql.Types.NULL;


public class LapHoaDon_GUI extends JFrame implements ActionListener , MouseListener{

	private JPanel contentPane;
	private JTable table;
	private JTable table_1;
	private DefaultTableModel tableModel;
	private DefaultTableModel tableModel1;
	private DefaultTableModel tableModel2;
	private PhieuDatPhongDao pdp_dao;
	private DichVuDao dv_dao;
	private LoaiDichVuDao ldv_dao;
	private JButton btnThmDchV;
	private JButton btnNewButton_themP;
	private JTextField textField_ngayTao;
	private JTextField textField_shd;
	private JTextField textField_ghiChu;
	private JTextField textField_tongTien;
	private JTextField textField_khachTra;
	private JTextField textField_tienThua;
	private KhachHangDao kh_dao;
	private NhanVienDao nv_dao;
	private HoaDonDao dao;
	private JButton btnNewButton_thanhToan;
	private JButton btnNewButton_taoHD;
	private int eventMask;
	private PhongDao p_dao;
	private TaiKhoan user;
	private TaiKhoanDao tk_dao;
	private SessionManager currentUser = SessionManager.getInstance();

	public LapHoaDon_GUI() throws RemoteException {
		try{
			dao = (HoaDonDao) Naming.lookup(Config.SERVER_URL + "hoaDonDao");
			nv_dao = (NhanVienDao) Naming.lookup(Config.SERVER_URL + "nhanVienDao");
			kh_dao = (KhachHangDao) Naming.lookup(Config.SERVER_URL + "khachHangDao");
			p_dao = (PhongDao) Naming.lookup(Config.SERVER_URL + "phongDao");
			pdp_dao = (PhieuDatPhongDao) Naming.lookup(Config.SERVER_URL + "phieuDatPhongDao");
			dv_dao = (DichVuDao) Naming.lookup(Config.SERVER_URL + "dichVuDao");
			ldv_dao = (LoaiDichVuDao) Naming.lookup(Config.SERVER_URL + "loaiDichVuDao");
			tk_dao = (TaiKhoanDao) Naming.lookup(Config.SERVER_URL + "taiKhoanDao");
		}catch (Exception e){
			JOptionPane.showMessageDialog(this, "Server chưa mở");
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

		JLabel lblNewLabel = new JLabel("LẬP HÓA ĐƠN");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 50));
		lblNewLabel.setBounds(391, 0, 650, 74);
		contentPane.add(lblNewLabel);

		String [] headers = {"Mã PDP", "Tên Phòng", "Loại Phòng", "Giá Phòng", "Khách Hàng","Nhân Viên PV","Thời gian đặt phòng"};
		tableModel=new DefaultTableModel(headers,0);
		JScrollPane scroll = new JScrollPane();
		scroll.setViewportView(table = new JTable(tableModel));
		table.setRowHeight(25);
		table.setAutoCreateRowSorter(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		table.getColumnModel().getColumn(6).setMinWidth(0);
		table.getColumnModel().getColumn(6).setMaxWidth(0);
		table.getColumnModel().getColumn(6).setWidth(0);
		table.getColumnModel().getColumn(6).setPreferredWidth(0);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(20, 102, 590, 312);
		contentPane.add(scrollPane);

		String [] headers1 = {"STT", "Mã Hàng Hóa", "Tên Hàng Hóa", "Đơn Giá", "Số Giờ","Số Lượng", "Thành Tiền", "Ghi Chú"};
		tableModel1=new DefaultTableModel(headers1,0);
		JScrollPane scroll1 = new JScrollPane();
		scroll1.setViewportView(table_1 = new JTable(tableModel1));
		table_1.setRowHeight(25);
		table_1.setAutoCreateRowSorter(true);
		table_1.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		table_1.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
		table_1.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);

		JScrollPane scrollPane_1 = new JScrollPane(table_1);
		scrollPane_1.setBounds(649, 187, 866, 401);
		contentPane.add(scrollPane_1);

		btnNewButton_themP = new JButton("Thêm phòng vào hóa đơn");
		btnNewButton_themP.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_themP.setBounds(200, 424, 223, 36);
		contentPane.add(btnNewButton_themP);

		btnThmDchV = new JButton("Thêm dịch vụ vào hóa đơn");
		btnThmDchV.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnThmDchV.setBounds(200, 685, 223, 36);
		contentPane.add(btnThmDchV);

		JLabel lblNewLabel_4_1_2 = new JLabel("Ngày tạo:");
		lblNewLabel_4_1_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_4_1_2.setBounds(663, 102, 96, 23);
		contentPane.add(lblNewLabel_4_1_2);

		textField_ngayTao = new JTextField();
		textField_ngayTao.setColumns(10);
		textField_ngayTao.setBounds(769, 102, 158, 27);
		textField_ngayTao.setText(ChuyenDoi.DinhDangNgay(new java.util.Date()));
		contentPane.add(textField_ngayTao);

		JLabel lblNewLabel_4_1_2_1 = new JLabel("Số Hóa Đơn:");
		lblNewLabel_4_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_4_1_2_1.setBounds(663, 139, 96, 23);
		contentPane.add(lblNewLabel_4_1_2_1);

		textField_shd = new JTextField();
		textField_shd.setColumns(10);
		textField_shd.setBounds(769, 139, 158, 27);
		textField_shd.setText(SoHoaDon());
		contentPane.add(textField_shd);

		JLabel lblNewLabel_4_1_2_2 = new JLabel("Khách Hàng:");
		lblNewLabel_4_1_2_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_4_1_2_2.setBounds(937, 102, 96, 23);
		contentPane.add(lblNewLabel_4_1_2_2);


		JLabel lblNewLabel_4_1_2_2_1 = new JLabel("Nhân Viên:");
		lblNewLabel_4_1_2_2_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_4_1_2_2_1.setBounds(937, 139, 96, 23);
		contentPane.add(lblNewLabel_4_1_2_2_1);


		JLabel lblNewLabel_4_1_2_1_1_1 = new JLabel("Ghi chú:");
		lblNewLabel_4_1_2_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_4_1_2_1_1_1.setBounds(1211, 102, 96, 23);
		contentPane.add(lblNewLabel_4_1_2_1_1_1);

		textField_ghiChu = new JTextField();
		textField_ghiChu.setColumns(10);
		textField_ghiChu.setBounds(1317, 102, 158, 27);
		contentPane.add(textField_ghiChu);

		btnNewButton_taoHD = new JButton("Tạo Hóa Đơn Mới");
		btnNewButton_taoHD.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_taoHD.setBounds(721, 685, 223, 36);
		contentPane.add(btnNewButton_taoHD);

		JLabel lblNewLabel_4_1_2_3 = new JLabel("Tổng Tiền:");
		lblNewLabel_4_1_2_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_4_1_2_3.setBounds(663, 627, 96, 23);
		contentPane.add(lblNewLabel_4_1_2_3);

		textField_tongTien = new JTextField();
		textField_tongTien.setColumns(10);
		textField_tongTien.setBounds(769, 627, 158, 27);
		contentPane.add(textField_tongTien);

		JLabel lblNewLabel_4_1_2_3_1 = new JLabel("Khách trả:");
		lblNewLabel_4_1_2_3_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_4_1_2_3_1.setBounds(937, 627, 96, 23);
		contentPane.add(lblNewLabel_4_1_2_3_1);

		textField_khachTra = new JTextField();
		textField_khachTra.setColumns(10);
		textField_khachTra.setBounds(1043, 627, 158, 27);
		contentPane.add(textField_khachTra);

		textField_khachTra.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String input = textField_khachTra.getText().trim();

				if (!input.matches("[0-9,.]*")) {
					textField_khachTra.setText(input.replaceAll("[^0-9,.]", ""));
				}
				calculateTienThua();
			}
		});
		textField_khachTra.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String input = textField_khachTra.getText().trim();

				try {
					if (!input.isEmpty()) {
						double value = Double.parseDouble(input.replace(",", ""));

						// Định dạng lại chuỗi
						DecimalFormat decimalFormat = new DecimalFormat("#,###.00");
						textField_khachTra.setText(decimalFormat.format(value));
					}
				} catch (NumberFormatException ex) {
					System.out.println("Lỗi định dạng số: " + ex.getMessage());
					textField_khachTra.setText(""); // Xóa nếu không hợp lệ
				}
			}
		});


		JLabel lblNewLabel_4_1_2_3_1_1 = new JLabel("Tiền thừa:");
		lblNewLabel_4_1_2_3_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_4_1_2_3_1_1.setBounds(1211, 627, 96, 23);
		contentPane.add(lblNewLabel_4_1_2_3_1_1);

		textField_tienThua = new JTextField();
		textField_tienThua.setColumns(10);
		textField_tienThua.setBounds(1317, 627, 158, 27);
		textField_tienThua.setEditable(false);
		contentPane.add(textField_tienThua);

		btnNewButton_thanhToan = new JButton("Thanh Toán");
		btnNewButton_thanhToan.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_thanhToan.setBounds(1233, 685, 223, 36);
		contentPane.add(btnNewButton_thanhToan);

		Box horizontalBox = Box.createHorizontalBox();
		horizontalBox.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Danh s\u00E1ch ph\u00F2ng \u0111\u00E3 \u0111\u1EB7t:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		horizontalBox.setBounds(10, 84, 615, 335);
		contentPane.add(horizontalBox);

		Box horizontalBox_1 = Box.createHorizontalBox();
		horizontalBox_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Danh s\u00E1ch D\u1ECBch v\u1EE5", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		horizontalBox_1.setBounds(10, 462, 615, 213);
		contentPane.add(horizontalBox_1);

		JScrollPane scrollPane_2 = new JScrollPane((Component) null);
		horizontalBox_1.add(scrollPane_2);

		String [] headers2 = {"Mã DV", "Tên DV","Giá DV", "Loại DV"};
		tableModel2=new DefaultTableModel(headers2,0);
		JScrollPane scroll2 = new JScrollPane();
		scroll.setViewportView(table_2 = new JTable(tableModel2));
		table_2.setRowHeight(25);
		table_2.setAutoCreateRowSorter(true);
		table_2.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);

		scrollPane_2.setViewportView(table_2);

		Box horizontalBox_2_2 = Box.createHorizontalBox();
		horizontalBox_2_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Thanh To\u00E1n", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		horizontalBox_2_2.setBounds(635, 607, 895, 121);
		contentPane.add(horizontalBox_2_2);

		textField_kh = new JTextField();
		textField_kh.setBounds(1040, 102, 158, 25);
		contentPane.add(textField_kh);
		textField_kh.setColumns(10);

		textField_nv = new JTextField();
		textField_nv.setBounds(1040, 139, 158, 27);
		contentPane.add(textField_nv);
		textField_nv.setColumns(10);

		table.addMouseListener(this);
		table_1.addMouseListener(this);
		table_2.addMouseListener(this);

		DocDuLieuDatabaseVaoTable();
		DocDuLieuDatabaseVaoTable2();

		btnThmDchV.addActionListener(this);
		btnNewButton_themP.addActionListener(this);
		btnNewButton_thanhToan.addActionListener(this);
		btnNewButton_taoHD.addActionListener(this);



		this.setVisible(true);
	}

	private double TinhTien() {
		double tongTien = 0;
		for (int i = 0; i < table_1.getRowCount(); i++) {
			tongTien += ChuyenDoi.ChuyenTien(table_1.getValueAt(i, 6).toString());
		}
		return tongTien ;
	}
	private void calculateTienThua() {
		try {
			// Lấy giá trị từ textField_tongTien và textField_khachTra
			String tongTienStr = textField_tongTien.getText().trim();
			String khachTraStr = textField_khachTra.getText().trim();

			if (tongTienStr.isEmpty() || khachTraStr.isEmpty()) {
				System.out.println("Vui lòng nhập đủ thông tin.");
				textField_tienThua.setText("");
				return;
			}

			tongTienStr = tongTienStr.replace(",", "");
			khachTraStr = khachTraStr.replace(",", "");

			double tongTien = Double.parseDouble(tongTienStr);
			double khachTra = Double.parseDouble(khachTraStr);

			System.out.println("Tổng tiền: " + tongTien);
			System.out.println("Khách trả: " + khachTra);

			double tienThua = khachTra - tongTien;

			DecimalFormat decimalFormat = new DecimalFormat("#,###.00");
			textField_tienThua.setText(decimalFormat.format(tienThua));

		} catch (NumberFormatException e) {
			System.out.println("Lỗi định dạng số: " + e.getMessage());
			textField_tienThua.setText("");
		} catch (Exception e) {
			System.out.println("Lỗi: " + e.getMessage());
			textField_tienThua.setText("");
		}
	}

	private String SoHoaDon() {
		String soHoaDon = "";
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyMMdd");
			java.util.Date d = new java.util.Date();
			soHoaDon = dateFormat.format(d);
			System.out.println("soHoaDon: " + soHoaDon);
			//HoaDonDao dao = (HoaDonDao) Naming.lookup(Config.SERVER_URL + "hoaDonDao");
			Long count = dao.countSoHoaDon(soHoaDon);
			int rowCount = count != null ? count.intValue() : 0;
			boolean dup = false;
			do {
				if (rowCount > 98) {
					soHoaDon = soHoaDon + (rowCount + 1);
				} else if (rowCount > 8) {
					soHoaDon = soHoaDon + "0" + (rowCount + 1);
				} else {
					soHoaDon = soHoaDon + "00" + (rowCount + 1);
				}
				System.out.println("soHoaDon: " + soHoaDon);
				HoaDon hoaDon = dao.getBySoHoaDon(soHoaDon);
				if (hoaDon != null) {
					dup = true;
					rowCount++;
					soHoaDon = dateFormat.format(d);
				} else {
					dup = false;
				}
			} while (dup);
		} catch (Exception e) {
			System.out.println("Lỗi số hóa đơn");
		}
		return soHoaDon;
	}


	private void DocDuLieuDatabaseVaoTable2() throws RemoteException {
		List<DichVu> list = dv_dao.getAllDichVu();
		for(DichVu s : list) {
			String[] rowData = {s.getMaDV(), s.getTenDV(),s.getGiaDV()+"", s.getLoaiDV().getLoaiDV()};
			tableModel2.addRow(rowData);
		}
		table_2.setModel(tableModel2);
	}

	private void DocDuLieuDatabaseVaoTable() throws RemoteException {
		// TODO Auto-generated method stub
		//xoa du lieu cu de cap nhat
		tableModel.setRowCount(0);

		List<PhieuDatPhong> list = pdp_dao.getAllPhieuDatPhong();
		for(PhieuDatPhong pdp : list) {
			String[] rowData = {pdp.getId()+"",pdp.getMaPhong().getTenPhong(),pdp.getMaPhong().getLoaiPhong().getLoaiPhong()
					,pdp.getMaPhong().getGiaPhong()+"",pdp.getMaKH().getMaKH(),pdp.getMaNV().getMaNV(),pdp.getTgDatPhong().toString()};
			tableModel.addRow(rowData);
		}
		table.setModel(tableModel);
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
		if (e.getActionCommand().equals("Đặt Phòng Trước")) {
			dispose();
			try {
				new DatPhongTruoc_GUI();
			} catch (RemoteException ex) {
				throw new RuntimeException(ex);
			}
		}
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
		Object o = e.getSource();
		if(o.equals(btnNewButton_themP))
			themP();
		if(o.equals(btnThmDchV))
			themDV();
		if(o.equals(btnNewButton_thanhToan)) {
			try {
				thanhToan();
			} catch (MalformedURLException ex) {
				throw new RuntimeException(ex);
			} catch (NotBoundException ex) {
				throw new RuntimeException(ex);
			} catch (RemoteException ex) {
				throw new RuntimeException(ex);
			}
		}
		if(o.equals(btnNewButton_taoHD))
			taoHD();

	}

	public static double SoGio;
	public static int SoLuong;
	public static String GhiChu;
	public static int maPDP;
	public static Timestamp tgDatPhong;
	public static String maDV;
	private JTable table_2;
	private JTextField textField_kh;
	private JTextField textField_nv;

	private void themDV() {
		// TODO Auto-generated method stub
		maDV = table_2.getValueAt(table_2.getSelectedRow(), 0).toString();
		String tenDV = table_2.getValueAt(table_2.getSelectedRow(), 1).toString();
		double giaDV = Double.parseDouble(table_2.getValueAt(table_2.getSelectedRow(), 2).toString());
		String loaiDV = table_2.getValueAt(table_2.getSelectedRow(), 3).toString();

		jdlAddDV jdl = new jdlAddDV(this, true);
		jdl.setVisible(true);
		System.out.println(SoLuong);
		System.out.println(GhiChu);

		double thanhTien = SoLuong * giaDV;
		if (SoLuong !=0){
			ThemDVTbCTHD(maDV,tenDV,giaDV,SoLuong,thanhTien,GhiChu);
			textField_tongTien.setText(ChuyenDoi.DinhDangTien(TinhTien()));
			calculateTienThua();

		}

	}

	private void ThemDVTbCTHD(String maDV, String tenDV, double giaDV, int soLuong, double thanhTien,
							  String ghiChu) {
		// TODO Auto-generated method stub
		DefaultTableModel tbModel = (DefaultTableModel) table_1.getModel();
		boolean isUpdated = false;

		// Duyệt qua bảng để kiểm tra sự tồn tại của mã dịch vụ
		for (int i = 0; i < tbModel.getRowCount(); i++) {
			String existingMaDV = String.valueOf(tbModel.getValueAt(i, 1));
			if (existingMaDV.equals(maDV)) {
				int existingSoLuong = (int) tbModel.getValueAt(i, 5);
				String existingThanhTienStr = String.valueOf(tbModel.getValueAt(i, 6));
				double existingThanhTien = 0.0;
				try {
					existingThanhTien = Double.parseDouble(existingThanhTienStr.replace(",", "").replace("đ", "").trim());  // Loại bỏ dấu phẩy và ký tự "đ" nếu có
				} catch (NumberFormatException e) {
					existingThanhTien = 0.0;
				}
				tbModel.setValueAt(existingSoLuong + soLuong, i, 5);
				tbModel.setValueAt(existingThanhTien + thanhTien, i, 6);

				isUpdated = true;
				break;
			}
		}

		if (!isUpdated) {
			Object obj[] = new Object[8];
			obj[0] = tbModel.getRowCount() + 1;
			obj[1] = maDV;
			obj[2] = tenDV;
			obj[3] = ChuyenDoi.DinhDangTien(giaDV);
			obj[4] = '-';
			obj[5] = soLuong;
			obj[6] = ChuyenDoi.DinhDangTien(thanhTien);
			obj[7] = ghiChu;
			tbModel.addRow(obj);
		}
	}
	private boolean isMaPDPExistInTable(JTable tbModel, int maPDP) {
		for (int i = 0; i < tbModel.getRowCount(); i++) {
			int existingMaPDP = Integer.parseInt( tbModel.getValueAt(i, 0).toString());
			if (existingMaPDP ==  maPDP) {
				return true;
			}
		}
		return false;
	}
	private void ThemPhongTbCTHD(int maPDP, String tenP, double giaP, double soGio, double thanhTien, String ghiChu) {
		// TODO Auto-generated method stub
		DefaultTableModel tbModel = (DefaultTableModel) table_1.getModel();
		DecimalFormat df = new DecimalFormat("#.00");

		// Kiểm tra các dòng chứa thông tin phòng trong bảng
		for (int i = 0; i < tbModel.getRowCount(); i++) {
			try {
				// Lấy giá trị từ cột 1 và chuyển đổi sang số nguyên
				int existingMaPDP = Integer.parseInt(tbModel.getValueAt(i, 1).toString().trim());

				// Kiểm tra nếu mã phòng đã tồn tại
				if (existingMaPDP == maPDP) {
					JOptionPane.showMessageDialog(this, "Mã phòng đã được thêm vào", "Thông báo", JOptionPane.WARNING_MESSAGE);
					return;
				}

				if (isMaPDPExistInTable(table, existingMaPDP)) {
					JOptionPane.showMessageDialog(this, "Đã thêm phòng vào bảng!", "Thông báo", JOptionPane.WARNING_MESSAGE);
					return;
				}
			} catch (NumberFormatException e) {
				System.out.println("Giá trị không hợp lệ ở dòng: " + i + ", bỏ qua");
			}
		}


		// Nếu chưa có phòng trong bảng, thêm phòng mới vào bảng
		Object obj[] = new Object[8];
		obj[0] = tbModel.getRowCount() + 1;
		obj[1] = maPDP;
		obj[2] = tenP;
		obj[3] = ChuyenDoi.DinhDangTien(giaP);
		obj[4] = df.format(soGio);
		obj[5] = '-';
		obj[6] = ChuyenDoi.DinhDangTien(thanhTien);
		obj[7] = ghiChu;
		tbModel.addRow(obj);


	}

	private void themP() {
		// TODO Auto-generated method stub
		maPDP = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
		tgDatPhong = Timestamp.valueOf(table.getValueAt(table.getSelectedRow(), 6).toString());
		String tenP = table.getValueAt(table.getSelectedRow(), 1).toString();
		double giaP = Double.parseDouble(table.getValueAt(table.getSelectedRow(), 3).toString());


		jdlAddPDP jdl = new jdlAddPDP(this, true);
		jdl.getDateChooser().setDate(new java.util.Date());

		java.util.Date tgTraPhong = jdl.getDateChooser().getDate();
		if (tgTraPhong != null) {
			Timestamp tgNhanPhong = new Timestamp(tgTraPhong.getTime());

			long diffMillis = tgNhanPhong.getTime() - tgDatPhong.getTime();
			SoGio = diffMillis / (1000.0 * 60 * 60);
            if(SoGio > 1){
				jdl.getSpnSoGio().setValue(SoGio);
			}
			System.out.println("Số giờ: " + SoGio);
		}
		jdl.setVisible(true);
		System.out.println(SoGio);
		System.out.println(GhiChu);

		double thanhTien = SoGio * giaP;
		if(SoGio >0){
			ThemPhongTbCTHD(maPDP,tenP,giaP,SoGio,thanhTien,GhiChu);
			textField_tongTien.setText(ChuyenDoi.DinhDangTien(TinhTien()));
			calculateTienThua();

		}


	}


	private int thanhToan() throws MalformedURLException, NotBoundException, RemoteException {
		HoaDon hd = new HoaDon();
		String SoHoaDon = textField_shd.getText();

		String NgayTaoHD1 = textField_ngayTao.getText().trim();
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate lcd = LocalDate.parse(NgayTaoHD1, fmt);
		Date NgayTaoHD = Date.valueOf(lcd);

		int row = table.getSelectedRow();
//		String tp = table.getModel().getValueAt(row, 0).toString();
		int pdp = Integer.parseInt(table.getModel().getValueAt(row, 0).toString());
		String tp = pdp_dao.getPhieuDatPhongByID(pdp).getMaPhong().getMaPhong();

		NhanVien nv = new NhanVien();
		nv.setMaNV(textField_nv.getText());
		KhachHang kh = new KhachHang();
		kh.setMaKH(textField_kh.getText());
		hd.setMaKH(kh);
//		String tongTienStr = textField_tongTien.getText().trim();
//		tongTienStr = tongTienStr.replace(",", "");
		double tt = ChuyenDoi.ChuyenTien(textField_tongTien.getText().trim());
		String gc = textField_ghiChu.getText();

		java.util.Date date = new java.util.Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHmsS");
		String idcode = dateFormat.format(date);

		//HoaDonDao dao = (HoaDonDao) Naming.lookup(Config.SERVER_URL + "hoaDonDao");
		hd.setSoHoaDon(SoHoaDon);
		hd.setTongTien(BigDecimal.valueOf(tt));
		hd.setNgayTaoHD(NgayTaoHD.toLocalDate());
		hd.setMaNV(nv);
		hd.setGhiChu(gc);
		hd.setiDCode(idcode);
		int maHD = dao.createHoaDon(hd);

		System.out.println(maHD);
		ThemCTHDTuTable(maHD);
		p_dao.updateTrangThaiTP(tp);
		pdp_dao.deletePhieuDatPhong(pdp);
		XuatHoaDon(maHD);

		TaoMoiHD();
		return maHD;
	}


	public void XuatHoaDon(int idhd) throws MalformedURLException, NotBoundException, RemoteException {
		//HoaDonDao dao = (HoaDonDao) Naming.lookup(Config.SERVER_URL + "hoaDonDao");
		try {
			dao.xuatHoaDon(idhd);
			//Load lai data
			DocDuLieuDatabaseVaoTable();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}




	private void TaoMoiHD() {
		textField_shd.setText(SoHoaDon());
		DefaultTableModel tbModel = (DefaultTableModel)table_1.getModel();
		tbModel.setRowCount(0);
	}
	private void taoHD() {
		textField_shd.setText(SoHoaDon());
	}

	public static boolean isNumeric(String strNum) {
		if (strNum == null) {
			return false;
		}
		try {
			int d = Integer.parseInt(strNum);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	private void ThemCTHDTuTable(int maHD) throws RemoteException, MalformedURLException, NotBoundException {
		ChiTietHoaDonDao cthd_dao = (ChiTietHoaDonDao) Naming.lookup(Config.SERVER_URL + "chiTietHoaDonDao");
		for (int i = 0; i < table_1.getRowCount(); i++) {
			String temp = table_1.getValueAt(i, 1).toString();
			System.out.println("temp " + temp);
			int pdp1 = 0;
			if (isNumeric(temp)) {
				System.out.println("láy id ");

				pdp1 = pdp_dao.getPhieuDatPhongByID(Integer.parseInt(temp)).getId();
				System.out.println("là id "+ pdp1);

			}
			int sl = 0;
			double sg = 0.0;
			try {
				String slStr = table_1.getValueAt(i, 5).toString();
				if ("-".equals(slStr)) {
					sl = NULL;
				} else {
					sl = Integer.parseInt(slStr);
				}

				String sgStr = table_1.getValueAt(i, 4).toString();
				if ("-".equals(sgStr)) {
					sg = NULL;
				} else {
					sg = Double.parseDouble(sgStr);
				}

			} catch (NumberFormatException e) {
				System.out.println("Lỗi định dạng dữ liệu: " + e.getMessage());
				sl = 0;
				sg = 0.0;
			}
			double ThanhTien = ChuyenDoi.ChuyenTien(table_1.getValueAt(i, 6).toString());
			String gc = table_1.getValueAt(i, 7).toString();
			ChiTietHoaDon cthd = new ChiTietHoaDon();
			HoaDon hd = new HoaDon();
			hd.setId(maHD);
			cthd.setMaHD(hd);
			DichVu dv = dv_dao.getDichVuByLoaiDichVu(temp);
			System.out.println("Dịch Vụ: " + dv);

			if (dv == null) {
				// Tạo một DichVu mặc định
				dv = new DichVu();
				dv.setMaDV("DV000");
				dv.setTenDV("Phòng hát");
				// Kiểm tra dịch vụ có tồn tại không
//				DichVu existingDv = dv_dao.getDichVuByLoaiDichVu(dv.getLoaiDV().toString());
//				if (existingDv != null) {
//					// Nếu đã tồn tại, cập nhật
//					existingDv.setTenDV(dv.getTenDV());
//					existingDv.setGiaDV(dv.getGiaDV());
//					dv_dao.updateDichVu(existingDv);
//				} else {
//					dv_dao.createDichVu(dv);
//				}
			}
			cthd.setMaPDP(pdp1);
			cthd.setMaDV(dv);
			cthd.setSoLuong(sl);
			cthd.setSoGio(BigDecimal.valueOf(sg));
			cthd.setThanhTien(BigDecimal.valueOf(ThanhTien));
			cthd.setGhiChu(gc);
			cthd_dao.createChiTietHoaDon(cthd);

		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		textField_kh.setText(tableModel.getValueAt(row, 4).toString().trim());
		textField_nv.setText(tableModel.getValueAt(row, 5).toString().trim());
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
