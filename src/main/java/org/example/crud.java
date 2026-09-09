package org.example;
import java.sql.*;
import java.util.*;
public class crud {
    public static void main(String [] args) throws ClassNotFoundException, SQLException {

        Scanner sc = new Scanner(System.in);

        org.postgresql.Driver driver = new org.postgresql.Driver();
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/crudUser";
        String user = "postgres";
        String password = "1234";

        Connection conn =  DriverManager.getConnection(url, user, password);
        System.out.println("Connected to database successfully");

//      CREATE
        System.out.println("Please enter the userId of the user you want to add");
        int userid = sc.nextInt();
        sc.nextLine();
        System.out.println("Please enter the name of the user you want to add");
        String name = sc.nextLine();
        System.out.println("Please enter the email of the user you want to add");
        String email = sc.nextLine();
        System.out.println("Please enter the location of the user you want to add");
        String location = sc.nextLine();

        String query = "INSERT INTO users (userId, name, email, location) VALUES (?, ?, ?, ?)";

        PreparedStatement pst = conn.prepareStatement(query);
        pst.setInt(1, userid);
        pst.setString(2, name);
        pst.setString(3, email);
        pst.setString(4, location);

        pst.execute();
        System.out.println("User added successfully");

//      READ
        String readQuery = "SELECT * FROM users";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(readQuery);
        while (rs.next()) {
            int id = rs.getInt("userid");
            String nm = rs.getString("name");
            String em = rs.getString("email");
            String loc = rs.getString("location");

            System.out.print("ID: "+id);
            System.out.print(", Name: "+nm);
            System.out.print(", Email: "+em);
            System.out.print(", Location: "+loc);
            System.out.println();
        }



//      UPDATE
        System.out.println("Please enter the userId of the user you want to update");
        int update_id = sc.nextInt();
        sc.nextLine();
        System.out.println("Please enter the name of the user you want to update");
        String updateName = sc.nextLine();
        System.out.println("Please enter the email of the user you want to update");
        String updateEmail = sc.nextLine();
        System.out.println("Please enter the location of the user you want to update");
        String updateLocation = sc.nextLine();

        String updateQuery = "UPDATE users SET name=?, email=?, location=? WHERE userid=?";
        PreparedStatement pst2 = conn.prepareStatement(updateQuery);
        pst2.setString(1, updateName);
        pst2.setString(2, updateEmail);
        pst2.setString(3, updateLocation);
        pst2.setInt(4, update_id);

        pst2.execute();
        System.out.println("User updated successfully");

//        DELETE
        System.out.println("Please enter the userId of the user you want to delete");
        int delete_id = sc.nextInt();
        sc.nextLine();

        String deleteQuery = "DELETE FROM users WHERE userid=?";
        PreparedStatement pst3 = conn.prepareStatement(deleteQuery);
        pst3.setInt(1, delete_id);
        pst3.execute();
        System.out.println("User deleted successfully");

        conn.close();
        System.out.println("Connection to database closed successfully");








    }
}
