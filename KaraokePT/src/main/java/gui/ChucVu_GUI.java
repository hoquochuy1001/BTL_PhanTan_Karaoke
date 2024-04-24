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


import dao.ChucVuDao;
import entity.ChucVu;
import util.HibernateUtil;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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


	public ChucVu_GUI() {
		cv_dao = new ChucVuDao(HibernateUtil.getSessionFactory());

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setBounds(0, 0, 1650, 1080);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu_menu = new JMenu("Nhân Viên");
		mnNewMenu_menu.setIcon(new ImageIcon(Menu_GUI.class.getResource("../image/user.png")));
		mnNewMenu_menu.setFont(new Font("Segoe UI", Font.PLAIN, 30));
		mnNewMenu_menu.addActionListener(this);
		menuBar.add(mnNewMenu_menu);

		JMenuItem mntmNewMenuItem_upNV = new JMenuItem("Cập Nhập Nhân Viên");
		mntmNewMenuItem_upNV.setIcon(new ImageIcon(Menu_GUI.class.getResource("../image/plus.png")));
		mntmNewMenuItem_upNV.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mntmNewMenuItem_upNV.addActionListener(this);
		mnNewMenu_menu.add(mntmNewMenuItem_upNV);

		JMenuItem mntmNewMenuItem_findNV = new JMenuItem("Tìm Kiếm Nhân Viên");
		mntmNewMenuItem_findNV.setIcon(new ImageIcon(Menu_GUI.class.getResource("../image/search.png")));
		mntmNewMenuItem_findNV.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mntmNewMenuItem_findNV.addActionListener(this);
		mnNewMenu_menu.add(mntmNewMenuItem_findNV);

		JMenuItem mntmNewMenuItem_tkNV = new JMenuItem("Tài Khoản");
		mntmNewMenuItem_tkNV.setIcon(new ImageIcon(Menu_GUI.class.getResource("../image/user4.png")));
		mntmNewMenuItem_tkNV.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mntmNewMenuItem_tkNV.addActionListener(this);
		mnNewMenu_menu.add(mntmNewMenuItem_tkNV);

		JMenuItem mntmNewMenuItem_cvNV = new JMenuItem("Chức Vụ");
		mntmNewMenuItem_cvNV.setIcon(new ImageIcon(Menu_GUI.class.getResource("../images/ic_KH.png")));
		mntmNewMenuItem_cvNV.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mntmNewMenuItem_cvNV.addActionListener(this);
		mnNewMenu_menu.add(mntmNewMenuItem_cvNV);

		JMenu mnNewMenu_kh = new JMenu("Khách Hàng");
		mnNewMenu_kh.setIcon(new ImageIcon(Menu_GUI.class.getResource("../image/users.png")));
		mnNewMenu_kh.setFont(new Font("Segoe UI", Font.PLAIN, 30));
		mnNewMenu_kh.addActionListener(this);
		menuBar.add(mnNewMenu_kh);

		JMenuItem mntmCpNhp_upKH = new JMenuItem("Cập Nhập Khách Hàng");
		mntmCpNhp_upKH.setIcon(new ImageIcon(Menu_GUI.class.getResource("../image/plus.png")));
		mntmCpNhp_upKH.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mntmCpNhp_upKH.addActionListener(this);
		mnNewMenu_kh.add(mntmCpNhp_upKH);

		JMenuItem mntmNewMenuItem_findKH = new JMenuItem("Tìm Kiếm Khách Hàng");
		mntmNewMenuItem_findKH.setIcon(new ImageIcon(Menu_GUI.class.getResource("../image/search.png")));
		mntmNewMenuItem_findKH.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mntmNewMenuItem_findKH.addActionListener(this);
		mnNewMenu_kh.add(mntmNewMenuItem_findKH);

		JMenu mnNewMenu_dv = new JMenu("Dịch Vụ");
		mnNewMenu_dv.setIcon(new ImageIcon(Menu_GUI.class.getResource("../images/ic_DV (5).png")));
		mnNewMenu_dv.setFont(new Font("Segoe UI", Font.PLAIN, 30));
		mnNewMenu_dv.addActionListener(this);
		menuBar.add(mnNewMenu_dv);

		JMenuItem mntmNewMenuItem_upDV = new JMenuItem("Cập Nhập Dịch Vụ");
		mntmNewMenuItem_upDV.setIcon(new ImageIcon(Menu_GUI.class.getResource("../image/plus.png")));
		mntmNewMenuItem_upDV.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mntmNewMenuItem_upDV.addActionListener(this);
		mnNewMenu_dv.add(mntmNewMenuItem_upDV);

		JMenuItem mntmNewMenuItem_findDV = new JMenuItem("Tìm Kiếm Dịch Vụ");
		mntmNewMenuItem_findDV.setIcon(new ImageIcon(Menu_GUI.class.getResource("../image/search.png")));
		mntmNewMenuItem_findDV.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mntmNewMenuItem_findDV.addActionListener(this);
		mnNewMenu_dv.add(mntmNewMenuItem_findDV);

		JMenuItem mntmNewMenuItem_loaiDV = new JMenuItem("Loại Dịch Vụ");
		mntmNewMenuItem_loaiDV.setIcon(new ImageIcon(Menu_GUI.class.getResource("../image/food.png")));
		mntmNewMenuItem_loaiDV.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mntmNewMenuItem_loaiDV.addActionListener(this);
		mnNewMenu_dv.add(mntmNewMenuItem_loaiDV);

		JMenu mnNewMenu_phong = new JMenu("Phòng");
		mnNewMenu_phong.setIcon(new ImageIcon(Menu_GUI.class.getResource("../image/essentials-08.png")));
		mnNewMenu_phong.setFont(new Font("Segoe UI", Font.PLAIN, 30));
		mnNewMenu_phong.addActionListener(this);
		menuBar.add(mnNewMenu_phong);

		JMenuItem mntmNewMenuItem_upPhong = new JMenuItem("Cập Nhập Phòng");
		mntmNewMenuItem_upPhong.setIcon(new ImageIcon(Menu_GUI.class.getResource("../image/plus.png")));
		mntmNewMenuItem_upPhong.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mntmNewMenuItem_upPhong.addActionListener(this);
		mnNewMenu_phong.add(mntmNewMenuItem_upPhong);

		JMenuItem mntmNewMenuItem_upLP = new JMenuItem("Cập Nhập Loại Phòng");
		mntmNewMenuItem_upLP.setIcon(new ImageIcon(Menu_GUI.class.getResource("../image/plus.png")));
		mntmNewMenuItem_upLP.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mntmNewMenuItem_upLP.addActionListener(this);
		mnNewMenu_phong.add(mntmNewMenuItem_upLP);

		JMenuItem mntmNewMenuItem_findPhong = new JMenuItem("Tìm Kiếm Phòng");
		mntmNewMenuItem_findPhong.setIcon(new ImageIcon(Menu_GUI.class.getResource("../image/search.png")));
		mntmNewMenuItem_findPhong.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mntmNewMenuItem_findPhong.addActionListener(this);
		mnNewMenu_phong.add(mntmNewMenuItem_findPhong);

		JMenuItem mntmNewMenuItem_datPhong = new JMenuItem("Đặt Phòng");
		mntmNewMenuItem_datPhong.setIcon(new ImageIcon(Menu_GUI.class.getResource("../image/bell.png")));
		mntmNewMenuItem_datPhong.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mntmNewMenuItem_datPhong.addActionListener(this);
		mnNewMenu_phong.add(mntmNewMenuItem_datPhong);

		JMenu mnNewMenu_hd = new JMenu("Hoá Đơn");
		mnNewMenu_hd.setIcon(new ImageIcon(Menu_GUI.class.getResource("../images/ic_HD.png")));
		mnNewMenu_hd.setFont(new Font("Segoe UI", Font.PLAIN, 30));
		mnNewMenu_hd.addActionListener(this);
		menuBar.add(mnNewMenu_hd);

		JMenuItem mntmNewMenuItem_lapHD = new JMenuItem("Lập Hoá Đơn");
		mntmNewMenuItem_lapHD.setIcon(new ImageIcon(Menu_GUI.class.getResource("../images/ic_ThanhToan.png")));
		mntmNewMenuItem_lapHD.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mntmNewMenuItem_lapHD.addActionListener(this);
		mnNewMenu_hd.add(mntmNewMenuItem_lapHD);

		JMenu mnNewMenu_thongKe = new JMenu("Thống Kê");
		mnNewMenu_thongKe.setIcon(new ImageIcon(Menu_GUI.class.getResource("../images/ic_TK.png")));
		mnNewMenu_thongKe.setFont(new Font("Segoe UI", Font.PLAIN, 30));
		mnNewMenu_thongKe.addActionListener(this);
		menuBar.add(mnNewMenu_thongKe);

		JMenuItem mntmNewMenuItem_tkDoanhThu = new JMenuItem("Thống Kê Doanh Thu");
		mntmNewMenuItem_tkDoanhThu.setIcon(new ImageIcon(Menu_GUI.class.getResource("../images/ic_TK.png")));
		mntmNewMenuItem_tkDoanhThu.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mntmNewMenuItem_tkDoanhThu.addActionListener(this);
		mnNewMenu_thongKe.add(mntmNewMenuItem_tkDoanhThu);



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

	private void DocDuLieuDatabaseVaoTable() {
		List<ChucVu> list = cv_dao.getAllChucVu();
		for (ChucVu s : list) {
			tableModel.addRow(new Object[] { s.getMaCV(), s.getTenCV() });
		}
		table.setModel(tableModel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
//		if (e.getActionCommand().equals("Cập Nhập Nhân Viên")) {
//			dispose();
//            new CapNhapNV_GUI();
//        }
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
//		////////////////////////////////////////////////////////////////////////////
//		if (e.getActionCommand().equals("Cập Nhập Khách Hàng")) {
//			dispose();
//            new CapNhapKH_GUI();
//        }
//		if (e.getActionCommand().equals("Tìm Kiếm Khách Hàng")) {
//			dispose();
//            new TimKiemKH_GUI();
//        }
//		///////////////////////////////////////////////////////////////////////////
//		if (e.getActionCommand().equals("Cập Nhập Dịch Vụ")) {
//			dispose();
//            new CapNhapDV_GUI();
//        }
//		if (e.getActionCommand().equals("Tìm Kiếm Dịch Vụ")) {
//			dispose();
//            new TimKiemDV_GUI();
//        }
//		if (e.getActionCommand().equals("Loại Dịch Vụ")) {
//			dispose();
//            new LoaiDichVu_GUI();
//        }
//		///////////////////////////////////////////////////////////////////////////
//		if (e.getActionCommand().equals("Cập Nhập Phòng")) {
//			dispose();
//            new CapNhapPhong_GUI();
//        }
//		if (e.getActionCommand().equals("Cập Nhập Loại Phòng")) {
//			dispose();
//            new LoaiPhong_GUI();
//        }
//		if (e.getActionCommand().equals("Tìm Kiếm Phòng")) {
//			dispose();
//            new TimKiemPhong_GUI();
//        }
//		if (e.getActionCommand().equals("Đặt Phòng")) {
//			dispose();
//            new DatPhong_GUI();
//        }
//		///////////////////////////////////////////////////////////////////////////
//		if (e.getActionCommand().equals("Lập Hoá Đơn")) {
//			dispose();
//            new LapHoaDon_GUI();
//        }
//		if (e.getActionCommand().equals("Thống Kê Doanh Thu")) {
//			dispose();
//            new ThongKe_GUI();
//        }
		///////////////////////////////////////////////////////////////////////////
		Object o = e.getSource();
		if(o.equals(btnNewButton_them))
			themCV();
		if(o.equals(btnNewButton_xoa))
			xoaCV();
		if(o.equals(btnNewButton_sua))
			suaCV();
		if(o.equals(btnNewButton_xoaTrang))
			xoaTrang();
	}
	private void xoaTrang() {
		textField_maCV.setText("");
		textField_tenCV.setText("");
		textField_maCV.requestFocus();
	}

	private void suaCV() {
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


	private void xoaCV() {
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
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> new ChucVu_GUI());
	}
}
