package com.teamvietdev.qlhv.dao;

/**
 *
 * @author HP
 */
import com.teamvietdev.qlhv.model.HocVien;
import java.util.List;

//interface là bản thiết kế lớp giao tiếp
public interface HocVienDAO {

    public List<HocVien> getList();
    public int createOrUpdate(HocVien hocVien);
    public List<HocVien> getListActiveStudents();
}