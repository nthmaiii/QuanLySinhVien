package com.teamvietdev.qlhv.service;

import com.teamvietdev.qlhv.model.TaiKhoan;
import java.util.List;

/**
 *
 * @author HP
 */
public interface TaoTaiKhoanService {
    public List<TaiKhoan> getList();
    public int createOrUpdate(TaiKhoan taiKhoan);
    public List<TaiKhoan> getListActiveGV();
    
}
