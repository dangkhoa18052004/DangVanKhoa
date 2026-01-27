import java.sql.*;
import java.time.LocalDate;

public class CustomerRepository {

    public boolean existsCustomerCode(String code) throws SQLException {
        String sql = "SELECT 1 FROM customers WHERE customer_code = ? LIMIT 1";
        try (Connection con = Db.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, code);
            return ps.executeQuery().next();
        }
    }

    public boolean existsEmail(String email) throws SQLException {
        String sql = "SELECT 1 FROM customers WHERE email = ? LIMIT 1";
        try (Connection con = Db.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, email);
            return ps.executeQuery().next();
        }
    }

    public void insertCustomer(String code, String fullName, String email, String phone,
            String address, String passwordHash,
            LocalDate dob, String gender) throws SQLException {

        String sql = "INSERT INTO customers(customer_code, full_name, email, phone, address, password_hash, dob, gender) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = Db.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, code);
            ps.setString(2, fullName);
            ps.setString(3, email);
            ps.setString(4, phone);
            ps.setString(5, address);
            ps.setString(6, passwordHash);

            if (dob != null)
                ps.setDate(7, Date.valueOf(dob));
            else
                ps.setNull(7, Types.DATE);

            if (gender != null && !gender.isBlank())
                ps.setString(8, gender);
            else
                ps.setNull(8, Types.VARCHAR);

            ps.executeUpdate();
        }
    }
}
