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
import javax.swing.AbstractButton;
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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;


import Server.Config;
import entity.ChucVu;
import entity.NhanVien;
import model.ChucVuDao;
import model.NhanVienDao;
import util.HibernateUtil;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class CapNhapNV_GUI extends JFrame implements ActionListener, MouseListener{

	private JPanel contentPane;
	private JTextField textField_maNV;
	private JTextField textField_tenNV;
	private JTextField textField_ngaySinh;
	private JTextField textField_sdt;
	private JTable table;
	private NhanVienDao nv_dao;
	private ChucVuDao cv_dao;
	private DefaultTableModel tableModel;
	private JComboBox comboBox_gioiTinh;
	private JComboBox comboBox_chucVu;
	private JButton btnNewButton_them;
	private JButton btnNewButton_xoa;
	private JButton btnNewButton_sua;
	private JButton btnNewButton_xoaTrang;
	private JTextField txt_timkiemnhanvien;
	
	public CapNhapNV_GUI() throws RemoteException {

		try{
			nv_dao = (NhanVienDao) Naming.lookup(Config.SERVER_URL+"nhanVienDao");
			cv_dao = (ChucVuDao) Naming.lookup(Config.SERVER_URL + "chucVuDao");
		}catch (Exception e){
			JOptionPane.showMessageDialog(this, "Server chưa mở");
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setBounds(0, 0, 1650, 1080);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(255, 255, 255));
		setJMenuBar(menuBar);
		  
		JMenu mnNewMenu_menu = new JMenu("Nhân Viên");
		mnNewMenu_menu.setIcon(new ImageIcon(Menu_GUI.class.getResource("/image/user.png")));
		mnNewMenu_menu.setFont(new Font("Segoe UI", Font.PLAIN, 30));
		mnNewMenu_menu.addActionListener(this);
		menuBar.add(mnNewMenu_menu);
		
		JMenuItem mntmNewMenuItem_upNV = new JMenuItem("Cập Nhập Nhân Viên");
		mntmNewMenuItem_upNV.setIcon(new ImageIcon(Menu_GUI.class.getResource("/image/plus.png")));
		mntmNewMenuItem_upNV.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mntmNewMenuItem_upNV.addActionListener(this);
		mnNewMenu_menu.add(mntmNewMenuItem_upNV);
		
		JMenuItem mntmNewMenuItem_findNV = new JMenuItem("Tìm Kiếm Nhân Viên");
		mntmNewMenuItem_findNV.setIcon(new ImageIcon(Menu_GUI.class.getResource("/image/search.png")));
		mntmNewMenuItem_findNV.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mntmNewMenuItem_findNV.addActionListener(this);
		mnNewMenu_menu.add(mntmNewMenuItem_findNV);
		
		JMenuItem mntmNewMenuItem_tkNV = new JMenuItem("Tài Khoản");
		mntmNewMenuItem_tkNV.setIcon(new ImageIcon(Menu_GUI.class.getResource("/image/user4.png")));
		mntmNewMenuItem_tkNV.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mntmNewMenuItem_tkNV.addActionListener(this);
		mnNewMenu_menu.add(mntmNewMenuItem_tkNV);
		
		JMenuItem mntmNewMenuItem_cvNV = new JMenuItem("Chức Vụ");
		mntmNewMenuItem_cvNV.setIcon(new ImageIcon(Menu_GUI.class.getResource("/images/ic_KH.png")));
		mntmNewMenuItem_cvNV.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mntmNewMenuItem_cvNV.addActionListener(this);
		mnNewMenu_menu.add(mntmNewMenuItem_cvNV);
		
		JMenu mnNewMenu_kh = new JMenu("Khách Hàng");
		mnNewMenu_kh.setIcon(new ImageIcon(Menu_GUI.class.getResource("/image/users.png")));
		mnNewMenu_kh.setFont(new Font("Segoe UI", Font.PLAIN, 30));
		mnNewMenu_kh.addActionListener(this);
		menuBar.add(mnNewMenu_kh);
		
		JMenuItem mntmCpNhp_upKH = new JMenuItem("Cập Nhập Khách Hàng");
		mntmCpNhp_upKH.setIcon(new ImageIcon(Menu_GUI.class.getResource("/image/plus.png")));
		mntmCpNhp_upKH.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mntmCpNhp_upKH.addActionListener(this);
		mnNewMenu_kh.add(mntmCpNhp_upKH);
		
		JMenuItem mntmNewMenuItem_findKH = new JMenuItem("Tìm Kiếm Khách Hàng");
		mntmNewMenuItem_findKH.setIcon(new ImageIcon(Menu_GUI.class.getResource("/image/search.png")));
		mntmNewMenuItem_findKH.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mntmNewMenuItem_findKH.addActionListener(this);
		mnNewMenu_kh.add(mntmNewMenuItem_findKH);
		
		JMenu mnNewMenu_dv = new JMenu("Dịch Vụ");
		mnNewMenu_dv.setIcon(new ImageIcon(Menu_GUI.class.getResource("/images/ic_DV (5).png")));
		mnNewMenu_dv.setFont(new Font("Segoe UI", Font.PLAIN, 30));
		mnNewMenu_dv.addActionListener(this);
		menuBar.add(mnNewMenu_dv);
		
		JMenuItem mntmNewMenuItem_upDV = new JMenuItem("Cập Nhập Dịch Vụ");
		mntmNewMenuItem_upDV.setIcon(new ImageIcon(Menu_GUI.class.getResource("/image/plus.png")));
		mntmNewMenuItem_upDV.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mntmNewMenuItem_upDV.addActionListener(this);
		mnNewMenu_dv.add(mntmNewMenuItem_upDV);
		
		JMenuItem mntmNewMenuItem_findDV = new JMenuItem("Tìm Kiếm Dịch Vụ");
		mntmNewMenuItem_findDV.setIcon(new ImageIcon(Menu_GUI.class.getResource("/image/search.png")));
		mntmNewMenuItem_findDV.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mntmNewMenuItem_findDV.addActionListener(this);
		mnNewMenu_dv.add(mntmNewMenuItem_findDV);
		
		JMenuItem mntmNewMenuItem_loaiDV = new JMenuItem("Loại Dịch Vụ");
		mntmNewMenuItem_loaiDV.setIcon(new ImageIcon(Menu_GUI.class.getResource("/image/food.png")));
		mntmNewMenuItem_loaiDV.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mntmNewMenuItem_loaiDV.addActionListener(this);
		mnNewMenu_dv.add(mntmNewMenuItem_loaiDV);
		
		JMenu mnNewMenu_phong = new JMenu("Phòng");
		mnNewMenu_phong.setIcon(new ImageIcon(Menu_GUI.class.getResource("/image/essentials-08.png")));
		mnNewMenu_phong.setFont(new Font("Segoe UI", Font.PLAIN, 30));
		mnNewMenu_phong.addActionListener(this);
		menuBar.add(mnNewMenu_phong);
		
		JMenuItem mntmNewMenuItem_upPhong = new JMenuItem("Cập Nhập Phòng");
		mntmNewMenuItem_upPhong.setIcon(new ImageIcon(Menu_GUI.class.getResource("/image/plus.png")));
		mntmNewMenuItem_upPhong.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mntmNewMenuItem_upPhong.addActionListener(this);
		mnNewMenu_phong.add(mntmNewMenuItem_upPhong);
		
		JMenuItem mntmNewMenuItem_upLP = new JMenuItem("Cập Nhập Loại Phòng");
		mntmNewMenuItem_upLP.setIcon(new ImageIcon(Menu_GUI.class.getResource("/image/plus.png")));
		mntmNewMenuItem_upLP.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mntmNewMenuItem_upLP.addActionListener(this);
		mnNewMenu_phong.add(mntmNewMenuItem_upLP);
		
		JMenuItem mntmNewMenuItem_findPhong = new JMenuItem("Tìm Kiếm Phòng");
		mntmNewMenuItem_findPhong.setIcon(new ImageIcon(Menu_GUI.class.getResource("/image/search.png")));
		mntmNewMenuItem_findPhong.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mntmNewMenuItem_findPhong.addActionListener(this);
		mnNewMenu_phong.add(mntmNewMenuItem_findPhong);
		
		JMenuItem mntmNewMenuItem_datPhong = new JMenuItem("Đặt Phòng");
		mntmNewMenuItem_datPhong.setIcon(new ImageIcon(Menu_GUI.class.getResource("/image/bell.png")));
		mntmNewMenuItem_datPhong.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mntmNewMenuItem_datPhong.addActionListener(this);
		mnNewMenu_phong.add(mntmNewMenuItem_datPhong);
		
		JMenu mnNewMenu_hd = new JMenu("Hoá Đơn");
		mnNewMenu_hd.setIcon(new ImageIcon(Menu_GUI.class.getResource("/images/ic_HD.png")));
		mnNewMenu_hd.setFont(new Font("Segoe UI", Font.PLAIN, 30));
		mnNewMenu_hd.addActionListener(this);
		menuBar.add(mnNewMenu_hd);
		
		JMenuItem mntmNewMenuItem_lapHD = new JMenuItem("Lập Hoá Đơn");
		mntmNewMenuItem_lapHD.setIcon(new ImageIcon(Menu_GUI.class.getResource("/images/ic_ThanhToan.png")));
		mntmNewMenuItem_lapHD.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mntmNewMenuItem_lapHD.addActionListener(this);
		mnNewMenu_hd.add(mntmNewMenuItem_lapHD);
		
		
		JMenu mnNewMenu_thongKe = new JMenu("Thống Kê");
		mnNewMenu_thongKe.setIcon(new ImageIcon(Menu_GUI.class.getResource("/images/ic_TK.png")));
		mnNewMenu_thongKe.setFont(new Font("Segoe UI", Font.PLAIN, 30));
		mnNewMenu_thongKe.addActionListener(this);
		menuBar.add(mnNewMenu_thongKe);
		
		JMenuItem mntmNewMenuItem_tkDoanhThu = new JMenuItem("Thống Kê Doanh Thu");
		mntmNewMenuItem_tkDoanhThu.setIcon(new ImageIcon(Menu_GUI.class.getResource("/images/ic_TK.png")));
		mntmNewMenuItem_tkDoanhThu.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mntmNewMenuItem_tkDoanhThu.addActionListener(this);
		mnNewMenu_thongKe.add(mntmNewMenuItem_tkDoanhThu);
		
		
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CẬP NHẬT NHÂN VIÊN");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 50));
		lblNewLabel.setBounds(426, 0, 650, 74);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_maNV = new JLabel("Mã NV:");
		lblNewLabel_maNV.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_maNV.setBounds(587, 118, 58, 29);
		contentPane.add(lblNewLabel_maNV);
		
		JLabel lblNewLabel_tenNV = new JLabel("Tên NV:");
		lblNewLabel_tenNV.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_tenNV.setBounds(587, 172, 58, 29);
		contentPane.add(lblNewLabel_tenNV);
		
		JLabel lblNewLabel_ngaySinh = new JLabel("Ngày Sinh:");
		lblNewLabel_ngaySinh.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_ngaySinh.setBounds(860, 118, 74, 29);
		contentPane.add(lblNewLabel_ngaySinh);
		
		JLabel lblNewLabel_sdt = new JLabel("SĐT:");
		lblNewLabel_sdt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_sdt.setBounds(860, 172, 74, 29);
		contentPane.add(lblNewLabel_sdt);
		
		JLabel lblNewLabel_gioiTinh = new JLabel("Giới Tính:");
		lblNewLabel_gioiTinh.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_gioiTinh.setBounds(1169, 118, 74, 29);
		contentPane.add(lblNewLabel_gioiTinh);
		
		JLabel lblNewLabel_chucVu = new JLabel("Chức Vụ:");
		lblNewLabel_chucVu.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_chucVu.setBounds(1169, 172, 74, 29);
		contentPane.add(lblNewLabel_chucVu);
		
		textField_maNV = new JTextField();
		textField_maNV.setBounds(655, 122, 146, 26);
		contentPane.add(textField_maNV);
		textField_maNV.setColumns(10);
		
		textField_tenNV = new JTextField();
		textField_tenNV.setBounds(655, 176, 146, 26);
		contentPane.add(textField_tenNV);
		textField_tenNV.setColumns(10);
		
		textField_ngaySinh = new JTextField();
		textField_ngaySinh.setColumns(10);
		textField_ngaySinh.setBounds(944, 122, 146, 26);
		contentPane.add(textField_ngaySinh);
		
		textField_sdt = new JTextField();
		textField_sdt.setColumns(10);
		textField_sdt.setBounds(944, 176, 146, 26);
		contentPane.add(textField_sdt);
		
		comboBox_gioiTinh = new JComboBox();
		comboBox_gioiTinh.setEditable(true);
		comboBox_gioiTinh.addItem("Nam");
		comboBox_gioiTinh.addItem("Nữ");
//		for(NhanVien nv : nv_dao.getalltbNhanVien()) {
//			comboBox_gioiTinh.addItem(nv.getGioiTinh());
//		}
		comboBox_gioiTinh.setBounds(1253, 120, 146, 29);
		contentPane.add(comboBox_gioiTinh);
		
		comboBox_chucVu = new JComboBox();
		comboBox_chucVu.setEditable(true);
		for(ChucVu cv : cv_dao.getAllChucVu()) {
			comboBox_chucVu.addItem(cv.getMaCV());
		}
		comboBox_chucVu.setBounds(1253, 174, 146, 29);
		contentPane.add(comboBox_chucVu);
		
		 btnNewButton_them = new JButton("Thêm");
		btnNewButton_them.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_them.setBounds(724, 236, 134, 39);
		contentPane.add(btnNewButton_them);
		
		 btnNewButton_xoa = new JButton("Xoá");
		btnNewButton_xoa.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_xoa.setBounds(906, 236, 134, 39);
		contentPane.add(btnNewButton_xoa);
		
		 btnNewButton_sua = new JButton("Sửa");
		btnNewButton_sua.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_sua.setBounds(1084, 236, 134, 39);
		contentPane.add(btnNewButton_sua);
		
		btnNewButton_xoaTrang = new JButton("Xoá Trắng");
		btnNewButton_xoaTrang.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_xoaTrang.setBounds(1265, 236, 134, 39);
		contentPane.add(btnNewButton_xoaTrang);
		
		
		String [] headers = {"Mã NV", "Tên NV","Ngày sinh","SĐT", "Giới tính",   "Chức Vụ"};
		tableModel=new DefaultTableModel(headers,0);
		JScrollPane scroll = new JScrollPane();
		scroll.setViewportView(table = new JTable(tableModel));
		table.setRowHeight(25);
		table.setAutoCreateRowSorter(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(314, 331, 1216, 391);
		contentPane.add(scrollPane);
		
		JLabel lblNewLabel_2 = new JLabel("Danh sách Nhân Viên:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2.setBounds(314, 300, 188, 29);
		contentPane.add(lblNewLabel_2);
		
		Box horizontalBox = Box.createHorizontalBox();
		horizontalBox.setBorder(new TitledBorder(null, "Thông Tin Nhân Viên", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		horizontalBox.setBounds(314, 103, 1216, 135);
		contentPane.add(horizontalBox);
		
		JLabel lblNewLabel_maNV_1 = new JLabel("Tìm Kiếm Nhân Viên");
		lblNewLabel_maNV_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_maNV_1.setBounds(10, 0, 188, 29);
		contentPane.add(lblNewLabel_maNV_1);
		
		txt_timkiemnhanvien = new JTextField();
		txt_timkiemnhanvien.setForeground(Color.GRAY);
		txt_timkiemnhanvien.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txt_timkiemnhanvien.getText().equals("Mã, Tên, Điện thoại")) {
					txt_timkiemnhanvien.setText("");
					txt_timkiemnhanvien.setForeground(new Color(153,153,153));
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(txt_timkiemnhanvien.getText().equals("")) {
					txt_timkiemnhanvien.setText("Mã, Tên, Điện thoại");
					txt_timkiemnhanvien.setForeground(new Color(153,153,153));
				}
			}
		});
		txt_timkiemnhanvien.setText("Mã, Tên, Điện thoại");
		txt_timkiemnhanvien.setColumns(10);
		txt_timkiemnhanvien.setBounds(10, 28, 236, 26);
		contentPane.add(txt_timkiemnhanvien);
		
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
		List<NhanVien> list = nv_dao.getAllNhanVien();
		for (NhanVien nv : list) {
			Date date = Date.valueOf(nv.getNgaySinh());
			tableModel.addRow(new Object[] {nv.getMaNV(), nv.getTenNV(), new SimpleDateFormat("dd-MM-yyyy").format(date),
					nv.getSdt(), nv.getGioiTinh(), nv.getMaCV().getMaCV()});
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
//		if (e.getActionCommand().equals("Tài Khoản")) {
//			dispose();
//            new TaiKhoan_GUI();
//        }
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
//		if (e.getActionCommand().equals("Thống Kê Doanh Thu")) {
//			dispose();
//            new ThongKe_GUI();
//        }
		//////////////////////////////////////////////////////////////////////////
		Object o = e.getSource();
		if(o.equals(btnNewButton_them)) {
			try {
				themNV();
			} catch (RemoteException ex) {
				throw new RuntimeException(ex);
			}
		}
		if(o.equals(btnNewButton_xoa)) {
			try {
				xoaNV();
			} catch (RemoteException ex) {
				throw new RuntimeException(ex);
			}
		}
		if(o.equals(btnNewButton_sua)) {
			try {
				suaNV();
			} catch (RemoteException ex) {
				throw new RuntimeException(ex);
			}
		}
		if(o.equals(btnNewButton_xoaTrang))
			xoaTrang();
	}


	private void xoaTrang() {
		// TODO Auto-generated method stub
		textField_maNV.setText("");
		textField_tenNV.setText("");
		textField_ngaySinh.setText("");
		textField_sdt.setText("");
		textField_maNV.requestFocus();
	}


	private void suaNV() throws RemoteException {
		int row = table.getSelectedRow();

		String ma = textField_maNV.getText();
		String hoten = textField_tenNV.getText();

		String ngaysinh = textField_ngaySinh.getText().trim();
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate lcd = LocalDate.parse(ngaysinh, fmt);
		LocalDate ngaysinh1 = LocalDate.parse(ngaysinh, fmt);

		String sdt = textField_sdt.getText();
		String gioitinh = comboBox_gioiTinh.getSelectedItem().toString();
		String chucvu = comboBox_chucVu.getSelectedItem().toString();
		ChucVu cv = cv_dao.getChucVuByMa(chucvu);
		NhanVien nv = new NhanVien(ma, hoten, ngaysinh1, sdt, gioitinh, cv);
		if(row>=0) {
			if(nv_dao.updateNhanVien(nv)) {
				table.setValueAt(textField_tenNV.getText(), row, 1);
				table.setValueAt(textField_ngaySinh.getText(), row, 2);
				table.setValueAt(textField_sdt.getText(), row, 3);
				table.setValueAt(comboBox_gioiTinh.getSelectedItem().toString(), row, 4);
				table.setValueAt(comboBox_chucVu.getSelectedItem().toString(), row, 5);
			}
		}
	}

	private void themNV() throws RemoteException {
		String ma = textField_maNV.getText();
		String hoten = textField_tenNV.getText();

		String ngaysinh = textField_ngaySinh.getText().trim();
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate lcd = LocalDate.parse(ngaysinh, fmt);
		Date ngaysinh1 = Date.valueOf(lcd);

		String sdt = textField_sdt.getText();
		String gioitinh = comboBox_gioiTinh.getSelectedItem().toString();
		String chucvu = comboBox_chucVu.getSelectedItem().toString();
		ChucVu cv = cv_dao.getChucVuByMa(chucvu);
		NhanVien nv = new NhanVien(ma, hoten, ngaysinh1.toLocalDate(), sdt, gioitinh, cv);
		if (nv_dao.createNhanVien(nv)) {
			LocalDate localDate = nv.getNgaySinh();
			java.util.Date date = java.util.Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
			tableModel.addRow(new Object[] {nv.getMaNV(), nv.getTenNV(), new SimpleDateFormat("dd-MM-yyyy").format(date),
					nv.getSdt(), nv.getGioiTinh(), nv.getMaCV().getMaCV()});
		} else {
			JOptionPane.showMessageDialog(this, "Không thể tạo nhân viên mới");
		}

	}
	private void xoaNV() throws RemoteException {
		int row = table.getSelectedRow();
		int luaChon = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa Nhân viên này không?");
		if(luaChon == JOptionPane.YES_OPTION) {
			String maNV = (String) table.getValueAt(row, 0);
			if(nv_dao.deleteNhanVien(maNV)) {
				tableModel.removeRow(row);
				xoaTrang();
			}
		}
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		textField_maNV.setText(tableModel.getValueAt(row, 0).toString());
		textField_tenNV.setText(tableModel.getValueAt(row, 1).toString());
		textField_ngaySinh.setText(tableModel.getValueAt(row, 2).toString());
		textField_sdt.setText(tableModel.getValueAt(row, 3).toString());
		((JComboBox) comboBox_gioiTinh).setSelectedItem(tableModel.getValueAt(row, 4).toString());
		((JComboBox) comboBox_chucVu).setSelectedItem(tableModel.getValueAt(row, 5).toString());
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
