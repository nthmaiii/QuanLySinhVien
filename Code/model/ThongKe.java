/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.teamvietdev.qlhv.model;

/**
 *
 * @author ADMIN
 */
public class ThongKe {
    private String ten_khoa_hoc;
    private int so_hoc_vien;

    public String getTen_khoa_hoc() {
        return ten_khoa_hoc;
    }

    public void setTen_khoa_hoc(String ten_khoa_hoc) {
        this.ten_khoa_hoc = ten_khoa_hoc;
    }

    public int getSo_hoc_vien() {
        return so_hoc_vien;
    }

    @Override
    public String toString() {
        return "ThongKe{" + "ten_khoa_hoc=" + ten_khoa_hoc + ", so_hoc_vien=" + so_hoc_vien + '}';
    }
    

    public void setSo_hoc_vien(int so_hoc_vien) {
        this.so_hoc_vien = so_hoc_vien;
    }
    

    
}
