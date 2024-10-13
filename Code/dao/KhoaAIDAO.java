/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.teamvietdev.qlhv.dao;
import com.teamvietdev.qlhv.model.HocVien;
import com.teamvietdev.qlhv.model.KhoaHocAI;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface KhoaAIDAO {
    public List<KhoaHocAI> getList();
    public int createOrUpdate(KhoaHocAI KhoaAI);
    public List<KhoaHocAI> getListActiveStudents();
    public HocVien getHocVienByMa(int maHocVien);
}
