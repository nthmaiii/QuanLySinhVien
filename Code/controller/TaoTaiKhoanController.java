package com.teamvietdev.qlhv.controller;

import com.teamvietdev.qlhv.model.TaiKhoan;
import com.teamvietdev.qlhv.service.TaoTaiKhoanService;
import com.teamvietdev.qlhv.service.TaoTaiKhoanServiceImpl;
import com.teamvietdev.qlhv.view.MainJFrame;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.sql.SQLIntegrityConstraintViolationException;


/**
 *
 * @author HP
 */

public class TaoTaiKhoanController {
    private JButton btnSubmitTK;
    private JTextField jtfMaTK;
    private JTextField jtfHoTenNV;
    private JTextField jtfHoTenLogin;
    private JTextField jtfPassword;
    private JCheckBox jcbKichHoat;
    private JLabel jlbMsg;

    private TaiKhoan taiKhoan = null;

    private TaoTaiKhoanService taoTaiKhoanService = null;
    
    public TaoTaiKhoanController(JButton btnSubmitTK, JTextField jtfMaTK, JTextField jtfHoTenNV, JTextField jtfHoTenLogin,
           JTextField jtfPassword, JCheckBox jcbKichHoat, JLabel jlbMsg) {
        this.btnSubmitTK = btnSubmitTK;
        this.jtfMaTK = jtfMaTK;
        this.jtfHoTenNV = jtfHoTenNV;
        this.jtfHoTenLogin = jtfHoTenLogin;
        this.jtfPassword = jtfPassword;
        this.jcbKichHoat = jcbKichHoat;
        this.jlbMsg = jlbMsg;

        this.taoTaiKhoanService = new TaoTaiKhoanServiceImpl();
    }

    public void setView(TaiKhoan taiKhoan) {
       
        this.taiKhoan = taiKhoan;
        // set data
        jtfMaTK.setText("#" + taiKhoan.getMa_tai_khoan());
        jtfHoTenNV.setText(taiKhoan.getTen_tk());
        jtfHoTenLogin.setText(taiKhoan.getTen_dang_nhap());
        jtfPassword.setText(taiKhoan.getMat_khau());
        jcbKichHoat.setSelected(taiKhoan.isTinh_trang());
        
        //set Event
        
    }

    public void setEvent() {
        btnSubmitTK.addMouseListener(new MouseAdapter() {
           
            @Override
            public void mouseClicked(MouseEvent e) {
               
                try {
                    if (!checkNotNull()) {
                        jlbMsg.setText("Vui lòng nhập dữ liệu bắt buộc!");
                    } else {
                        
                        taiKhoan.setTen_tk(jtfHoTenNV.getText());
                        taiKhoan.setTen_dang_nhap(jtfHoTenLogin.getText());
                        taiKhoan.setMat_khau(jtfPassword.getText());
                        taiKhoan.setTinh_trang(jcbKichHoat.isSelected());
                        taiKhoan.setMa_nguoi_tao_tk(1);
                         if (showDialog()) {
                            int lastId = taoTaiKhoanService.createOrUpdate(taiKhoan);
                            if (lastId != 0) {
                                taiKhoan.setMa_tai_khoan(lastId);
                                jtfMaTK.setText("#" + taiKhoan.getMa_tai_khoan());
                                jlbMsg.setText("Xử lý cập nhật dữ liệu thành công!");
                                //setDataToTable();
                            } else {
                                jlbMsg.setText("Có lỗi xảy ra, vui lòng thử lại!");
                            }
                        }
                    }
                } catch (Exception ex) {
                    jlbMsg.setText(ex.toString());
                }

//                    try {
//                       if (!checkNotNull()) {
//                       jlbMsg.setText("Vui lòng nhập dữ liệu bắt buộc!");
//                     return;
//                       }
//
//                        // Kiểm tra xem có thay đổi tên tài khoản hoặc tên đăng nhập không
//                        if (!jtfHoTenNV.getText().equals(taiKhoan.getTen_tk()) || 
//                           !jtfHoTenLogin.getText().equals(taiKhoan.getTen_dang_nhap())) {
//                            jlbMsg.setText("Không được phép sửa tên tài khoản hoặc tên đăng nhập!");
//                            return;
//                        }
//                                                
//                       // Chỉ cho phép sửa mật khẩu
//                        taiKhoan.setMat_khau(jtfPassword.getText());
//                        if (showDialog()) {
//                            int lastId = taoTaiKhoanService.createOrUpdate(taiKhoan);
//                            if (lastId != 0) {
//                                taiKhoan.setMa_tai_khoan(lastId);
//                                jtfMaTK.setText("#" + taiKhoan.getMa_tai_khoan());
//                                jlbMsg.setText("Xử lý cập nhật dữ liệu thành công!");
//                                // setDataToTable();
//                                } else {
//                                    jlbMsg.setText("Có lỗi xảy ra, Bạn đã nhập trùng tên đăng nhập!");
//                                }
//                       }
//                } catch (Exception ex) {
//                    jlbMsg.setText(ex.toString());
//                }
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
                btnSubmitTK.setBackground(new Color(0, 204, 0)); // Màu mong muốn khi hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Giữ màu nền ban đầu khi chuột ra
                btnSubmitTK.setBackground(new Color(0,204,0)); // Màu ban đầu
            }
            private void setDataToTable() {
                
            }
        });
    }

    private boolean checkNotNull() {
        
        return jtfHoTenNV.getText() != null && !jtfHoTenNV.getText().equalsIgnoreCase("") && jtfPassword.getText() != null 
                && !jtfPassword.getText().equalsIgnoreCase("")
                && jtfHoTenLogin.getText() != null 
                && !jtfHoTenLogin.getText().equalsIgnoreCase("");
        
    }

    private boolean showDialog() {
        int dialogResult = JOptionPane.showConfirmDialog(null,
                "Bạn muốn cập nhật dữ liệu hay không?", "Thông báo", JOptionPane.YES_NO_OPTION);
        return dialogResult == JOptionPane.YES_OPTION;
    }
    
   
    
}



/*

public class TaoTaiKhoanController {
    private JButton btnSubmitTK;
    private JTextField jtfMaTK;
    private JTextField jtfHoTenNV;
    private JTextField jtfHoTenLogin;
    private JTextField jtfPassword;
    private JCheckBox jcbKichHoat;
    private JLabel jlbMsg;

    private TaiKhoan taiKhoan = null;
    private TaoTaiKhoanService taoTaiKhoanService = null;

    public TaoTaiKhoanController(JButton btnSubmitTK, JTextField jtfMaTK, JTextField jtfHoTenNV, JTextField jtfHoTenLogin,
                                  JTextField jtfPassword, JCheckBox jcbKichHoat, JLabel jlbMsg) {
        this.btnSubmitTK = btnSubmitTK;
        this.jtfMaTK = jtfMaTK;
        this.jtfHoTenNV = jtfHoTenNV;
        this.jtfHoTenLogin = jtfHoTenLogin;
        this.jtfPassword = jtfPassword;
        this.jcbKichHoat = jcbKichHoat;
        this.jlbMsg = jlbMsg;

        this.taoTaiKhoanService = new TaoTaiKhoanServiceImpl();
    }

    public void setView(TaiKhoan taiKhoan) {
        this.taiKhoan = taiKhoan;
        // set data
        jtfMaTK.setText("#" + taiKhoan.getMa_tai_khoan());
        jtfHoTenNV.setText(taiKhoan.getTen_tk());
        jtfHoTenLogin.setText(taiKhoan.getTen_dang_nhap());
        jtfPassword.setText(taiKhoan.getMat_khau());
        jcbKichHoat.setSelected(taiKhoan.isTinh_trang());
    }

    public void setEvent() {
        btnSubmitTK.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleSubmit();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnSubmitTK.setBackground(new Color(0, 200, 83));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnSubmitTK.setBackground(new Color(100, 221, 23));
            }
        });
    }

    private void handleSubmit() {
        try {
            if (!checkNotNull()) {
                jlbMsg.setText("Vui lòng nhập dữ liệu bắt buộc!");
                return;
            }

            // Kiểm tra xem có thay đổi tên tài khoản hoặc tên đăng nhập không
            if (!taiKhoan.getTen_tk().equals(jtfHoTenNV.getText()) || 
                !taiKhoan.getTen_dang_nhap().equals(jtfHoTenLogin.getText())) {
                jlbMsg.setText("Bạn chỉ được thay đổi mật khẩu!");
                return;
            }

            // Chỉ cho phép sửa mật khẩu
            taiKhoan.setMat_khau(jtfPassword.getText());
            if (showDialog()) {
                int lastId = taoTaiKhoanService.createOrUpdate(taiKhoan);
                handleCreationResponse(lastId);
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            JOptionPane.showMessageDialog(null, "Tên đăng nhập đã tồn tại. Vui lòng chọn tên khác!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            jlbMsg.setText(ex.toString());
        }
    }

    private void handleCreationResponse(int lastId) {
        if (lastId != 0) {
            taiKhoan.setMa_tai_khoan(lastId);
            jtfMaTK.setText("#" + taiKhoan.getMa_tai_khoan());
            jlbMsg.setText("Xử lý cập nhật dữ liệu thành công!");
            // setDataToTable();
        } else {
            jlbMsg.setText("Có lỗi xảy ra, Bạn đã nhập trùng tên đăng nhập!");
        }
    }

    private boolean checkNotNull() {
        return jtfPassword.getText() != null && !jtfPassword.getText().equals("");
    }

    private boolean showDialog() {
        int dialogResult = JOptionPane.showConfirmDialog(null,
                "Bạn muốn cập nhật dữ liệu hay không?", "Thông báo", JOptionPane.YES_NO_OPTION);
        return dialogResult == JOptionPane.YES_OPTION;
    }
}*/