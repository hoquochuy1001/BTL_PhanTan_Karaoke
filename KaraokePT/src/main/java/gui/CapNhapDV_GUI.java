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
import javax.swing.ButtonGroup;
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
import entity.DichVu;
import entity.LoaiDichVu;
import entity.NhanVien;
import entity.TaiKhoan;
import model.DichVuDao;
import model.LoaiDichVuDao;
import model.NhanVienDao;
import model.TaiKhoanDao;
import org.hibernate.LazyInitializationException;
import util.HibernateUtil;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
public class CapNhapDV_GUI extends JFrame implements ActionListener, MouseListener{

	private JPanel contentPane;
	private JTextField textField_maDV;
	private JTable table;
	private DichVuDao dv_dao;
	private DefaultTableModel tableModel;
	private JComboBox comboBox_loaiDV;
	private JButton btnNewButton_them;
	private JButton btnNewButton_xoa;
	private JButton btnNewButton_sua;
	private JButton btnNewButton_xoaTrang;
	private LoaiDichVuDao ldv_dao;
	private JComboBox comboBox_dvt;
	private JTextField textField_tenDV;
	private JTextField textField_giaDV;
	private JTextField txt_Madichvu;
	private JTextField txt_timkiem;
	private TaiKhoan user;
	private TaiKhoanDao tk_dao;
	private SessionManager currentUser = SessionManager.getInstance();
	private NhanVienDao nv_dao;
	public CapNhapDV_GUI() throws RemoteException {
		try{
			dv_dao = (DichVuDao) Naming.lookup(Config.SERVER_URL + "dichVuDao");
			ldv_dao = (LoaiDichVuDao) Naming.lookup(Config.SERVER_URL + "loaiDichVuDao");
			tk_dao = (TaiKhoanDao) Naming.lookup(Config.SERVER_URL + "taiKhoanDao");
			nv_dao = (NhanVienDao) Naming.lookup(Config.SERVER_URL + "nhanVienDao");
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
		JLabel lblNewLabel = new JLabel("CẬP NHẬT DỊCH VỤ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 50));
		lblNewLabel.setBounds(567, 0, 650, 74);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_maDV = new JLabel("Mã DV:");
		lblNewLabel_maDV.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_maDV.setBounds(459, 118, 58, 29);
		contentPane.add(lblNewLabel_maDV);
		
		JLabel lblNewLabel_tenDV = new JLabel("Tên DV:");
		lblNewLabel_tenDV.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_tenDV.setBounds(459, 172, 58, 29);
		contentPane.add(lblNewLabel_tenDV);
		
		JLabel lblNewLabel_dvt = new JLabel("Đơn Vị Tính:");
		lblNewLabel_dvt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_dvt.setBounds(724, 118, 84, 29);
		contentPane.add(lblNewLabel_dvt);
		
		JLabel lblNewLabel_giaDV = new JLabel("Giá DV:");
		lblNewLabel_giaDV.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_giaDV.setBounds(1015, 118, 74, 29);
		contentPane.add(lblNewLabel_giaDV);
		
		JLabel lblNewLabel_loaiDV = new JLabel("Loại DV:");
		lblNewLabel_loaiDV.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_loaiDV.setBounds(734, 172, 74, 29);
		contentPane.add(lblNewLabel_loaiDV);
		
		textField_maDV = new JTextField();
		textField_maDV.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_maDV.setBounds(527, 121, 161, 26);
		contentPane.add(textField_maDV);
		textField_maDV.setColumns(10);
		
		comboBox_loaiDV = new JComboBox();
		comboBox_loaiDV.setFont(new Font("Tahoma", Font.PLAIN, 12));
		comboBox_loaiDV.setEditable(true);
		for(LoaiDichVu ldv : ldv_dao.getAllLoaiDichVu()) {
			comboBox_loaiDV.addItem(ldv.getLoaiDV());
		}
		comboBox_loaiDV.setBounds(818, 173, 161, 29);
		contentPane.add(comboBox_loaiDV);
		
		btnNewButton_them = new JButton("Thêm");
		btnNewButton_them.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_them.setBounds(459, 236, 134, 39);
		contentPane.add(btnNewButton_them);
		
		 btnNewButton_xoa = new JButton("Xoá");
		btnNewButton_xoa.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_xoa.setBounds(655, 236, 134, 39);
		contentPane.add(btnNewButton_xoa);
		
		 btnNewButton_sua = new JButton("Sửa");
		btnNewButton_sua.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_sua.setBounds(854, 236, 134, 39);
		contentPane.add(btnNewButton_sua);
		
		 btnNewButton_xoaTrang = new JButton("Xoá Trắng");
		btnNewButton_xoaTrang.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_xoaTrang.setBounds(1052, 236, 134, 39);
		contentPane.add(btnNewButton_xoaTrang);
		
		String [] headers = {"Mã DV", "Tên DV", "Đơn Vị Tính", "Giá Dịch Vụ", "Loại Dịch Vụ"};
		tableModel=new DefaultTableModel(headers,0);
		JScrollPane scroll = new JScrollPane();
		scroll.setViewportView(table = new JTable(tableModel));
		table.setRowHeight(25);
		table.setAutoCreateRowSorter(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(348, 331, 1182, 395);
		contentPane.add(scrollPane);
		
		JLabel lblNewLabel_2 = new JLabel("Danh sách Dịch Vụ:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(348, 292, 151, 29);
		contentPane.add(lblNewLabel_2);
		
		 comboBox_dvt = new JComboBox();
		 comboBox_dvt.setFont(new Font("Tahoma", Font.PLAIN, 12));
		 comboBox_dvt.setEditable(true);

		 comboBox_dvt.addItem("Bịch");
		 comboBox_dvt.addItem("Lon");
		 comboBox_dvt.addItem("Điếu");
		 comboBox_dvt.addItem("Gói");
		 
		comboBox_dvt.setBounds(818, 120, 161, 28);
		contentPane.add(comboBox_dvt);
		
		textField_tenDV = new JTextField();
		textField_tenDV.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_tenDV.setColumns(10);
		textField_tenDV.setBounds(527, 175, 161, 26);
		contentPane.add(textField_tenDV);
		
		textField_giaDV = new JTextField();
		textField_giaDV.setFont(new Font("Tahoma", Font.PLAIN, 10));
		textField_giaDV.setColumns(10);
		textField_giaDV.setBounds(1088, 122, 146, 26);
		contentPane.add(textField_giaDV);
		
		JLabel lbl_maDichvu = new JLabel("Mã dịch vụ: ");
		lbl_maDichvu.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_maDichvu.setBounds(10, 0, 151, 29);
		contentPane.add(lbl_maDichvu);
		
		txt_Madichvu = new JTextField();
		txt_Madichvu.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txt_Madichvu.setColumns(10);
		txt_Madichvu.setBounds(10, 30, 161, 26);
		contentPane.add(txt_Madichvu);
		
		JLabel lbl_timkiemmathang = new JLabel("Tìm kiếm mặt hàng: ");
		lbl_timkiemmathang.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_timkiemmathang.setBounds(10, 66, 151, 29);
		contentPane.add(lbl_timkiemmathang);
		
		txt_timkiem = new JTextField();
		txt_timkiem.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txt_timkiem.setColumns(10);
		txt_timkiem.setBounds(10, 92, 161, 26);
		contentPane.add(txt_timkiem);
		
		JLabel lbl_Loctheotrangthai = new JLabel("Loc theo trang thai");
		lbl_Loctheotrangthai.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_Loctheotrangthai.setBounds(10, 128, 151, 29);
		contentPane.add(lbl_Loctheotrangthai);
		
		JCheckBox cb_Dathang = new JCheckBox("Đặt hàng");
		cb_Dathang.setBounds(10, 159, 93, 21);
		contentPane.add(cb_Dathang);
		
		JCheckBox cb_Hoanthanhdathang = new JCheckBox("Hoàn Thành");
		cb_Hoanthanhdathang.setBounds(10, 182, 93, 21);
		contentPane.add(cb_Hoanthanhdathang);
		
		JCheckBox cb_Huyhang = new JCheckBox("Huỷ hàng");
		cb_Huyhang.setBounds(10, 205, 93, 21);
		contentPane.add(cb_Huyhang);
		
		JLabel lbl_Loctheongay = new JLabel("Loc theo ngày");
		lbl_Loctheongay.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_Loctheongay.setBounds(10, 228, 151, 29);
		contentPane.add(lbl_Loctheongay);
		
		JComboBox<String> comboBox = new JComboBox<>();
		comboBox.addItem("Ngày bán");
		comboBox.addItem("Ngày tạo");
		comboBox.setBounds(10, 254, 150, 21);
		contentPane.add(comboBox);
		
		// Tạo ButtonGroup
		ButtonGroup group = new ButtonGroup();

		JRadioButton rb_Toantg = new JRadioButton("Toàn thời gian");
		rb_Toantg.setBounds(10, 281, 103, 21);
		group.add(rb_Toantg); // Thêm vào nhóm
		contentPane.add(rb_Toantg);

		JRadioButton rb_homnay = new JRadioButton("Hôm nay");
		rb_homnay.setBounds(10, 304, 103, 21);
		group.add(rb_homnay); // Thêm vào nhóm
		contentPane.add(rb_homnay);

		JRadioButton rb_homqua = new JRadioButton("Hôm qua");
		rb_homqua.setBounds(10, 327, 103, 21);
		group.add(rb_homqua); // Thêm vào nhóm
		contentPane.add(rb_homqua);

		JRadioButton rb_7ngay = new JRadioButton("7 ngày trước");
		rb_7ngay.setBounds(10, 350, 103, 21);
		group.add(rb_7ngay); // Thêm vào nhóm
		contentPane.add(rb_7ngay);

		JRadioButton rb_thangnay = new JRadioButton("Tháng này");
		rb_thangnay.setBounds(10, 373, 103, 21);
		group.add(rb_thangnay); // Thêm vào nhóm
		contentPane.add(rb_thangnay);

		JRadioButton rb_thangtruoc = new JRadioButton("Tháng trước");
		rb_thangtruoc.setBounds(10, 396, 103, 21);
		group.add(rb_thangtruoc); // Thêm vào nhóm
		contentPane.add(rb_thangtruoc);

		JRadioButton rb_others = new JRadioButton("Lựa chọn khác");
		rb_others.setBounds(10, 419, 103, 21);
		group.add(rb_others); // Thêm vào nhóm
		contentPane.add(rb_others);
		
		JLabel lbl_trangthaihoadon = new JLabel("Trạng Thái hóa đơn");
		lbl_trangthaihoadon.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_trangthaihoadon.setBounds(10, 446, 151, 29);
		contentPane.add(lbl_trangthaihoadon);



		
		
		btnNewButton_them.addActionListener(this);
		btnNewButton_xoa.addActionListener(this);
		btnNewButton_sua.addActionListener(this);
		btnNewButton_xoaTrang.addActionListener(this);
		
		table.addMouseListener(this);
		DocDuLieuDatabaseVaoTable();
		this.setVisible(true);
	}


	private void DocDuLieuDatabaseVaoTable() throws RemoteException {
		// TODO Auto-generated method stub
		List<DichVu> list = dv_dao.getAllDichVu();
		for(DichVu s : list) {
			LoaiDichVu loaiDV = s.getLoaiDV();
			String loaiDVStr = (loaiDV != null) ? loaiDV.getLoaiDV() : "N/A";
			String[] rowData = {s.getMaDV(), s.getTenDV(), s.getDonViTinh(), s.getGiaDV()+"", loaiDVStr};
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
		///////////////////////////////////////////////////////////////////////
		Object o = e.getSource();
		if(o.equals(btnNewButton_them))
			themDV();
		if(o.equals(btnNewButton_xoa)) {
			try {
				xoaDV();
			} catch (RemoteException ex) {
				throw new RuntimeException(ex);
			}
		}
		if(o.equals(btnNewButton_sua)) {
			try {
				suaDV();
			} catch (RemoteException ex) {
				throw new RuntimeException(ex);
			}
		}
		if(o.equals(btnNewButton_xoaTrang))
			xoaTrang();
	}


	private void xoaTrang() {
		// TODO Auto-generated method stub
		textField_maDV.setText("");
		textField_tenDV.setText("");
		//textField_dvt.setText("");
		textField_giaDV.setText("");
		textField_maDV.requestFocus();
		
	}
//
//
	private void suaDV() throws RemoteException {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		String ma = textField_maDV.getText();
		String ten = textField_tenDV.getText();
		String dvt = comboBox_dvt.getSelectedItem().toString();
		BigDecimal giadv = new BigDecimal(textField_giaDV.getText());
		String loaidv = comboBox_loaiDV.getSelectedItem().toString();
		LoaiDichVu ldv = new LoaiDichVu(loaidv);
		DichVu dv = new DichVu(ma, ten, dvt, giadv, ldv);
		if(row>=0) {
			if(dv_dao.updateDichVu(dv)) {
				table.setValueAt(textField_tenDV.getText(), row, 1);
				table.setValueAt(comboBox_dvt.getSelectedItem().toString(), row, 2);
				table.setValueAt(textField_giaDV.getText(), row, 3);
				table.setValueAt(comboBox_loaiDV.getSelectedItem().toString(), row,4);
			}
		}
	}
//
	private void xoaDV() throws RemoteException {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		if(row>=0) {
			String maDV = (String) table.getValueAt(row, 0);
			if(dv_dao.deleteDichVu(maDV)) {
				tableModel.removeRow(row);
				xoaTrang();
			}
		}
	}
//
//
	private void themDV() {
		// TODO Auto-generated method stub
		String ma = textField_maDV.getText();
		String ten = textField_tenDV.getText();
		String dvt = comboBox_dvt.getSelectedItem().toString();
		BigDecimal giadv = new BigDecimal(textField_giaDV.getText());
		String loaidv = comboBox_loaiDV.getSelectedItem().toString();
		LoaiDichVu ldv = new LoaiDichVu(loaidv);
		DichVu dv = new DichVu(ma, ten, dvt, giadv, ldv);
		try {
			dv_dao.createDichVu(dv);
			tableModel.addRow(new Object[] {dv.getMaDV(), dv.getTenDV(), dv.getDonViTinh(), dv.getGiaDV(), dv.getLoaiDV().getLoaiDV()});
		}catch(Exception e) {
			JOptionPane.showMessageDialog(this, "Trùng");
			e.printStackTrace();
		}
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		textField_maDV.setText(tableModel.getValueAt(row, 0).toString());
		textField_tenDV.setText(tableModel.getValueAt(row, 1).toString());
		((JComboBox) comboBox_dvt).setSelectedItem(tableModel.getValueAt(row, 2).toString());
		textField_giaDV.setText(tableModel.getValueAt(row, 3).toString());
		((JComboBox) comboBox_loaiDV).setSelectedItem(tableModel.getValueAt(row, 4).toString());
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
