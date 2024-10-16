package com.teamvietdev.qlhv.model;

/**
 *
 * @author HP
 */
public class TaiKhoan {

    public void setMa_nguoi_tao_tk(int ma_nguoi_tao_tk) {
        this.ma_nguoi_tao_tk = ma_nguoi_tao_tk;
    }

    public int getMa_nguoi_tao_tk() {
        return ma_nguoi_tao_tk;
    }

    private int ma_tai_khoan;
    private String ten_dang_nhap;
    private String mat_khau;
    private boolean tinh_trang;
    private String ten_tk;
    private int ma_nguoi_tao_tk;
    public int getMa_tai_khoan() {
        return ma_tai_khoan;
    }

    public String getTen_tk() {
        return ten_tk;
    }

    public void setTen_tk(String ten_tk) {
        this.ten_tk = ten_tk;
    }

    public void setMa_tai_khoan(int ma_tai_khoan) {
        this.ma_tai_khoan = ma_tai_khoan;
    }

    public String getTen_dang_nhap() {
        return ten_dang_nhap;
    }

    public void setTen_dang_nhap(String ten_dang_nhap) {
        this.ten_dang_nhap = ten_dang_nhap;
    }

    public String getMat_khau() {
        return mat_khau;
    }

    public void setMat_khau(String mat_khau) {
        this.mat_khau = mat_khau;
    }

    public boolean isTinh_trang() {
        return tinh_trang;
    }

    public void setTinh_trang(boolean tinh_trang) {
        this.tinh_trang = tinh_trang;
    }
   
    
    
    @Override
    public String toString() {
        return ma_tai_khoan + " - " + ten_dang_nhap;
    }
}