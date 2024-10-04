package com.teamvietdev.qlhv.dao;

/**
 *
 * @author HP
 */
import java.sql.Connection;
import java.sql.DriverManager;
//DAO (Data Access Object):
//Phần này chịu trách nhiệm truy cập và thao tác với cơ sở dữ liệu.
//Tách biệt logic truy cập dữ liệu khỏi Model, giúp mã nguồn dễ bảo trì.

//Model: Chịu trách nhiệm về dữ liệu và logic nghiệp vụ của ứng dụng.
//Nó xác định các đối tượng và mối quan hệ giữa chúng.

//DAO: Chịu trách nhiệm về việc truy cập dữ liệu từ cơ sở dữ liệu (DB). 
//DAO thực hiện các thao tác như thêm, sửa, xóa và truy vấn dữ liệu.
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