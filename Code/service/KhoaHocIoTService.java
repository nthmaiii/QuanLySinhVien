/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.teamvietdev.qlhv.service;

import com.teamvietdev.qlhv.model.HocVien;
import com.teamvietdev.qlhv.model.KhoaHoc;
import com.teamvietdev.qlhv.model.KhoaHocIoT;
import java.util.List;

/**
 *
 * @author HP
 */
public interface KhoaHocIoTService {
    public List<KhoaHocIoT> getList();
    public int createOrUpdate(KhoaHocIoT KhoaIoT);
    public List<KhoaHocIoT> getListActiveStudents();
    public HocVien getHocVienByMa(int maHocVien);
}