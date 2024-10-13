/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.teamvietdev.qlhv.controller;

import com.teamvietdev.qlhv.model.HocVien;
import com.teamvietdev.qlhv.model.KhoaHocIoT;
import com.teamvietdev.qlhv.service.KhoaHocIoTService;
import com.teamvietdev.qlhv.service.KhoaHocIoTServiceImpl;
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
public class KhoaHocIoTController {
    private JButton btnSubmitIoT;
    private JTextField jtfMaHocVienIoT;
    private JTextField jtfHoTenIoT;
    private JDateChooser jdcNgaySinhIoT;
    private JRadioButton jtfGioiTinhNamIoT;
    private JRadioButton jtfGioiTinhNuIoT;
    private JCheckBox jcbKichHoatIoT;
    private JTextField jtfDiemTKIoT;
    private JDateChooser jdcNgayBDIoT;
    private JDateChooser jdcKetthucIoT;
    private JTextField textFieldKhoaIoT;
    
    
    private JLabel jlbMsgIoT;
    
    private KhoaHocIoT khoaHocIoT = null;

    private KhoaHocIoTService  khocHocIoTService = null;

    public KhoaHocIoTController(JButton btnSubmitIoT, JTextField jtfMaHocVienIoT, JTextField jtfHoTenIoT, JDateChooser jdcNgaySinhIoT
            , JRadioButton jtfGioiTinhNamIoT, JRadioButton jtfGioiTinhNuIoT,
            JCheckBox jcbKichHoatIoT,  JTextField jtfDiemTKIoT ,JDateChooser jdcNgayBDIoT,JDateChooser jdcKetthucIoT,JTextField textFieldKhoaIoT,JLabel jlbMsgIoT) {
        this.btnSubmitIoT = btnSubmitIoT;
        this.jtfMaHocVienIoT = jtfMaHocVienIoT;
        this.jtfHoTenIoT = jtfHoTenIoT;
        this.jdcNgaySinhIoT = jdcNgaySinhIoT;
        this.jtfGioiTinhNamIoT = jtfGioiTinhNamIoT;
        this.jtfGioiTinhNuIoT = jtfGioiTinhNuIoT;
        this.jcbKichHoatIoT = jcbKichHoatIoT;
        this.jtfDiemTKIoT = jtfDiemTKIoT;
        this.jdcNgayBDIoT = jdcNgayBDIoT;
        this.jdcKetthucIoT = jdcKetthucIoT;
        this.textFieldKhoaIoT = textFieldKhoaIoT;
        this.jlbMsgIoT = jlbMsgIoT;
        
        this.khocHocIoTService = new KhoaHocIoTServiceImpl();
    }
    
    
    
    public void setView(KhoaHocIoT khoaHocIoT) {
        //Cap nhat du lieu vao giao dien
        this.khoaHocIoT = khoaHocIoT;
        // set data
        jtfMaHocVienIoT.setText("" + khoaHocIoT.getMa_hoc_vien());
        jtfHoTenIoT.setText(khoaHocIoT.getHo_ten());
        jdcNgaySinhIoT.setDate(khoaHocIoT.getNgay_sinh());
        if (khoaHocIoT.isGioi_tinh()) {
            jtfGioiTinhNamIoT.setSelected(true);
        } else {
            jtfGioiTinhNuIoT.setSelected(true);
        }
        jcbKichHoatIoT.setSelected(khoaHocIoT.isTinh_trang());
        jtfDiemTKIoT.setText(""+khoaHocIoT.getDiem());
        // Thiết lập ngày bắt đầu và ngày kết thúc
        // Set ngày bắt đầu và ngày kết thúc
        jdcNgayBDIoT.setDate(khoaHocIoT.getNgay_bat_dau());
        jdcKetthucIoT.setDate(khoaHocIoT.getNgay_ket_thuc());


        // Thiết lập khóa (kiểu int)
        textFieldKhoaIoT.setText(""+khoaHocIoT.getKhoa()); // Khóa
        //set Event
        
    }

    public void setEvent() {
        btnSubmitIoT.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
                    try {
                    // Kiểm tra dữ liệu bắt buộc
                    if (!checkNotNull()) {
                        jlbMsgIoT.setText("Vui lòng nhập dữ liệu bắt buộc!");
                        return;
                    }

                    // Lấy mã học viên và kiểm tra
                    int maHocVien = Integer.parseInt(jtfMaHocVienIoT.getText());
                    HocVien hocVien = khocHocIoTService.getHocVienByMa(maHocVien);
                    if (hocVien == null) {
                        jlbMsgIoT.setText("Học viên không tồn tại với mã: " + maHocVien);
                        return;
                    }
                            // Kiểm tra ngày bắt đầu và ngày kết thúc
                       if (jdcNgayBDIoT.getDate() == null) {
                           jlbMsgIoT.setText("Vui lòng chọn ngày bắt đầu!");
                           return;
                       }
                       if (jdcKetthucIoT.getDate() == null) {
                           jlbMsgIoT.setText("Vui lòng chọn ngày kết thúc!");
                           return;
                       }
                    // Nếu học viên tồn tại, gán thông tin vào đối tượng khoaHocAI
                    khoaHocIoT.setMa_hoc_vien(maHocVien);
                    khoaHocIoT.setHo_ten(hocVien.getHo_ten());
                    khoaHocIoT.setNgay_sinh(hocVien.getNgay_sinh());
                    khoaHocIoT.setGioi_tinh(hocVien.isGioi_tinh());

                    // Gán tình trạng học viên từ checkbox
                    khoaHocIoT.setTinh_trang(jcbKichHoatIoT.isSelected());
                    khoaHocIoT.setNgay_bat_dau(covertDateToDateSql(jdcNgayBDIoT.getDate()));              
                    khoaHocIoT.setNgay_ket_thuc(covertDateToDateSql(jdcKetthucIoT.getDate()));
                    khoaHocIoT.setKhoa(Integer.parseInt(textFieldKhoaIoT.getText()));
                    khoaHocIoT.setDiem(Float.parseFloat(jtfDiemTKIoT.getText()));
                   

                    // Gán thông tin vào giao diện
                    jtfHoTenIoT.setText(hocVien.getHo_ten());
                    jdcNgaySinhIoT.setDate(hocVien.getNgay_sinh());
                    jtfGioiTinhNamIoT.setSelected(hocVien.isGioi_tinh());
                    jtfGioiTinhNuIoT.setSelected(!hocVien.isGioi_tinh());
                    jcbKichHoatIoT.setSelected(khoaHocIoT.isTinh_trang());
                    // Gán ngày bắt đầu và ngày kết thúc vào giao diện
                    
                    
                    jdcNgayBDIoT.setDate(khoaHocIoT.getNgay_bat_dau());
                    jdcKetthucIoT.setDate(khoaHocIoT.getNgay_ket_thuc());
                    textFieldKhoaIoT.setText(String.valueOf(khoaHocIoT.getKhoa())); // Cập nhật khóa
                    jtfDiemTKIoT.setText(Float.toString(khoaHocIoT.getDiem())); // Cập nhật điểm mới
                     
                    // Hiển thị hộp thoại xác nhận
                    if (showDialog()) {
                        int lastId = khocHocIoTService.createOrUpdate(khoaHocIoT);
                        if (lastId > 0) {
                            khoaHocIoT.setMa_hoc_vien(lastId);
                            jtfMaHocVienIoT.setText(String.valueOf(khoaHocIoT.getMa_hoc_vien()));
                            jlbMsgIoT.setText("Xử lý cập nhật dữ liệu thành công!");
                            // setDataToTable(); // Nếu cần cập nhật bảng
                        } else {
                            jlbMsgIoT.setText("Xử lý cập nhật dữ liệu thành công!");
                        }
                    }
                } catch (NumberFormatException ex) {
                    jlbMsgIoT.setText("Vui lòng nhập mã học viên hợp lệ!");
                } catch (Exception ex) {
                    jlbMsgIoT.setText("Có lỗi xảy ra: " + ex.getMessage()); // Hiển thị thông báo lỗi chi tiết
                    ex.printStackTrace(); // Ghi log lỗi để dễ dàng tìm nguyên nhân
                }
}
            public void mousePressed(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
                // Giữ màu nền khi di chuột vào (có thể bỏ qua hoặc đặt lại màu)
                btnSubmitIoT.setBackground(new Color(0, 204,0)); // Màu mong muốn khi hover
            }

            public void mouseExited(MouseEvent e) {
                 // Giữ màu nền ban đầu khi chuột ra
                btnSubmitIoT.setBackground(new Color(0,204,0));  // Màu ban đầu

            }

            private void setDataToTable() {
                
            }
        });
    }

    private boolean checkNotNull() {
        return jtfHoTenIoT.getText() != null && !jtfHoTenIoT.getText().equalsIgnoreCase("");
                
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
    
    
    

