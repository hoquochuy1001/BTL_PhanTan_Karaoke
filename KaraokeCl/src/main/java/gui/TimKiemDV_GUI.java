package gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

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
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.Component;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.DichVuDao;
import entity.DichVu;
import entity.LoaiDichVu;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import util.HibernateUtil;

public class TimKiemDV_GUI extends JFrame implements ActionListener, MouseListener {

	private JPanel contentPane;
	private JTextField textField_maDV;
	private JTextField textField_tenDV;
	private JTable table;
	private DichVuDao dv_dao;
	private DefaultTableModel tableModel;
	private JComboBox comboBox_loaiDV;
	private JButton btnNewButton_tailai;
	private JButton btnNewButton_xoaTrang;
	private JButton btnNewButton_find;
	private JComboBox comboBox_dvt;
	private Component btnNewButton;

	public TimKiemDV_GUI() {
		dv_dao = new DichVuDao(HibernateUtil.getSessionFactory());

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

		JLabel lblNewLabel = new JLabel("TÌM KIẾM DỊCH VỤ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 50));
		lblNewLabel.setBounds(427, 7, 650, 74);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_maDV = new JLabel("Mã DV:");
		lblNewLabel_maDV.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_maDV.setBounds(523, 118, 58, 29);
		contentPane.add(lblNewLabel_maDV);

		JLabel lblNewLabel_tenDV = new JLabel("Tên DV:");
		lblNewLabel_tenDV.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_tenDV.setBounds(523, 172, 58, 29);
		contentPane.add(lblNewLabel_tenDV);

		JLabel lblNewLabel_dvt = new JLabel("Đơn Vị Tính:");
		lblNewLabel_dvt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_dvt.setBounds(895, 172, 87, 29);
		contentPane.add(lblNewLabel_dvt);

		JLabel lblNewLabel_loaiDV = new JLabel("Loại DV:");
		lblNewLabel_loaiDV.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_loaiDV.setBounds(895, 118, 74, 29);
		contentPane.add(lblNewLabel_loaiDV);

		textField_maDV = new JTextField();
		textField_maDV.setBounds(577, 122, 146, 26);
		contentPane.add(textField_maDV);
		textField_maDV.setColumns(10);

		textField_tenDV = new JTextField();
		textField_tenDV.setBounds(577, 176, 146, 26);
		contentPane.add(textField_tenDV);
		textField_tenDV.setColumns(10);

		comboBox_loaiDV = new JComboBox();
		comboBox_loaiDV.setEditable(true);
		comboBox_loaiDV.addItem("-Chọn-");
		for (DichVu dv : dv_dao.getAllDichVu()) {
			comboBox_loaiDV.addItem(dv.getLoaiDV());
		}

		comboBox_loaiDV.setBounds(989, 120, 146, 29);
		contentPane.add(comboBox_loaiDV);

		btnNewButton_find = new JButton("Tìm Kiếm");
		btnNewButton_find.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_find.setBounds(551, 236, 134, 39);
		contentPane.add(btnNewButton_find);

		String[] headers = { "Mã DV", "Tên DV", "Đơn Vị Tính", "Loại Dịch Vụ" };
		tableModel = new DefaultTableModel(headers, 0);
		JScrollPane scroll = new JScrollPane();
		scroll.setViewportView(table = new JTable(tableModel));
		table.setRowHeight(25);
		table.setAutoCreateRowSorter(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 331, 1520, 397);
		contentPane.add(scrollPane);

		JLabel lblNewLabel_2 = new JLabel("Danh sách Dịch Vụ:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(10, 292, 151, 29);
		contentPane.add(lblNewLabel_2);

		btnNewButton_tailai = new JButton("Tải Lại");
		btnNewButton_tailai.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_tailai.setBounds(755, 236, 134, 39);
		contentPane.add(btnNewButton_tailai);

		btnNewButton_xoaTrang = new JButton("Xóa Trắng");
		btnNewButton_xoaTrang.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_xoaTrang.setBounds(977, 236, 134, 39);
		contentPane.add(btnNewButton_xoaTrang);

		comboBox_dvt = new JComboBox();
		comboBox_dvt.setEditable(true);
		comboBox_dvt.addItem("-Chọn-");
		comboBox_dvt.addItem("Bịch");
		comboBox_dvt.addItem("Lon");
		comboBox_dvt.addItem("Điếu");
		comboBox_dvt.addItem("Gói");
		comboBox_dvt.setBounds(989, 174, 146, 29);
		contentPane.add(comboBox_dvt);

		btnNewButton_find.addActionListener(this);
		btnNewButton_tailai.addActionListener(this);
		btnNewButton_xoaTrang.addActionListener(this);
		table.addMouseListener(this);
		DocDuLieuDatabaseVaoTable();
		this.setVisible(true);
	}

	private void DocDuLieuDatabaseVaoTable() {
		// TODO Auto-generated method stub
		List<DichVu> list = dv_dao.getAllDichVu();
		for (DichVu s : list) {
			LoaiDichVu loaiDV = s.getLoaiDV();
			String loaiDVStr = (loaiDV != null) ? loaiDV.getLoaiDV() : "N/A";
			String[] rowData = { s.getMaDV(), s.getTenDV(), s.getDonViTinh(), s.getGiaDV() + "", loaiDVStr };
			tableModel.addRow(rowData);
		}

		table.setModel(tableModel);
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
		if (e.getActionCommand().equals("Tìm Kiếm Dịch Vụ")) {
			dispose();
			new TimKiemDV_GUI();
		}
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
		if (e.getActionCommand().equals("Lập Hoá Đơn")) {
			dispose();
			new LapHoaDon_GUI();
		}
//		if (e.getActionCommand().equals("Thống Kê Doanh Thu")) {
//			dispose();
//            new ThongKe_GUI();
//        }
		///////////////////////////////////////////////////////////////////////

		Object o = e.getSource();
		if (o.equals(btnNewButton_find))
			timDV();
		if (o.equals(btnNewButton_tailai))
			tailai();
		if (o.equals(btnNewButton_xoaTrang))
			xoaTrang();
	}

	private void xoaTrang() {
		// TODO Auto-generated method stub
		textField_maDV.setText("");
		textField_tenDV.setText("");
		textField_maDV.requestFocus();
	}

	private void tailai() {
		// TODO Auto-generated method stub
		tableModel.setRowCount(0);
		List<DichVu> list = dv_dao.getAllDichVu();
		for (DichVu s : list) {
			LoaiDichVu loaiDV = s.getLoaiDV();
			String loaiDVStr = (loaiDV != null) ? loaiDV.getLoaiDV() : "N/A";
			String[] rowData = { s.getMaDV(), s.getTenDV(), s.getDonViTinh(), s.getGiaDV() + "", loaiDVStr };
			tableModel.addRow(rowData);
		}

		table.setModel(tableModel);
	}

//	private void timDV() {
//		// TODO Auto-generated method stub
//		ResultSet rs = null;
//		Connection connection = null;
//		Statement  st = null;
//		String ma = textField_maDV.getText();
//		String ten = textField_tenDV.getText();
//		String dvt = comboBox_dvt.getSelectedItem().toString();
//		String loaidv = comboBox_loaiDV.getSelectedItem().toString();
//		 try {
//	        	String sql = "select * from DichVu ";
//	          
//	        	String chon = "-Chọn-";
//	        	
//	        	if(ma.length()>0) {
//	            	sql = sql + "where maDV like N'%" + ma + "%'";
//	            }
//	            else if(ten.length()>0) {
//	            	sql = sql + "where tenDV like N'%" + ten + "%'";
//	            }
//	            else if(dvt!=chon) {
//	            	sql = sql + "where donViTinh like N'%" + dvt + "%'";
//	            }
//	            else if(loaidv!=chon) {
//	            	sql = sql + "where loaiDV like N'%" + loaidv + "%'";
//	            }  
//	            
//	            st = connection.createStatement();
//	            rs = st.executeQuery(sql);
//	            Vector data = null;
//	            tableModel.setRowCount(0); 
//	            
//	            if (rs.isBeforeFirst() == false) {
//	            	
//	            	JOptionPane.showMessageDialog(btnNewButton, "Không tìm thấy dịch vụ");
//	            	return;
//	            }
//	            while(rs.next()) {
//	            	data = new Vector();
//	            	data.add(rs.getString(1));
//	            	data.add(rs.getString(2));
//	            	data.add(rs.getString(3));
//	            	data.add(rs.getString(4));
//	            	
//	            	tableModel.addRow(data);
//	            }
//	            table.setModel(tableModel);
//	            xoaTrang();
//	            
//	        }catch (SQLException sqlException) {
//	            sqlException.printStackTrace();
//	        }
//	}

	private void timDV() {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		String ma = textField_maDV.getText();
		String ten = textField_tenDV.getText();
		String dvt = comboBox_dvt.getSelectedItem().toString();
		String loaidv = comboBox_loaiDV.getSelectedItem().toString();

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<DichVu> query = builder.createQuery(DichVu.class);
			Root<DichVu> root = query.from(DichVu.class);
			List<Predicate> predicates = new ArrayList<>();

			if (ma.length() > 0) {
				predicates.add(builder.like(root.get("maDV"), "%" + ma + "%"));
			}
			if (ten.length() > 0) {
				predicates.add(builder.like(root.get("tenDV"), "%" + ten + "%"));
			}
			if (!dvt.equals("-Chọn-")) {
				predicates.add(builder.like(root.get("donViTinh"), "%" + dvt + "%"));
			}
			if (!loaidv.equals("-Chọn-")) {
				predicates.add(builder.like(root.get("loaiDV"), "%" + loaidv + "%"));
			}

			query.select(root).where(predicates.toArray(new Predicate[0]));
			List<DichVu> dichVuList = session.createQuery(query).getResultList();

			if (dichVuList.isEmpty()) {
				JOptionPane.showMessageDialog(btnNewButton, "Không tìm thấy dịch vụ");
				return;
			}

			tableModel.setRowCount(0);
			for (DichVu dichVu : dichVuList) {
				Vector data = new Vector();
				data.add(dichVu.getMaDV());
				data.add(dichVu.getTenDV());
				data.add(dichVu.getDonViTinh());
				data.add(dichVu.getGiaDV());

				tableModel.addRow(data);
			}
			table.setModel(tableModel);
			xoaTrang();

			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		textField_maDV.setText(tableModel.getValueAt(row, 0).toString());
		textField_tenDV.setText(tableModel.getValueAt(row, 1).toString());
		((JComboBox) comboBox_dvt).setSelectedItem(tableModel.getValueAt(row, 3).toString());
		((JComboBox) comboBox_loaiDV).setSelectedItem(tableModel.getValueAt(row, 3).toString());
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
