package MySQL;

import java.sql.*;

public class deleteDatabase {
    public static void main(String[] args) {

        Connection con;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root", "harshu@123");
            Statement st = con.createStatement();
            int rowsEffected = st.executeUpdate("delete from student where id = 4");
            if(rowsEffected == 1) {
                System.out.println("Deletion completed....");
            }
            else {
                System.out.println("Wrong Query...");
            }



            con.close();

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
