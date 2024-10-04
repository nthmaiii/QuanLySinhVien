package com.teamvietdev.qlhv.controller;

import com.teamvietdev.qlhv.bean.DanhMucBean;
import com.teamvietdev.qlhv.view.BaoLoiJPanel;
import com.teamvietdev.qlhv.view.DangNhapJDialog;
import com.teamvietdev.qlhv.view.HocVienJPanel;
import com.teamvietdev.qlhv.view.KhoaHocJPanel;
import com.teamvietdev.qlhv.view.MainJFrame;
import com.teamvietdev.qlhv.view.TaoTKJPanel;
import com.teamvietdev.qlhv.view.TaoTKJPanel;
import com.teamvietdev.qlhv.view.ThongKeJPanel;
import com.teamvietdev.qlhv.view.TrangChuJPanel;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
//Nhận yêu cầu từ người dùng, xử lý nó và tương tác với model để cập nhật view.
// Xu ly chuyen qua lao cac man hinh voi nhau
public class ChuyenManHinhController {
    private final JPanel root;
    private String kindSelected = "";

    private List<DanhMucBean> listItem = null;
    
    
    public ChuyenManHinhController (JPanel jpnRoot) {
        this.root = jpnRoot;    
    }
    
    public void setView(JPanel jpnItem, JLabel jlbItem) {
        kindSelected = "TrangChu";
        jpnItem.setBackground(new Color(96, 100, 191));
        jlbItem.setBackground(new Color(96, 100, 191));
        
        root.removeAll();
        root.setLayout(new BorderLayout());
        root.add(new TrangChuJPanel());
        root.validate();
        root.repaint();
    }
    
    public void setEvent(List<DanhMucBean> listItem) {
        this.listItem = listItem;
        for(DanhMucBean item : listItem){
            item.getJlb().addMouseListener(new LabelEvent(item.getKind(),item.getJpn(), item.getJlb()));
        }
        
    }

    
    
    class LabelEvent implements MouseListener {
        private JPanel node;
        
        private String kind;
        private JPanel jpnItem;
        private JLabel jlbItem;
        
        public LabelEvent(String kind, JPanel jpnItem, JLabel jlbItem) {
            this.kind = kind;
            this.jpnItem = jpnItem;
            this.jlbItem = jlbItem;
        }
        
        

        
        @Override
        public void mouseClicked(MouseEvent e) {
            // Lay frame o thu muc hien tai
            MainJFrame frame = (MainJFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
    
            switch(kind){
                case "TrangChu":
                    node = new TrangChuJPanel();
                    break;
                case "HocVien":
                    node = new HocVienJPanel();
                    break;
                case "KhoaHoc":
                    node = new KhoaHocJPanel();
                    break;
                case "ThongKe":   
                    if("admin".equals(frame.getTitle())){
                        node = new ThongKeJPanel();
                        break;
                    }else{
                       
                        node = new BaoLoiJPanel();
                         // Dừng lại nếu không có quyền
                         // Hiển thị thông báo không có quyền truy cập
                        JOptionPane.showMessageDialog(null, 
                            "Nhân viên không có quyền truy cập vào tính năng này!", 
                            "Thông báo", 
                        JOptionPane.WARNING_MESSAGE);
                         break; // Dừng lại nếu không có quyền
                    }
                    
                    // more ...
                case "TaoTK":
                    if("admin".equals(frame.getTitle())){
                        node = new TaoTKJPanel();
                        break;
                    }else{
                       
                        node = new BaoLoiJPanel();
                         // Dừng lại nếu không có quyền
                         // Hiển thị thông báo không có quyền truy cập
                        JOptionPane.showMessageDialog(null, 
                            "Nhân viên không có quyền truy cập vào tính năng này!", 
                            "Thông báo", 
                        JOptionPane.WARNING_MESSAGE);
                         break; // Dừng lại nếu không có quyền
                    }
                case "Logout":
                    // Mở hộp thoại đăng nhập
                     DangNhapJDialog loginDialog = new DangNhapJDialog(frame, true);
                     loginDialog.setResizable(false);
                     //Tao vi tri chinh giua khi check:
                     loginDialog.setLocationRelativeTo(null);
                     frame.setVisible(false);
                     loginDialog.setVisible(true);
                     
                     return; // Trả về để không thực hiện thêm các thao tác khác
               
                default:
                    node = new TrangChuJPanel();
                    break;
            }
            root.removeAll();
            root.setLayout(new BorderLayout());
            root.add(node);
            root.validate();
            root.repaint();
            setChangeBackground(kind);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            kindSelected = kind;
            jpnItem.setBackground(new Color(96,100, 191));
            jlbItem.setBackground(new Color(96,100, 191));
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            jpnItem.setBackground(new Color(96,100, 191));
            jlbItem.setBackground(new Color(96,100, 191));
        
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (!kindSelected.equalsIgnoreCase(kind)){
                jpnItem.setBackground(new Color(76,175, 80));
                jlbItem.setBackground(new Color(76, 175, 80));
        
            }
        }
        
    }
    
    private void setChangeBackground (String kind) {
        for (DanhMucBean item : listItem){
            if(item.getKind().equalsIgnoreCase(kind)){
                item.getJpn().setBackground(new Color(96, 100, 191));
                item.getJlb().setBackground(new Color(96, 100, 191));

            }else {
                item.getJpn().setBackground(new Color(76,175, 80));
                item.getJlb().setBackground(new Color(76,175, 80));

            }
        }
    }
}