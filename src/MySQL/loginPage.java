package MySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import io.github.cdimascio.dotenv.Dotenv;

public class loginPage {
    public static void main(String[] args) {
        try {
            Dotenv dotenv = Dotenv.load();
            String username = dotenv.get("MYSQL_USERNAME");  // Get username from .env
            String passwd = dotenv.get("MYSQL_PASSWORD");   // Get password from .env
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish the connection to the database
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", username, passwd);

            // Display menu options
            System.out.println("Enter your choice:");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();
            sc.nextLine(); // Consume the newline
            switch (choice) {
                case 1:
                    registerUser(con, sc);
                    break;
                case 2:
                    loginUser(con, sc);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    // Method to register a user
    public static void registerUser(Connection con, Scanner sc) {
        try {
            System.out.println("------Registration------");
            System.out.println("Enter username, email, password:");
            String username = sc.nextLine();
            String email = sc.nextLine();
            String password = sc.nextLine();

            // Insert user into the database
            String query = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, email);
            stmt.setString(3, password);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Registration successful!");
            } else {
                System.out.println("Registration failed. Try again.");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Method to login a user
    public static void loginUser(Connection con, Scanner sc) {
        try {
            System.out.println("------Login------");
            System.out.println("Enter email and password:");
            String email = sc.nextLine();
            String password = sc.nextLine();

            // Check if the user exists in the database
            String query = "SELECT * FROM users WHERE email = ? AND password = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, email);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("Login successful! Welcome, " + rs.getString("username"));
            } else {
                System.out.println("Invalid email or password.");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
