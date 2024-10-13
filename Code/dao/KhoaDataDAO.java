/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.teamvietdev.qlhv.dao;

import com.teamvietdev.qlhv.model.HocVien;
import com.teamvietdev.qlhv.model.KhoaHocData;
import java.util.List;

/**
 *
 * @author HP
 */
public interface KhoaDataDAO {
    public List<KhoaHocData> getList();
    public int createOrUpdate(KhoaHocData KhoaData);
    public List<KhoaHocData> getListActiveStudents();
    public HocVien getHocVienByMa(int maHocVien);
}
