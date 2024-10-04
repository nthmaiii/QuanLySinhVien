package com.teamvietdev.qlhv.controller;

import com.teamvietdev.qlhv.model.TaiKhoan;
import com.teamvietdev.qlhv.service.TaoTaiKhoanService;
import com.teamvietdev.qlhv.service.TaoTaiKhoanServiceImpl;
import com.teamvietdev.qlhv.utility.ClassTableModel;
import com.teamvietdev.qlhv.view.MainJFrame;
import com.teamvietdev.qlhv.view.TKJFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
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
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author HP
 */
public class QuanLyTaiKhoanController {
    private JPanel jpnViewTK;
    private JButton btnAddTK;
    private JTextField jtfSearchTK;
    private JButton btnXoaTK;
    private JButton btnRefreshTK;

    private ClassTableModel classTableModel = null;
    private JTable table;
    private String[] COLUMNS = {"Mã tài khoản", "STT", "Tên đăng nhập", "Mật khẩu",
        "Trạng thái", "Tên tài khoản"};

    private TaoTaiKhoanService taoTaiKhoanService = null;

    private TableRowSorter<TableModel> rowSorter = null;
    public QuanLyTaiKhoanController(JPanel jpnViewTK, JButton btnAddTK,JButton btnXoaTK ,JButton btnRefreshTK,JTextField jtfSearchTK) {
        this.jpnViewTK = jpnViewTK;
        this.btnAddTK = btnAddTK;
        this.btnXoaTK = btnXoaTK;
        this.btnRefreshTK= btnRefreshTK;
        this.jtfSearchTK = jtfSearchTK;
        
        this.classTableModel = new ClassTableModel();

        this.taoTaiKhoanService = new TaoTaiKhoanServiceImpl();
    }

    public void setDataToTable() {
        List<TaiKhoan> listItem = taoTaiKhoanService.getListActiveGV();
        DefaultTableModel model = new ClassTableModel().setTableTaiKhoan(listItem, COLUMNS);
        table = new JTable(model);

        rowSorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(rowSorter);

        jtfSearchTK.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = jtfSearchTK.getText();
                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = jtfSearchTK.getText();
                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        }
    );
        
        
        table.getColumnModel().getColumn(1).setMaxWidth(80);
        table.getColumnModel().getColumn(1).setMinWidth(80);
        table.getColumnModel().getColumn(1).setPreferredWidth(80);
        
        
        table.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    int selectedRowIndex = table.getSelectedRow();
                    selectedRowIndex = table.convertRowIndexToModel(selectedRowIndex);
                    
                    TaiKhoan taiKhoan = new TaiKhoan();
                    taiKhoan.setMa_tai_khoan((int) model.getValueAt(selectedRowIndex, 0));
                    taiKhoan.setTen_dang_nhap(model.getValueAt(selectedRowIndex,2).toString());
                    taiKhoan.setMat_khau(model.getValueAt(selectedRowIndex, 3).toString());
                    taiKhoan.setTinh_trang((boolean) model.getValueAt(selectedRowIndex, 4));
                    taiKhoan.setTen_tk(model.getValueAt(selectedRowIndex,5).toString());
                    //taiKhoan.setMa_nguoi_tao_tk((int) model.getValueAt(selectedRowIndex,6));
                    TKJFrame frame = new TKJFrame(taiKhoan);
                    frame.setTitle("Đổi mật khẩu");
                    frame.setResizable(false);
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                }
            }  
    });


    // design
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setPreferredSize(new Dimension(100, 50));
        table.setRowHeight(50);
        table.validate();
        table.repaint();
        
        JScrollPane scroll = new JScrollPane();
        scroll.getViewport().add(table);
        scroll.setPreferredSize(new Dimension(1350, 400));
        jpnViewTK.removeAll();
        jpnViewTK.setLayout(new BorderLayout());
        jpnViewTK.add(scroll);
        jpnViewTK.validate();
        jpnViewTK.repaint();
    }
    public void setEvent() {
        btnAddTK.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                TKJFrame frame = new TKJFrame(new TaiKhoan());
                frame.setTitle("Thông tin nhân viên");
                frame.setLocationRelativeTo(null);
                frame.setResizable(false);
                frame.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
                frame.setVisible(true);
                frame.pack(); 
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                 // Giữ màu nền khi di chuột vào (có thể bỏ qua hoặc đặt lại màu)
                btnAddTK.setBackground(new Color(76,175,80)); // Màu mong muốn khi hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Giữ màu nền ban đầu khi chuột ra
                btnAddTK.setBackground(new Color(76,175,80)); // Màu ban đầu
            }
        });
        
        btnXoaTK.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (table != null) { // Kiểm tra xem table có phải là null không
                    int selectedRowIndex = table.getSelectedRow();
                    if (selectedRowIndex != -1) {
                        selectedRowIndex = table.convertRowIndexToModel(selectedRowIndex);

                        // Lấy ID tài khoản từ hàng đã chọn (giả sử ID ở cột 0)
                        int studentId = (int) table.getModel().getValueAt(selectedRowIndex, 0);

                        // Hiển thị hộp thoại xác nhận
                        int confirm = JOptionPane.showConfirmDialog(null, 
                                "Bạn có chắc chắn muốn xóa tài khoản này không?", 
                                "Xác nhận xóa", 
                                JOptionPane.YES_NO_OPTION);

                        if (confirm == JOptionPane.YES_OPTION) {
                            // Cập nhật trạng thái tài khoản trong cơ sở dữ liệu thành false
                            try {
                                updateGVStatus(studentId, false); // Đánh dấu trạng thái là false

                                // Xóa hàng khỏi mô hình bảng
                                DefaultTableModel model = (DefaultTableModel) table.getModel();
                                model.removeRow(selectedRowIndex);

                                JOptionPane.showMessageDialog(null, "Tài khoản đã được xóa khỏi danh sách.");
                                setDataToTable();
                            } catch (SQLException ex) {
                                JOptionPane.showMessageDialog(null, "Lỗi khi cập nhật cơ sở dữ liệu: " + ex.getMessage());
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Vui lòng chọn một tài khoản để xóa.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Bảng Tài khoản chưa được khởi tạo.");
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                btnXoaTK.setBackground(new Color(255,51,51));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnXoaTK.setBackground(new Color(255,51,51));
            }
        });
        
        btnRefreshTK.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Gọi phương thức để cập nhật lại bảng
                setDataToTable();
                JOptionPane.showMessageDialog(null, "Bảng đã được làm mới.");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnRefreshTK.setBackground(new Color(153,255,255));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnRefreshTK.setBackground(new Color(153,255,255));
            }
        });
    }
    // Phương thức cập nhật trạng thái học viên trong cơ sở dữ liệu
    private void updateGVStatus(int id, boolean status) throws SQLException {
        String query = "UPDATE tai_khoan SET tinh_trang = ? WHERE ma_tai_khoan = ?";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_qlhv", "root", "");
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
