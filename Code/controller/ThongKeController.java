/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.teamvietdev.qlhv.controller;

/**
 *
 * @author ADMIN
 */
import com.teamvietdev.qlhv.service.ThongKeServiceImpl2;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.teamvietdev.qlhv.service.ThongKeServiceImpl2;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;

import com.teamvietdev.qlhv.service.ThongKeServiceImpl2;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import javax.swing.table.DefaultTableCellRenderer;

public class ThongKeController {
    private JPanel jPanelThongKe;
    private JTable table;
    private ThongKeServiceImpl2 thongKeService;

    private final String[] COLUMNS = {"Tên khóa học", "Số học viên"};

    public ThongKeController(JPanel jPanelThongKe, Connection connection) {
        this.jPanelThongKe = jPanelThongKe;

        // Khởi tạo service với Connection
        this.thongKeService = new ThongKeServiceImpl2(connection);
        setEvent();
        loadDataToTable();
    }

    private void loadDataToTable() {
       DefaultTableModel model = new DefaultTableModel();
    model.setColumnIdentifiers(COLUMNS);

    // Lấy dữ liệu thống kê
    model.addRow(new Object[]{"Khóa AI", thongKeService.getSoHocVienKhoaHocAi()});
    model.addRow(new Object[]{"Khóa BigData", thongKeService.getSoHocVienKhoaHocData()});
    model.addRow(new Object[]{"Khóa IoT", thongKeService.getSoHocVienKhoaHocIot()});
    model.addRow(new Object[]{"Số học viên đang học ở trung tâm", thongKeService.getSoHocVienFromHocVien()});

    table = new JTable(model);
    table.setRowHeight(50); // Đặt chiều cao hàng lớn hơn

    // Làm đậm và to hơn chữ trong bảng
    table.setFont(new Font("Arial", Font.PLAIN, 14)); // Chữ bình thường cho dữ liệu
    table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16)); // Chữ đậm cho tiêu đề cột

    // Thiết lập chiều rộng cho các cột
    table.getColumnModel().getColumn(0).setPreferredWidth(350); // Chiều rộng cho cột "Tên khóa học"
    table.getColumnModel().getColumn(1).setPreferredWidth(250); // Chiều rộng cho cột "Số học viên"

    // Thiết lập renderer để làm đậm và to hơn nội dung ô
    DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
    renderer.setFont(new Font("Arial", Font.BOLD, 30)); // Chữ đậm và to cho nội dung
    table.setDefaultRenderer(Object.class, renderer);

    // Đặt kích thước cố định cho bảng
    table.setPreferredScrollableViewportSize(new Dimension(700, 400)); // Điều chỉnh kích thước theo nhu cầu
    table.setFillsViewportHeight(true); // Bảng sẽ lấp đầy viewport

    JScrollPane scroll = new JScrollPane(table);
    scroll.setPreferredSize(new Dimension(700, 400)); // Kích thước cho JScrollPane

    jPanelThongKe.removeAll();
    jPanelThongKe.setLayout(new BorderLayout());
    jPanelThongKe.add(scroll, BorderLayout.CENTER);
    jPanelThongKe.validate();
    jPanelThongKe.repaint();
    }

    private void setEvent() {
        
    }
}
