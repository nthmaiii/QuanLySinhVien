package com.teamvietdev.qlhv.dao;

/**
 *
 * @author HP
 */
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
//Tap ket noi den co so du lieu:
    public static Connection getConnection() {
        Connection cons = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cons = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/db_qlhv", "root", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cons;
    }

    public static void main(String[] args) {
        System.out.println(getConnection());
    }
    
}