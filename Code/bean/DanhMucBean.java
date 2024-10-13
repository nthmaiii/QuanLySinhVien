package com.teamvietdev.qlhv.bean;

import javax.swing.JLabel;
import javax.swing.JPanel;

//Lớp DanhMucBean giúp tổ chức và quản lý dữ liệu hiệu quả, cải thiện khả năng bảo trì và
//tái sử dụng mã. Nó cũng tạo điều kiện thuận lợi cho việc xử lý các sự kiện trong giao diện người dùng
//Ho tro xu ly bat su kien khi nhan vao moi Lable
public class DanhMucBean {

    private String kind;
    private JPanel jpn;
    private JLabel jlb;

    public DanhMucBean() {
    }

    public DanhMucBean(String kind, JPanel jpn, JLabel jlb) {
        this.kind = kind;
        this.jpn = jpn;
        this.jlb = jlb;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public JPanel getJpn() {
        return jpn;
    }

    public void setJpn(JPanel jpn) {
        this.jpn = jpn;
    }

    public JLabel getJlb() {
        return jlb;
    }

    public void setJlb(JLabel jlb) {
        this.jlb = jlb;
    }

}