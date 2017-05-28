import java.sql.SQLException;
import java.sql.Connection;
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

        Connection con = DriverManager.getConnection("jdbc:hive2://172.17.0.1:10000/default", "hive", "cloudera");
        Statement stmt = con.createStatement();

        stmt.execute("drop table if exists scores");
        stmt.execute("CREATE TABLE IF NOT EXISTS scores( id int, test_id int, student_id int, value int ) row format delimited fields terminated by \",\"");
        stmt.execute("LOAD DATA INPATH 'hdfs:///user/cloudera/data/scores.csv' OVERWRITE INTO TABLE scores");

        stmt.execute("drop table if exists students");
        stmt.execute("CREATE TABLE IF NOT EXISTS students( id int, group varchar(10), full_name varchar(250), avg_score double ) row format delimited fields terminated by \",\"");
        stmt.execute("LOAD DATA INPATH 'hdfs:///user/cloudera/data/students.csv' OVERWRITE INTO TABLE students");

        stmt.execute("drop table if exists subjects");
        stmt.execute("CREATE TABLE IF NOT EXISTS subjects( id int, name varchar(250), teacher_id int ) row format delimited fields terminated by \",\"");
        stmt.execute("LOAD DATA INPATH 'hdfs:///user/cloudera/data/subjects.csv' OVERWRITE INTO TABLE subjects");
    }
}
