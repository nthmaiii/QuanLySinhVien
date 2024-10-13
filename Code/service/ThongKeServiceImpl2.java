/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.teamvietdev.qlhv.service;
import com.teamvietdev.qlhv.dao.ThongKeDAO2;

import java.sql.Connection;
import java.sql.SQLException;

public class ThongKeServiceImpl2 {
    private ThongKeDAO2 thongKeDAO;

    public ThongKeServiceImpl2(Connection connection) {
        this.thongKeDAO = new ThongKeDAO2(connection);
    }

    public void refreshSoHocVien() {
        try {
            thongKeDAO.updateSoHocVienForKhoaAi();
            thongKeDAO.updateSoHocVienForKhoaData();
            thongKeDAO.updateSoHocVienForKhoaIot();
            System.out.println("Cập nhật số học viên cho các khóa học thành công.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getSoHocVienKhoaHocAi() {
        try {
            return thongKeDAO.getSoHocVienKhoaHocAi();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getSoHocVienKhoaHocData() {
        try {
            return thongKeDAO.getSoHocVienKhoaHocData();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getSoHocVienKhoaHocIot() {
        try {
            return thongKeDAO.getSoHocVienKhoaHocIot();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
    public int getSoHocVienFromHocVien() {
        try {
            return thongKeDAO.getSoHocVienFromHocVien();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
}