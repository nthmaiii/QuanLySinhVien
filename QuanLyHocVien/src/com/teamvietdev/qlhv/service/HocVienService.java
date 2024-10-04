package com.teamvietdev.qlhv.service;

import com.teamvietdev.qlhv.model.HocVien;
import java.util.List;
//Thực hiện các nghiệp vụ chính của ứng dụng.

//Thường gọi tới DAO để lấy dữ liệu và thực hiện các xử lý cần thiết trước khi trả về cho Controller.
//Dinh nghia cac phuog thuc ma lop dich vu hoc vien

//Thực hiện các logic nghiệp vụ chính. Controller sẽ gọi đến Service để thực hiện 
//các tác vụ như tính toán, xử lý dữ liệu, và tương tác với Model (thường thông qua DAO).


// can phai trien khai.Giao dien nay thuong dc su dung trong cac ung dung Java de 
// tach biet logic xu ly va logic truy van du lieu
public interface HocVienService {
    
    public List<HocVien> getList();
    
    public int createOrUpdate(HocVien hocVien);

    public List<HocVien> getListActiveStudents();

    
}