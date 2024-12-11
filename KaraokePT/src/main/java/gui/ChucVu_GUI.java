package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.*;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JTable;
import javax.swing.JTextField;

import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;


import Server.Config;
import bll.SessionManager;
import entity.ChucVu;
import entity.NhanVien;
import entity.TaiKhoan;
import model.ChucVuDao;
import model.NhanVienDao;
import model.TaiKhoanDao;
import org.hibernate.LazyInitializationException;
import util.HibernateUtil;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.List;
public class ChucVu_GUI extends JFrame implements ActionListener, MouseListener{

	private JPanel contentPane;
	private JTable table;
	private ChucVuDao cv_dao;
	private DefaultTableModel tableModel;
	private JTextField textField_tenCV;
	private Container lblNewLabel_tenCV;
	private JTextField textField_maCV;
	private JButton btnNewButton_sua;
	private JButton btnNewButton_xoaTrang;
	private JButton btnNewButton_xoa;
	private JButton btnNewButton_them;
	private TaiKhoan user;
	private TaiKhoanDao tk_dao;
	private NhanVienDao nv_dao;
	private SessionManager currentUser = SessionManager.getInstance();

	public ChucVu_GUI() throws RemoteException {
		try{
			cv_dao = (ChucVuDao) Naming.lookup(Config.SERVER_URL + "chucVuDao");
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

		JLabel lblNewLabel = new JLabel("CHỨC VỤ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 50));
		lblNewLabel.setBounds(410, 0, 650, 74);
		contentPane.add(lblNewLabel);


		JLabel lblNewLabel_chucVu = new JLabel("Mã chức vụ:");
		lblNewLabel_chucVu.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_chucVu.setBounds(544, 94, 103, 29);
		contentPane.add(lblNewLabel_chucVu);

		JLabel lblNewLabel_tenCV = new JLabel("Tên chức vụ:");
		lblNewLabel_tenCV.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_tenCV.setBounds(544, 153, 103, 29);
		contentPane.add(lblNewLabel_tenCV);

		textField_maCV = new JTextField();
		textField_maCV.setColumns(10);
		textField_maCV.setBounds(642, 98, 183, 26);
		contentPane.add(textField_maCV);

		textField_tenCV = new JTextField();
		textField_tenCV.setColumns(10);
		textField_tenCV.setBounds(642, 157, 183, 26);
		contentPane.add(textField_tenCV);

		 btnNewButton_them = new JButton("Thêm");
		btnNewButton_them.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_them.setBounds(300, 249, 134, 39);
		contentPane.add(btnNewButton_them);

		 btnNewButton_xoa = new JButton("Xoá");
		btnNewButton_xoa.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_xoa.setBounds(530, 249, 134, 39);
		contentPane.add(btnNewButton_xoa);

		 btnNewButton_sua = new JButton("Sửa");
		btnNewButton_sua.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_sua.setBounds(764, 249, 134, 39);
		contentPane.add(btnNewButton_sua);

		 btnNewButton_xoaTrang = new JButton("Xoá Trắng");
		btnNewButton_xoaTrang.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_xoaTrang.setBounds(1008, 249, 134, 39);
		contentPane.add(btnNewButton_xoaTrang);

		String [] headers = {"Mã chức vụ", "Tên chức vụ"};
		tableModel=new DefaultTableModel(headers,0);
		JScrollPane scroll = new JScrollPane();
		scroll.setViewportView(table = new JTable(tableModel));
		table.setRowHeight(25);
		table.setAutoCreateRowSorter(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 331, 1520, 396);
		contentPane.add(scrollPane);

		JLabel lblNewLabel_2 = new JLabel("Danh sách Chức Vụ:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(10, 292, 151, 29);
		contentPane.add(lblNewLabel_2);

		Box horizontalBox = Box.createHorizontalBox();
		horizontalBox.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Th\u00F4ng Tin Ch\u1EE9c V\u1EE5", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		horizontalBox.setBounds(0, 72, 1520, 135);
		contentPane.add(horizontalBox);

		btnNewButton_them.addActionListener(this);
		btnNewButton_xoa.addActionListener(this);
		btnNewButton_sua.addActionListener(this);
		btnNewButton_xoaTrang.addActionListener(this);
		table.addMouseListener(this);
		DocDuLieuDatabaseVaoTable();
		this.setVisible(true);
	}

	private void DocDuLieuDatabaseVaoTable() throws RemoteException {
		List<ChucVu> list = cv_dao.getAllChucVu();
		for (ChucVu s : list) {
			tableModel.addRow(new Object[] { s.getMaCV(), s.getTenCV() });
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
		///////////////////////////////////////////////////////////////////////////
		Object o = e.getSource();
		if(o.equals(btnNewButton_them))
			themCV();
		if(o.equals(btnNewButton_xoa)) {
			try {
				xoaCV();
			} catch (RemoteException ex) {
				throw new RuntimeException(ex);
			}
		}
		if(o.equals(btnNewButton_sua)) {
			try {
				suaCV();
			} catch (RemoteException ex) {
				throw new RuntimeException(ex);
			}
		}
		if(o.equals(btnNewButton_xoaTrang))
			xoaTrang();
	}
	private void xoaTrang() {
		textField_maCV.setText("");
		textField_tenCV.setText("");
		textField_maCV.requestFocus();
	}

	private void suaCV() throws RemoteException {
		int row = table.getSelectedRow();
		String ma = textField_maCV.getText();
		String hoten = textField_tenCV.getText();

		if (row >= 0) {
			// Lấy đối tượng ChucVu hiện tại từ cơ sở dữ liệu
			ChucVu cv = cv_dao.getChucVuByMa(ma);
			if (cv != null) {
				// Cập nhật các trường của đối tượng
				cv.setTenCV(hoten);

				// Cập nhật đối tượng trong cơ sở dữ liệu
				if (cv_dao.updateChucVu(cv)) {
					table.setValueAt(textField_tenCV.getText(), row, 1);
				}
			}
		}
	}


	private void xoaCV() throws RemoteException {
		int row = table.getSelectedRow();
		if (row >= 0) {
			String maCV = (String) table.getValueAt(row, 0);
			if (cv_dao.deleteChucVu(maCV)) {
				tableModel.removeRow(row);
				xoaTrang();
			}
		}
	}

	private void themCV() {
		String ma = textField_maCV.getText();
		String hoten = textField_tenCV.getText();
		ChucVu cv = new ChucVu(ma, hoten);
		try {
			cv_dao.createChucVu(cv);
			tableModel.addRow(new Object[]{cv.getMaCV(), cv.getTenCV()});
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Error");
			e.printStackTrace();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = table.getSelectedRow();
		textField_maCV.setText((String) table.getValueAt(row, 0));
		textField_tenCV.setText((String) table.getValueAt(row, 1));
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}
