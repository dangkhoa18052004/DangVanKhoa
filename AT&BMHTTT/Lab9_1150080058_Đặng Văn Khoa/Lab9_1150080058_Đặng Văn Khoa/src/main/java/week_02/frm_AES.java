package week_02;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class frm_AES extends JFrame {

    private String currentUser = null;

    private JTextField txt_username;
    private JPasswordField txt_password;
    private JButton btn_sql;
    private JButton btn_register;
    private JButton btn_login;
    private JTextField txt_ticket;
    private JTextArea txt_plaintext;
    private JTextField txt_key;
    private JTextArea txt_ciphertext;
    private JButton btn_encrypt;
    private JButton btn_decrypt;
    private JButton btn_save;
    private JButton btn_open;
    private JLabel lbl_status;

    public frm_AES() {
        initComponents();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        setTitle("AES Cipher - Register / Login");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 6, 5, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblTitle = new JLabel("AES-GCM - ĐĂNG KÝ / ĐĂNG NHẬP (SQL SERVER)");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        mainPanel.add(lblTitle, gbc);

        // Username
        gbc.gridwidth = 1;
        gbc.gridx = 0; gbc.gridy = 1;
        mainPanel.add(new JLabel("Username:"), gbc);
        txt_username = new JTextField("sinhvien01", 22);
        gbc.gridx = 1; gbc.gridy = 1;
        mainPanel.add(txt_username, gbc);

        // Password
        gbc.gridx = 0; gbc.gridy = 2;
        mainPanel.add(new JLabel("Password:"), gbc);
        JPanel passPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        txt_password = new JPasswordField(22);
        passPanel.add(txt_password);
        passPanel.add(new JLabel("   (ít nhất 8 ký tự)"));
        gbc.gridx = 1; gbc.gridy = 2;
        mainPanel.add(passPanel, gbc);

        // Nút SQL / Register / Login
        JPanel authBtnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 2));
        btn_sql = new JButton("Kết nối SQL Server");
        btn_register = new JButton("Register");
        btn_login = new JButton("Login");
        authBtnPanel.add(btn_sql);
        authBtnPanel.add(btn_register);
        authBtnPanel.add(btn_login);
        gbc.gridx = 1; gbc.gridy = 3;
        mainPanel.add(authBtnPanel, gbc);

        // Ticket
        gbc.gridx = 0; gbc.gridy = 4;
        mainPanel.add(new JLabel("Ticket:"), gbc);
        txt_ticket = new JTextField(34);
        txt_ticket.setEditable(false);
        gbc.gridx = 1; gbc.gridy = 4;
        mainPanel.add(txt_ticket, gbc);

        // Plaintext
        gbc.gridx = 0; gbc.gridy = 5; gbc.anchor = GridBagConstraints.NORTHWEST;
        mainPanel.add(new JLabel("Plaintext:"), gbc);
        txt_plaintext = new JTextArea(3, 44);
        txt_plaintext.setLineWrap(true);
        txt_plaintext.setWrapStyleWord(true);
        gbc.gridx = 1; gbc.gridy = 5; gbc.weightx = 1.0; gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(new JScrollPane(txt_plaintext), gbc);

        // Key AES
        gbc.weightx = 0; gbc.weighty = 0; gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0; gbc.gridy = 6;
        mainPanel.add(new JLabel("Key AES:"), gbc);
        JPanel keyPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        txt_key = new JTextField(24);
        keyPanel.add(txt_key);
        keyPanel.add(new JLabel("   Mật khẩu để tạo khóa AES-256"));
        gbc.gridx = 1; gbc.gridy = 6;
        mainPanel.add(keyPanel, gbc);

        // Ciphertext
        gbc.gridx = 0; gbc.gridy = 7; gbc.anchor = GridBagConstraints.NORTHWEST;
        mainPanel.add(new JLabel("Ciphertext:"), gbc);
        txt_ciphertext = new JTextArea(3, 44);
        txt_ciphertext.setLineWrap(true);
        txt_ciphertext.setWrapStyleWord(true);
        gbc.gridx = 1; gbc.gridy = 7; gbc.weightx = 1.0; gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(new JScrollPane(txt_ciphertext), gbc);

        // Nút Encrypt / Decrypt / Save / Open
        JPanel cryptoBtnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        btn_encrypt = new JButton("Encrypt");
        btn_decrypt = new JButton("Decrypt");
        btn_save = new JButton("Save File");
        btn_open = new JButton("Open File");
        cryptoBtnPanel.add(btn_encrypt);
        cryptoBtnPanel.add(btn_decrypt);
        cryptoBtnPanel.add(btn_save);
        cryptoBtnPanel.add(btn_open);
        gbc.gridx = 1; gbc.gridy = 8; gbc.weightx = 0; gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(cryptoBtnPanel, gbc);

        // Trạng thái
        lbl_status = new JLabel("Sẵn sàng.");
        lbl_status.setPreferredSize(new Dimension(540, 36));
        gbc.gridx = 1; gbc.gridy = 9;
        mainPanel.add(lbl_status, gbc);

        // Sự kiện
        btn_sql.addActionListener(e -> new frm_KetNoiSQL().setVisible(true));
        btn_register.addActionListener(e -> btn_registerActionPerformed());
        btn_login.addActionListener(e -> btn_loginActionPerformed());
        btn_encrypt.addActionListener(e -> btn_encryptActionPerformed());
        btn_decrypt.addActionListener(e -> btn_decryptActionPerformed());
        btn_save.addActionListener(e -> btn_saveActionPerformed());
        btn_open.addActionListener(e -> btn_openActionPerformed());

        setContentPane(mainPanel);
        pack();
    }

    private void btn_registerActionPerformed() {
        try {
            String user = txt_username.getText().trim();
            UserDao.register(user, txt_password.getPassword());
            HistoryDao.tryInsert("AES", "REGISTER", user, 0, 0);
            lbl_status.setText("Register thành công: " + user + ". Bấm Login để đăng nhập.");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(),
                    "Dữ liệu không hợp lệ", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Lỗi SQL Server: " + ex.getMessage(),
                    "Lỗi SQL Server", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btn_loginActionPerformed() {
        try {
            String user = txt_username.getText().trim();
            if (UserDao.login(user, txt_password.getPassword())) {
                currentUser = user;
                txt_ticket.setText(UserDao.newTicket());
                HistoryDao.tryInsert("AES", "LOGIN", user, 0, 0);
                lbl_status.setText("Login thành công: " + user + ". Có thể dùng Encrypt/Decrypt.");
            } else {
                currentUser = null;
                txt_ticket.setText("");
                JOptionPane.showMessageDialog(this, "Sai username hoặc mật khẩu.",
                        "Login thất bại", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Lỗi SQL Server: " + ex.getMessage(),
                    "Lỗi SQL Server", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btn_encryptActionPerformed() {
        if (currentUser == null) {
            JOptionPane.showMessageDialog(this, "Hãy Login trước khi dùng AES.");
            return;
        }
        String plaintext = txt_plaintext.getText();
        if (plaintext.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Hãy nhập Plaintext trước khi mã hóa.");
            return;
        }
        try {
            String ciphertext = AESCipher.encrypt(plaintext, txt_key.getText());
            txt_ciphertext.setText(ciphertext);
            lbl_status.setText("<html>Đã mã hóa AES. " + HistoryDao.tryInsert("AES", "ENCRYPT", currentUser,
                    plaintext.length(), ciphertext.length()) + "</html>");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(),
                    "Dữ liệu không hợp lệ", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Mã hóa thất bại:\n" + ex.getMessage(),
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btn_decryptActionPerformed() {
        if (currentUser == null) {
            JOptionPane.showMessageDialog(this, "Hãy Login trước khi dùng AES.");
            return;
        }
        String ciphertext = txt_ciphertext.getText();
        if (ciphertext.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Hãy nhập Ciphertext trước khi giải mã.");
            return;
        }
        try {
            String plaintext = AESCipher.decrypt(ciphertext, txt_key.getText());
            txt_plaintext.setText(plaintext);
            lbl_status.setText("<html>Đã giải mã AES. " + HistoryDao.tryInsert("AES", "DECRYPT", currentUser,
                    ciphertext.length(), plaintext.length()) + "</html>");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(),
                    "Dữ liệu không hợp lệ", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Giải mã thất bại: sai Key hoặc bản mã bị sửa.\n" + ex.getMessage(),
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btn_saveActionPerformed() {
        String path = FileTextIO.saveText(this, txt_ciphertext, "aes_ciphertext.txt");
        if (path != null) {
            lbl_status.setText("Đã lưu tệp: " + path);
        }
    }

    private void btn_openActionPerformed() {
        String path = FileTextIO.openText(this, txt_ciphertext);
        if (path != null) {
            lbl_status.setText("Đã mở tệp: " + path);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new frm_AES().setVisible(true));
    }
}