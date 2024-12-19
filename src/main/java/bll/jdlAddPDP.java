package bll;

import com.toedter.calendar.JDateChooser;
import gui.LapHoaDon_GUI;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
public class jdlAddPDP extends javax.swing.JDialog {

    public jdlAddPDP(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        txtMaSP = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        spnSoLuong = new javax.swing.JSpinner();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtGhiChu = new javax.swing.JTextArea();
        lblGhiChu = new javax.swing.JLabel();
        btnChon = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();

        // Initialize the JDateChooser
        dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("yyyy-MM-dd HH:mm:ss"); // Set format for DateTime
        dateChooser.setEnabled(false);


        // Initialize label for "Thời gian trả phòng"
        lblThoiGianTraPhong = new javax.swing.JLabel();
        lblThoiGianTraPhong.setText("Thời gian trả phòng:");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClose(evt);
            }
        });

        jLabel1.setText("Mã:");
        txtMaSP.setForeground(Color.BLACK);
        txtMaSP.setEnabled(false);
        jLabel2.setText("Số giờ:");

        spnSoLuong.setModel(new SpinnerNumberModel(1.0, 0.0, 100.0, 0.1));
        JSpinner.DefaultEditor editor = (JSpinner.DefaultEditor) spnSoLuong.getEditor();
        editor.getTextField().setForeground(Color.BLACK);
        spnSoLuong.setEnabled(false);
        txtGhiChu.setColumns(20);
        txtGhiChu.setRows(5);
        jScrollPane1.setViewportView(txtGhiChu);

        lblGhiChu.setText("Ghi chú:");

        btnChon.setText("Chọn");
        btnChon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonActionPerformed(evt);
            }
        });

        btnHuy.setText("Hủy");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(65, 65, 65)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel2)
                                        .addComponent(lblThoiGianTraPhong)
                                        .addComponent(lblGhiChu)
                                )
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jScrollPane1)
                                        .addComponent(spnSoLuong)
                                        .addComponent(txtMaSP)
                                        .addComponent(dateChooser)  // Chỉ thêm dateChooser một lần ở đây
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(btnChon, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        )
                                )
                                .addGap(97, 97, 97)
                        )
        );

        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(spnSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblThoiGianTraPhong)
                                        .addComponent(dateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblGhiChu))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnChon)
                                        .addComponent(btnHuy))
                                .addContainerGap(61, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void btnChonActionPerformed(java.awt.event.ActionEvent evt) {
        // Get selected date and time from the dateChooser
        java.util.Date selectedDate = dateChooser.getDate();
        String formattedDate = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(selectedDate);

        // Xử lý giá trị đã chọn
        System.out.println("Selected Date: " + formattedDate);
        // Process selected date, time, and other fields
        LapHoaDon_GUI.SoGio = ((Number) spnSoLuong.getValue()).doubleValue();
        LapHoaDon_GUI.GhiChu = txtGhiChu.getText();
        System.out.println("Selected DateTime: " + formattedDate);

        this.dispose();
    }

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {
        // Close the dialog without making any changes
        LapHoaDon_GUI.SoGio = 0;
        this.dispose();
    }

    private void formWindowOpened(java.awt.event.WindowEvent evt) {
        // Set initial values when the dialog is opened
        txtMaSP.setText(LapHoaDon_GUI.maPDP + "");
        spnSoLuong.setModel(new javax.swing.SpinnerNumberModel(LapHoaDon_GUI.SoGio, 1.0, null, 0.1));
    }
    private void formWindowClose(java.awt.event.WindowEvent evt){
        LapHoaDon_GUI.SoGio = 0;
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                jdlAddPDP dialog = new jdlAddPDP(new javax.swing.JFrame(), true);
                dialog.setVisible(true);
            }
        });
    }

    public JDateChooser getDateChooser() {
        return dateChooser;
    }
    public JSpinner getSpnSoGio() {
        return spnSoLuong;
    }
    // Variables declaration
    private javax.swing.JButton btnChon;
    private javax.swing.JButton btnHuy;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblGhiChu;
    private javax.swing.JSpinner spnSoLuong;
    private javax.swing.JTextArea txtGhiChu;
    private javax.swing.JTextField txtMaSP;
    private JDateChooser dateChooser;  // Declare JDateChooser
    private javax.swing.JLabel lblThoiGianTraPhong;
}
