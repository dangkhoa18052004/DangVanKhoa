package week_01;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class frm_TongHop extends JFrame {

    private JComboBox<String> cbo_algorithm;
    private JLabel lbl_hint;
    private JTextArea txt_plaintext;
    private JTextField txt_key;
    private JTextArea txt_ciphertext;
    private JButton btn_encrypt;
    private JButton btn_decrypt;
    private JButton btn_open;
    private JButton btn_save;
    private JButton btn_sql;
    private JButton btn_history;
    private JLabel lbl_status;

    public frm_TongHop() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Chương trình tổng hợp - 6 thuật toán");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Tiêu đề
        JLabel lblTitle = new JLabel("CHƯƠNG TRÌNH TỔNG HỢP 6 THUẬT TOÁN CỔ ĐIỂN");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 3;
        mainPanel.add(lblTitle, gbc);

        // Combo Box Thuật toán
        JPanel algoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        JLabel lblAlgo = new JLabel("Thuật toán: ");
        cbo_algorithm = new JComboBox<>(CipherTongHop.ALGORITHMS);
        lbl_hint = new JLabel("Khóa mẫu: 3");
        algoPanel.add(lblAlgo);
        algoPanel.add(cbo_algorithm);
        algoPanel.add(lbl_hint);

        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 3;
        mainPanel.add(algoPanel, gbc);

        // Plaintext
        JLabel lblPlaintext = new JLabel("Plaintext:");
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 3;
        mainPanel.add(lblPlaintext, gbc);

        txt_plaintext = new JTextArea(3, 30);
        txt_plaintext.setLineWrap(true);
        txt_plaintext.setWrapStyleWord(true);
        JScrollPane scrollPlain = new JScrollPane(txt_plaintext);
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 3;
        gbc.weightx = 1.0; gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(scrollPlain, gbc);

        // Key
        JPanel keyPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        JLabel lblKey = new JLabel("Key: ");
        txt_key = new JTextField("3", 15);
        keyPanel.add(lblKey);
        keyPanel.add(txt_key);

        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 3;
        gbc.weightx = 0; gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(keyPanel, gbc);

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

        // Hàng 1 Nút bấm
        JPanel btnPanel1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        btn_encrypt = new JButton("Encrypt");
        btn_decrypt = new JButton("Decrypt");
        btn_open = new JButton("Open File");
        btn_save = new JButton("Save File");

        btnPanel1.add(btn_encrypt);
        btnPanel1.add(btn_decrypt);
        btnPanel1.add(btn_open);
        btnPanel1.add(btn_save);

        gbc.gridx = 0; gbc.gridy = 7; gbc.gridwidth = 3;
        gbc.weightx = 0; gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(btnPanel1, gbc);

        // Hàng 2 Nút bấm (SQL & Lịch sử)
        JPanel btnPanel2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        btn_sql = new JButton("Kết nối SQL Server");
        btn_history = new JButton("Xem lịch sử");
        btnPanel2.add(btn_sql);
        btnPanel2.add(btn_history);

        gbc.gridx = 0; gbc.gridy = 8; gbc.gridwidth = 3;
        mainPanel.add(btnPanel2, gbc);

        // Trạng thái
        lbl_status = new JLabel("Sẵn sàng.");
        gbc.gridx = 0; gbc.gridy = 9; gbc.gridwidth = 3;
        mainPanel.add(lbl_status, gbc);

        // Sự kiện
        cbo_algorithm.addActionListener(e -> cbo_algorithmActionPerformed());
        btn_encrypt.addActionListener(e -> btn_encryptActionPerformed());
        btn_decrypt.addActionListener(e -> btn_decryptActionPerformed());
        btn_open.addActionListener(e -> btn_openActionPerformed());
        btn_save.addActionListener(e -> btn_saveActionPerformed());

        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null);
    }

    private void cbo_algorithmActionPerformed() {
        String algorithm = (String) cbo_algorithm.getSelectedItem();
        txt_key.setText(CipherTongHop.sampleKey(algorithm));
        lbl_hint.setText("Khóa mẫu: " + CipherTongHop.sampleKey(algorithm));
    }

    private void btn_encryptActionPerformed() {
        String algorithm = (String) cbo_algorithm.getSelectedItem();
        String input = txt_plaintext.getText();
        try {
            String result = CipherTongHop.encrypt(algorithm, input, txt_key.getText());
            txt_ciphertext.setText(result);
            String saved = HistoryDao.tryInsert(algorithm, "ENCRYPT", input, result);
            lbl_status.setText("Đã mã hóa " + algorithm + ". " + saved);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(),
                    "Dữ liệu không hợp lệ", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btn_decryptActionPerformed() {
        String algorithm = (String) cbo_algorithm.getSelectedItem();
        String input = txt_ciphertext.getText();
        try {
            String result = CipherTongHop.decrypt(algorithm, input, txt_key.getText());
            txt_plaintext.setText(result);
            String saved = HistoryDao.tryInsert(algorithm, "DECRYPT", input, result);
            lbl_status.setText("Đã giải mã " + algorithm + ". " + saved);
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
        String path = FileTextIO.saveText(this, txt_ciphertext, "ciphertext.txt");
        if (path != null) {
            lbl_status.setText("Đã lưu tệp: " + path);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new frm_TongHop().setVisible(true));
    }
}