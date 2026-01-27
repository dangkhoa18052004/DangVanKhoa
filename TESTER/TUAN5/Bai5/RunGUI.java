import java.awt.*;
import javax.swing.*;

public class RunGUI extends JFrame {

    JTextField txtMaKH, txtHoTen, txtEmail, txtSDT, txtNgaySinh;
    JTextArea txtDiaChi;
    JPasswordField txtMatKhau, txtXacNhanMK;
    JCheckBox chkDieuKhoan;
    JButton btnDangKy, btnNhapLai;

    JRadioButton rdoNam, rdoNu;
    ButtonGroup grpGioiTinh;

    RegistrationForm logic = new RegistrationForm();

    public RunGUI() {
        setTitle("ĐĂNG KÝ TÀI KHOẢN KHÁCH HÀNG");
        setSize(520, 620);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // 1) Mã KH
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Mã Khách Hàng *"), gbc);
        txtMaKH = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(txtMaKH, gbc);

        // 2) Họ tên
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Họ và Tên *"), gbc);
        txtHoTen = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(txtHoTen, gbc);

        // 3) Email
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Email *"), gbc);
        txtEmail = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(txtEmail, gbc);

        // 4) SĐT
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Số điện thoại *"), gbc);
        txtSDT = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(txtSDT, gbc);

        // 5) Địa chỉ
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(new JLabel("Địa chỉ *"), gbc);
        txtDiaChi = new JTextArea(3, 20);
        txtDiaChi.setLineWrap(true);
        txtDiaChi.setWrapStyleWord(true);
        txtDiaChi.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        gbc.gridx = 1;
        gbc.gridy = 4;
        add(txtDiaChi, gbc);

        // 6) Mật khẩu
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(new JLabel("Mật khẩu *"), gbc);
        txtMatKhau = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 5;
        add(txtMatKhau, gbc);

        // 7) Xác nhận MK
        gbc.gridx = 0;
        gbc.gridy = 6;
        add(new JLabel("Xác nhận Mật khẩu *"), gbc);
        txtXacNhanMK = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 6;
        add(txtXacNhanMK, gbc);

        // 8) Ngày sinh
        gbc.gridx = 0;
        gbc.gridy = 7;
        add(new JLabel("Ngày sinh (dd/MM/yyyy)"), gbc);
        txtNgaySinh = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 7;
        add(txtNgaySinh, gbc);

        // 9) Giới tính
        gbc.gridx = 0;
        gbc.gridy = 8;
        add(new JLabel("Giới tính"), gbc);
        JPanel pnlGioiTinh = new JPanel(new FlowLayout(FlowLayout.LEFT));
        rdoNam = new JRadioButton("Nam");
        rdoNu = new JRadioButton("Nữ");
        grpGioiTinh = new ButtonGroup();
        grpGioiTinh.add(rdoNam);
        grpGioiTinh.add(rdoNu);
        pnlGioiTinh.add(rdoNam);
        pnlGioiTinh.add(rdoNu);
        gbc.gridx = 1;
        gbc.gridy = 8;
        add(pnlGioiTinh, gbc);

        // 10) Điều khoản
        gbc.gridx = 1;
        gbc.gridy = 9;
        chkDieuKhoan = new JCheckBox("Tôi đồng ý với các điều khoản dịch vụ *");
        add(chkDieuKhoan, gbc);

        // 11) Nút
        JPanel pnlNut = new JPanel();
        btnDangKy = new JButton("Đăng ký");
        btnDangKy.setBackground(Color.BLUE);
        btnDangKy.setForeground(Color.WHITE);

        btnNhapLai = new JButton("Nhập lại");
        pnlNut.add(btnDangKy);
        pnlNut.add(btnNhapLai);
        gbc.gridx = 1;
        gbc.gridy = 10;
        add(pnlNut, gbc);

        // ====== EVENT: Đăng ký ======
        btnDangKy.addActionListener(e -> {
            String maKH = txtMaKH.getText();
            String hoTen = txtHoTen.getText();
            String email = txtEmail.getText();
            String sdt = txtSDT.getText();
            String diaChi = txtDiaChi.getText();
            String matKhau = new String(txtMatKhau.getPassword());
            String xacNhan = new String(txtXacNhanMK.getPassword());
            String ngaySinh = txtNgaySinh.getText();
            boolean tos = chkDieuKhoan.isSelected();

            String gioiTinh = "";
            if (rdoNam.isSelected())
                gioiTinh = "Nam";
            else if (rdoNu.isSelected())
                gioiTinh = "Nữ";

            String ketQua = logic.validateAndRegister(
                    maKH, hoTen, email, sdt,
                    diaChi, matKhau, xacNhan,
                    ngaySinh, gioiTinh,
                    tos);

            if ("Đăng ký tài khoản thành công!".equals(ketQua)) {
                JOptionPane.showMessageDialog(this, ketQua, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, ketQua, "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });

        // ====== EVENT: Nhập lại ======
        btnNhapLai.addActionListener(e -> resetForm());
    }

    private void resetForm() {
        txtMaKH.setText("");
        txtHoTen.setText("");
        txtEmail.setText("");
        txtSDT.setText("");
        txtDiaChi.setText("");
        txtMatKhau.setText("");
        txtXacNhanMK.setText("");
        txtNgaySinh.setText("");
        chkDieuKhoan.setSelected(false);
        grpGioiTinh.clearSelection();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RunGUI().setVisible(true));
    }
}
