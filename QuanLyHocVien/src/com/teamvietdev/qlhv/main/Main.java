package com.teamvietdev.qlhv.main;

import com.teamvietdev.qlhv.view.DangNhapJDialog;
import com.teamvietdev.qlhv.view.MainJFrame;

/**
 *
 * @author HP
 */
public class Main {
    public static void main(String[] args) {
//        new MainJFrame().setVisible(true);
        DangNhapJDialog dialog = new DangNhapJDialog(null, true);
        dialog.setTitle("ĐĂNG NHẬP HỆ THỐNG");
        dialog.setResizable(false);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    
    }
}
