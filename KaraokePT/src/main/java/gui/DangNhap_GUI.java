package gui;

import Server.Config;
import bll.SessionManager;
import model.LoaiPhongDao;
import model.TaiKhoanDao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class DangNhap_GUI extends JFrame implements ActionListener {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin, btnExit;
    private TaiKhoanDao tk_dao;
    private JPanel contentPanel;
    public DangNhap_GUI() {
        try{
            tk_dao = (TaiKhoanDao) Naming.lookup(Config.SERVER_URL + "taiKhoanDao");
        }catch (Exception e){
            JOptionPane.showMessageDialog(this, "Server chưa mở");
        }
        setTitle("KARAOKE NICE");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBackground(new Color(255, 255, 255));

        JPanel pnlTitle = new JPanel();
        JLabel lblTitle = new JLabel("Đăng Nhập Hệ Thống");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        pnlTitle.add(lblTitle);
        pnlTitle.setBackground(new Color(0, 255, 255));

        // Panel nhập thông tin
        JPanel pnlInput = new JPanel(null);
        pnlInput.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        pnlInput.setBackground(new Color(240, 240, 240));

        JLabel lblUsername = new JLabel("Tên đăng nhập:");
        lblUsername.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblUsername.setBounds(10, 20, 150, 26);
        pnlInput.add(lblUsername);

        JLabel lblPassword = new JLabel("Mật khẩu:");
        lblPassword.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblPassword.setBounds(10, 70, 150, 26);
        pnlInput.add(lblPassword);

        txtUsername = new JTextField();
        txtUsername.setColumns(10);
        txtUsername.setBounds(150, 20, 150, 26);
        pnlInput.add(txtUsername);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(150, 70, 150, 26);  // Đặt vị trí cho txtPassword
        pnlInput.add(txtPassword);
        txtUsername.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    handleLogin();
                }
            }
        });

        txtPassword.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    handleLogin();
                }
            }
        });
        // Panel nút
        JPanel pnlButtons = new JPanel();
        pnlButtons.setBackground(new Color(0, 255, 255));
        btnLogin = new JButton("Đăng Nhập");
        btnExit = new JButton("Thoát");
        pnlButtons.add(btnLogin);
        pnlButtons.add(btnExit);

        // Thêm các panel vào contentPanel
        contentPanel.add(pnlTitle, BorderLayout.NORTH);
        contentPanel.add(pnlInput, BorderLayout.CENTER);
        contentPanel.add(pnlButtons, BorderLayout.SOUTH);

        // Thêm contentPanel vào JFrame
        add(contentPanel);

        // Thiết lập hành động khi nhấn nút
        btnLogin.addActionListener(this);
        btnExit.addActionListener(this);

        // Thiết lập nền cho toàn bộ cửa sổ
        getContentPane().setBackground(new Color(255, 255, 255));  // Màu nền trắng cho cửa sổ

        this.setVisible(true);
    }

    private void handleLogin() {
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword()).trim();
        System.out.println("username"+ username);
        System.out.println("password"+ password);

        try {
            if (username.equals("") || password.equals("")) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!", "Lỗi", JOptionPane.WARNING_MESSAGE);

            }
            else if (tk_dao.checkLogin(username, password)) {
                JOptionPane.showMessageDialog(this, "Đăng nhập thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                SessionManager.getInstance().setCurrentUser(username);
                new Menu_GUI();
                this.dispose();
            }  else {
                JOptionPane.showMessageDialog(this, "Tên đăng nhập hoặc mật khẩu không đúng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new DangNhap_GUI().setVisible(true);
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btnLogin)) {
            handleLogin();
        }
        if (o.equals(btnExit)) {
            dispose();
            System.exit(0);
        }
    }
}
