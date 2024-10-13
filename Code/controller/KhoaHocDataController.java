package com.teamvietdev.qlhv.controller;

import com.teamvietdev.qlhv.model.HocVien;
import com.teamvietdev.qlhv.model.KhoaHocData;
import com.teamvietdev.qlhv.service.KhoaHocDataService;
import com.teamvietdev.qlhv.service.KhoaHocDataServiceImpl;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author HP
 */
public class KhoaHocDataController {
    private JButton btnSubmitData;
    private JTextField jtfMaHocVienData;
    private JTextField jtfHoTenData;
    private JDateChooser jdcNgaySinhData;
    private JRadioButton jtfGioiTinhNamData;
    private JRadioButton jtfGioiTinhNuData;
    private JCheckBox jcbKichHoatData;
    private JTextField jtfDiemTKData;
    private JDateChooser jdcNgayBDData;
    private JDateChooser jdcKetthucData;
    private JTextField textFieldKhoaData;
    
    
    private JLabel jlbMsgData;
    
    private KhoaHocData khoaHocData = null;

    private KhoaHocDataService  khocHocDataService = null;

    public KhoaHocDataController(JButton btnSubmitData, JTextField jtfMaHocVienData, JTextField jtfHoTenData, JDateChooser jdcNgaySinhData
            , JRadioButton jtfGioiTinhNamData, JRadioButton jtfGioiTinhNuData,
            JCheckBox jcbKichHoatData,  JTextField jtfDiemTKData ,JDateChooser jdcNgayBDData,JDateChooser jdcKetthucData,JTextField textFieldKhoaData,JLabel jlbMsgData) {
        this.btnSubmitData = btnSubmitData;
        this.jtfMaHocVienData = jtfMaHocVienData;
        this.jtfHoTenData = jtfHoTenData;
        this.jdcNgaySinhData = jdcNgaySinhData;
        this.jtfGioiTinhNamData = jtfGioiTinhNamData;
        this.jtfGioiTinhNuData = jtfGioiTinhNuData;
        this.jcbKichHoatData = jcbKichHoatData;
        this.jtfDiemTKData = jtfDiemTKData;
        this.jdcNgayBDData = jdcNgayBDData;
        this.jdcKetthucData = jdcKetthucData;
        this.textFieldKhoaData = textFieldKhoaData;
        this.jlbMsgData = jlbMsgData;
        
        this.khocHocDataService = new KhoaHocDataServiceImpl();
    }
    
    
    
    public void setView(KhoaHocData khoaHocData) {
        //Cap nhat du lieu vao giao dien
        this.khoaHocData = khoaHocData;
        // set data
        jtfMaHocVienData.setText("" + khoaHocData.getMa_hoc_vien());
        jtfHoTenData.setText(khoaHocData.getHo_ten());
        jdcNgaySinhData.setDate(khoaHocData.getNgay_sinh());
        if (khoaHocData.isGioi_tinh()) {
            jtfGioiTinhNamData.setSelected(true);
        } else {
            jtfGioiTinhNuData.setSelected(true);
        }
        jcbKichHoatData.setSelected(khoaHocData.isTinh_trang());
        jtfDiemTKData.setText(""+khoaHocData.getDiem());
        // Thiết lập ngày bắt đầu và ngày kết thúc
        // Set ngày bắt đầu và ngày kết thúc
        jdcNgayBDData.setDate(khoaHocData.getNgay_bat_dau());
        jdcKetthucData.setDate(khoaHocData.getNgay_ket_thuc());


        // Thiết lập khóa (kiểu int)
        textFieldKhoaData.setText(""+khoaHocData.getKhoa()); // Khóa
        //set Event
        
    }

    public void setEvent() {
        btnSubmitData.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
                    try {
                    // Kiểm tra dữ liệu bắt buộc
                    if (!checkNotNull()) {
                        jlbMsgData.setText("Vui lòng nhập dữ liệu bắt buộc!");
                        return;
                    }

                    // Lấy mã học viên và kiểm tra
                    int maHocVien = Integer.parseInt(jtfMaHocVienData.getText());
                    HocVien hocVien = khocHocDataService.getHocVienByMa(maHocVien);
                    if (hocVien == null) {
                        jlbMsgData.setText("Học viên không tồn tại với mã: " + maHocVien);
                        return;
                    }
                            // Kiểm tra ngày bắt đầu và ngày kết thúc
                       if (jdcNgayBDData.getDate() == null) {
                           jlbMsgData.setText("Vui lòng chọn ngày bắt đầu!");
                           return;
                       }
                       if (jdcKetthucData.getDate() == null) {
                           jlbMsgData.setText("Vui lòng chọn ngày kết thúc!");
                           return;
                       }
                    // Nếu học viên tồn tại, gán thông tin vào đối tượng khoaHocAI
                    khoaHocData.setMa_hoc_vien(maHocVien);
                    khoaHocData.setHo_ten(hocVien.getHo_ten());
                    khoaHocData.setNgay_sinh(hocVien.getNgay_sinh());
                    khoaHocData.setGioi_tinh(hocVien.isGioi_tinh());

                    // Gán tình trạng học viên từ checkbox
                    khoaHocData.setTinh_trang(jcbKichHoatData.isSelected());
                    khoaHocData.setNgay_bat_dau(covertDateToDateSql(jdcNgayBDData.getDate()));              
                    khoaHocData.setNgay_ket_thuc(covertDateToDateSql(jdcKetthucData.getDate()));
                    khoaHocData.setKhoa(Integer.parseInt(textFieldKhoaData.getText()));
                    khoaHocData.setDiem(Float.parseFloat(jtfDiemTKData.getText()));
                   

                    // Gán thông tin vào giao diện
                    jtfHoTenData.setText(hocVien.getHo_ten());
                    jdcNgaySinhData.setDate(hocVien.getNgay_sinh());
                    jtfGioiTinhNamData.setSelected(hocVien.isGioi_tinh());
                    jtfGioiTinhNuData.setSelected(!hocVien.isGioi_tinh());
                    jcbKichHoatData.setSelected(khoaHocData.isTinh_trang());
                    // Gán ngày bắt đầu và ngày kết thúc vào giao diện
                    
                    
                    jdcNgayBDData.setDate(khoaHocData.getNgay_bat_dau());
                    jdcKetthucData.setDate(khoaHocData.getNgay_ket_thuc());
                    textFieldKhoaData.setText(String.valueOf(khoaHocData.getKhoa())); // Cập nhật khóa
                    jtfDiemTKData.setText(Float.toString(khoaHocData.getDiem())); // Cập nhật điểm mới
                     
                    // Hiển thị hộp thoại xác nhận
                    if (showDialog()) {
                        int lastId = khocHocDataService.createOrUpdate(khoaHocData);
                        if (lastId > 0) {
                            khoaHocData.setMa_hoc_vien(lastId);
                            jtfMaHocVienData.setText(String.valueOf(khoaHocData.getMa_hoc_vien()));
                            jlbMsgData.setText("Xử lý cập nhật dữ liệu thành công!");
                            // setDataToTable(); // Nếu cần cập nhật bảng
                        } else {
                            jlbMsgData.setText("Xử lý cập nhật dữ liệu thành công!");
                        }
                    }
                } catch (NumberFormatException ex) {
                    jlbMsgData.setText("Vui lòng nhập mã học viên hợp lệ!");
                } catch (Exception ex) {
                    jlbMsgData.setText("Có lỗi xảy ra: " + ex.getMessage()); // Hiển thị thông báo lỗi chi tiết
                    ex.printStackTrace(); // Ghi log lỗi để dễ dàng tìm nguyên nhân
                }
}
            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // Giữ màu nền khi di chuột vào (có thể bỏ qua hoặc đặt lại màu)
                btnSubmitData.setBackground(new Color(0, 204,0)); // Màu mong muốn khi hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                 // Giữ màu nền ban đầu khi chuột ra
                btnSubmitData.setBackground(new Color(0,204,0));  // Màu ban đầu

            }

            private void setDataToTable() {
                
            }
        });
    }

    private boolean checkNotNull() {
        return jtfHoTenData.getText() != null && !jtfHoTenData.getText().equalsIgnoreCase("");
                
    }

    private boolean showDialog() {
        int dialogResult = JOptionPane.showConfirmDialog(null,
                "Bạn muốn cập nhật dữ liệu hay không?", "Thông báo", JOptionPane.YES_NO_OPTION);
        return dialogResult == JOptionPane.YES_OPTION;
    }
    
    public java.sql.Date covertDateToDateSql(Date d) {
        
         if (d != null) {
            return new java.sql.Date(d.getTime());
        } else {
            return null;  // Hoặc bạn có thể xử lý giá trị null theo cách khác nếu cần.
        }

        
    }
    
}
    
    
    


