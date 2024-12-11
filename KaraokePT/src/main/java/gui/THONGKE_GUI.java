package gui;

import Server.Config;
import bll.ChartStatitic;
import bll.ChuyenDoi;
import bll.SessionManager;
import entity.HoaDonRevenue;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.util.List;

import entity.NhanVien;
import entity.TaiKhoan;
import model.*;
import org.hibernate.LazyInitializationException;
import org.jfree.chart.ChartPanel;
import java.time.LocalDate;
import java.time.DayOfWeek;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.text.DecimalFormat;

public class THONGKE_GUI extends JFrame implements ActionListener, MouseListener {

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
    private HoaDonDao hoaDonDao;
    private TaiKhoan user;
    private TaiKhoanDao tk_dao;
    private SessionManager currentUser = SessionManager.getInstance();

    LocalDate today = LocalDate.now();
    DayOfWeek dayOfWeek = today.getDayOfWeek();
    int todayIndex = dayOfWeek.getValue();
    public THONGKE_GUI() throws RemoteException {
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
        contentPane.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

// Tiêu đề "THỐNG KÊ"
        JLabel lblNewLabel = new JLabel("THỐNG KÊ");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 50));
        gbc.gridx = 0; // Cột đầu tiên
        gbc.gridy = 0; // Hàng đầu tiên
        gbc.gridwidth = 2; // Chiếm 2 cột
        gbc.insets = new Insets(10, 10, 10, 10); // Giảm khoảng cách bên ngoài (so với 20, 10, 20, 10)
        contentPane.add(lblNewLabel, gbc);

// Biểu đồ doanh thu theo tháng
        Box horizontalBox = Box.createHorizontalBox();
        horizontalBox.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED,
                new Color(255, 255, 255), new Color(160, 160, 160)),
                "Thống kê doanh thu theo tháng",
                TitledBorder.LEADING,
                TitledBorder.TOP,
                null,
                new Color(0, 0, 0)));
        ChartStatitic chartStatitic = new ChartStatitic(dao); // Truyền HoaDonDao vào
        JPanel chartPanel = chartStatitic.createMonthlyRevenueChartPanel(); // Tạo biểu đồ
        horizontalBox.add(chartPanel);

        Box horizontalBox1 = Box.createHorizontalBox();
        horizontalBox1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED,
                new Color(255, 255, 255), new Color(160, 160, 160)),
                "Thống kê doanh thu trong tuần",
                TitledBorder.LEADING,
                TitledBorder.TOP,
                null,
                new Color(0, 0, 0)));
        JPanel chartPanelLine = chartStatitic.createDailyRevenueChartPanel(); // Tạo biểu đồ
        horizontalBox1.add(chartPanelLine);

// Đặt cả hai biểu đồ lên cùng một hàng
        gbc.gridx = 0; // Cột đầu tiên
        gbc.gridy = 1; // Hàng thứ hai (cùng hàng cho cả hai biểu đồ)
        gbc.gridwidth = 1; // Mỗi biểu đồ chiếm một cột
        gbc.weightx = 0.5; // Trọng số ngang bằng nhau
        gbc.weighty = 1; // Trọng số dọc, để chiếm toàn bộ chiều cao
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10); // Khoảng cách bên ngoài
        contentPane.add(horizontalBox, gbc);

        gbc.gridx = 1; // Cột thứ hai
        gbc.gridy = 1; // Cùng hàng với biểu đồ đầu tiên
        contentPane.add(horizontalBox1, gbc);

        Box horizontalBox2 = Box.createHorizontalBox();
        horizontalBox2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED,
                new Color(255, 255, 255), new Color(160, 160, 160)),
                "Tỉ lệ doanh thu theo từng ngày",
                TitledBorder.LEADING,
                TitledBorder.TOP,
                null,
                new Color(0, 0, 0)));
        try {
            List<HoaDonRevenue> dailyRevenueList = dao.getDoanhThuTheoNgayTrongTuan();
            if (dailyRevenueList != null) {
                System.out.println("dailyRevenueList: " +dailyRevenueList);

                JPanel doanhThuTheoNgayPanel = createDoanhThuTheoNgayPanel(dailyRevenueList);

                    horizontalBox2.add(doanhThuTheoNgayPanel);
                    gbc.gridx = 0; // Cột đầu tiên
                    gbc.gridy = 2; // Hàng thứ ba (dưới các biểu đồ)
                    gbc.gridwidth = 2; // Chiếm toàn bộ chiều rộng
                    gbc.insets = new Insets(10, 10, 10, 10); // Khoảng cách bên ngoài
                    gbc.fill = GridBagConstraints.HORIZONTAL;
                    contentPane.add(horizontalBox2, gbc);
            } else {
                System.out.println("Dữ liệu doanh thu theo ngày không có.");
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }



        this.setVisible(true);
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


    }

    @Override
    public void mouseClicked(MouseEvent e) {

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
    private JPanel createDoanhThuTheoNgayPanel(List<HoaDonRevenue> dailyRevenueList) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2, 20, 0));  // Sử dụng GridLayout với 2 cột để dễ dàng chia thông tin

        // Doanh thu hôm nay
        JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        JLabel lblDoanhThuHienTai = new JLabel("Doanh thu hôm nay");
        lblDoanhThuHienTai.setFont(new Font("Tahoma", Font.PLAIN, 16));
        JLabel lblDoanhThuHienTaiValue = new JLabel("0 đ");  // Giá trị sẽ thay đổi động
        lblDoanhThuHienTaiValue.setFont(new Font("Tahoma", Font.BOLD, 18));
        panel1.add(lblDoanhThuHienTai);
        panel1.add(lblDoanhThuHienTaiValue);

        // Doanh thu hôm qua
        JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        JLabel lblDoanhThuHomQua = new JLabel("Doanh thu hôm qua");
        lblDoanhThuHomQua.setFont(new Font("Tahoma", Font.PLAIN, 16));
        JLabel lblDoanhThuHomQuaValue = new JLabel("0 đ");  // Giá trị sẽ thay đổi động
        lblDoanhThuHomQuaValue.setFont(new Font("Tahoma", Font.BOLD, 18));
        panel2.add(lblDoanhThuHomQua);
        panel2.add(lblDoanhThuHomQuaValue);

        // Tỷ lệ thay đổi giữa ngày hôm nay và ngày hôm qua
        JPanel panel3 = new JPanel();
        panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));
        JLabel lblTiLeThayDoi = new JLabel("Tăng");
        lblTiLeThayDoi.setFont(new Font("Tahoma", Font.PLAIN, 16));
        JLabel lblTiLeThayDoiValue = new JLabel("↑ 0.00%");  // Giá trị sẽ thay đổi động
        lblTiLeThayDoiValue.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblTiLeThayDoiValue.setForeground(new Color(0, 123, 0));  // Màu xanh lá cho tỷ lệ tăng
        panel3.add(lblTiLeThayDoi);
        panel3.add(lblTiLeThayDoiValue);

        // Lấy doanh thu hôm nay và hôm qua từ danh sách doanh thu
        double todayRevenue = 0;
        double yesterdayRevenue = 0;

        int todayIndex = LocalDate.now().getDayOfWeek().getValue(); // Lấy thứ trong tuần (1-7)
        int yesterdayIndex = (todayIndex == 1) ? 7 : todayIndex - 1;  // Tính ngày hôm qua
        System.out.println("todayIndex "+todayIndex );
        System.out.println("yesterdayIndex "+yesterdayIndex );

        // Tìm doanh thu hôm nay và hôm qua
        for (HoaDonRevenue revenue : dailyRevenueList) {
            System.out.println("revenue.getDayWeek() "+revenue.getDayWeek() );

            if (revenue.getDayWeek() == todayIndex) {
                todayRevenue = Double.parseDouble(revenue.getTotalRevenue().toString());
            } else if (revenue.getDayWeek() == yesterdayIndex) {
                yesterdayRevenue = Double.parseDouble(revenue.getTotalRevenue().toString());
            }
        }

        // Cập nhật doanh thu hôm nay và hôm qua vào các JLabel
        DecimalFormat formatter = new DecimalFormat("#,###.##");
        lblDoanhThuHienTaiValue.setText(formatter.format(todayRevenue) + " đ");
        lblDoanhThuHomQuaValue.setText(formatter.format(yesterdayRevenue)  + " đ");

        // Tính tỷ lệ thay đổi
        double changeRate = 0;
        if(todayRevenue > 0){
            changeRate = ((todayRevenue - yesterdayRevenue) / todayRevenue) * 100;
        }


        // Cập nhật tỷ lệ thay đổi
        if (changeRate < 0) {
            lblTiLeThayDoiValue.setText("↓ " + String.format("%.2f", Math.abs(changeRate)) + "%");
            lblTiLeThayDoiValue.setForeground(new Color(255, 0, 0));  // Màu đỏ cho tỷ lệ giảm
        }else{
            lblTiLeThayDoiValue.setText("↑ " + String.format("%.2f", Math.abs(changeRate)) + "%");
            lblTiLeThayDoiValue.setForeground(new Color(0, 123, 0));
        }

        // Thêm các panel con vào panel chính
        panel.add(panel1);
        panel.add(panel2);
        panel.add(panel3);

        return panel;
    }


}
