
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.teamvietdev.qlhv.service;

import com.teamvietdev.qlhv.dao.KhoaAIDAO;
import com.teamvietdev.qlhv.dao.KhoaAIDAOImpl;
import com.teamvietdev.qlhv.model.HocVien;
import com.teamvietdev.qlhv.model.KhoaHocAI;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class KhoaHocAIServiceImpl implements KhoaHocAIService{
    private KhoaAIDAO khoaAIDAO = null;
    private Object DriverManager;
     public KhoaHocAIServiceImpl() {
        this.khoaAIDAO = new KhoaAIDAOImpl();
    }
    @Override
    public List<KhoaHocAI> getList() {
        return khoaAIDAO.getList();
    }

    @Override
    public int createOrUpdate(KhoaHocAI KhoaAI) {
        return khoaAIDAO.createOrUpdate(KhoaAI);
    }

    @Override
    public List<KhoaHocAI> getListActiveStudents() {
        return khoaAIDAO.getListActiveStudents();
    }

    @Override
    public HocVien getHocVienByMa(int maHocVien) {
        return khoaAIDAO.getHocVienByMa(maHocVien);
    }
    
}
