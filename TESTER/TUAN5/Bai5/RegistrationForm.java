import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class RegistrationForm {

    private final CustomerRepository repo = new CustomerRepository();

    /**
     * Validate + lưu DB
     * Trả về: thông báo lỗi cụ thể hoặc "Đăng ký tài khoản thành công!"
     */
    public String validateAndRegister(String maKH, String hoTen, String email, String sdt,
            String diaChi, String matKhau, String xacNhanMK,
            String ngaySinhStr, String gioiTinh,
            boolean daDongYDieuKhoan) {

        // 1) Mã KH (Required, 6-10, a-zA-Z0-9, unique)
        if (isBlank(maKH))
            return "Mã khách hàng không được để trống";
        if (maKH.length() < 6 || maKH.length() > 10)
            return "Mã khách hàng phải từ 6-10 ký tự";
        if (!maKH.matches("^[a-zA-Z0-9]+$"))
            return "Mã khách hàng chỉ cho phép chữ và số";

        // 2) Họ và Tên (Required, 5-50, cho phép tiếng Việt + khoảng trắng)
        if (isBlank(hoTen))
            return "Họ và tên không được để trống";
        if (hoTen.length() < 5 || hoTen.length() > 50)
            return "Họ và tên phải từ 5-50 ký tự";
        if (!hoTen.matches("^[\\p{L} ]+$"))
            return "Họ và tên chỉ được chứa chữ và khoảng trắng";

        // 3) Email (Required, format, unique)
        if (isBlank(email))
            return "Email không được để trống";
        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"))
            return "Email không hợp lệ";

        // 4) Số điện thoại (Required, số, 10-12, bắt đầu 0)
        if (isBlank(sdt))
            return "SĐT không được để trống";
        if (!sdt.matches("^[0-9]+$"))
            return "SĐT chỉ được chứa số";
        if (!sdt.startsWith("0"))
            return "SĐT phải bắt đầu bằng 0";
        if (sdt.length() < 10 || sdt.length() > 12)
            return "SĐT phải từ 10-12 số";

        // 5) Địa chỉ (Required, max 255)
        if (isBlank(diaChi))
            return "Địa chỉ không được để trống";
        if (diaChi.length() > 255)
            return "Địa chỉ tối đa 255 ký tự";

        // 6) Mật khẩu (Required, min 8)
        if (isBlank(matKhau))
            return "Mật khẩu không được để trống";
        if (matKhau.length() < 8)
            return "Mật khẩu phải ít nhất 8 ký tự";

        // 7) Xác nhận mật khẩu (Required, match)
        if (isBlank(xacNhanMK))
            return "Xác nhận mật khẩu không được để trống";
        if (!matKhau.equals(xacNhanMK))
            return "Mật khẩu xác nhận không khớp";

        // 8) Ngày sinh (Optional, nếu nhập phải đủ 18 tuổi)
        LocalDate dob = null;
        if (!isBlank(ngaySinhStr)) {
            dob = parseDateFlexible(ngaySinhStr.trim());
            if (dob == null)
                return "Ngày sinh không đúng định dạng (dd/MM/yyyy hoặc MM/dd/yyyy)";

            int age = Period.between(dob, LocalDate.now()).getYears();
            if (age < 18)
                return "Người dùng phải đủ 18 tuổi";
        }

        // 9) Giới tính (Optional) -> không bắt buộc nên không validate

        // 10) Điều khoản (Required checked)
        if (!daDongYDieuKhoan)
            return "Bạn chưa đồng ý điều khoản";

        // ====== CHECK DB TRÙNG + INSERT ======
        try {
            if (repo.existsCustomerCode(maKH))
                return "Mã khách hàng đã tồn tại";
            if (repo.existsEmail(email))
                return "Email đã tồn tại";

            String passwordHash = sha256(matKhau);
            repo.insertCustomer(maKH, hoTen, email, sdt, diaChi, passwordHash, dob, gioiTinh);

            return "Đăng ký tài khoản thành công!";
        } catch (Exception ex) {
            // Lỗi DB
            return "Lỗi kết nối/lưu dữ liệu: " + ex.getMessage();
        }
    }

    // ===== Helpers =====

    private static boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }

    private static LocalDate parseDateFlexible(String s) {
        // Hỗ trợ 2 kiểu: dd/MM/yyyy hoặc MM/dd/yyyy
        DateTimeFormatter f1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter f2 = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        try {
            return LocalDate.parse(s, f1);
        } catch (Exception ignored) {
        }
        try {
            return LocalDate.parse(s, f2);
        } catch (Exception ignored) {
        }
        return null;
    }

    private static String sha256(String raw) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(raw.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : hash)
                sb.append(String.format("%02x", b));
            return sb.toString();
        } catch (Exception e) {
            // fallback (không nên xảy ra)
            return raw;
        }
    }
}
