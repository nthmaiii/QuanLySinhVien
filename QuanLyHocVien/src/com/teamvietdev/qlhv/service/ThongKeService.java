package com.teamvietdev.qlhv.service;

/**
 *
 * @author HP
 */
import com.teamvietdev.qlhv.bean.KhoaHocBean;
import com.teamvietdev.qlhv.bean.LopHocBean;
import java.util.List;

public interface ThongKeService {
    
    public List<LopHocBean> getListByLopHoc();
    
    public List<KhoaHocBean> getListByKhoaHoc();
    
}