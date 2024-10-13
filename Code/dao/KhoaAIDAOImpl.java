/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.teamvietdev.qlhv.dao;

import com.teamvietdev.qlhv.model.HocVien;
import com.teamvietdev.qlhv.model.KhoaHocAI;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class KhoaAIDAOImpl implements KhoaAIDAO{

    @Override
    public List<KhoaHocAI> getList() {
        //Ket noi với cơ sở dữ liệu
        Connection cons = DBConnect.getConnection();
        //Dinh nghia cau lenh SQL để chọn tất cả các cột từ bảng hoc_vien
        String sql = "SELECT * FROM khoa_hoc_ai";
        //Khoi tao mot danh sách rỗng để chứa các dối tượng học viên
        List<KhoaHocAI> list = new ArrayList<>();
        try {
            //Tao mot ... để thực thi câu lệnh SQL
            PreparedStatement ps = (PreparedStatement) cons.prepareStatement(sql);
            //Thực thi câu lệnh để lấy kết quả
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KhoaHocAI khoaAI = new KhoaHocAI();
                khoaAI.setMa_hoc_vien(rs.getInt("ma_hoc_vien"));
                khoaAI.setHo_ten(rs.getString("ho_ten"));
                khoaAI.setNgay_sinh(rs.getDate("ngay_sinh"));
                khoaAI.setGioi_tinh(rs.getBoolean("gioi_tinh"));
                khoaAI.setTinh_trang(rs.getBoolean("tinh_trang"));
                khoaAI.setDiem(rs.getFloat("diem_tk"));
                khoaAI.setNgay_bat_dau(rs.getDate("ngay_bat_dau")); // Thêm trường ngày bắt đầu
                khoaAI.setNgay_bat_dau(rs.getDate("ngay_ket_thuc")); // Thêm trường ngày kết thúc
                khoaAI.setKhoa(rs.getInt("khoa")); // Thêm trường khóa
                list.add(khoaAI);
            }
            ps.close();
            cons.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int createOrUpdate(KhoaHocAI khoaAI) {
       
        String checkSql = "SELECT * FROM khoa_hoc_ai WHERE ma_hoc_vien = ?";
        
                try (Connection cons = DBConnect.getConnection();
                PreparedStatement checkPs = cons.prepareStatement(checkSql)) {

               checkPs.setInt(1, khoaAI.getMa_hoc_vien());
               ResultSet checkRs = checkPs.executeQuery();
/*
               if (checkRs.next()) {
                   // Nếu học viên tồn tại, cập nhật
                   String updateSql = "UPDATE khoa_hoc_ai SET ho_ten = ?, ngay_sinh = ?, gioi_tinh = ?, tinh_trang = ?, diem_tk = ?, "
                                    + "ngay_bat_dau = ?, ngay_ket_thuc = ?, khoa = ? WHERE ma_hoc_vien = ?";
                   try (PreparedStatement ps = cons.prepareStatement(updateSql)) {
                       ps.setString(1, khoaAI.getHo_ten());
                       ps.setDate(2, khoaAI.getNgay_sinh());
                       ps.setBoolean(3, khoaAI.isGioi_tinh());
                       ps.setBoolean(4, khoaAI.isTinh_trang());
                       ps.setFloat(5, khoaAI.getDiem());
                       ps.setDate(6, khoaAI.getNgay_bat_dau()); // Ngày bắt đầu
                       ps.setDate(7, khoaAI.getNgay_ket_thuc()); // Ngày kết thúc
                       ps.setInt(8, khoaAI.getKhoa()); // Khóa
                       ps.setInt(9, khoaAI.getMa_hoc_vien());
                       ps.executeUpdate();
                       return khoaAI.getMa_hoc_vien(); // Trả về mã học viên đã cập nhật
                   }
               } else {*/
                   // Nếu không tồn tại, chèn mới
                   String insertSql = "INSERT INTO khoa_hoc_ai(ma_hoc_vien, ho_ten, ngay_sinh, gioi_tinh, tinh_trang, diem_tk, "
                  + "ngay_bat_dau, ngay_ket_thuc, khoa) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?) "
                  + "ON DUPLICATE KEY UPDATE ho_ten = VALUES(ho_ten), "
                  + "ngay_sinh = VALUES(ngay_sinh), gioi_tinh = VALUES(gioi_tinh), "
                  + "tinh_trang = VALUES(tinh_trang), diem_tk = VALUES(diem_tk), "
                  + "ngay_bat_dau = VALUES(ngay_bat_dau), ngay_ket_thuc = VALUES(ngay_ket_thuc), "
                  + "khoa = VALUES(khoa)";
                   try (PreparedStatement ps = cons.prepareStatement(insertSql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                       ps.setInt(1, khoaAI.getMa_hoc_vien());
                       ps.setString(2, khoaAI.getHo_ten());
                       ps.setDate(3, khoaAI.getNgay_sinh());
                       ps.setBoolean(4, khoaAI.isGioi_tinh());
                       ps.setBoolean(5, khoaAI.isTinh_trang());
                       ps.setFloat(6, khoaAI.getDiem());
                       ps.setDate(7, khoaAI.getNgay_bat_dau()); // Ngày bắt đầu
                       ps.setDate(8, khoaAI.getNgay_ket_thuc()); // Ngày kết thúc
                       ps.setInt(9, khoaAI.getKhoa()); // Khóa
                       ps.executeUpdate();

                       try (ResultSet rs = ps.getGeneratedKeys()) {
                           if (rs.next()) {
                               return rs.getInt(1); // Trả về khóa sinh ra
                           }
                       }
                   
               }
           } catch (Exception ex) {
               // Ghi log chi tiết lỗi
               ex.printStackTrace();
               throw new RuntimeException("Có lỗi khi thêm/sửa dữ liệu: " + ex.getMessage());
           }
           return 0; // Nếu không có bản ghi nào được cập nhật hoặc chèn
}
    @Override
    public List<KhoaHocAI> getListActiveStudents() {
        //Ket noi với cơ sở dữ liệu
        Connection cons = DBConnect.getConnection();
        //Dinh nghia cau lenh SQL để chọn tất cả các cột từ bảng hoc_vien
        String sql = "SELECT * FROM khoa_hoc_ai WHERE tinh_trang = true";
        //Khoi tao mot danh sách rỗng để chứa các dối tượng học viên
        List<KhoaHocAI> list = new ArrayList<>();
        try {
            //Tao mot ... để thực thi câu lệnh SQL
            PreparedStatement ps = (PreparedStatement) cons.prepareStatement(sql);
            //Thực thi câu lệnh để lấy kết quả
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KhoaHocAI khoaAI = new KhoaHocAI();
                khoaAI.setMa_hoc_vien(rs.getInt("ma_hoc_vien"));
                khoaAI.setHo_ten(rs.getString("ho_ten"));
                khoaAI.setNgay_sinh(rs.getDate("ngay_sinh"));
                khoaAI.setGioi_tinh(rs.getBoolean("gioi_tinh"));
                khoaAI.setTinh_trang(rs.getBoolean("tinh_trang"));
                khoaAI.setDiem(rs.getFloat("diem_tk"));
                khoaAI.setNgay_bat_dau(rs.getDate("ngay_bat_dau")); // Thêm trường ngày bắt đầu
                khoaAI.setNgay_ket_thuc(rs.getDate("ngay_ket_thuc")); // Thêm trường ngày kết thúc
                khoaAI.setKhoa(rs.getInt("khoa")); // Thêm trường khóa
                list.add(khoaAI);
            }
            ps.close();
            cons.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public HocVien getHocVienByMa(int maHocVien) {
        String sql = "SELECT * FROM hoc_vien WHERE ma_hoc_vien = ?";
        HocVien hocVien = null;

        try (Connection cons = DBConnect.getConnection();
             PreparedStatement ps = cons.prepareStatement(sql)) {
             
            ps.setInt(1, maHocVien);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                hocVien = new HocVien();
                hocVien.setMa_hoc_vien(rs.getInt("ma_hoc_vien"));
                hocVien.setHo_ten(rs.getString("ho_ten"));
                hocVien.setNgay_sinh(rs.getDate("ngay_sinh"));
                hocVien.setGioi_tinh(rs.getBoolean("gioi_tinh"));
                // Gán các trường khác nếu cần
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            // Có thể thêm thông báo lỗi hoặc xử lý lỗi tại đây
        }

        return hocVien; // Trả về null nếu không tìm thấy
    }
    
}
