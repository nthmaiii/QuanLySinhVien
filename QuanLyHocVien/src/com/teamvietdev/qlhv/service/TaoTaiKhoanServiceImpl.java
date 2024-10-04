package com.teamvietdev.qlhv.service;

import com.teamvietdev.qlhv.dao.TaoTaiKhoanDAO;
import com.teamvietdev.qlhv.dao.TaoTaiKhoanDAOImpl;
import com.teamvietdev.qlhv.model.TaiKhoan;
import java.util.List;

/**
 *
 * @author HP
 */
public class TaoTaiKhoanServiceImpl implements TaoTaiKhoanService{

    private TaoTaiKhoanDAO taoTaiKhoanDAO = null;

    public TaoTaiKhoanServiceImpl() {
        this.taoTaiKhoanDAO = new TaoTaiKhoanDAOImpl();
    }

    @Override
    public List<TaiKhoan> getList() {
        return taoTaiKhoanDAO.getList();
    }

    public int createOrUpdate(TaiKhoan taiKhoan) {
        return taoTaiKhoanDAO.createOrUpdate(taiKhoan);
    }

    @Override
    public List<TaiKhoan> getListActiveGV() {
        return  taoTaiKhoanDAO.getListActiveGV();
                
                
     }
   
    
}
