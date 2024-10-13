package com.teamvietdev.qlhv.dao;

import java.util.List;
import com.teamvietdev.qlhv.model.TaiKhoan;
/**
 *
 * @author HP
 */
public interface TaoTaiKhoanDAO {
    public List<TaiKhoan> getList();
    public int createOrUpdate(TaiKhoan taiKhoan);
    public List<TaiKhoan> getListActiveGV();
}
