package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
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
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class Menu_GUI extends JFrame implements ActionListener{

	private JPanel contentPane;
	JMenuBar menuBar;
	
	public Menu_GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setBounds(0, 0, 1650, 1080);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		  
		JMenu mnNewMenu_menu = new JMenu("Nhân Viên");
<<<<<<< HEAD
<<<<<<< HEAD
		mnNewMenu_menu.setIcon(new ImageIcon(Menu_GUI.class.getResource("/image/user.png")));
=======
		mnNewMenu_menu.setIcon(new ImageIcon(Menu_GUI.class.getResource("../image/user.png")));
>>>>>>> Khai-branch
=======

		mnNewMenu_menu.setIcon(new ImageIcon(Menu_GUI.class.getResource("/image/user.png")));

		mnNewMenu_menu.setIcon(new ImageIcon(Menu_GUI.class.getResource("../image/user.png")));

>>>>>>> Khai-branch
		mnNewMenu_menu.setFont(new Font("Segoe UI", Font.PLAIN, 30));
		mnNewMenu_menu.addActionListener(this);
		menuBar.add(mnNewMenu_menu);
		
		JMenuItem mntmNewMenuItem_upNV = new JMenuItem("Cập Nhập Nhân Viên");
<<<<<<< HEAD
<<<<<<< HEAD
		mntmNewMenuItem_upNV.setIcon(new ImageIcon(Menu_GUI.class.getResource("/image/plus.png")));
=======
		mntmNewMenuItem_upNV.setIcon(new ImageIcon(Menu_GUI.class.getResource("../image/plus.png")));
>>>>>>> Khai-branch
=======

		mntmNewMenuItem_upNV.setIcon(new ImageIcon(Menu_GUI.class.getResource("/image/plus.png")));

		mntmNewMenuItem_upNV.setIcon(new ImageIcon(Menu_GUI.class.getResource("../image/plus.png")));

>>>>>>> Khai-branch
		mntmNewMenuItem_upNV.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mntmNewMenuItem_upNV.addActionListener(this);
		mnNewMenu_menu.add(mntmNewMenuItem_upNV);
		
		JMenuItem mntmNewMenuItem_findNV = new JMenuItem("Tìm Kiếm Nhân Viên");
<<<<<<< HEAD
<<<<<<< HEAD
		mntmNewMenuItem_findNV.setIcon(new ImageIcon(Menu_GUI.class.getResource("/image/search.png")));
=======
		mntmNewMenuItem_findNV.setIcon(new ImageIcon(Menu_GUI.class.getResource("../image/search.png")));
>>>>>>> Khai-branch
=======

		mntmNewMenuItem_findNV.setIcon(new ImageIcon(Menu_GUI.class.getResource("/image/search.png")));

		mntmNewMenuItem_findNV.setIcon(new ImageIcon(Menu_GUI.class.getResource("../image/search.png")));

>>>>>>> Khai-branch
		mntmNewMenuItem_findNV.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mntmNewMenuItem_findNV.addActionListener(this);
		mnNewMenu_menu.add(mntmNewMenuItem_findNV);
		
		JMenuItem mntmNewMenuItem_tkNV = new JMenuItem("Tài Khoản");
<<<<<<< HEAD
<<<<<<< HEAD
		mntmNewMenuItem_tkNV.setIcon(new ImageIcon(Menu_GUI.class.getResource("/image/user4.png")));
=======
		mntmNewMenuItem_tkNV.setIcon(new ImageIcon(Menu_GUI.class.getResource("../image/user4.png")));
>>>>>>> Khai-branch
=======

		mntmNewMenuItem_tkNV.setIcon(new ImageIcon(Menu_GUI.class.getResource("/image/user4.png")));

		mntmNewMenuItem_tkNV.setIcon(new ImageIcon(Menu_GUI.class.getResource("../image/user4.png")));

>>>>>>> Khai-branch
		mntmNewMenuItem_tkNV.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mntmNewMenuItem_tkNV.addActionListener(this);
		mnNewMenu_menu.add(mntmNewMenuItem_tkNV);
		
		JMenuItem mntmNewMenuItem_cvNV = new JMenuItem("Chức Vụ");
<<<<<<< HEAD
<<<<<<< HEAD
		mntmNewMenuItem_cvNV.setIcon(new ImageIcon(Menu_GUI.class.getResource("/images/ic_KH.png")));
=======
		mntmNewMenuItem_cvNV.setIcon(new ImageIcon(Menu_GUI.class.getResource("../images/ic_KH.png")));
>>>>>>> Khai-branch
=======

		mntmNewMenuItem_cvNV.setIcon(new ImageIcon(Menu_GUI.class.getResource("/images/ic_KH.png")));

		mntmNewMenuItem_cvNV.setIcon(new ImageIcon(Menu_GUI.class.getResource("../images/ic_KH.png")));

>>>>>>> Khai-branch
		mntmNewMenuItem_cvNV.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mntmNewMenuItem_cvNV.addActionListener(this);
		mnNewMenu_menu.add(mntmNewMenuItem_cvNV);
		
		JMenu mnNewMenu_kh = new JMenu("Khách Hàng");
<<<<<<< HEAD
<<<<<<< HEAD
		mnNewMenu_kh.setIcon(new ImageIcon(Menu_GUI.class.getResource("/image/users.png")));
=======
		mnNewMenu_kh.setIcon(new ImageIcon(Menu_GUI.class.getResource("../image/users.png")));
>>>>>>> Khai-branch
=======

		mnNewMenu_kh.setIcon(new ImageIcon(Menu_GUI.class.getResource("/image/users.png")));

		mnNewMenu_kh.setIcon(new ImageIcon(Menu_GUI.class.getResource("../image/users.png")));

>>>>>>> Khai-branch
		mnNewMenu_kh.setFont(new Font("Segoe UI", Font.PLAIN, 30));
		mnNewMenu_kh.addActionListener(this);
		menuBar.add(mnNewMenu_kh);
		
		JMenuItem mntmCpNhp_upKH = new JMenuItem("Cập Nhập Khách Hàng");
<<<<<<< HEAD
<<<<<<< HEAD
		mntmCpNhp_upKH.setIcon(new ImageIcon(Menu_GUI.class.getResource("/image/plus.png")));
=======
		mntmCpNhp_upKH.setIcon(new ImageIcon(Menu_GUI.class.getResource("../image/plus.png")));
>>>>>>> Khai-branch
=======

		mntmCpNhp_upKH.setIcon(new ImageIcon(Menu_GUI.class.getResource("/image/plus.png")));

		mntmCpNhp_upKH.setIcon(new ImageIcon(Menu_GUI.class.getResource("../image/plus.png")));

>>>>>>> Khai-branch
		mntmCpNhp_upKH.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mntmCpNhp_upKH.addActionListener(this);
		mnNewMenu_kh.add(mntmCpNhp_upKH);
		
		JMenuItem mntmNewMenuItem_findKH = new JMenuItem("Tìm Kiếm Khách Hàng");
<<<<<<< HEAD
<<<<<<< HEAD
		mntmNewMenuItem_findKH.setIcon(new ImageIcon(Menu_GUI.class.getResource("/image/search.png")));
=======
		mntmNewMenuItem_findKH.setIcon(new ImageIcon(Menu_GUI.class.getResource("../image/search.png")));
>>>>>>> Khai-branch
=======

		mntmNewMenuItem_findKH.setIcon(new ImageIcon(Menu_GUI.class.getResource("/image/search.png")));

		mntmNewMenuItem_findKH.setIcon(new ImageIcon(Menu_GUI.class.getResource("../image/search.png")));

>>>>>>> Khai-branch
		mntmNewMenuItem_findKH.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mntmNewMenuItem_findKH.addActionListener(this);
		mnNewMenu_kh.add(mntmNewMenuItem_findKH);
		
		JMenu mnNewMenu_dv = new JMenu("Dịch Vụ");
<<<<<<< HEAD
<<<<<<< HEAD
		mnNewMenu_dv.setIcon(new ImageIcon(Menu_GUI.class.getResource("/images/ic_DV (5).png")));
=======
		mnNewMenu_dv.setIcon(new ImageIcon(Menu_GUI.class.getResource("../images/ic_DV (5).png")));
>>>>>>> Khai-branch
=======

		mnNewMenu_dv.setIcon(new ImageIcon(Menu_GUI.class.getResource("/images/ic_DV (5).png")));

		mnNewMenu_dv.setIcon(new ImageIcon(Menu_GUI.class.getResource("../images/ic_DV (5).png")));

>>>>>>> Khai-branch
		mnNewMenu_dv.setFont(new Font("Segoe UI", Font.PLAIN, 30));
		mnNewMenu_dv.addActionListener(this);
		menuBar.add(mnNewMenu_dv);
		
		JMenuItem mntmNewMenuItem_upDV = new JMenuItem("Cập Nhập Dịch Vụ");
<<<<<<< HEAD
<<<<<<< HEAD
		mntmNewMenuItem_upDV.setIcon(new ImageIcon(Menu_GUI.class.getResource("/image/plus.png")));
=======
		mntmNewMenuItem_upDV.setIcon(new ImageIcon(Menu_GUI.class.getResource("../image/plus.png")));
>>>>>>> Khai-branch
=======

		mntmNewMenuItem_upDV.setIcon(new ImageIcon(Menu_GUI.class.getResource("/image/plus.png")));

		mntmNewMenuItem_upDV.setIcon(new ImageIcon(Menu_GUI.class.getResource("../image/plus.png")));

>>>>>>> Khai-branch
		mntmNewMenuItem_upDV.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mntmNewMenuItem_upDV.addActionListener(this);
		mnNewMenu_dv.add(mntmNewMenuItem_upDV);
		
		JMenuItem mntmNewMenuItem_findDV = new JMenuItem("Tìm Kiếm Dịch Vụ");
<<<<<<< HEAD
<<<<<<< HEAD
		mntmNewMenuItem_findDV.setIcon(new ImageIcon(Menu_GUI.class.getResource("/image/search.png")));
=======
		mntmNewMenuItem_findDV.setIcon(new ImageIcon(Menu_GUI.class.getResource("../image/search.png")));
>>>>>>> Khai-branch
=======

		mntmNewMenuItem_findDV.setIcon(new ImageIcon(Menu_GUI.class.getResource("/image/search.png")));

		mntmNewMenuItem_findDV.setIcon(new ImageIcon(Menu_GUI.class.getResource("../image/search.png")));

>>>>>>> Khai-branch
		mntmNewMenuItem_findDV.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mntmNewMenuItem_findDV.addActionListener(this);
		mnNewMenu_dv.add(mntmNewMenuItem_findDV);
		
		JMenuItem mntmNewMenuItem_loaiDV = new JMenuItem("Loại Dịch Vụ");
<<<<<<< HEAD
<<<<<<< HEAD
		mntmNewMenuItem_loaiDV.setIcon(new ImageIcon(Menu_GUI.class.getResource("/image/food.png")));
=======
		mntmNewMenuItem_loaiDV.setIcon(new ImageIcon(Menu_GUI.class.getResource("../image/food.png")));
>>>>>>> Khai-branch
=======

		mntmNewMenuItem_loaiDV.setIcon(new ImageIcon(Menu_GUI.class.getResource("/image/food.png")));

		mntmNewMenuItem_loaiDV.setIcon(new ImageIcon(Menu_GUI.class.getResource("../image/food.png")));

>>>>>>> Khai-branch
		mntmNewMenuItem_loaiDV.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mntmNewMenuItem_loaiDV.addActionListener(this);
		mnNewMenu_dv.add(mntmNewMenuItem_loaiDV);
		
		JMenu mnNewMenu_phong = new JMenu("Phòng");
<<<<<<< HEAD
<<<<<<< HEAD
		mnNewMenu_phong.setIcon(new ImageIcon(Menu_GUI.class.getResource("/image/essentials-08.png")));
=======
		mnNewMenu_phong.setIcon(new ImageIcon(Menu_GUI.class.getResource("../image/essentials-08.png")));
>>>>>>> Khai-branch
=======

		mnNewMenu_phong.setIcon(new ImageIcon(Menu_GUI.class.getResource("/image/essentials-08.png")));

		mnNewMenu_phong.setIcon(new ImageIcon(Menu_GUI.class.getResource("../image/essentials-08.png")));

>>>>>>> Khai-branch
		mnNewMenu_phong.setFont(new Font("Segoe UI", Font.PLAIN, 30));
		mnNewMenu_phong.addActionListener(this);
		menuBar.add(mnNewMenu_phong);
		
		JMenuItem mntmNewMenuItem_upPhong = new JMenuItem("Cập Nhập Phòng");
<<<<<<< HEAD
<<<<<<< HEAD
		mntmNewMenuItem_upPhong.setIcon(new ImageIcon(Menu_GUI.class.getResource("/image/plus.png")));
=======
		mntmNewMenuItem_upPhong.setIcon(new ImageIcon(Menu_GUI.class.getResource("../image/plus.png")));
>>>>>>> Khai-branch
=======

		mntmNewMenuItem_upPhong.setIcon(new ImageIcon(Menu_GUI.class.getResource("/image/plus.png")));

		mntmNewMenuItem_upPhong.setIcon(new ImageIcon(Menu_GUI.class.getResource("../image/plus.png")));

>>>>>>> Khai-branch
		mntmNewMenuItem_upPhong.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mntmNewMenuItem_upPhong.addActionListener(this);
		mnNewMenu_phong.add(mntmNewMenuItem_upPhong);
		
		JMenuItem mntmNewMenuItem_upLP = new JMenuItem("Cập Nhập Loại Phòng");
<<<<<<< HEAD
<<<<<<< HEAD
		mntmNewMenuItem_upLP.setIcon(new ImageIcon(Menu_GUI.class.getResource("/image/plus.png")));
=======
		mntmNewMenuItem_upLP.setIcon(new ImageIcon(Menu_GUI.class.getResource("../image/plus.png")));
>>>>>>> Khai-branch
=======

		mntmNewMenuItem_upLP.setIcon(new ImageIcon(Menu_GUI.class.getResource("/image/plus.png")));

		mntmNewMenuItem_upLP.setIcon(new ImageIcon(Menu_GUI.class.getResource("../image/plus.png")));

>>>>>>> Khai-branch
		mntmNewMenuItem_upLP.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mntmNewMenuItem_upLP.addActionListener(this);
		mnNewMenu_phong.add(mntmNewMenuItem_upLP);
		
		JMenuItem mntmNewMenuItem_findPhong = new JMenuItem("Tìm Kiếm Phòng");
<<<<<<< HEAD
<<<<<<< HEAD
		mntmNewMenuItem_findPhong.setIcon(new ImageIcon(Menu_GUI.class.getResource("/image/search.png")));
=======
		mntmNewMenuItem_findPhong.setIcon(new ImageIcon(Menu_GUI.class.getResource("../image/search.png")));
>>>>>>> Khai-branch
=======

		mntmNewMenuItem_findPhong.setIcon(new ImageIcon(Menu_GUI.class.getResource("/image/search.png")));

		mntmNewMenuItem_findPhong.setIcon(new ImageIcon(Menu_GUI.class.getResource("../image/search.png")));

>>>>>>> Khai-branch
		mntmNewMenuItem_findPhong.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mntmNewMenuItem_findPhong.addActionListener(this);
		mnNewMenu_phong.add(mntmNewMenuItem_findPhong);
		
		JMenuItem mntmNewMenuItem_datPhong = new JMenuItem("Đặt Phòng");
<<<<<<< HEAD
<<<<<<< HEAD
		mntmNewMenuItem_datPhong.setIcon(new ImageIcon(Menu_GUI.class.getResource("/image/bell.png")));
=======
		mntmNewMenuItem_datPhong.setIcon(new ImageIcon(Menu_GUI.class.getResource("../image/bell.png")));
>>>>>>> Khai-branch
=======

		mntmNewMenuItem_datPhong.setIcon(new ImageIcon(Menu_GUI.class.getResource("/image/bell.png")));

		mntmNewMenuItem_datPhong.setIcon(new ImageIcon(Menu_GUI.class.getResource("../image/bell.png")));

>>>>>>> Khai-branch
		mntmNewMenuItem_datPhong.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mntmNewMenuItem_datPhong.addActionListener(this);
		mnNewMenu_phong.add(mntmNewMenuItem_datPhong);
		
		JMenu mnNewMenu_hd = new JMenu("Hoá Đơn");
<<<<<<< HEAD
<<<<<<< HEAD
		mnNewMenu_hd.setIcon(new ImageIcon(Menu_GUI.class.getResource("/images/ic_HD.png")));
=======
		mnNewMenu_hd.setIcon(new ImageIcon(Menu_GUI.class.getResource("../images/ic_HD.png")));
>>>>>>> Khai-branch
=======

		mnNewMenu_hd.setIcon(new ImageIcon(Menu_GUI.class.getResource("/images/ic_HD.png")));

		mnNewMenu_hd.setIcon(new ImageIcon(Menu_GUI.class.getResource("../images/ic_HD.png")));

>>>>>>> Khai-branch
		mnNewMenu_hd.setFont(new Font("Segoe UI", Font.PLAIN, 30));
		mnNewMenu_hd.addActionListener(this);
		menuBar.add(mnNewMenu_hd);
		
		JMenuItem mntmNewMenuItem_lapHD = new JMenuItem("Lập Hoá Đơn");
<<<<<<< HEAD
<<<<<<< HEAD
		mntmNewMenuItem_lapHD.setIcon(new ImageIcon(Menu_GUI.class.getResource("/images/ic_ThanhToan.png")));
=======
		mntmNewMenuItem_lapHD.setIcon(new ImageIcon(Menu_GUI.class.getResource("../images/ic_ThanhToan.png")));
>>>>>>> Khai-branch
=======

		mntmNewMenuItem_lapHD.setIcon(new ImageIcon(Menu_GUI.class.getResource("/images/ic_ThanhToan.png")));

		mntmNewMenuItem_lapHD.setIcon(new ImageIcon(Menu_GUI.class.getResource("../images/ic_ThanhToan.png")));

>>>>>>> Khai-branch
		mntmNewMenuItem_lapHD.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mntmNewMenuItem_lapHD.addActionListener(this);
		mnNewMenu_hd.add(mntmNewMenuItem_lapHD);
		
		JMenu mnNewMenu_thongKe = new JMenu("Thống Kê");
<<<<<<< HEAD
<<<<<<< HEAD
		mnNewMenu_thongKe.setIcon(new ImageIcon(Menu_GUI.class.getResource("/images/ic_TK.png")));
=======
		mnNewMenu_thongKe.setIcon(new ImageIcon(Menu_GUI.class.getResource("../images/ic_TK.png")));
>>>>>>> Khai-branch
=======

		mnNewMenu_thongKe.setIcon(new ImageIcon(Menu_GUI.class.getResource("/images/ic_TK.png")));

		mnNewMenu_thongKe.setIcon(new ImageIcon(Menu_GUI.class.getResource("../images/ic_TK.png")));

>>>>>>> Khai-branch
		mnNewMenu_thongKe.setFont(new Font("Segoe UI", Font.PLAIN, 30));
		mnNewMenu_thongKe.addActionListener(this);
		menuBar.add(mnNewMenu_thongKe);
		
		JMenuItem mntmNewMenuItem_tkDoanhThu = new JMenuItem("Thống Kê Doanh Thu");
<<<<<<< HEAD
<<<<<<< HEAD
		mntmNewMenuItem_tkDoanhThu.setIcon(new ImageIcon(Menu_GUI.class.getResource("/images/ic_TK.png")));
=======
		mntmNewMenuItem_tkDoanhThu.setIcon(new ImageIcon(Menu_GUI.class.getResource("../images/ic_TK.png")));
>>>>>>> Khai-branch
=======

		mntmNewMenuItem_tkDoanhThu.setIcon(new ImageIcon(Menu_GUI.class.getResource("/images/ic_TK.png")));

		mntmNewMenuItem_tkDoanhThu.setIcon(new ImageIcon(Menu_GUI.class.getResource("../images/ic_TK.png")));

>>>>>>> Khai-branch
		mntmNewMenuItem_tkDoanhThu.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mntmNewMenuItem_tkDoanhThu.addActionListener(this);
		mnNewMenu_thongKe.add(mntmNewMenuItem_tkDoanhThu);
		
		
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("KARAOKE NICE");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 50));
		lblNewLabel.setBounds(426, 0, 650, 74);
		contentPane.add(lblNewLabel);
		

		
		this.setVisible(true);
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
//		////////////////////////////////////////////////////////////////////////////
		if (e.getActionCommand().equals("Cập Nhập Khách Hàng")) {
			dispose();
            new CapNhapKH_GUI();
        }
//		if (e.getActionCommand().equals("Tìm Kiếm Khách Hàng")) {
//			dispose();
//            new TimKiemKH_GUI();
//        }
//		///////////////////////////////////////////////////////////////////////////
		if (e.getActionCommand().equals("Cập Nhập Dịch Vụ")) {
			dispose();
            new CapNhapDV_GUI();
        }
<<<<<<< HEAD
//		if (e.getActionCommand().equals("Tìm Kiếm Dịch Vụ")) {
//			dispose();
//            new TimKiemDV_GUI();
//        }
=======
		if (e.getActionCommand().equals("Tìm Kiếm Dịch Vụ")) {
			dispose();
            new TimKiemDV_GUI();
        }
>>>>>>> Khai-branch
		if (e.getActionCommand().equals("Loại Dịch Vụ")) {
			dispose();
            new LoaiDichVu_GUI();
        }
//		///////////////////////////////////////////////////////////////////////////
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
//		///////////////////////////////////////////////////////////////////////////
		if (e.getActionCommand().equals("Lập Hoá Đơn")) {
			dispose();
            new LapHoaDon_GUI();
        }
//		if (e.getActionCommand().equals("Thống Kê Doanh Thu")) {
//			dispose();
//            new ThongKe_GUI();
//        }
	}
}
