package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.TextField;
import javax.swing.JComboBox;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.border.BevelBorder;
import javax.swing.JTabbedPane;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.Component;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;


import Server.Config;
import bll.SessionManager;
import entity.ChucVu;
import entity.KhachHang;
import entity.NhanVien;
import entity.TaiKhoan;
import model.KhachHangDao;
import model.NhanVienDao;
import model.TaiKhoanDao;
import org.hibernate.LazyInitializationException;
import util.HibernateUtil;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.List;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class CapNhapKH_GUI extends JFrame implements ActionListener, MouseListener{

	private JPanel contentPane;
	private JTextField textField_maKH;
	private JTextField textField_tenKH;
	private JTextField textField_cmnd;
	private JTextField textField_sdt;
	private JTable table;
	private DefaultTableModel tableModel;
	private KhachHangDao kh_dao;
	private JButton btnNewButton_them;
	private JButton btnNewButton_xoa;
	private JButton btnNewButton_sua;
	private JButton btnNewButton_xoaTrang;
	private JTextField txt_timkiemkhachhang;
	private JTextField txt_tinhthanh;
	private JTextField txt_tuKH;
	private JTextField txt_denKH;
	private JTextField txt_duno;
	private JTextField txt_denduno;
	private TaiKhoan user;
	private TaiKhoanDao tk_dao;
	private SessionManager currentUser = SessionManager.getInstance();
	private NhanVienDao nv_dao;
	public CapNhapKH_GUI() throws RemoteException {

		try{
			tk_dao = (TaiKhoanDao) Naming.lookup(Config.SERVER_URL + "taiKhoanDao");
			nv_dao = (NhanVienDao) Naming.lookup(Config.SERVER_URL + "nhanVienDao");
			kh_dao = (KhachHangDao) Naming.lookup(Config.SERVER_URL + "khachHangDao");
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
		
		JLabel lblNewLabel = new JLabel("CẬP NHẬT KHÁCH HÀNG");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 50));
		lblNewLabel.setBounds(442, 10, 650, 74);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_maKH = new JLabel("Mã KH:");
		lblNewLabel_maKH.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_maKH.setBounds(923, 118, 58, 29);
		contentPane.add(lblNewLabel_maKH);
		
		JLabel lblNewLabel_tenKH = new JLabel("Tên KH:");
		lblNewLabel_tenKH.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_tenKH.setBounds(923, 172, 58, 29);
		contentPane.add(lblNewLabel_tenKH);
		
		JLabel lblNewLabel_cmnd = new JLabel("CMND:");
		lblNewLabel_cmnd.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_cmnd.setBounds(1196, 118, 74, 29);
		contentPane.add(lblNewLabel_cmnd);
		
		JLabel lblNewLabel_sdt = new JLabel("SĐT:");
		lblNewLabel_sdt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_sdt.setBounds(1196, 172, 74, 29);
		contentPane.add(lblNewLabel_sdt);
		
		textField_maKH = new JTextField();
		textField_maKH.setBounds(991, 122, 146, 26);
		textField_maKH.setColumns(10);
		contentPane.add(textField_maKH);
		
		textField_tenKH = new JTextField();
		textField_tenKH.setBounds(991, 176, 146, 26);
		textField_tenKH.setColumns(10);
		contentPane.add(textField_tenKH);
		
		textField_cmnd = new JTextField();
		textField_cmnd.setColumns(10);
		textField_cmnd.setBounds(1280, 122, 146, 26);
		contentPane.add(textField_cmnd);
		
		textField_sdt = new JTextField();
		textField_sdt.setColumns(10);
		textField_sdt.setBounds(1280, 176, 146, 26);
		contentPane.add(textField_sdt);
		
		btnNewButton_them = new JButton("Thêm");
		btnNewButton_them.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_them.setBounds(682, 236, 134, 39);
		contentPane.add(btnNewButton_them);
		
		btnNewButton_xoa = new JButton("Xoá");
		btnNewButton_xoa.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_xoa.setBounds(877, 236, 134, 39);
		contentPane.add(btnNewButton_xoa);
		
		 btnNewButton_sua = new JButton("Sửa");
		btnNewButton_sua.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_sua.setBounds(1072, 236, 134, 39);
		contentPane.add(btnNewButton_sua);
		
		 btnNewButton_xoaTrang = new JButton("Xoá Trắng");
		btnNewButton_xoaTrang.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_xoaTrang.setBounds(1248, 236, 134, 39);
		contentPane.add(btnNewButton_xoaTrang);
		
		String [] headers = {"Mã KH", "Tên KH", "CMND", "SĐT"};
		tableModel=new DefaultTableModel(headers,0);
		JScrollPane scroll = new JScrollPane();
		scroll.setViewportView(table = new JTable(tableModel));
		table.setRowHeight(25);
		table.setAutoCreateRowSorter(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(413, 331, 1117, 391);
		contentPane.add(scrollPane);
		
		JLabel lblNewLabel_2 = new JLabel("Danh sách Khách Hàng:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(413, 292, 175, 29);
		contentPane.add(lblNewLabel_2);
		
		Box horizontalBox = Box.createHorizontalBox();
		horizontalBox.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Th\u00F4ng Tin Kh\u00E1ch H\u00E0ng", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		horizontalBox.setBounds(413, 91, 1117, 135);
		contentPane.add(horizontalBox);
		
		JLabel lblNewLabel_maKH_1 = new JLabel("Tìm kiếm khách hàng");
		lblNewLabel_maKH_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_maKH_1.setBounds(10, 10, 233, 29);
		contentPane.add(lblNewLabel_maKH_1);
		
		txt_timkiemkhachhang = new JTextField();
		txt_timkiemkhachhang.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txt_timkiemkhachhang.getText().equals("Mã, Tên, Điện Thoại")) {
					txt_timkiemkhachhang.setText("");
					txt_timkiemkhachhang.setForeground(new Color(153,153,153));
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(txt_timkiemkhachhang.getText().equals("")) {
					txt_timkiemkhachhang.setText("Mã, Tên, Điện Thoại");
					txt_timkiemkhachhang.setForeground(new Color(153,153,153));
				}
			}
		});
		txt_timkiemkhachhang.setForeground(Color.GRAY); 
		txt_timkiemkhachhang.setText("Mã, Tên, Điện Thoại");
		txt_timkiemkhachhang.getText().isEmpty();
		txt_timkiemkhachhang.setColumns(10);
		txt_timkiemkhachhang.setBounds(10, 39, 233, 26);
		contentPane.add(txt_timkiemkhachhang);
		
		txt_tinhthanh = new JTextField();
		txt_tinhthanh.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txt_tinhthanh.getText().equals("Tỉnh, thành")) {
					txt_tinhthanh.setText(" ");
					txt_tinhthanh.setForeground(new Color(153,153,153));
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(txt_tinhthanh.getText().equals("")) {
					txt_tinhthanh.setText("Tỉnh, thành");
					txt_tinhthanh.setForeground(new Color(153,153,153));
				}
			}
		});
		txt_tinhthanh.setForeground(Color.GRAY);
		txt_tinhthanh.setText("Tỉnh, thành");
		txt_tinhthanh.setColumns(10);
		txt_tinhthanh.setBounds(10, 75, 233, 26);
		contentPane.add(txt_tinhthanh);
		
		JComboBox<String> comboBox = new JComboBox<>();
		comboBox.setBounds(10, 111, 233, 21);
		contentPane.add(comboBox);
		
		JLabel lbl_tong_gdkh = new JLabel("Tổng giao dịch");
		lbl_tong_gdkh.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl_tong_gdkh.setBounds(10, 142, 175, 29);
		contentPane.add(lbl_tong_gdkh);
		
		JLabel lbl_fromKH = new JLabel("Từ");
		lbl_fromKH.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_fromKH.setBounds(10, 172, 36, 29);
		contentPane.add(lbl_fromKH);
		
		JLabel lbl_toKH_1 = new JLabel("Đến");
		lbl_toKH_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_toKH_1.setBounds(10, 209, 36, 29);
		contentPane.add(lbl_toKH_1);
		
		txt_tuKH = new JTextField();
		txt_tuKH.setText("0");
		txt_tuKH.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		txt_tuKH.setColumns(10);
		txt_tuKH.setBounds(39, 172, 204, 26);
		txt_tuKH.setHorizontalAlignment(JTextField.RIGHT);
		contentPane.add(txt_tuKH);
		
		txt_denKH = new JTextField();
		txt_denKH.setText("0");
		txt_denKH.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		txt_denKH.setColumns(10);
		txt_denKH.setBounds(39, 211, 204, 26);
		txt_denKH.setHorizontalAlignment(JTextField.RIGHT);
		contentPane.add(txt_denKH);
		
		JButton btn_loc = new JButton("");
		btn_loc.setBounds(10, 249, 233, 29);
		contentPane.add(btn_loc);
		
		ImageIcon icon = new ImageIcon(""); 
		btn_loc.setIcon(new ImageIcon("D:\\z_nam_4\\BTL_PhanTan_Karaoke\\KaraokePT\\src\\main\\resources\\images\\filt.png"));
		btn_loc.setHorizontalTextPosition(SwingConstants.CENTER);
		btn_loc.setVerticalTextPosition(SwingConstants.BOTTOM);
		btn_loc.setIconTextGap(5);
		
		JLabel lbl_duno = new JLabel("Dư nợ");
		lbl_duno.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl_duno.setBounds(10, 292, 175, 29);
		contentPane.add(lbl_duno);
		
		JLabel lbl_fromduno_1 = new JLabel("Từ");
		lbl_fromduno_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_fromduno_1.setBounds(10, 318, 36, 29);
		contentPane.add(lbl_fromduno_1);
		
		txt_duno = new JTextField();
		txt_duno.setText("0");
		txt_duno.setHorizontalAlignment(SwingConstants.RIGHT);
		txt_duno.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		txt_duno.setColumns(10);
		txt_duno.setBounds(39, 318, 204, 26);
		contentPane.add(txt_duno);
		
		JLabel lbl_toduno_1_1 = new JLabel("Đến");
		lbl_toduno_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_toduno_1_1.setBounds(10, 356, 36, 29);
		contentPane.add(lbl_toduno_1_1);
		
		txt_denduno = new JTextField();
		txt_denduno.setText("0");
		txt_denduno.setHorizontalAlignment(SwingConstants.RIGHT);
		txt_denduno.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		txt_denduno.setColumns(10);
		txt_denduno.setBounds(39, 354, 204, 26);
		contentPane.add(txt_denduno);
		
		JButton btn_loc_1 = new JButton("");
		btn_loc_1.setIcon(new ImageIcon("D:\\z_nam_4\\BTL_PhanTan_Karaoke\\KaraokePT\\src\\main\\resources\\images\\filt.png"));
		btn_loc_1.setVerticalTextPosition(SwingConstants.BOTTOM);
		btn_loc_1.setIconTextGap(5);
		btn_loc_1.setHorizontalTextPosition(SwingConstants.CENTER);
		btn_loc_1.setBounds(10, 390, 233, 29);
		contentPane.add(btn_loc_1);

		comboBox.addItem("Sinh nhật");
		
		comboBox.addItem("Tháng 1");
		comboBox.addItem("Tháng 2");
		comboBox.addItem("Tháng 3");
		comboBox.addItem("Tháng 4");
		comboBox.addItem("Tháng 5");
		comboBox.addItem("Tháng 6");
		comboBox.addItem("Tháng 7");
		comboBox.addItem("Tháng 8");
		comboBox.addItem("Tháng 9");
		comboBox.addItem("Tháng 10");
		comboBox.addItem("Tháng 11");
		comboBox.addItem("Tháng 12");

		
		btnNewButton_them.addActionListener(this);
		btnNewButton_xoa.addActionListener(this);
		btnNewButton_sua.addActionListener(this);
		btnNewButton_xoaTrang.addActionListener(this);
		
		table.addMouseListener(this);
		DocDuLieuDatabaseVaoTable();
		this.setVisible(true);
	}

	private void DocDuLieuDatabaseVaoTable() throws RemoteException {
		List<KhachHang> list = kh_dao.getAllKhachHang();
		for (KhachHang kh : list) {
			tableModel.addRow(new Object[]{kh.getMaKH(), kh.getTenKH(), kh.getCmnd(), kh.getSdt()});
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
		/////////////////////////////////////////////////////////////////////////
		Object o = e.getSource();
		if(o.equals(btnNewButton_them))
			themKH();
		if(o.equals(btnNewButton_xoa)) {
			try {
				xoaKH();
			} catch (RemoteException ex) {
				throw new RuntimeException(ex);
			}
		}
		if(o.equals(btnNewButton_sua)) {
			try {
				suaKH();
			} catch (RemoteException ex) {
				throw new RuntimeException(ex);
			}
		}
		if(o.equals(btnNewButton_xoaTrang))
			xoaTrang();
	}

	private void xoaTrang() {
		// TODO Auto-generated method stub
		textField_maKH.setText("");
		textField_tenKH.setText("");
		textField_cmnd.setText("");
		textField_sdt.setText("");
		textField_maKH.requestFocus();
	}

	private void suaKH() throws RemoteException {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		String ma = textField_maKH.getText();
		String hoten = textField_tenKH.getText();
		String cmnd = textField_cmnd.getText();
		String sdt = textField_sdt.getText();
		KhachHang kh = new KhachHang(ma, hoten, cmnd, sdt);
		if(row>=0) {
			if(kh_dao.updateKhachHang(kh)) {
				table.setValueAt(textField_tenKH.getText(), row, 1);
				table.setValueAt(textField_cmnd.getText(), row, 2);
				table.setValueAt(textField_sdt.getText(), row, 3);
			}
		}
	}

	private void xoaKH() throws RemoteException {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		if(row>=0) {
			String maKH = (String) table.getValueAt(row, 0);
			if(kh_dao.deleteKhachHang(maKH)) {
				tableModel.removeRow(row);
				xoaTrang();
			}
		}
	}

	private void themKH() {
		// TODO Auto-generated method stub
		String ma = textField_maKH.getText();
		String hoten = textField_tenKH.getText();
		String cmnd = textField_cmnd.getText();
		String sdt = textField_sdt.getText();
		KhachHang kh = new KhachHang(ma, hoten, cmnd, sdt);
		try {
			kh_dao.createKhachHang(kh);
			tableModel.addRow(new Object[] {kh.getMaKH(), kh.getTenKH(), kh.getCmnd(), kh.getSdt()
			});
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(this, "Trùng");
			e.printStackTrace();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		textField_maKH.setText(tableModel.getValueAt(row, 0).toString());
		textField_tenKH.setText(tableModel.getValueAt(row, 1).toString());
		textField_cmnd.setText(tableModel.getValueAt(row, 2).toString());
		textField_sdt.setText(tableModel.getValueAt(row, 3).toString());
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
