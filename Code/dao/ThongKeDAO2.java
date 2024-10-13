/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.teamvietdev.qlhv.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ThongKeDAO2 {
    private Connection connection;

    public ThongKeDAO2(Connection connection) {
        this.connection = connection;
    }

    // Phương thức để lấy số học viên có tình trạng true từ bảng hoc_vien
    public int getSoHocVienFromHocVien() throws SQLException {
        String sql = "SELECT COUNT(*) FROM hoc_vien WHERE tinh_trang = true"; // Thêm điều kiện
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }

    // Phương thức để lấy số học viên có tình trạng true từ bảng khoa_hoc_ai
    public int getSoHocVienKhoaHocAi() throws SQLException {
        String sql = "SELECT COUNT(*) FROM khoa_hoc_ai WHERE tinh_trang = true";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }

    // Phương thức để lấy số học viên có tình trạng true từ bảng khoa_hoc_data
    public int getSoHocVienKhoaHocData() throws SQLException {
        String sql = "SELECT COUNT(*) FROM khoa_hoc_data WHERE tinh_trang = true";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }

    // Phương thức để lấy số học viên có tình trạng true từ bảng khoa_hoc_iot
    public int getSoHocVienKhoaHocIot() throws SQLException {
        String sql = "SELECT COUNT(*) FROM khoa_hoc_iot WHERE tinh_trang = true";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }

    // Phương thức để cập nhật số học viên cho khóa AI
    public void updateSoHocVienForKhoaAi() throws SQLException {
        int soHocVien = getSoHocVienKhoaHocAi() + getSoHocVienFromHocVien(); // Cộng thêm số học viên từ hoc_vien
        String sql = "UPDATE thong_ke SET so_hoc_vien = ? WHERE ten_khoa_hoc = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, soHocVien);
            stmt.setString(2, "Khóa AI");
            stmt.executeUpdate();
        }
    }

    // Phương thức để cập nhật số học viên cho khóa Data
    public void updateSoHocVienForKhoaData() throws SQLException {
        int soHocVien = getSoHocVienKhoaHocData() + getSoHocVienFromHocVien(); // Cộng thêm số học viên từ hoc_vien
        String sql = "UPDATE thong_ke SET so_hoc_vien = ? WHERE ten_khoa_hoc = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, soHocVien);
            stmt.setString(2, "Khóa BigData");
            stmt.executeUpdate();
        }
    }

    // Phương thức để cập nhật số học viên cho khóa IoT
    public void updateSoHocVienForKhoaIot() throws SQLException {
        int soHocVien = getSoHocVienKhoaHocIot() + getSoHocVienFromHocVien(); // Cộng thêm số học viên từ hoc_vien
        String sql = "UPDATE thong_ke SET so_hoc_vien = ? WHERE ten_khoa_hoc = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, soHocVien);
            stmt.setString(2, "Khóa IoT");
            stmt.executeUpdate();
        }
    }
    public void updateSoHocVienForTrungTam() throws SQLException {
        int soHocVien = getSoHocVienFromHocVien(); // Lấy số học viên từ hoc_vien
        String sql = "UPDATE thong_ke SET so_hoc_vien = ? WHERE ten_khoa_hoc = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, soHocVien);
            stmt.setString(2, "Số học viên đang học ở trung tâm"); // Tên khóa học
            stmt.executeUpdate();
        }
    }
}
