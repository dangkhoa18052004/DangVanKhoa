import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ShopVNRegisterForm extends JFrame {

    private JTextField txtFullName;
    private JTextField txtUsername;
    private JTextField txtEmail;
    private JTextField txtPhone;
    private JPasswordField txtPassword;
    private JPasswordField txtConfirmPassword;
    private JTextField txtDob;
    private JRadioButton rdoMale;
    private JRadioButton rdoFemale;
    private JRadioButton rdoSecret;
    private JTextField txtReferralCode;
    private JCheckBox chkTerms;
    private JButton btnViewTerms;
    private JButton btnRegister;

    public ShopVNRegisterForm() {
        setTitle("Form Đăng Ký Tài Khoản - ShopVN.vn");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(550, 650);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        JLabel lblTitle = new JLabel("FORM ĐĂNG KÝ TÀI KHOẢN - SHOPVN", JLabel.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        mainPanel.add(lblTitle, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(new EmptyBorder(20, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int row = 0;

        // Họ và tên
        gbc.gridx = 0;
        gbc.gridy = row;
        formPanel.add(new JLabel("Họ và tên (*):"), gbc);

        txtFullName = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = row++;
        formPanel.add(txtFullName, gbc);

        // Tên đăng nhập
        gbc.gridx = 0;
        gbc.gridy = row;
        formPanel.add(new JLabel("Tên đăng nhập (*):"), gbc);

        txtUsername = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = row++;
        formPanel.add(txtUsername, gbc);

        // Email
        gbc.gridx = 0;
        gbc.gridy = row;
        formPanel.add(new JLabel("Email (*):"), gbc);

        txtEmail = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = row++;
        formPanel.add(txtEmail, gbc);

        // Số điện thoại
        gbc.gridx = 0;
        gbc.gridy = row;
        formPanel.add(new JLabel("Số điện thoại (*):"), gbc);

        txtPhone = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = row++;
        formPanel.add(txtPhone, gbc);

        // Mật khẩu
        gbc.gridx = 0;
        gbc.gridy = row;
        formPanel.add(new JLabel("Mật khẩu (*):"), gbc);

        txtPassword = new JPasswordField();
        gbc.gridx = 1;
        gbc.gridy = row++;
        formPanel.add(txtPassword, gbc);

        // Xác nhận mật khẩu
        gbc.gridx = 0;
        gbc.gridy = row;
        formPanel.add(new JLabel("Xác nhận mật khẩu (*):"), gbc);

        txtConfirmPassword = new JPasswordField();
        gbc.gridx = 1;
        gbc.gridy = row++;
        formPanel.add(txtConfirmPassword, gbc);

        // Ngày sinh
        gbc.gridx = 0;
        gbc.gridy = row;
        formPanel.add(new JLabel("Ngày sinh:"), gbc);

        txtDob = new JTextField();
        txtDob.setToolTipText("dd/mm/yyyy");
        gbc.gridx = 1;
        gbc.gridy = row++;
        formPanel.add(txtDob, gbc);

        // Giới tính
        gbc.gridx = 0;
        gbc.gridy = row;
        formPanel.add(new JLabel("Giới tính:"), gbc);

        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        rdoMale = new JRadioButton("Nam");
        rdoFemale = new JRadioButton("Nữ");
        rdoSecret = new JRadioButton("Không muốn tiết lộ");

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(rdoMale);
        genderGroup.add(rdoFemale);
        genderGroup.add(rdoSecret);

        genderPanel.add(rdoMale);
        genderPanel.add(rdoFemale);
        genderPanel.add(rdoSecret);

        gbc.gridx = 1;
        gbc.gridy = row++;
        formPanel.add(genderPanel, gbc);

        // Mã giới thiệu
        gbc.gridx = 0;
        gbc.gridy = row;
        formPanel.add(new JLabel("Mã giới thiệu:"), gbc);

        txtReferralCode = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = row++;
        formPanel.add(txtReferralCode, gbc);

        // Điều khoản
        gbc.gridx = 0;
        gbc.gridy = row;
        formPanel.add(new JLabel("Điều khoản (*):"), gbc);

        JPanel termsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        chkTerms = new JCheckBox("Tôi đồng ý Điều khoản");
        btnViewTerms = new JButton("Xem Điều khoản");

        termsPanel.add(chkTerms);
        termsPanel.add(btnViewTerms);

        gbc.gridx = 1;
        gbc.gridy = row++;
        formPanel.add(termsPanel, gbc);

        // Nút đăng ký
        btnRegister = new JButton("Đăng ký");
        gbc.gridx = 1;
        gbc.gridy = row++;
        formPanel.add(btnRegister, gbc);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        add(mainPanel);

        // Sự kiện nút Xem Điều khoản
        btnViewTerms.addActionListener(e -> {
            JOptionPane.showMessageDialog(
                    this,
                    "ĐIỀU KHOẢN SỬ DỤNG\n\n"
                            + "1. Người dùng cung cấp thông tin chính xác.\n"
                            + "2. Không sử dụng tài khoản vào mục đích gian lận.\n"
                            + "3. Hệ thống có quyền khóa tài khoản nếu vi phạm.",
                    "Điều khoản sử dụng",
                    JOptionPane.INFORMATION_MESSAGE);
        });

        // Sự kiện nút Đăng ký
        btnRegister.addActionListener(e -> {
            String fullName = txtFullName.getText().trim();
            String username = txtUsername.getText().trim();
            String email = txtEmail.getText().trim();
            String phone = txtPhone.getText().trim();
            String password = new String(txtPassword.getPassword());
            String confirmPassword = new String(txtConfirmPassword.getPassword());

            if (fullName.isEmpty() || username.isEmpty() || email.isEmpty()
                    || phone.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Vui lòng nhập đầy đủ các trường bắt buộc.",
                        "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!chkTerms.isSelected()) {
                JOptionPane.showMessageDialog(this,
                        "Bạn phải đồng ý Điều khoản trước khi đăng ký.",
                        "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            JOptionPane.showMessageDialog(this,
                    "Đăng ký thành công.",
                    "Thông báo",
                    JOptionPane.INFORMATION_MESSAGE);
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ShopVNRegisterForm().setVisible(true);
        });
    }
}