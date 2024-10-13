package com.teamvietdev.qlhv.view;

import com.teamvietdev.qlhv.controller.KhoaHocAIController;
import com.teamvietdev.qlhv.model.HocVien;

import com.teamvietdev.qlhv.model.KhoaHocAI;
import com.teamvietdev.qlhv.service.KhoaHocAIService;
import com.teamvietdev.qlhv.service.KhoaHocAIServiceImpl;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.accessibility.Accessible;
import javax.swing.JFrame;
import javax.swing.JToggleButton;

/**
 *
 * @author HP
 */
public class KhoaAIFrame extends javax.swing.JFrame {
    private KhoaHocAIService khoaHocAIService = new KhoaHocAIServiceImpl();
    /**
     * Creates new form HocVienJFrame
     */
    public KhoaAIFrame(KhoaHocAI khoaHocAI) {
        initComponents();
        KhoaHocAIController controller = new KhoaHocAIController(btnSubmitAI, jtfMaHocVienAI, jtfHoTenAI, 
                jdcNgaySinhAI, jtfGioiTinhNamAI, jtfGioiTinhNuAI, jcbKichHoatAI, jtfDiemTK, 
                jdcNgayBDAI, jdcKetthucAI, textFieldKhoa, jlbMsgAI);
        controller.setView(khoaHocAI);
        controller.setEvent();
        jtfMaHocVienAI.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                updateStudentInfo();
            }
        });
    }

    private void updateStudentInfo() {
        String text = jtfMaHocVienAI.getText();
        if (!text.isEmpty()) {
            try {
                int maHocVien = Integer.parseInt(text);
                HocVien hocVien = khoaHocAIService.getHocVienByMa(maHocVien);
                if (hocVien != null) {
                    // Gán thông tin vào các trường
                    updateFieldsWithHocVien(hocVien);
                } else {
                    jlbMsgAI.setText("Học viên không tồn tại với mã: " + maHocVien);
                    clearFields(); // Xóa thông tin nếu không tìm thấy học viên
                }
            } catch (NumberFormatException ex) {
                jlbMsgAI.setText("Vui lòng nhập mã học viên hợp lệ!");
            }
        } else {
            clearFields(); // Xóa thông tin nếu trường mã học viên rỗng
        }
    }

    private void updateFieldsWithHocVien(HocVien hocVien) {
        jtfHoTenAI.setText(hocVien.getHo_ten());
        jdcNgaySinhAI.setDate(hocVien.getNgay_sinh());
        jtfGioiTinhNamAI.setSelected(hocVien.isGioi_tinh());
        jtfGioiTinhNuAI.setSelected(!hocVien.isGioi_tinh()); // Nếu có trường nữ
    }

    private void clearFields() {
        jtfHoTenAI.setText("");
        jdcNgaySinhAI.setDate(null);
        jtfGioiTinhNamAI.setSelected(false);
        jtfGioiTinhNuAI.setSelected(false);
    }

    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jtfMaHocVienAI = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jtfHoTenAI = new javax.swing.JTextField();
        jtfGioiTinhNamAI = new javax.swing.JRadioButton();
        jtfGioiTinhNuAI = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        jtfDiemTK = new javax.swing.JTextField();
        jdcNgaySinhAI = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        jcbKichHoatAI = new javax.swing.JCheckBox();
        jdcNgayBDAI = new com.toedter.calendar.JDateChooser();
        jlbNgayBatDau = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        textFieldKhoa = new javax.swing.JTextField();
        jdcKetthucAI = new com.toedter.calendar.JDateChooser();
        jlbMsgAI = new javax.swing.JLabel();
        btnSubmitAI = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin học viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setText("Mã học viên:");

        jtfMaHocVienAI.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText("Họ và tên:");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText("Ngày sinh:");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel4.setText("Giới tính:");

        jtfHoTenAI.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        buttonGroup1.add(jtfGioiTinhNamAI);
        jtfGioiTinhNamAI.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jtfGioiTinhNamAI.setSelected(true);
        jtfGioiTinhNamAI.setText("Nam");
        jtfGioiTinhNamAI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfGioiTinhNamAIActionPerformed(evt);
            }
        });

        buttonGroup1.add(jtfGioiTinhNuAI);
        jtfGioiTinhNuAI.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jtfGioiTinhNuAI.setText("Nữ");
        jtfGioiTinhNuAI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfGioiTinhNuAIActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel6.setText("Điểm:");

        jtfDiemTK.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jdcNgaySinhAI.setDateFormatString("dd/MM/yyyy");
        jdcNgaySinhAI.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel5.setText("Tình trạng:");

        jcbKichHoatAI.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jcbKichHoatAI.setText("Kích hoạt");

        jdcNgayBDAI.setDateFormatString("dd/MM/yyyy");

        jlbNgayBatDau.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jlbNgayBatDau.setText("Ngày bắt đầu:");

        jLabel7.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel7.setText("Ngày kết thúc: ");

        jLabel8.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel8.setText("Khóa:");

        textFieldKhoa.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jdcKetthucAI.setDateFormatString("dd/MM/yyyy");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jdcNgaySinhAI, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jtfGioiTinhNamAI, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jtfGioiTinhNuAI, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jtfHoTenAI, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtfMaHocVienAI, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(jlbNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jtfDiemTK, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                                                .addComponent(jdcNgayBDAI, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE))
                                            .addComponent(jdcKetthucAI, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(textFieldKhoa, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(50, 50, 50))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jcbKichHoatAI, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfMaHocVienAI, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtfHoTenAI, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jlbNgayBatDau))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtfDiemTK, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addComponent(jdcNgayBDAI, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jdcKetthucAI, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jdcNgaySinhAI, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfGioiTinhNamAI)
                            .addComponent(jtfGioiTinhNuAI)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcbKichHoatAI))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(textFieldKhoa, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(98, 98, 98))))
        );

        jlbMsgAI.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jlbMsgAI.setForeground(new java.awt.Color(255, 0, 0));

        btnSubmitAI.setBackground(new java.awt.Color(0, 204, 0));
        btnSubmitAI.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnSubmitAI.setForeground(new java.awt.Color(255, 255, 255));
        btnSubmitAI.setText("Lưu dữ liệu");
        btnSubmitAI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitAIActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jlbMsgAI, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSubmitAI, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jlbMsgAI, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSubmitAI, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtfGioiTinhNamAIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfGioiTinhNamAIActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfGioiTinhNamAIActionPerformed

    private void jtfGioiTinhNuAIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfGioiTinhNuAIActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfGioiTinhNuAIActionPerformed

    private void btnSubmitAIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitAIActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSubmitAIActionPerformed
    public class JRadioButton extends JToggleButton
        implements Accessible{
        
    }
    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSubmitAI;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JCheckBox jcbKichHoatAI;
    private com.toedter.calendar.JDateChooser jdcKetthucAI;
    private com.toedter.calendar.JDateChooser jdcNgayBDAI;
    private com.toedter.calendar.JDateChooser jdcNgaySinhAI;
    private javax.swing.JLabel jlbMsgAI;
    private javax.swing.JLabel jlbNgayBatDau;
    private javax.swing.JTextField jtfDiemTK;
    private javax.swing.JRadioButton jtfGioiTinhNamAI;
    private javax.swing.JRadioButton jtfGioiTinhNuAI;
    private javax.swing.JTextField jtfHoTenAI;
    private javax.swing.JTextField jtfMaHocVienAI;
    private javax.swing.JTextField textFieldKhoa;
    // End of variables declaration//GEN-END:variables
}
