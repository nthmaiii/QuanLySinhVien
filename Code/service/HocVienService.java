package com.teamvietdev.qlhv.service;

import com.teamvietdev.qlhv.model.HocVien;
import java.util.List;

//Dinh nghia cac phuog thuc ma lop dich vu hoc vien
// can phai trien khai.Giao dien nay thuong dc su dung trong cac ung dung Java de 
// tach biet logic xu ly va logic truy van du lieu
public interface HocVienService {
    
    public List<HocVien> getList();
    
    public int createOrUpdate(HocVien hocVien);

    public List<HocVien> getListActiveStudents();

    
}