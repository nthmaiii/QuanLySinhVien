package com.teamvietdev.qlhv.dao;

import com.teamvietdev.qlhv.model.TaiKhoan;

/**
 *
 * @author HP
 */
public interface TaiKhoanDAO {
    public TaiKhoan login(String tenDangNhap, String matKhau);
}
