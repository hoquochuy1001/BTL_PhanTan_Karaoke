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
import entity.ChucVu;
import entity.KhachHang;
import entity.NhanVien;
import model.KhachHangDao;
import util.HibernateUtil;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.List;

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

	
	public CapNhapKH_GUI() throws RemoteException {

		try{

			kh_dao = (KhachHangDao) Naming.lookup(Config.SERVER_URL + "khachHangDao");
		}catch (Exception e){
			JOptionPane.showMessageDialog(this, "Server chưa mở");
		}

		
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
		
		JLabel lblNewLabel = new JLabel("CẬP NHẬT KHÁCH HÀNG");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 50));
		lblNewLabel.setBounds(442, 10, 650, 74);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_maKH = new JLabel("Mã KH:");
		lblNewLabel_maKH.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_maKH.setBounds(369, 118, 58, 29);
		contentPane.add(lblNewLabel_maKH);
		
		JLabel lblNewLabel_tenKH = new JLabel("Tên KH:");
		lblNewLabel_tenKH.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_tenKH.setBounds(369, 172, 58, 29);
		contentPane.add(lblNewLabel_tenKH);
		
		JLabel lblNewLabel_cmnd = new JLabel("CMND:");
		lblNewLabel_cmnd.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_cmnd.setBounds(878, 118, 74, 29);
		contentPane.add(lblNewLabel_cmnd);
		
		JLabel lblNewLabel_sdt = new JLabel("SĐT:");
		lblNewLabel_sdt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_sdt.setBounds(878, 172, 74, 29);
		contentPane.add(lblNewLabel_sdt);
		
		textField_maKH = new JTextField();
		textField_maKH.setBounds(437, 118, 146, 26);
		textField_maKH.setColumns(10);
		contentPane.add(textField_maKH);
		
		textField_tenKH = new JTextField();
		textField_tenKH.setBounds(437, 176, 146, 26);
		textField_tenKH.setColumns(10);
		contentPane.add(textField_tenKH);
		
		textField_cmnd = new JTextField();
		textField_cmnd.setColumns(10);
		textField_cmnd.setBounds(932, 122, 146, 26);
		contentPane.add(textField_cmnd);
		
		textField_sdt = new JTextField();
		textField_sdt.setColumns(10);
		textField_sdt.setBounds(932, 176, 146, 26);
		contentPane.add(textField_sdt);
		
		btnNewButton_them = new JButton("Thêm");
		btnNewButton_them.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_them.setBounds(352, 236, 134, 39);
		contentPane.add(btnNewButton_them);
		
		btnNewButton_xoa = new JButton("Xoá");
		btnNewButton_xoa.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_xoa.setBounds(594, 236, 134, 39);
		contentPane.add(btnNewButton_xoa);
		
		 btnNewButton_sua = new JButton("Sửa");
		btnNewButton_sua.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_sua.setBounds(818, 236, 134, 39);
		contentPane.add(btnNewButton_sua);
		
		 btnNewButton_xoaTrang = new JButton("Xoá Trắng");
		btnNewButton_xoaTrang.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_xoaTrang.setBounds(1054, 236, 134, 39);
		contentPane.add(btnNewButton_xoaTrang);
		
		String [] headers = {"Mã KH", "Tên KH", "CMND", "SĐT"};
		tableModel=new DefaultTableModel(headers,0);
		JScrollPane scroll = new JScrollPane();
		scroll.setViewportView(table = new JTable(tableModel));
		table.setRowHeight(25);
		table.setAutoCreateRowSorter(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 331, 1520, 391);
		contentPane.add(scrollPane);
		
		JLabel lblNewLabel_2 = new JLabel("Danh sách Khách Hàng:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(10, 292, 175, 29);
		contentPane.add(lblNewLabel_2);
		
		Box horizontalBox = Box.createHorizontalBox();
		horizontalBox.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Th\u00F4ng Tin Kh\u00E1ch H\u00E0ng", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		horizontalBox.setBounds(10, 91, 1520, 135);
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
