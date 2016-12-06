
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class hive_test {


    private static String dirverName =
            "org.apache.hive.jdbc.HiveDriver";

    public static void main(String[] args) throws SQLException {
        Connection conn = DriverManager.getConnection(
                "jdbc:hive2://120.92.44.128:10000/default",
                "hadoop", "");
        Statement stmt = conn.createStatement();
        ResultSet res = stmt.executeQuery(
                "SELECT ip FROM test_ray_regex_external limit 20");
        while (res.next()) {
            System.out.println(res.getString(1));
        }
    }
}


