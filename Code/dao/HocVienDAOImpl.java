package com.teamvietdev.qlhv.dao;

import com.teamvietdev.qlhv.model.HocVien;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

//Trien khai trong thiet ke va phat trien phan mem
//Tach biet giao dien va trien khai

public class HocVienDAOImpl implements HocVienDAO {

    private Object DriverManager;

    @Override
    //Lay ds đối tượng học viên từ cơ sở dữ liệu
    public List<HocVien> getList() {
        //Ket noi với cơ sở dữ liệu
        Connection cons = DBConnect.getConnection();
        //Dinh nghia cau lenh SQL để chọn tất cả các cột từ bảng hoc_vien
        String sql = "SELECT * FROM hoc_vien";
        //Khoi tao mot danh sách rỗng để chứa các dối tượng học viên
        List<HocVien> list = new ArrayList<>();
        try {
            //Tao mot ... để thực thi câu lệnh SQL
            PreparedStatement ps = (PreparedStatement) cons.prepareStatement(sql);
            //Thực thi câu lệnh để lấy kết quả
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HocVien hocVien = new HocVien();
                hocVien.setMa_hoc_vien(rs.getInt("ma_hoc_vien"));
                hocVien.setHo_ten(rs.getString("ho_ten"));
                hocVien.setSo_dien_thoai(rs.getString("so_dien_thoai"));
                
                hocVien.setDia_chi(rs.getString("dia_chi"));
                hocVien.setNgay_sinh(rs.getDate("ngay_sinh"));
                hocVien.setGioi_tinh(rs.getBoolean("gioi_tinh"));
                hocVien.setTinh_trang(rs.getBoolean("tinh_trang"));
                hocVien.setMa_nguoi_tao_tk(rs.getInt("ma_nguoi_tao_hv"));
                list.add(hocVien);
            }
            ps.close();
            cons.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
   
        
    public int createOrUpdate(HocVien hocVien) {
      

            if (!isValidMaNguoiTaoTk((int)hocVien.getMa_nguoi_tao_tk())) {
                JOptionPane.showMessageDialog(null, "Mã người tạo học viên không hợp lệ: " + hocVien.getMa_nguoi_tao_tk(),
                                  "Lỗi", JOptionPane.ERROR_MESSAGE);
                
            }

            String sql = "INSERT INTO hoc_vien(ma_hoc_vien, ho_ten, ngay_sinh, gioi_tinh, so_dien_thoai, dia_chi, tinh_trang, ma_nguoi_tao_hv) "
                       + "VALUES(?, ?, ?, ?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE ho_ten = VALUES(ho_ten), "
                       + "ngay_sinh = VALUES(ngay_sinh), gioi_tinh = VALUES(gioi_tinh), "
                       + "so_dien_thoai = VALUES(so_dien_thoai), dia_chi = VALUES(dia_chi), "
                       + "tinh_trang = VALUES(tinh_trang), ma_nguoi_tao_hv = VALUES(ma_nguoi_tao_hv)";

            try (Connection cons = DBConnect.getConnection();
                 PreparedStatement ps = cons.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

                ps.setInt(1, hocVien.getMa_hoc_vien());
                ps.setString(2, hocVien.getHo_ten());
                ps.setDate(3, hocVien.getNgay_sinh());
                ps.setBoolean(4, hocVien.isGioi_tinh());
                ps.setString(5, hocVien.getSo_dien_thoai());
                ps.setString(6, hocVien.getDia_chi());
                ps.setBoolean(7, hocVien.isTinh_trang());
                ps.setInt(8, hocVien.getMa_nguoi_tao_tk());
                ps.execute();

                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getInt(1); // Trả về khóa sinh ra
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return 0;

    }
     public List<HocVien> getListActiveStudents() {
          //Ket noi với cơ sở dữ liệu
        Connection cons = DBConnect.getConnection();
        List<HocVien> list = new ArrayList<>();
        String query = "SELECT * FROM hoc_vien WHERE tinh_trang = true"; // Chỉ lấy học viên còn hoạt động
        try {
            //Tao mot ... để thực thi câu lệnh SQL
            PreparedStatement ps = (PreparedStatement) cons.prepareStatement(query);
            //Thực thi câu lệnh để lấy kết quả
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HocVien hocVien = new HocVien();
                hocVien.setMa_hoc_vien(rs.getInt("ma_hoc_vien"));
                hocVien.setHo_ten(rs.getString("ho_ten"));
                hocVien.setSo_dien_thoai(rs.getString("so_dien_thoai"));
                hocVien.setDia_chi(rs.getString("dia_chi"));
                hocVien.setNgay_sinh(rs.getDate("ngay_sinh"));
                hocVien.setGioi_tinh(rs.getBoolean("gioi_tinh"));
                hocVien.setTinh_trang(rs.getBoolean("tinh_trang"));
                hocVien.setMa_nguoi_tao_tk( rs.getInt("ma_nguoi_tao_hv"));
                list.add(hocVien);
            }
            ps.close();
            cons.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }
     private boolean isValidMaNguoiTaoTk(int maNguoiTaoTk) {
            String query = "SELECT COUNT(*) FROM tai_khoan WHERE ma_tai_khoan = ?";

            try (Connection cons = DBConnect.getConnection();
                 PreparedStatement ps = cons.prepareStatement(query)) {

                ps.setInt(1, maNguoiTaoTk);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return rs.getInt(1) > 0; // Trả về true nếu tồn tại ít nhất một bản ghi
                    }
                }
            } catch (Exception e) {
                // Xử lý lỗi ở đây, có thể ném ra một ngoại lệ tùy chỉnh hoặc in ra log
                e.printStackTrace();
            }

            return false; // Mặc định là không hợp lệ
        }

}