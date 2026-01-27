import org.junit.Test;
import static org.junit.Assert.*;

public class RegistrationFormTest {

    private final RegistrationForm form = new RegistrationForm();

    // Helper: tạo mã/email không trùng để test "thành công" (vì có check DB unique)
    private String uniqueCode() {
        return "u" + System.currentTimeMillis(); // dài > 6, chỉ chữ+số
    }

    private String uniqueEmail() {
        return "u" + System.currentTimeMillis() + "@email.com";
    }

    // TC01: Đăng ký thành công
    @Test
    public void testRegisterSuccess() {
        String code = uniqueCode();
        String email = uniqueEmail();

        String result = form.validateAndRegister(
                code,                   // maKH
                "Nguyễn Văn Trúc",       // hoTen (>=5)
                email,                  // email
                "0909123456",           // sdt (10-12, bắt đầu 0)
                "123 Đường ABC, Q1",    // diaChi
                "password123",          // matKhau (>=8)
                "password123",          // xacNhanMK
                "01/01/2000",           // ngaySinh (>=18)
                "Nam",                  // gioiTinh (optional)
                true                    // điều khoản
        );

        assertEquals("Đăng ký tài khoản thành công!", result);
    }

    // TC02: Mã KH quá ngắn
    @Test
    public void testCustomerCodeTooShort() {
        String result = form.validateAndRegister(
                "abc",
                "Nguyễn Văn Trúc",
                uniqueEmail(),
                "0909123456",
                "123 Đường ABC, Q1",
                "password123",
                "password123",
                "01/01/2000",
                "Nam",
                true
        );

        assertEquals("Mã khách hàng phải từ 6-10 ký tự", result);
    }

    // TC03: Mã KH chứa ký tự đặc biệt
    @Test
    public void testCustomerCodeSpecialChar() {
        String result = form.validateAndRegister(
                "user@#1",
                "Nguyễn Văn Trúc",
                uniqueEmail(),
                "0909123456",
                "123 Đường ABC, Q1",
                "password123",
                "password123",
                "01/01/2000",
                "Nam",
                true
        );

        assertEquals("Mã khách hàng chỉ cho phép chữ và số", result);
    }

    // TC04: SĐT không bắt đầu bằng 0
    @Test
    public void testPhoneNoLeadingZero() {
        String result = form.validateAndRegister(
                uniqueCode(),
                "Nguyễn Văn Trúc",
                uniqueEmail(),
                "9091234567",          // không bắt đầu 0
                "123 Đường ABC, Q1",
                "password123",
                "password123",
                "01/01/2000",
                "Nam",
                true
        );

        assertEquals("SĐT phải bắt đầu bằng 0 và dài 10-12 số", result);
    }

    // TC05: Mật khẩu xác nhận không khớp
    @Test
    public void testPasswordMismatch() {
        String result = form.validateAndRegister(
                uniqueCode(),
                "Nguyễn Văn Trúc",
                uniqueEmail(),
                "0909123456",
                "123 Đường ABC, Q1",
                "password123",
                "password888",
                "01/01/2000",
                "Nam",
                true
        );

        assertEquals("Mật khẩu xác nhận không khớp", result);
    }

    // TC06: Chưa đủ 18 tuổi
    @Test
    public void testUnderAge() {
        String result = form.validateAndRegister(
                uniqueCode(),
                "Nguyễn Văn Trúc",
                uniqueEmail(),
                "0909123456",
                "123 Đường ABC, Q1",
                "password123",
                "password123",
                "01/01/2010",
                "Nam",
                true
        );

        assertEquals("Người dùng phải đủ 18 tuổi", result);
    }

    // TC07: Chưa tick điều khoản
    @Test
    public void testNotAcceptTos() {
        String result = form.validateAndRegister(
                uniqueCode(),
                "Nguyễn Văn Trúc",
                uniqueEmail(),
                "0909123456",
                "123 Đường ABC, Q1",
                "password123",
                "password123",
                "01/01/2000",
                "Nam",
                false
        );

        assertEquals("Bạn chưa đồng ý điều khoản", result);
    }
}
