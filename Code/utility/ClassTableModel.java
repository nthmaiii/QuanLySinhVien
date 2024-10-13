package com.teamvietdev.qlhv.utility;

import com.teamvietdev.qlhv.model.HocVien;
import com.teamvietdev.qlhv.model.KhoaHocAI;
import com.teamvietdev.qlhv.model.KhoaHocData;
import com.teamvietdev.qlhv.model.KhoaHocIoT;
import com.teamvietdev.qlhv.model.TaiKhoan;
import com.teamvietdev.qlhv.model.ThongKe;
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
                obj[8]= hocVien.getMa_nguoi_tao_tk();
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
    public DefaultTableModel setTableKhoaAI(List<KhoaHocAI> listItem, String[] listColumn) {
         // Lấy số lượng cột
    int columns = listColumn.length;
    DefaultTableModel dtm = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            // Các ô không được phép chỉnh sửa
            return false;
        }
        

        @Override
        // Xác định loại dữ liệu của cột
        public Class<?> getColumnClass(int columnIndex) {
            return columnIndex == 5 ? Boolean.class : String.class;
        }
    };

    dtm.setColumnIdentifiers(listColumn); // Gán tên cột từ listColumn
    Object[] obj; // Mảng lưu dữ liệu của mỗi hàng

    // Lấy số lượng học viên
    int num = listItem.size();
    if (num > 0) {
        for (int i = 0; i < num; i++) {
            // Lấy danh sách từng học viên
            KhoaHocAI khoaAI = listItem.get(i);
            obj = new Object[columns];
            obj[0] = khoaAI.getMa_hoc_vien(); // Mã học viên
            obj[1] = (i + 1); // Số thứ tự
            obj[2] = khoaAI.getHo_ten(); // Họ tên
            obj[3] = khoaAI.getNgay_sinh(); // Ngày sinh
            obj[4] = khoaAI.isGioi_tinh() ? "Nam" : "Nữ"; // Giới tính
            obj[5] = khoaAI.isTinh_trang(); // Tình trạng
            obj[6] = khoaAI.getDiem(); // Điểm
            obj[7] = khoaAI.getNgay_bat_dau(); // Ngày bắt đầu
            obj[8] = khoaAI.getNgay_ket_thuc(); // Ngày kết thúc
            obj[9] = khoaAI.getKhoa(); // Khóa
            dtm.addRow(obj); // Thêm hàng vào mô hình bảng
        }
    }
    return dtm; // Trả về mô hình bảng
    }
    public DefaultTableModel setTableKhoaData(List<KhoaHocData> listItem, String[] listColumn) {
         // Lấy số lượng cột
    int columns = listColumn.length;
    DefaultTableModel dtm = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            // Các ô không được phép chỉnh sửa
            return false;
        }
        

        @Override
        // Xác định loại dữ liệu của cột
        public Class<?> getColumnClass(int columnIndex) {
            return columnIndex == 5 ? Boolean.class : String.class;
        }
    };

    dtm.setColumnIdentifiers(listColumn); // Gán tên cột từ listColumn
    Object[] obj = null; // Mảng lưu dữ liệu của mỗi hàng

    // Lấy số lượng học viên
    int num = listItem.size();
    if (num > 0) {
        for (int i = 0; i < num; i++) {
            // Lấy danh sách từng học viên
            KhoaHocData khoaData = listItem.get(i);
            obj = new Object[columns];
            obj[0] = khoaData.getMa_hoc_vien(); // Mã học viên
            obj[1] = (i + 1); // Số thứ tự
            obj[2] = khoaData.getHo_ten(); // Họ tên
            obj[3] = khoaData.getNgay_sinh(); // Ngày sinh
            obj[4] = khoaData.isGioi_tinh() ? "Nam" : "Nữ"; // Giới tính
            obj[5] = khoaData.isTinh_trang(); // Tình trạng
            obj[6] = khoaData.getDiem(); // Điểm
            obj[7] = khoaData.getNgay_bat_dau(); // Ngày bắt đầu
            obj[8] = khoaData.getNgay_ket_thuc(); // Ngày kết thúc
            obj[9] = khoaData.getKhoa(); // Khóa
            dtm.addRow(obj); // Thêm hàng vào mô hình bảng
        }
    }
    return dtm; // Trả về mô hình bảng
    }
    public DefaultTableModel setTableKhoaIoT(List<KhoaHocIoT> listItem, String[] listColumn) {
         // Lấy số lượng cột
    int columns = listColumn.length;
    DefaultTableModel dtm = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            // Các ô không được phép chỉnh sửa
            return false;
        }
        

        @Override
        // Xác định loại dữ liệu của cột
        public Class<?> getColumnClass(int columnIndex) {
            return columnIndex == 5 ? Boolean.class : String.class;
        }
    };

    dtm.setColumnIdentifiers(listColumn); // Gán tên cột từ listColumn
    Object[] obj = null; // Mảng lưu dữ liệu của mỗi hàng

    // Lấy số lượng học viên
    int num = listItem.size();
    if (num > 0) {
        for (int i = 0; i < num; i++) {
            // Lấy danh sách từng học viên
            KhoaHocIoT khoaIoT = listItem.get(i);
            obj = new Object[columns];
            obj[0] = khoaIoT.getMa_hoc_vien(); // Mã học viên
            obj[1] = (i + 1); // Số thứ tự
            obj[2] = khoaIoT.getHo_ten(); // Họ tên
            obj[3] = khoaIoT.getNgay_sinh(); // Ngày sinh
            obj[4] = khoaIoT.isGioi_tinh() ? "Nam" : "Nữ"; // Giới tính
            obj[5] = khoaIoT.isTinh_trang(); // Tình trạng
            obj[6] = khoaIoT.getDiem(); // Điểm
            obj[7] = khoaIoT.getNgay_bat_dau(); // Ngày bắt đầu
            obj[8] = khoaIoT.getNgay_ket_thuc(); // Ngày kết thúc
            obj[9] = khoaIoT.getKhoa(); // Khóa
            dtm.addRow(obj); // Thêm hàng vào mô hình bảng
        }
    }
    return dtm; // Trả về mô hình bảng
    }
            public DefaultTableModel setTableThongKe(List<ThongKe> listItem, String[] listColumn) {
            // Lấy số lượng cột
            int columns = listColumn.length;
            DefaultTableModel dtm = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    // Các ô không được phép chỉnh sửa
                    return false;
                }

                @Override
                // Xác định loại dữ liệu của cột
                public Class<?> getColumnClass(int columnIndex) {
                    return columnIndex == 1 ? Integer.class : String.class;
                }
            };

            dtm.setColumnIdentifiers(listColumn); // Gán tên cột từ listColumn
            Object[] obj = null; // Mảng lưu dữ liệu từng hàng
            // Lấy số lượng thống kê
            int num = listItem.size();
            if (num > 0) {
                for (int i = 0; i < num; i++) {
                    // Lấy danh sách từng thống kê
                    ThongKe thongKe = listItem.get(i);
                    obj = new Object[columns];
                    obj[0] = thongKe.getTen_khoa_hoc();
                    obj[1] = thongKe.getSo_hoc_vien();
                    dtm.addRow(obj); // Thêm hàng vào mô hình bảng
                }
            }
            return dtm;
        }

    
}