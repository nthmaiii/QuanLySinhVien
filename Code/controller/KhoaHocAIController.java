/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.teamvietdev.qlhv.controller;

import com.teamvietdev.qlhv.model.HocVien;
import com.teamvietdev.qlhv.model.KhoaHocAI;
import com.teamvietdev.qlhv.service.HocVienService;
import com.teamvietdev.qlhv.service.HocVienServiceImpl;
import com.teamvietdev.qlhv.service.KhoaHocAIService;
import com.teamvietdev.qlhv.service.KhoaHocAIServiceImpl;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author ADMIN
 */
public class KhoaHocAIController {
    private JButton btnSubmitAI;
    private JTextField jtfMaHocVienAI;
    private JTextField jtfHoTenAI;
    private JDateChooser jdcNgaySinhAI;
    private JRadioButton jtfGioiTinhNamAI;
    private JRadioButton jtfGioiTinhNuAI;
    private JCheckBox jcbKichHoatAI;
    private JTextField jtfDiemTK;
    private JDateChooser jdcNgayBDAI;
    private JDateChooser jdcKetthucAI;
    private JTextField textFieldKhoa;
    
    
    private JLabel jlbMsgAI;
    
    private KhoaHocAI khoaHocAI = null;

    private KhoaHocAIService  khocHocAIService = null;

    public KhoaHocAIController(JButton btnSubmitAI, JTextField jtfMaHocVienAI, JTextField jtfHoTenAI, JDateChooser jdcNgaySinhAI
            , JRadioButton jtfGioiTinhNamAI, JRadioButton jtfGioiTinhNuAI,
            JCheckBox jcbKichHoatAI,  JTextField jtfDiemTK,JDateChooser jdcNgayBDAI,JDateChooser jdcKetthucAI,JTextField textFieldKhoa,JLabel jlbMsgAI) {
        this.btnSubmitAI = btnSubmitAI;
        this.jtfMaHocVienAI = jtfMaHocVienAI;
        this.jtfHoTenAI = jtfHoTenAI;
        this.jdcNgaySinhAI = jdcNgaySinhAI;
        this.jtfGioiTinhNamAI = jtfGioiTinhNamAI;
        this.jtfGioiTinhNuAI = jtfGioiTinhNuAI;
        this.jcbKichHoatAI=jcbKichHoatAI;
        this.jtfDiemTK = jtfDiemTK;
        this.jdcNgayBDAI=jdcNgayBDAI;
        this.jdcKetthucAI=jdcKetthucAI;
        this.textFieldKhoa = textFieldKhoa;
        this.jlbMsgAI = jlbMsgAI;
        
        this.khocHocAIService = new KhoaHocAIServiceImpl();
    }
    
    
    
    public void setView(KhoaHocAI khoaHocAI) {
        //Cap nhat du lieu vao giao dien
        this.khoaHocAI = khoaHocAI;
        // set data
        jtfMaHocVienAI.setText("" + khoaHocAI.getMa_hoc_vien());
        jtfHoTenAI.setText(khoaHocAI.getHo_ten());
        jdcNgaySinhAI.setDate(khoaHocAI.getNgay_sinh());
        if (khoaHocAI.isGioi_tinh()) {
            jtfGioiTinhNamAI.setSelected(true);
        } else {
            jtfGioiTinhNuAI.setSelected(true);
        }
        jcbKichHoatAI.setSelected(khoaHocAI.isTinh_trang());
        jtfDiemTK.setText(""+khoaHocAI.getDiem());
        // Thiết lập ngày bắt đầu và ngày kết thúc
        // Set ngày bắt đầu và ngày kết thúc
        jdcNgayBDAI.setDate(khoaHocAI.getNgay_bat_dau());
        jdcKetthucAI.setDate(khoaHocAI.getNgay_ket_thuc());


        // Thiết lập khóa (kiểu int)
        textFieldKhoa.setText(""+khoaHocAI.getKhoa()); // Khóa
        //set Event
        
    }

    public void setEvent() {
        btnSubmitAI.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
                    try {
                    // Kiểm tra dữ liệu bắt buộc
                    if (!checkNotNull()) {
                        jlbMsgAI.setText("Vui lòng nhập dữ liệu bắt buộc!");
                        return;
                    }

                    // Lấy mã học viên và kiểm tra
                    int maHocVien = Integer.parseInt(jtfMaHocVienAI.getText());
                    HocVien hocVien = khocHocAIService.getHocVienByMa(maHocVien);
                    if (hocVien == null) {
                        jlbMsgAI.setText("Học viên không tồn tại với mã: " + maHocVien);
                        return;
                    }
                            // Kiểm tra ngày bắt đầu và ngày kết thúc
                       if (jdcNgayBDAI.getDate() == null) {
                           jlbMsgAI.setText("Vui lòng chọn ngày bắt đầu!");
                           return;
                       }
                       if (jdcKetthucAI.getDate() == null) {
                           jlbMsgAI.setText("Vui lòng chọn ngày kết thúc!");
                           return;
                       }
                    // Nếu học viên tồn tại, gán thông tin vào đối tượng khoaHocAI
                    khoaHocAI.setMa_hoc_vien(maHocVien);
                    khoaHocAI.setHo_ten(hocVien.getHo_ten());
                    khoaHocAI.setNgay_sinh(hocVien.getNgay_sinh());
                    khoaHocAI.setGioi_tinh(hocVien.isGioi_tinh());

                    // Gán tình trạng học viên từ checkbox
                    khoaHocAI.setTinh_trang(jcbKichHoatAI.isSelected());
                    khoaHocAI.setNgay_bat_dau(covertDateToDateSql(jdcNgayBDAI.getDate()));              
                    khoaHocAI.setNgay_ket_thuc(covertDateToDateSql(jdcKetthucAI.getDate()));
                    khoaHocAI.setKhoa(Integer.parseInt(textFieldKhoa.getText()));
                    khoaHocAI.setDiem(Float.parseFloat(jtfDiemTK.getText()));
                   

                    // Gán thông tin vào giao diện
                    jtfHoTenAI.setText(hocVien.getHo_ten());
                    jdcNgaySinhAI.setDate(hocVien.getNgay_sinh());
                    jtfGioiTinhNamAI.setSelected(hocVien.isGioi_tinh());
                    jtfGioiTinhNuAI.setSelected(!hocVien.isGioi_tinh());
                    jcbKichHoatAI.setSelected(khoaHocAI.isTinh_trang());
                    // Gán ngày bắt đầu và ngày kết thúc vào giao diện
                    
                    
                    jdcNgayBDAI.setDate(khoaHocAI.getNgay_bat_dau());
                    jdcKetthucAI.setDate(khoaHocAI.getNgay_ket_thuc());
                    textFieldKhoa.setText(String.valueOf(khoaHocAI.getKhoa())); // Cập nhật khóa
                    jtfDiemTK.setText(Float.toString(khoaHocAI.getDiem())); // Cập nhật điểm mới
                     
                    // Hiển thị hộp thoại xác nhận
                    if (showDialog()) {
                        int lastId = khocHocAIService.createOrUpdate(khoaHocAI);
                        if (lastId > 0) {
                            khoaHocAI.setMa_hoc_vien(lastId);
                            jtfMaHocVienAI.setText(String.valueOf(khoaHocAI.getMa_hoc_vien()));
                            jlbMsgAI.setText("Xử lý cập nhật dữ liệu thành công!");
                            // setDataToTable(); // Nếu cần cập nhật bảng
                        } else {
                            jlbMsgAI.setText("Xử lý cập nhật dữ liệu thành công!");
                        }
                    }
                } catch (NumberFormatException ex) {
                    jlbMsgAI.setText("Vui lòng nhập đầy đủ thông tin!");
                } catch (Exception ex) {
                    jlbMsgAI.setText("Có lỗi xảy ra: " + ex.getMessage()); // Hiển thị thông báo lỗi chi tiết
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
                btnSubmitAI.setBackground(new Color(0, 204,0)); // Màu mong muốn khi hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                 // Giữ màu nền ban đầu khi chuột ra
                btnSubmitAI.setBackground(new Color(0,204,0));  // Màu ban đầu

            }

            private void setDataToTable() {
                
            }
        });
    }

    private boolean checkNotNull() {
        return jtfHoTenAI.getText() != null && !jtfHoTenAI.getText().equalsIgnoreCase("");
                
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
    
    
    

