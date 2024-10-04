package com.teamvietdev.qlhv.service;

import com.sun.jdi.connect.spi.Connection;
import com.teamvietdev.qlhv.dao.HocVienDAO;
import com.teamvietdev.qlhv.dao.HocVienDAOImpl;
import com.teamvietdev.qlhv.model.HocVien;
import java.util.ArrayList;
import java.util.List;

public class HocVienServiceImpl implements HocVienService {

    private HocVienDAO hocVienDAO = null;
    private Object DriverManager;

    public HocVienServiceImpl() {
        this.hocVienDAO = new HocVienDAOImpl();
    }

    @Override
    public List<HocVien> getList() {
        return hocVienDAO.getList();
    }

    @Override
    public int createOrUpdate(HocVien hocVien) {
        return hocVienDAO.createOrUpdate(hocVien);
    }

    @Override
    public List<HocVien> getListActiveStudents() {
        return hocVienDAO.getListActiveStudents();
    }
   
   
}