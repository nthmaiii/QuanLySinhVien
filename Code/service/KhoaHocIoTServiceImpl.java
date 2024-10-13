/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.teamvietdev.qlhv.service;

import com.teamvietdev.qlhv.dao.KhoaIoTDAO;
import com.teamvietdev.qlhv.dao.KhoaIoTDAOImpl;
import com.teamvietdev.qlhv.model.HocVien;
import com.teamvietdev.qlhv.model.KhoaHocIoT;
import java.util.List;

/**
 *
 * @author HP
 */
public class KhoaHocIoTServiceImpl implements KhoaHocIoTService{
    private KhoaIoTDAO khoaIoTDAO = null;
    private Object DriverManager;
    public KhoaHocIoTServiceImpl() {
        this.khoaIoTDAO = new KhoaIoTDAOImpl();
    }
    @Override
    public List<KhoaHocIoT> getList() {
        return khoaIoTDAO.getList();
    }

    @Override
    public int createOrUpdate(KhoaHocIoT KhoaIoT) {
        return khoaIoTDAO.createOrUpdate(KhoaIoT);
    }

    @Override
    public List<KhoaHocIoT> getListActiveStudents() {
        return khoaIoTDAO.getListActiveStudents();
    }

    @Override
    public HocVien getHocVienByMa(int maHocVien) {
        return khoaIoTDAO.getHocVienByMa(maHocVien);
    }
    
}
