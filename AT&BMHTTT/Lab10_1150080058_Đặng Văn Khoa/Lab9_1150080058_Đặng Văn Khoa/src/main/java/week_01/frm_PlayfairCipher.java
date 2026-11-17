package week_01;

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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class frm_PlayfairCipher extends JFrame {

    private JTextArea txt_plaintext;
    private JTextField txt_key;
    private JTextArea txt_matrix;
    private JButton btn_matrix;
    private JTextArea txt_ciphertext;
    private JButton btn_encrypt;
    private JButton btn_decrypt;
    private JButton btn_open;
    private JButton btn_save;
    private JLabel lbl_status;

    public frm_PlayfairCipher() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Playfair Cipher");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Tiêu đề
        JLabel lblTitle = new JLabel("MẬT MÃ PLAYFAIR");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 3;
        mainPanel.add(lblTitle, gbc);

        // Plaintext
        JLabel lblPlaintext = new JLabel("Plaintext:");
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 3;
        mainPanel.add(lblPlaintext, gbc);

        txt_plaintext = new JTextArea(3, 30);
        txt_plaintext.setLineWrap(true);
        txt_plaintext.setWrapStyleWord(true);
        JScrollPane scrollPlain = new JScrollPane(txt_plaintext);
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 3;
        gbc.weightx = 1.0; gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(scrollPlain, gbc);

        // Key
        JPanel keyPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        JLabel lblKey = new JLabel("Key: ");
        txt_key = new JTextField("KEYWORD", 10);
        JLabel lblHint = new JLabel("Chỉ gồm chữ A-Z, ví dụ KEYWORD");
        keyPanel.add(lblKey);
        keyPanel.add(txt_key);
        keyPanel.add(lblHint);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 3;
        gbc.weightx = 0; gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(keyPanel, gbc);

        // Key Matrix Section
        JPanel matrixPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        JLabel lblMatrix = new JLabel("Key Matrix:");
        txt_matrix = new JTextArea(5, 9);
        txt_matrix.setEditable(false);
        txt_matrix.setFont(new Font("Monospaced", Font.BOLD, 14));
        JScrollPane scrollMatrix = new JScrollPane(txt_matrix);
        btn_matrix = new JButton("Generate Key Matrix");

        matrixPanel.add(lblMatrix);
        matrixPanel.add(scrollMatrix);
        matrixPanel.add(btn_matrix);

        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 3;
        mainPanel.add(matrixPanel, gbc);

        // Ciphertext
        JLabel lblCiphertext = new JLabel("Ciphertext:");
        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 3;
        mainPanel.add(lblCiphertext, gbc);

        txt_ciphertext = new JTextArea(3, 30);
        txt_ciphertext.setLineWrap(true);
        txt_ciphertext.setWrapStyleWord(true);
        JScrollPane scrollCipher = new JScrollPane(txt_ciphertext);
        gbc.gridx = 0; gbc.gridy = 6; gbc.gridwidth = 3;
        gbc.weightx = 1.0; gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(scrollCipher, gbc);

        // Hàng nút bấm
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        btn_encrypt = new JButton("Encrypt");
        btn_decrypt = new JButton("Decrypt");
        btn_open = new JButton("Open Ciphertext File");
        btn_save = new JButton("Save Ciphertext File");

        btnPanel.add(btn_encrypt);
        btnPanel.add(btn_decrypt);
        btnPanel.add(btn_open);
        btnPanel.add(btn_save);

        gbc.gridx = 0; gbc.gridy = 7; gbc.gridwidth = 3;
        gbc.weightx = 0; gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(btnPanel, gbc);

        // Trạng thái
        lbl_status = new JLabel("Sẵn sàng.");
        gbc.gridx = 0; gbc.gridy = 8; gbc.gridwidth = 3;
        mainPanel.add(lbl_status, gbc);

        // Sự kiện nút
        btn_matrix.addActionListener(e -> btn_matrixActionPerformed());
        btn_encrypt.addActionListener(e -> btn_encryptActionPerformed());
        btn_decrypt.addActionListener(e -> btn_decryptActionPerformed());
        btn_open.addActionListener(e -> btn_openActionPerformed());
        btn_save.addActionListener(e -> btn_saveActionPerformed());

        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null);
    }

    private void btn_matrixActionPerformed() {
        try {
            txt_matrix.setText(PlayfairCipher.getKeyMatrix(txt_key.getText()));
            txt_matrix.setCaretPosition(0);
            lbl_status.setText("Đã tạo ma trận khóa 5x5 (I và J dùng chung ô).");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(),
                    "Khóa không hợp lệ", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btn_encryptActionPerformed() {
        String plaintext = txt_plaintext.getText();
        if (plaintext.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Hãy nhập Plaintext trước khi mã hóa.");
            return;
        }
        try {
            String key = txt_key.getText().trim();
            txt_ciphertext.setText(PlayfairCipher.encrypt(plaintext, key));
            lbl_status.setText("Đã mã hóa Playfair (bỏ dấu cách, J -> I, chèn X).");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(),
                    "Dữ liệu không hợp lệ", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btn_decryptActionPerformed() {
        String ciphertext = txt_ciphertext.getText();
        if (ciphertext.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Hãy nhập Ciphertext trước khi giải mã.");
            return;
        }
        try {
            String key = txt_key.getText().trim();
            txt_plaintext.setText(PlayfairCipher.decrypt(ciphertext, key));
            lbl_status.setText("Đã giải mã Playfair (còn ký tự đệm X).");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(),
                    "Dữ liệu không hợp lệ", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btn_openActionPerformed() {
        String path = FileTextIO.openText(this, txt_ciphertext);
        if (path != null) {
            lbl_status.setText("Đã mở tệp: " + path);
        }
    }

    private void btn_saveActionPerformed() {
        String path = FileTextIO.saveText(this, txt_ciphertext, "playfair_ciphertext.txt");
        if (path != null) {
            lbl_status.setText("Đã lưu tệp: " + path);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new frm_PlayfairCipher().setVisible(true));
    }
}