
package com.teamvietdev.qlhv.service;

/**
 *
 * @author HP
 */
import com.teamvietdev.qlhv.bean.KhoaHocBean;
import com.teamvietdev.qlhv.bean.LopHocBean;
import com.teamvietdev.qlhv.dao.ThongKeDAO;
import com.teamvietdev.qlhv.dao.ThongKeDAOImpl;
import java.util.List;

public class ThongKeServiceImpl implements ThongKeService {

    private ThongKeDAO thongKeDAO = null;

    public ThongKeServiceImpl() {
        this.thongKeDAO = new ThongKeDAOImpl();
    }

    @Override
    public List<LopHocBean> getListByLopHoc() {
        return thongKeDAO.getListByLopHoc();
    }

    @Override
    public List<KhoaHocBean> getListByKhoaHoc() {
        return thongKeDAO.getListByKhoaHoc();
    }

}