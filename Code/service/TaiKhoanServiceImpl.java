package com.teamvietdev.qlhv.service;

/**
 *
 * @author HP
 */
import com.teamvietdev.qlhv.dao.TaiKhoanDAO;
import com.teamvietdev.qlhv.dao.TaiKhoanDAOImpl;
import com.teamvietdev.qlhv.model.TaiKhoan;

public class TaiKhoanServiceImpl implements TaiKhoanService {

    private TaiKhoanDAO taiKhoanDAO = null;

    public TaiKhoanServiceImpl() {
        taiKhoanDAO = new TaiKhoanDAOImpl();
    }

    @Override
    public TaiKhoan login(String tenDangNhap, String matKhau) {
        return taiKhoanDAO.login(tenDangNhap, matKhau);
    }
    

}
