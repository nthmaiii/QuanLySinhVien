package com.teamvietdev.qlhv.controller;

/**
 *
 * @author HP
 */
import com.teamvietdev.qlhv.model.HocVien;
import com.teamvietdev.qlhv.service.HocVienService;
import com.teamvietdev.qlhv.service.HocVienServiceImpl;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

public class HocVienController {
    //La bo di khien trong Mo hinh MVC .Xu ly cac su kien tu 
    //giao dien ng dung va tuong tac va dich vu
    private JButton btnSubmit;
    private JTextField jtfMaHocVien;
    private JTextField jtfHoTen;
    private JDateChooser jdcNgaySinh;
    private JTextField jtfSoDienThoai;
    private JTextField jtfMaNguoiTaoTK;
    private JRadioButton jtfGioiTinhNam;
    private JRadioButton jtfGioiTinhNu;
    private JTextArea jtaDiaChi;
    private JCheckBox jcbKichHoat;
    private JLabel jlbMsg;

    private HocVien hocVien = null;

    private HocVienService hocVienService = null;
    
    public HocVienController(JButton btnSubmit, JTextField jtfMaHocVien, JTextField jtfHoTen,
            JDateChooser jdcNgaySinh, JTextField jtfSoDienThoai, JTextField jtfMaNguoiTaoTK, JRadioButton jtfGioiTinhNam, JRadioButton jtfGioiTinhNu,
            JTextArea jtaDiaChi, JCheckBox jcbKichHoat, JLabel jlbMsg) {
        this.btnSubmit = btnSubmit;
        this.jtfMaHocVien = jtfMaHocVien;
        this.jtfHoTen = jtfHoTen;
        this.jdcNgaySinh = jdcNgaySinh;
        this.jtfSoDienThoai = jtfSoDienThoai;
        this.jtfMaNguoiTaoTK = jtfMaNguoiTaoTK;
        this.jtfGioiTinhNam = jtfGioiTinhNam;
        this.jtfGioiTinhNu = jtfGioiTinhNu;
        this.jtaDiaChi = jtaDiaChi;
        this.jcbKichHoat = jcbKichHoat;
        this.jlbMsg = jlbMsg;

        this.hocVienService = new HocVienServiceImpl();
    }

    public void setView(HocVien hocVien) {
        //Cap nhat du lieu vao giao dien
        this.hocVien = hocVien;
        // set data
        jtfMaHocVien.setText("#" + hocVien.getMa_hoc_vien());
        jtfHoTen.setText(hocVien.getHo_ten());
        jdcNgaySinh.setDate(hocVien.getNgay_sinh());
        if (hocVien.isGioi_tinh()) {
            jtfGioiTinhNam.setSelected(true);
        } else {
            jtfGioiTinhNu.setSelected(true);
        }
        jtfSoDienThoai.setText(hocVien.getSo_dien_thoai());
        jtfMaNguoiTaoTK.setText(""+hocVien.getMa_nguoi_tao_tk());
        jtaDiaChi.setText(hocVien.getDia_chi());
        jcbKichHoat.setSelected(hocVien.isTinh_trang());
        //set Event
        
    }

    public void setEvent() {
        btnSubmit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    if (!checkNotNull()) {
                        jlbMsg.setText("Vui lòng nhập dữ liệu bắt buộc!");
                    } else {
                        hocVien.setHo_ten(jtfHoTen.getText().trim());
                        hocVien.setNgay_sinh(covertDateToDateSql(jdcNgaySinh.getDate()));
                        hocVien.setSo_dien_thoai(jtfSoDienThoai.getText());
                        
                        hocVien.setDia_chi(jtaDiaChi.getText());
                        hocVien.setGioi_tinh(jtfGioiTinhNam.isSelected());
                        hocVien.setTinh_trang(jcbKichHoat.isSelected());
                        hocVien.setMa_nguoi_tao_tk(Integer.parseInt(jtfMaNguoiTaoTK.getText()));
                        if (showDialog()) {
                            int lastId = hocVienService.createOrUpdate(hocVien);
                            if (lastId != 0) {
                                hocVien.setMa_hoc_vien(lastId);
                                jtfMaHocVien.setText("#" + hocVien.getMa_hoc_vien());
                              
                                jlbMsg.setText("Xử lý cập nhật dữ liệu thành công!");
                                //setDataToTable();
                            } else {
                                jlbMsg.setText("Có lỗi xảy ra, vui lòng thử lại!");
                            }
                        }
                    }
                } catch (Exception ex) {
                    jlbMsg.setText("Có lỗi xảy ra, vui lòng thử lại!");
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
                btnSubmit.setBackground(new Color(0, 204,0)); // Màu mong muốn khi hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                 // Giữ màu nền ban đầu khi chuột ra
                btnSubmit.setBackground(new Color(0,204,0));  // Màu ban đầu

            }

            private void setDataToTable() {
                
            }
        });
    }

    private boolean checkNotNull() {
        return jtfHoTen.getText() != null && !jtfHoTen.getText().equalsIgnoreCase("")&&jtfSoDienThoai.getText() != null && !jtfSoDienThoai.getText().equalsIgnoreCase("")
                &&jtaDiaChi.getText() != null && !jtaDiaChi.getText().equalsIgnoreCase("");
    }

    private boolean showDialog() {
        int dialogResult = JOptionPane.showConfirmDialog(null,
                "Bạn muốn cập nhật dữ liệu hay không?", "Thông báo", JOptionPane.YES_NO_OPTION);
        return dialogResult == JOptionPane.YES_OPTION;
    }
    
    public java.sql.Date covertDateToDateSql(Date d) {
        return new java.sql.Date(d.getTime());
    }
    
}