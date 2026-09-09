package org.example;

import java.sql.*;
import java.util.*;

public class crudfunctionbased {

    // Database details
    static String url = "jdbc:postgresql://localhost:5432/crudUser";
    static String user = "postgres";
    static String password = "1234";


    // =========================
    // CREATE
    // =========================

    public static void createUser(Connection conn, Scanner sc)
            throws SQLException {

        System.out.println("Please enter the userId of the user you want to add");
        int userid = sc.nextInt();
        sc.nextLine();

        System.out.println("Please enter the name of the user you want to add");
        String name = sc.nextLine();

        System.out.println("Please enter the email of the user you want to add");
        String email = sc.nextLine();

        System.out.println("Please enter the location of the user you want to add");
        String location = sc.nextLine();


        String query =
                "INSERT INTO users (userId, name, email, location) VALUES (?, ?, ?, ?)";

        PreparedStatement pst = conn.prepareStatement(query);

        pst.setInt(1, userid);
        pst.setString(2, name);
        pst.setString(3, email);
        pst.setString(4, location);

        pst.executeUpdate();

        System.out.println("User added successfully");

        pst.close();
    }


    // =========================
    // READ
    // =========================

    public static void readUsers(Connection conn)
            throws SQLException {

        String readQuery = "SELECT * FROM users";

        Statement st = conn.createStatement();

        ResultSet rs = st.executeQuery(readQuery);

        System.out.println("\n========== USERS ==========");

        while (rs.next()) {

            int id = rs.getInt("userid");
            String nm = rs.getString("name");
            String em = rs.getString("email");
            String loc = rs.getString("location");

            System.out.print("ID: " + id);
            System.out.print(", Name: " + nm);
            System.out.print(", Email: " + em);
            System.out.print(", Location: " + loc);
            System.out.println();
        }

        rs.close();
        st.close();
    }


    // =========================
    // UPDATE
    // =========================

    public static void updateUser(Connection conn, Scanner sc)
            throws SQLException {

        System.out.println("Please enter the userId of the user you want to update");
        int update_id = sc.nextInt();
        sc.nextLine();

        System.out.println("Please enter the name of the user you want to update");
        String updateName = sc.nextLine();

        System.out.println("Please enter the email of the user you want to update");
        String updateEmail = sc.nextLine();

        System.out.println("Please enter the location of the user you want to update");
        String updateLocation = sc.nextLine();


        String updateQuery =
                "UPDATE users SET name=?, email=?, location=? WHERE userid=?";

        PreparedStatement pst = conn.prepareStatement(updateQuery);

        pst.setString(1, updateName);
        pst.setString(2, updateEmail);
        pst.setString(3, updateLocation);
        pst.setInt(4, update_id);

        int rowsUpdated = pst.executeUpdate();

        if (rowsUpdated > 0) {
            System.out.println("User updated successfully");
        } else {
            System.out.println("User with ID " + update_id + " not found");
        }

        pst.close();
    }


    // =========================
    // DELETE
    // =========================

    public static void deleteUser(Connection conn, Scanner sc)
            throws SQLException {

        System.out.println("Please enter the userId of the user you want to delete");
        int delete_id = sc.nextInt();
        sc.nextLine();


        String deleteQuery =
                "DELETE FROM users WHERE userid=?";

        PreparedStatement pst = conn.prepareStatement(deleteQuery);

        pst.setInt(1, delete_id);

        int rowsDeleted = pst.executeUpdate();

        if (rowsDeleted > 0) {
            System.out.println("User deleted successfully");
        } else {
            System.out.println("User with ID " + delete_id + " not found");
        }

        pst.close();
    }


    // =========================
    // MAIN
    // =========================

    public static void main(String[] args)
            throws ClassNotFoundException, SQLException {

        Scanner sc = new Scanner(System.in);

        // Load PostgreSQL Driver
        Class.forName("org.postgresql.Driver");

        // Create Connection
        Connection conn =
                DriverManager.getConnection(url, user, password);

        System.out.println("Connected to database successfully");
        // CREATE
        createUser(conn, sc);


        // READ
        readUsers(conn);


        // UPDATE
        updateUser(conn, sc);


        // DELETE
        deleteUser(conn, sc);


        // Close connection
        conn.close();
        sc.close();

        System.out.println("Connection to database closed successfully");
    }
}

