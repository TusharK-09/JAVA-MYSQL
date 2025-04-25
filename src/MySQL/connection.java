package MySQL;

import java.sql.*;

public class connection {

    public static void main(String[] args) {

        Connection con;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root", "harshu@123");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM student");
            ResultSetMetaData rsmd = rs.getMetaData();
            int cols = rsmd.getColumnCount();

            for (int i = 1; i <= cols; i++) {
                System.out.print(rsmd.getColumnName(i) + "   ");
            }
            System.out.println();

            while (rs.next()) {
                for (int i = 1; i <= cols; i++) {
                    System.out.print(rs.getString(i) + "   ");
                }
                System.out.println();
            }

            con.close();

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
