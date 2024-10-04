package com.teamvietdev.qlhv.dao;


import com.sun.jdi.connect.spi.Connection;
import com.teamvietdev.qlhv.model.HocVien;
import com.teamvietdev.qlhv.model.TaiKhoan;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;

/**
 *
 * @author HP
 */
public class TaoTaiKhoanDAOImpl implements TaoTaiKhoanDAO {
    @Override
    public List<TaiKhoan> getList() {
        //Ket noi với cơ sở dữ liệu
        java.sql.Connection cons = DBConnect.getConnection();
        //Dinh nghia cau lenh SQL để chọn tất cả các cột từ bảng 
        String sql = "SELECT * FROM tai_khoan";
        //Khoi tao mot danh sách rỗng để chứa các dối tượng 
        List<TaiKhoan> list = new ArrayList<>();
        try {
            //Tao mot ... để thực thi câu lệnh SQL
            PreparedStatement ps = (PreparedStatement) cons.prepareStatement(sql);
            //Thực thi câu lệnh để lấy kết quả
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TaiKhoan taiKhoan = new TaiKhoan();
                taiKhoan.setMa_tai_khoan(rs.getInt("ma_tai_khoan"));
                taiKhoan.setTen_dang_nhap(rs.getString("ten_dang_nhap"));
                taiKhoan.setMat_khau(rs.getString("mat_khau"));
                taiKhoan.setTinh_trang(rs.getBoolean("tinh_trang"));
                taiKhoan.setTen_tk(rs.getString("ten_tk"));
                taiKhoan.setMa_nguoi_tao_tk(rs.getInt("ma_nguoi_tao_tk"));
                list.add(taiKhoan);
            }
            ps.close();
            rs.close();
            cons.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int createOrUpdate(TaiKhoan taiKhoan) {
         try {
            // Chèn bản ghi tai_khoan mới vào cơ sở dữ liệu
                java.sql.Connection cons = DBConnect.getConnection();
                String sql = "INSERT INTO tai_khoan(ma_tai_khoan, ten_dang_nhap, mat_khau, tinh_trang, ten_tk, ma_nguoi_tao_tk) "
                        + "VALUES(?, ?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE ten_dang_nhap = VALUES(ten_dang_nhap), "
                        + "mat_khau = VALUES(mat_khau), tinh_trang = VALUES(tinh_trang), ten_tk = VALUES(ten_tk), ma_nguoi_tao_tk = VALUES(ma_nguoi_tao_tk)";

                PreparedStatement ps = cons.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

                ps.setInt(1, taiKhoan.getMa_tai_khoan());
                ps.setString(2, taiKhoan.getTen_dang_nhap());
                ps.setString(3, taiKhoan.getMat_khau());
                ps.setBoolean(4, taiKhoan.isTinh_trang());
                ps.setString(5, taiKhoan.getTen_tk());
                ps.setInt(6, 1); // Đặt giá trị cho tham số thứ 6

                ps.execute();

                ResultSet rs = ps.getGeneratedKeys();
                int generatedKey = 0;
                if (rs.next()) {
                    generatedKey = rs.getInt(1);
                }

                ps.close();
                cons.close();
                return generatedKey;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }
    
    
    public List<TaiKhoan> getListActiveGV() {
        //Ket noi với cơ sở dữ liệu
        java.sql.Connection cons = DBConnect.getConnection();
        String query = "SELECT * FROM tai_khoan WHERE tinh_trang = true"; // Chỉ lấy học viên còn hoạt động
        
        //Khoi tao mot danh sách rỗng để chứa các dối tượng 
        List<TaiKhoan> list = new ArrayList<>();
        try {
            //Tao mot ... để thực thi câu lệnh SQL
            PreparedStatement ps = (PreparedStatement) cons.prepareStatement(query);
            //Thực thi câu lệnh để lấy kết quả
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TaiKhoan taiKhoan = new TaiKhoan();
                taiKhoan.setMa_tai_khoan(rs.getInt("ma_tai_khoan"));
                taiKhoan.setTen_dang_nhap(rs.getString("ten_dang_nhap"));
                taiKhoan.setMat_khau(rs.getString("mat_khau"));
                taiKhoan.setTinh_trang(rs.getBoolean("tinh_trang"));
                taiKhoan.setTen_tk(rs.getString("ten_tk"));
                taiKhoan.setMa_nguoi_tao_tk(rs.getInt("ma_nguoi_tao_tk"));
                list.add(taiKhoan);
            }
            ps.close();
            rs.close();
            cons.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

   
    
    


}
