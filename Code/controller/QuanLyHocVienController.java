package com.teamvietdev.qlhv.controller;


import com.microsoft.schemas.office.visio.x2012.main.CellType;
//import com.sun.jdi.connect.spi.Connection;
import com.teamvietdev.qlhv.model.HocVien;
import com.teamvietdev.qlhv.service.HocVienService;
import com.teamvietdev.qlhv.service.HocVienServiceImpl;
import com.teamvietdev.qlhv.utility.ClassTableModel;
import com.teamvietdev.qlhv.view.HocVienJFrame;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
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
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 *
 * @author TVD
 */
//Quan ly viec hien thi
public class QuanLyHocVienController {

    private JPanel jpnView;
    private JButton btnAdd;
    private JTextField jtfSearch;
    private JButton btnRefresh;
    private JButton btnDelete;

    private ClassTableModel classTableModel = null;
    private JTable table;

    private final String[] COLUMNS = {"Mã học viên", "STT", "Tên học viên", "Ngày sinh",
        "Giới tính", "Số điện thoại", "Địa chỉ", "Trạng thái", "Mã người tạo"};

    private HocVienService hocVienService = null;

    private TableRowSorter<TableModel> rowSorter = null;

    public QuanLyHocVienController(JPanel jpnView, JButton btnAdd, JButton btnDelete,JTextField jtfSearch,JButton bntRefresh) {
        this.jpnView = jpnView;
        this.btnAdd = btnAdd;
        this.btnDelete = btnDelete;
        this.jtfSearch = jtfSearch;
        this.btnRefresh = bntRefresh;
        this.classTableModel = new ClassTableModel();

        this.hocVienService = new HocVienServiceImpl();
       // setDataToTable();
        //setEvent();
    }
    
    //Tao phuong thuc để làm mới bảng
   
    public void setDataToTable() {
       // List<HocVien> listItem = hocVienService.getList(); 
        List<HocVien> listItem = hocVienService.getListActiveStudents();
        
        DefaultTableModel model = classTableModel.setTableHocVien(listItem, COLUMNS);
        table = new JTable(model);
        
        rowSorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(rowSorter);

        jtfSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = jtfSearch.getText();
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
                String text = jtfSearch.getText();
                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
        
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
                    //Khoi tao doi tuong hoc vien
                    
                    //Lay thong tin tu bang thiet lap cho hocvien
                    HocVien hocVien = new HocVien();
                    hocVien.setMa_hoc_vien((int) model.getValueAt(selectedRowIndex, 0));
                    hocVien.setHo_ten(model.getValueAt(selectedRowIndex,2).toString());
                    hocVien.setNgay_sinh((java.sql.Date) (Date) model.getValueAt(selectedRowIndex, 3));
                    hocVien.setGioi_tinh(model.getValueAt(selectedRowIndex, 4).toString().equalsIgnoreCase("Nam"));
                    hocVien.setSo_dien_thoai(model.getValueAt(selectedRowIndex, 5) != null ? 
                    model.getValueAt(selectedRowIndex, 5).toString(): "");
                    hocVien.setDia_chi(model.getValueAt(selectedRowIndex, 6) != null ? 
                    model.getValueAt(selectedRowIndex, 6).toString(): "");
                    hocVien.setTinh_trang((boolean) model.getValueAt(selectedRowIndex, 7));
                    hocVien.setMa_nguoi_tao_tk((int) model.getValueAt(selectedRowIndex, 8));
                    
                    
                    HocVienJFrame frame = new HocVienJFrame(hocVien);
                    frame.setTitle("Sửa thông tin học viên");
                    frame.setResizable(false);
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                    //setDataToTable();
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
        jpnView.removeAll();
        jpnView.setLayout(new BorderLayout());
        jpnView.add(scroll);
        jpnView.validate();
        jpnView.repaint();
    }
    public void setEvent() {
        btnAdd.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                    HocVienJFrame frame = new HocVienJFrame(new HocVien());
                    frame.setTitle("Thông tin học viên");
                    frame.setLocationRelativeTo(null);
                    frame.setResizable(false);
                    frame.setVisible(true);
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // Giữ màu nền khi di chuột vào (có thể bỏ qua hoặc đặt lại màu)
                btnAdd.setBackground(new Color(76,175,80)); // Màu mong muốn khi hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Giữ màu nền ban đầu khi chuột ra
                btnAdd.setBackground(new Color(76,175,80)); // Màu ban đầu
            }
        });
        
        // Listener cho nút xóa
        btnDelete.addMouseListener(new MouseAdapter() {
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
                                updateStudentStatus(studentId, false); // Đánh dấu trạng thái là false

                                // Xóa hàng khỏi mô hình bảng
                                DefaultTableModel model = (DefaultTableModel) table.getModel();
                                model.removeRow(selectedRowIndex);

                                JOptionPane.showMessageDialog(null, "Học viên đã được xóa khỏi danh sách.");
                                setDataToTable();
                            } catch (SQLException ex) {
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
                btnDelete.setBackground(new Color(255,51,51)); // Màu mong muốn khi hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Giữ màu nền ban đầu khi chuột ra
                btnDelete.setBackground(new Color(255,51,51));  // Màu ban đầu
            }
        });
        btnRefresh.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Gọi phương thức để cập nhật lại bảng
                setDataToTable();
                JOptionPane.showMessageDialog(null, "Bảng đã được làm mới.");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnRefresh.setBackground(new Color(51,102,255));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnRefresh.setBackground(new Color(51,102,255));
            }
        });
       
        // Thiết lập kích thước và hiển thị thanh cuộn
    table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
    table.getTableHeader().setPreferredSize(new Dimension(100, 50));
    table.setRowHeight(50);
    
    // Khởi tạo JScrollPane với JTable
    JScrollPane scroll = new JScrollPane(table);
    scroll.setPreferredSize(new Dimension(1350, 400)); // Kích thước cho JScrollPane

    // Cập nhật jpnView
    jpnView.removeAll();
    jpnView.setLayout(new BorderLayout());
    jpnView.add(scroll, BorderLayout.CENTER); // Thêm JScrollPane vào jpnView
    jpnView.validate();
    jpnView.repaint();
    }
    // Phương thức cập nhật trạng thái học viên trong cơ sở dữ liệu
    private void updateStudentStatus(int id, boolean status) throws SQLException {
        String query = "UPDATE hoc_vien SET tinh_trang = ? WHERE ma_hoc_vien = ?";
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