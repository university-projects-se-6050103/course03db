import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;

public class Main {

    public static void main(String[] args) throws SQLException {
        try {
            String driverName = "org.apache.hive.jdbc.HiveDriver";
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }

        Connection con = DriverManager.getConnection("jdbc:hive2://localhost:10000/default", "hive", "cloudera");
        String tableName = "kkk";

        Statement stmt = con.createStatement();
        stmt.execute("drop table if exists " + tableName);
        stmt.execute("CREATE TABLE IF NOT EXISTS kkk( id int, test_id int, student_id int, value int ) row format delimited fields terminated by \",”;");

        // show tables
        String sql = "show tables '" + tableName + "'";
        System.out.println("Running: " + sql);

        ResultSet res = stmt.executeQuery(sql);
        if (res.next()) {
            System.out.println(res.getString(1));
        }

        sql = "describe " + tableName;
        System.out.println("Running: " + sql);

        res = stmt.executeQuery(sql);
        while (res.next()) {
            System.out.println(res.getString(1) + "\t" + res.getString(2));
        }

        sql = "LOAD DATA INPATH 'hdfs:///user/cloudera/data/scores.csv' OVERWRITE INTO TABLE kkk;";
        System.out.println("Running: " + sql);
        stmt.execute(sql);

        sql = "select * from " + tableName;
        System.out.println("Running: " + sql);

        res = stmt.executeQuery(sql);
        while (res.next()) {
            System.out.println(String.valueOf(res.getInt(1)) + "\t" + res.getString(2));
        }

        sql = "select count(1) from " + tableName;
        System.out.println("Running: " + sql);

        res = stmt.executeQuery(sql);
        while (res.next()) {
            System.out.println(res.getString(1));
        }
    }
}
