/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.teamvietdev.qlhv.service;

import com.teamvietdev.qlhv.dao.KhoaDataDAO;
import com.teamvietdev.qlhv.dao.KhoaDataDAOImpl;
import com.teamvietdev.qlhv.model.HocVien;
import com.teamvietdev.qlhv.model.KhoaHocData;
import java.util.List;

/**
 *
 * @author HP
 */
public class KhoaHocDataServiceImpl implements KhoaHocDataService{
    private KhoaDataDAO khoaDataDAO = null;
    private Object DriverManager;
    public KhoaHocDataServiceImpl() {
        this.khoaDataDAO = new KhoaDataDAOImpl();
    }
    @Override
    public List<KhoaHocData> getList() {
        return khoaDataDAO.getList();
    }

    @Override
    public int createOrUpdate(KhoaHocData KhoaData) {
        return khoaDataDAO.createOrUpdate(KhoaData);
    }

    @Override
    public List<KhoaHocData> getListActiveStudents() {
        return khoaDataDAO.getListActiveStudents();
    }

    @Override
    public HocVien getHocVienByMa(int maHocVien) {
        return khoaDataDAO.getHocVienByMa(maHocVien);
    }
    
}
