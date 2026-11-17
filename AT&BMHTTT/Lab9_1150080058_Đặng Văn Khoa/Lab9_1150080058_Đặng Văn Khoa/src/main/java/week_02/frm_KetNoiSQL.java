package week_02;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class frm_KetNoiSQL extends JFrame {

    private JTextField txt_server;
    private JTextField txt_port;
    private JTextField txt_database;
    private JTextField txt_user;
    private JPasswordField txt_password;
    private JButton btn_test;
    private JButton btn_history;
    private JLabel lbl_status;

    public frm_KetNoiSQL() {
        initComponents();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        setTitle("Kết nối Microsoft SQL Server");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblTitle = new JLabel("KẾT NỐI MICROSOFT SQL SERVER");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        mainPanel.add(lblTitle, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 0; gbc.gridy = 1; mainPanel.add(new JLabel("Server:"), gbc);
        txt_server = new JTextField("127.0.0.1", 22);
        gbc.gridx = 1; gbc.gridy = 1; mainPanel.add(txt_server, gbc);

        gbc.gridx = 0; gbc.gridy = 2; mainPanel.add(new JLabel("Port:"), gbc);
        txt_port = new JTextField("14333", 10);
        gbc.gridx = 1; gbc.gridy = 2; mainPanel.add(txt_port, gbc);

        gbc.gridx = 0; gbc.gridy = 3; mainPanel.add(new JLabel("Database:"), gbc);
        txt_database = new JTextField("LAB2_MaHoaHienDai", 22);
        gbc.gridx = 1; gbc.gridy = 3; mainPanel.add(txt_database, gbc);

        gbc.gridx = 0; gbc.gridy = 4; mainPanel.add(new JLabel("User:"), gbc);
        txt_user = new JTextField("lab2_user", 22);
        gbc.gridx = 1; gbc.gridy = 4; mainPanel.add(txt_user, gbc);

        gbc.gridx = 0; gbc.gridy = 5; mainPanel.add(new JLabel("Password:"), gbc);
        txt_password = new JPasswordField("LabUser@2026", 22);
        gbc.gridx = 1; gbc.gridy = 5; mainPanel.add(txt_password, gbc);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        btn_test = new JButton("Test Connection");
        btn_history = new JButton("Xem lịch sử");
        btnPanel.add(btn_test);
        btnPanel.add(btn_history);
        gbc.gridx = 1; gbc.gridy = 6; mainPanel.add(btnPanel, gbc);

        lbl_status = new JLabel("Chưa kết nối.");
        lbl_status.setPreferredSize(new Dimension(420, 40));
        gbc.gridx = 0; gbc.gridy = 7; gbc.gridwidth = 2;
        mainPanel.add(lbl_status, gbc);

        btn_test.addActionListener(e -> btn_testActionPerformed());
        btn_history.addActionListener(e -> new frm_LichSu().setVisible(true));

        setContentPane(mainPanel);
        pack();
    }

    private void btn_testActionPerformed() {
        try {
            int port = Integer.parseInt(txt_port.getText().trim());
            DbConfig.set(txt_server.getText().trim(), port, txt_database.getText().trim(),
                    txt_user.getText().trim(), txt_password.getPassword());
            lbl_status.setText("<html>" + DbConfig.test() + "</html>");
        } catch (Exception ex) {
            DbConfig.clear();
            lbl_status.setText("<html>Kết nối thất bại: " + ex.getMessage() + "</html>");
            JOptionPane.showMessageDialog(this, ex.getMessage(),
                    "Không kết nối được SQL Server", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new frm_KetNoiSQL().setVisible(true));
    }
}