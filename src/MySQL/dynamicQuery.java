package MySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class dynamicQuery {

        public static void main(String[] args) {

            Connection con;
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root", "harshu@123");

                if(con != null) System.out.println("Connected");
                else System.out.println("not connected");

                Scanner sc = new Scanner(System.in);
                int id = sc.nextInt();
                String name = sc.next();
                int age = sc.nextInt();



                PreparedStatement preparedStatement = con.prepareStatement("select * from student where id =?");
                preparedStatement.setInt(1,id);
//                preparedStatement.setString(2,name);
//                preparedStatement.setInt(3,age);
                ResultSet resultSet = preparedStatement.executeQuery();
                while(resultSet.next()){
                    System.out.println(resultSet.getInt(1)+" " + resultSet.getString(2)+ " "+
                            resultSet.getInt(3));
                }
//                int rowsEffected = preparedStatement.executeUpdate();
//                if(rowsEffected == 1){
//                    System.out.println("Data inserted");
//                }

            } catch (Exception exception) {
                System.out.println(exception);
            }
        }}