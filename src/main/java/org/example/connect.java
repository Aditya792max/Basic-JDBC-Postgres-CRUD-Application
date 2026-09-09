package org.example;

import java.util.*;
import java.sql.*;
public class connect{
    public static void main(String [] args) throws ClassNotFoundException, SQLException {
//        Steps
//        1.Import packages
//        2.Load Driver
//        3.Register Driver
//        4.Create Connection
//        5.Create Statement
//        6.Excecute Statement
//        7.Close Connection

        org.postgresql.Driver driver = new org.postgresql.Driver();

        String url = "jdbc:postgresql://localhost:5432/JDBCDemo1";
        String user="postgres";
        String pass="1234";
        String query = "SELECT name from Student where id=101";
        String query2 = "SELECT * FROM Student";

        Class.forName("org.postgresql.Driver");
        Connection conn = DriverManager.getConnection(url,user,pass);
        System.out.println("Connected to Database Successfull");

        Statement st = conn.createStatement();
        st.executeQuery(query);
        ResultSet rs = st.executeQuery(query);
        System.out.println(rs.next());
        String name = rs.getString("name");
        System.out.println(name);


        ResultSet rs2 = st.executeQuery(query2);
        while (rs2.next()) {
            int id = rs2.getInt("id");
            String n = rs2.getString("name");
            int roll = rs2.getInt("roll");
            String address = rs2.getString("address");
            int age = rs2.getInt("age");

            System.out.print("ID: " + id +", ");
            System.out.print("Name: " + n+", ");
            System.out.print("Roll: " + roll+", ");
            System.out.print("Address: " + address+", ");
            System.out.print("Age: " + age);
            System.out.println();
        }


//        String insertQuery = "INSERT INTO Student (id, name, roll, address, age) " +"VALUES (104, 'Aditya', 4, 'Kolkata', 24)";
//
//        int rows = st.executeUpdate(insertQuery);
//        System.out.println(rows + " row inserted");

        conn.close();
        System.out.println("Connection Closing to Database Successfull");

    }
}