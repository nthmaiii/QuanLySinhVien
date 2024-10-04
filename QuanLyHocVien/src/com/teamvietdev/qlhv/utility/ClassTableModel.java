package com.teamvietdev.qlhv.utility;

import com.teamvietdev.qlhv.model.HocVien;
import com.teamvietdev.qlhv.model.TaiKhoan;
import java.util.List;
import javax.swing.table.DefaultTableModel;
//Mo hinh tao bang table
//Chứa các hàm hoặc lớp tiện ích dùng chung cho nhiều phần của ứng dụng.
//Không thuộc về MVC nhưng hỗ trợ cho các chức năng của các phần khác.
public class ClassTableModel {

    public DefaultTableModel setTableHocVien(List<HocVien> listItem, String[] listColumn) {
        //Lay so luong cot
        int columns = listColumn.length;
        DefaultTableModel dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                //Cac o khong dc phep chinh sua
                return false;
            }

            @Override
            //Xac dinh loai du lieu cua cot
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 7 ? Boolean.class : String.class;
            }
        };
        dtm.setColumnIdentifiers(listColumn);//Gan ten cot tu listColumn
        Object[] obj = null;//Mang luu du lieu vua moi hang
        //Lay so luong hoc vien
        int num = listItem.size();
        if (num > 0) {
            for (int i = 0; i < num; i++) {
                //Lay ds tung hoc vien
                HocVien hocVien = listItem.get(i);
                obj = new Object[columns];
                obj[0] = hocVien.getMa_hoc_vien();
                obj[1] = (i + 1);
                obj[2] = hocVien.getHo_ten();
                obj[3] = hocVien.getNgay_sinh();
                obj[4] = hocVien.isGioi_tinh() == true ? "Nam" : "Nữ";
                obj[5] = hocVien.getSo_dien_thoai();
                obj[6] = hocVien.getDia_chi();
                obj[7] = hocVien.isTinh_trang();
                obj[8]= hocVien.getMa_nguoi_tao_hv();
                dtm.addRow(obj);//Them hang vao mo hinh bang
            }
        }
        return dtm;
    }

   public DefaultTableModel setTableTaiKhoan(List<TaiKhoan> listItem, String[] listColumn) {
        int columnsTK = listColumn.length;
        DefaultTableModel dtmTK = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 4 ? Boolean.class : String.class;
            }
            
        };
        dtmTK.setColumnIdentifiers(listColumn);//Gan ten cot
        Object[] obj;
        int numTK = listItem.size();
        TaiKhoan taiKhoan = null;
        if(numTK>0){
            for (int i = 0; i < numTK; i++) {
                taiKhoan = listItem.get(i);
                obj = new Object[columnsTK];
                obj[0] = taiKhoan.getMa_tai_khoan();
                obj[1] = (i + 1);
                obj[2] = taiKhoan.getTen_dang_nhap();
                obj[3] = taiKhoan.getMat_khau();
                obj[4] = taiKhoan.isTinh_trang();
                obj[5] = taiKhoan.getTen_tk();
                dtmTK.addRow(obj);
            }
        }
        return dtmTK;
    }

   

}