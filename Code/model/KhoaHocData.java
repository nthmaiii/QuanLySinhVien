/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.teamvietdev.qlhv.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author HP
 */
public class KhoaHocData implements Serializable {
    private int ma_hoc_vien;
    private String ho_ten;
    private Date ngay_sinh;
    private boolean gioi_tinh;
    private boolean tinh_trang;
    private float diem;
    private Date ngay_bat_dau;
    private Date ngay_ket_thuc;
    private int khoa;

    public int getMa_hoc_vien() {
        return ma_hoc_vien;
    }

    public void setMa_hoc_vien(int ma_hoc_vien) {
        this.ma_hoc_vien = ma_hoc_vien;
    }

    public String getHo_ten() {
        return ho_ten;
    }

    public void setHo_ten(String ho_ten) {
        this.ho_ten = ho_ten;
    }

    public Date getNgay_sinh() {
        return ngay_sinh;
    }

    public void setNgay_sinh(Date ngay_sinh) {
        this.ngay_sinh = ngay_sinh;
    }

    public boolean isGioi_tinh() {
        return gioi_tinh;
    }

    public void setGioi_tinh(boolean gioi_tinh) {
        this.gioi_tinh = gioi_tinh;
    }

    public boolean isTinh_trang() {
        return tinh_trang;
    }

    public void setTinh_trang(boolean tinh_trang) {
        this.tinh_trang = tinh_trang;
    }

    public float getDiem() {
        return diem;
    }

    public void setDiem(float diem) {
        this.diem = diem;
    }

    public Date getNgay_bat_dau() {
        return ngay_bat_dau;
    }

    public void setNgay_bat_dau(Date ngay_bat_dau) {
        this.ngay_bat_dau = ngay_bat_dau;
    }

    public Date getNgay_ket_thuc() {
        return ngay_ket_thuc;
    }

    public void setNgay_ket_thuc(Date ngay_ket_thuc) {
        this.ngay_ket_thuc = ngay_ket_thuc;
    }

    public int getKhoa() {
        return khoa;
    }

    public void setKhoa(int khoa) {
        this.khoa = khoa;
    }

    @Override
    public String toString() {
        return "KhoaHocData{" + "ma_hoc_vien=" + ma_hoc_vien + ", ho_ten=" + ho_ten + ", ngay_sinh=" + ngay_sinh + ", gioi_tinh=" + gioi_tinh + ", tinh_trang=" + tinh_trang + ", diem=" + diem + ", ngay_bat_dau=" + ngay_bat_dau + ", ngay_ket_thuc=" + ngay_ket_thuc + ", khoa=" + khoa + '}';
    }
  
}


