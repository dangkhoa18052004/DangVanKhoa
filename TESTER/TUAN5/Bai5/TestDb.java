import java.sql.Connection;

public class TestDb {
    public static void main(String[] args) throws Exception {
        try (Connection c = Db.getConnection()) {
            System.out.println("CONNECTED OK = " + (c != null));
        }
    }
}
