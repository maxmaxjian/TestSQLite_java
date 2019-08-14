import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Solution {

    private static final String DATABASE = "testDatabase";
    private static final String TABLE = "test";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_AGE = "age";

    public static void main(String[] args) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:" + DATABASE);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("drop table if exists " + TABLE);
            stmt.executeUpdate("create table " + TABLE +
                    "(" +
                    COLUMN_ID + " integer primary key autoincrement, " +
                    COLUMN_NAME + " text, " +
                    COLUMN_AGE + " integer" +
                    ")");
            stmt.executeUpdate("insert into " + TABLE + " values(1, 'John', 10)");
            stmt.executeUpdate("insert into " + TABLE + " values(2, 'James', 11)");
            stmt.executeUpdate("insert into " + TABLE + " values(null, 'Jason', 12)");
            stmt.executeUpdate("insert into " + TABLE + " values(null, 'Jason', 12)");

            ResultSet rs = stmt.executeQuery("select * from " + TABLE);
            while (rs.next()) {
                int id = rs.getInt(COLUMN_ID);
                String name = rs.getString(COLUMN_NAME);
                int age = rs.getInt(COLUMN_AGE);
                System.out.println("id = " + id + ", name = " + name + ", age = " + age);
            }
            stmt.close();
        } catch (Exception e) {
            System.err.println("Error while " + e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

}
