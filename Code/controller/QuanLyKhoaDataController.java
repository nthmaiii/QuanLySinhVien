/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.teamvietdev.qlhv.controller;

import com.sun.jdi.connect.spi.Connection;
import com.teamvietdev.qlhv.model.KhoaHocData;
import com.teamvietdev.qlhv.service.KhoaHocDataService;
import com.teamvietdev.qlhv.service.KhoaHocDataServiceImpl;
import com.teamvietdev.qlhv.utility.ClassTableModel;
import com.teamvietdev.qlhv.view.KhoaDataJFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author HP
 */
public class QuanLyKhoaDataController {
    private JPanel jPanelViewData;
    private JButton jbtAddData;
    private JTextField jTextSearchData;
    private JButton jbtRefreshData;
    private JButton jbtDeleteData;
    
    private ClassTableModel classTableModel = null;
    private JTable table;
    private KhoaHocDataService khoaHocDataService = null;
    private final String[] COLUMNS = {"Mã học viên", "STT", "Tên học viên", "Ngày sinh",
        "Giới tính", "Trạng thái", "Điểm", "Ngày bắt đầu", "Ngày kết thúc","Khóa"};
    
    

    
    private TableRowSorter<TableModel> rowSorter = null;
    
    public QuanLyKhoaDataController(JPanel jPanelViewData, JButton jbtAddData, JTextField jTextSearchData,JButton jbtRefreshData,JButton jbtDeleteData) {
        this.jPanelViewData = jPanelViewData;
        this.jbtAddData = jbtAddData;
        this.jTextSearchData = jTextSearchData;
        this.jbtRefreshData = jbtRefreshData;
        this.jbtDeleteData = jbtDeleteData;
       
        this.khoaHocDataService = new KhoaHocDataServiceImpl();
    }
    
     public void setDataToTable() {
        List<KhoaHocData> listItem = khoaHocDataService.getListActiveStudents();
        
        DefaultTableModel model = new ClassTableModel().setTableKhoaData(listItem, COLUMNS);
        table = new JTable(model);
         rowSorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(rowSorter);
        
        jTextSearchData.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = jTextSearchData.getText();
                if (text.trim().length() == 0) {
                    //Neu rong hien thi tat ca danh dach
                    rowSorter.setRowFilter(null);
                } else {
                    //Cho phep hien thi thong tin 
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = jTextSearchData.getText();
                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
            
         });
        table.getColumnModel().getColumn(1).setMaxWidth(80);
        table.getColumnModel().getColumn(1).setMinWidth(80);
        table.getColumnModel().getColumn(1).setPreferredWidth(80);
        
        
        table.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    
                    // Khởi tạo đối tượng học viên
                    // Khởi tạo đối tượng học viên
                    int selectedRowIndex = table.getSelectedRow();
                    selectedRowIndex = table.convertRowIndexToModel(selectedRowIndex);

                    // Lấy thông tin từ bảng thiết lập cho học viên
                    KhoaHocData khoaHocData = new KhoaHocData();
                    khoaHocData.setMa_hoc_vien((int) model.getValueAt(selectedRowIndex, 0));
                    khoaHocData.setHo_ten(model.getValueAt(selectedRowIndex, 2).toString());
                    khoaHocData.setNgay_sinh((java.sql.Date) model.getValueAt(selectedRowIndex, 3));
                    khoaHocData.setGioi_tinh(model.getValueAt(selectedRowIndex, 4).toString().equalsIgnoreCase("Nam"));
                    khoaHocData.setTinh_trang((Boolean) model.getValueAt(selectedRowIndex, 5));
                    khoaHocData.setDiem(Float.parseFloat(model.getValueAt(selectedRowIndex, 6).toString()));
                    khoaHocData.setNgay_bat_dau((java.sql.Date) model.getValueAt(selectedRowIndex, 7));
                    khoaHocData.setNgay_ket_thuc((java.sql.Date) model.getValueAt(selectedRowIndex, 8));
                    khoaHocData.setKhoa((int) model.getValueAt(selectedRowIndex, 9));

                    // Mở khung để chỉnh sửa thông tin học viên
                    KhoaDataJFrame frame = new KhoaDataJFrame(khoaHocData);
                    frame.setTitle("Sửa thông tin học viên");
                    frame.setResizable(false);
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                }
            }  
    });
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setPreferredSize(new Dimension(100, 50));
        table.setRowHeight(50);
        table.validate();
        table.repaint();
        
        JScrollPane scroll = new JScrollPane();
        scroll.getViewport().add(table);
        scroll.setPreferredSize(new Dimension(1350, 400));
        jPanelViewData.removeAll();
        jPanelViewData.setLayout(new BorderLayout());
        jPanelViewData.add(scroll);
        jPanelViewData.validate();
        jPanelViewData.repaint();
     }
      public void setEvent() {
                jbtAddData.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // Khởi tạo và hiển thị frame
                    
                    KhoaDataJFrame frame = new KhoaDataJFrame(new KhoaHocData());
                    frame.setTitle("Thông tin học viên Data");
                    frame.setLocationRelativeTo(null);
                    frame.setResizable(false);
                    frame.setVisible(true);

                    
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    jbtAddData.setBackground(new Color(76, 175, 80)); // Màu khi hover
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    jbtAddData.setBackground(new Color(76, 175, 80)); // Màu ban đầu
                }
            });
            jbtRefreshData.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Gọi phương thức để cập nhật lại bảng
                setDataToTable();
                JOptionPane.showMessageDialog(null, "Bảng đã được làm mới.");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                jbtRefreshData.setBackground(new Color(51,102,255));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jbtRefreshData.setBackground(new Color(51,102,255));
            }
        });
        jbtDeleteData.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (table != null) { // Kiểm tra xem table có phải là null không
                    int selectedRowIndex = table.getSelectedRow();
                    if (selectedRowIndex != -1) {
                        selectedRowIndex = table.convertRowIndexToModel(selectedRowIndex);

                        // Lấy ID học viên từ hàng đã chọn (giả sử ID ở cột 0)
                        int studentId = (int) table.getModel().getValueAt(selectedRowIndex, 0);

                        // Hiển thị hộp thoại xác nhận
                        int confirm = JOptionPane.showConfirmDialog(null, 
                                "Bạn có chắc chắn muốn xóa học viên này không?", 
                                "Xác nhận xóa", 
                                JOptionPane.YES_NO_OPTION);

                        if (confirm == JOptionPane.YES_OPTION) {
                            // Cập nhật trạng thái học viên trong cơ sở dữ liệu thành false
                            try {
                                updateStudentStatusInKhoaHocData(studentId, false); // Đánh dấu trạng thái là false

                                // Xóa hàng khỏi mô hình bảng
                                DefaultTableModel model = (DefaultTableModel) table.getModel();
                                model.removeRow(selectedRowIndex);

                                JOptionPane.showMessageDialog(null, "Học viên đã được xóa khỏi danh sách.");
                                setDataToTable();
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(null, "Lỗi khi cập nhật cơ sở dữ liệu: " + ex.getMessage());
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Vui lòng chọn một học viên để xóa.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Bảng học viên chưa được khởi tạo.");
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                // Giữ màu nền khi di chuột vào (có thể bỏ qua hoặc đặt lại màu)
                jbtDeleteData.setBackground(new Color(255,51,51)); // Màu mong muốn khi hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Giữ màu nền ban đầu khi chuột ra
                jbtDeleteData.setBackground(new Color(255,51,51));  // Màu ban đầu
            }
        });  
        
      }
     private void updateStudentStatusInKhoaHocData(int id, boolean status) throws SQLException {
            String query = "UPDATE khoa_hoc_data SET tinh_trang = ? WHERE ma_hoc_vien = ?";
            try (java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_qlhv", "root", "");
                 PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setBoolean(1, status);
                pstmt.setInt(2, id);
                pstmt.executeUpdate();
            } catch (SQLException ex) {
                System.err.println("Lỗi khi cập nhật cơ sở dữ liệu: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
}
    
    

