package com.teamvietdev.qlhv.dao;

/**
 *
 * @author HP
 */

import com.teamvietdev.qlhv.bean.KhoaHocBean;
import com.teamvietdev.qlhv.bean.LopHocBean;
import java.util.List;

public interface ThongKeDAO {
    
    public List<LopHocBean> getListByLopHoc();
    
    public List<KhoaHocBean> getListByKhoaHoc();
    
}
