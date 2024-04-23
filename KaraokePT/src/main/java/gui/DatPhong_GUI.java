package gui;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;
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
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
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


import dao.KhachHangDao;

import dao.NhanVienDao;

import dao.PhieuDatPhongDao;

import dao.PhongDao;


import entity.KhachHang;
import entity.LoaiPhong;
import entity.NhanVien;
import entity.Phong;
import entity.PhieuDatPhong;
import util.HibernateUtil;

public class DatPhong_GUI extends JFrame implements ActionListener, MouseListener{

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
	private JButton btnNewButton_load;
	

	
	public DatPhong_GUI() {

		kh_dao = new KhachHangDao(HibernateUtil.getSessionFactory());
		nv_dao = new NhanVienDao(HibernateUtil.getSessionFactory());
		phong_dao = new PhongDao(HibernateUtil.getSessionFactory());
		pdp_dao = new PhieuDatPhongDao(HibernateUtil.getSessionFactory());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setBounds(0, 0, 1650, 1080);
		
		JMenuBar menuBar = new JMenuBar();
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
		
		JLabel lblNewLabel = new JLabel("ĐẶT PHÒNG");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 50));
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
		horizontalBox_2.setBounds(509, 84, 503, 315);
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
		
		
		Box horizontalBox_3 = Box.createHorizontalBox();
		horizontalBox_3.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Danh s\u00E1ch nh\u00E2n vi\u00EAn ph\u1EE5c v\u1EE5", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		horizontalBox_3.setBounds(1008, 84, 522, 315);
		contentPane.add(horizontalBox_3);
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
		
		
		btnNewButton_DP = new JButton("Đặt Phòng");
		btnNewButton_DP.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_DP.setBounds(509, 409, 157, 59);
		contentPane.add(btnNewButton_DP);
		
		
		Box horizontalBox_4 = Box.createHorizontalBox();
		horizontalBox_4.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Danh s\u00E1ch ph\u00F2ng \u0111\u00E3 \u0111\u1EB7t", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		horizontalBox_4.setBounds(10, 475, 1520, 255);
		contentPane.add(horizontalBox_4);
		//
		String [] headers4 = {"Mã Phiếu Đặt Phòng","Tên Phòng", "Loại Phòng", "Giá Phòng", "Thời gian Đặt Phòng","Tên Khách Hàng","Tên Nhân Viên"};
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
		btnNewButton_load.setBounds(855, 409, 157, 59);
		contentPane.add(btnNewButton_load);
		
		btnNewButton_DP.addActionListener(this);
		btnNewButton_load.addActionListener(this);
		
		table_1.addMouseListener(this);
		table_2.addMouseListener(this);
		table_3.addMouseListener(this);
		table_4.addMouseListener(this);
		
		DocDuLieuDatabaseVaoTable1();
		DocDuLieuDatabaseVaoTable2();
		DocDuLieuDatabaseVaoTable3();
		DocDuLieuDatabaseVaoTable4();
		this.setVisible(true);
	}

	
	
	private void DocDuLieuDatabaseVaoTable4() {
		// TODO Auto-generated method stub
		List<PhieuDatPhong> list = pdp_dao.getAllPhieuDatPhong();
		for(PhieuDatPhong pdp : list) {
			String[] rowData = {pdp.getId()+"",pdp.getMaPhong().getTenPhong(),pdp.getMaPhong().getLoaiPhong().getLoaiPhong()
					,pdp.getMaPhong().getGiaPhong()+"",pdp.getTgDatPhong().toString()
					,pdp.getMaKH().getMaKH(),pdp.getMaNV().getMaNV()};

			tableModel4.addRow(rowData);
		}
		table_4.setModel(tableModel4);
	}



	private void DocDuLieuDatabaseVaoTable3() {

		List<NhanVien> list = nv_dao.getNhanVienExceptAdmin();
		for(NhanVien s : list) {
			String[] rowData = {s.getMaNV(), s.getTenNV(), s.getSdt()};
			tableModel3.addRow(rowData);
		}
		table_3.setModel(tableModel3);
	}

	private void DocDuLieuDatabaseVaoTable2() {
		// TODO Auto-generated method stub

		List<Phong> list = phong_dao.getAllPhong();
		for(Phong s : list) {
			String[] rowData = {s.getMaPhong(), s.getTenPhong(),s.getLoaiPhong().getLoaiPhong(),s.getTinhTrang()};
			tableModel2.addRow(rowData);
		}
		table_2.setModel(tableModel2);
	}

	private void DocDuLieuDatabaseVaoTable1() {
		// TODO Auto-generated method stub

		List<KhachHang> list = kh_dao.getAllKhachHang();
		for(KhachHang s : list) {
			String[] rowData = {s.getMaKH(), s.getTenKH(),s.getSdt()};
			tableModel1.addRow(rowData);
			}
		table_1.setModel(tableModel1);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("Cập Nhập Nhân Viên")) {
			dispose();
            new CapNhapNV_GUI();
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
            new ChucVu_GUI();
        }
		////////////////////////////////////////////////////////////////////////////
		if (e.getActionCommand().equals("Cập Nhập Khách Hàng")) {
			dispose();
            new CapNhapKH_GUI();
        }
//		if (e.getActionCommand().equals("Tìm Kiếm Khách Hàng")) {
//			dispose();
//            new TimKiemKH_GUI();
//        }
		///////////////////////////////////////////////////////////////////////////
		if (e.getActionCommand().equals("Cập Nhập Dịch Vụ")) {
			dispose();
            new CapNhapDV_GUI();
        }
//		if (e.getActionCommand().equals("Tìm Kiếm Dịch Vụ")) {
//			dispose();
//            new TimKiemDV_GUI();
//        }
		if (e.getActionCommand().equals("Loại Dịch Vụ")) {
			dispose();
            new LoaiDichVu_GUI();
        }
		///////////////////////////////////////////////////////////////////////////
		if (e.getActionCommand().equals("Cập Nhập Phòng")) {
			dispose();
            new CapNhapPhong_GUI();
        }
		if (e.getActionCommand().equals("Cập Nhập Loại Phòng")) {
			dispose();
            new LoaiPhong_GUI();
        }
//		if (e.getActionCommand().equals("Tìm Kiếm Phòng")) {
//			dispose();
//            new TimKiemPhong_GUI();
//        }
		if (e.getActionCommand().equals("Đặt Phòng")) {
			dispose();
            new DatPhong_GUI();
        }
		///////////////////////////////////////////////////////////////////////////
//		if (e.getActionCommand().equals("Lập Hoá Đơn")) {
//			dispose();
//            new LapHoaDon_GUI();
//        }
//		if (e.getActionCommand().equals("Thống Kê Doanh Thu")) {
//			dispose();
//            new ThongKe_GUI();
//        }
		//////////////////////////////////////////////////////////////////////////
		Object o = e.getSource();
		if(o.equals(btnNewButton_DP))
			datPhong();
		if(o.equals(btnNewButton_load))
			tailai();
	}
	private void tailai() {
		// TODO Auto-generated method stub
		tableModel4.setRowCount(0); 
		tableModel2.setRowCount(0);

		List<PhieuDatPhong> list = pdp_dao.getAllPhieuDatPhong();
		for(PhieuDatPhong pdp : list) {
			String[] rowData = {pdp.getId()+"",pdp.getMaPhong().getTenPhong(),pdp.getMaPhong().getLoaiPhong().getLoaiPhong()
					,pdp.getMaPhong().getGiaPhong()+"",pdp.getTgDatPhong().toString()
					,pdp.getMaKH().getMaKH(),pdp.getMaNV().getMaNV()};
					
			tableModel4.addRow(rowData);
		}
		table_4.setModel(tableModel4);
		

		List<Phong> list1 = phong_dao.getAllPhong();
		for(Phong s : list1) {
			String[] rowData = {s.getMaPhong(), s.getTenPhong(),s.getLoaiPhong().getLoaiPhong(),s.getTinhTrang()};
			tableModel2.addRow(rowData);
		}
		table_2.setModel(tableModel2);
	}

	private void datPhong(){
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
		PhieuDatPhong pdp = new PhieuDatPhong();
		pdp.setMaKH(kh_dao.getKhachHangByMaKhachHang(maKH));
		pdp.setMaPhong(phong_dao.getPhongByLoaiPhong(maPhong));
		pdp.setMaNV(nv_dao.getNhanVienByChucVu(maNV));
		pdp.setTgDatPhong(Timestamp.from(Instant.now()));
		if(pdp_dao.createPhieuDatPhong(pdp)) {
			JOptionPane.showMessageDialog(this, "Đặt phòng thành công");
			tailai();
		}
		else {
			JOptionPane.showMessageDialog(this, "Đặt phòng thất bại");
		}
		phong_dao.updateTrangThaiDP(maPhong);
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
