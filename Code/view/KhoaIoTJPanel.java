/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.teamvietdev.qlhv.view;

import com.teamvietdev.qlhv.controller.QuanLyKhoaIoTController;

/**
 *
 * @author ADMIN
 */
public class KhoaIoTJPanel extends javax.swing.JPanel {

    /**
     * Creates new form KhoaIoTJpanel
     */
    public KhoaIoTJPanel() {
        initComponents();
        QuanLyKhoaIoTController controller = new QuanLyKhoaIoTController(jpnViewIoT, btnAddIoT, jtfSearchIoT,btnRefreshIoT,btnDeleteIoT);
        controller.setDataToTable();
        controller.setEvent();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpnRoot = new javax.swing.JPanel();
        btnAddIoT = new javax.swing.JButton();
        btnDeleteIoT = new javax.swing.JButton();
        btnRefreshIoT = new javax.swing.JButton();
        jtfSearchIoT = new javax.swing.JTextField();
        jpnViewIoT = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        btnAddIoT.setBackground(new java.awt.Color(76, 175, 80));
        btnAddIoT.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnAddIoT.setForeground(new java.awt.Color(255, 255, 255));
        btnAddIoT.setText("Thêm mới");

        btnDeleteIoT.setBackground(new java.awt.Color(255, 51, 51));
        btnDeleteIoT.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnDeleteIoT.setForeground(new java.awt.Color(255, 255, 255));
        btnDeleteIoT.setText("Xóa");

        btnRefreshIoT.setBackground(new java.awt.Color(51, 102, 255));
        btnRefreshIoT.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnRefreshIoT.setForeground(new java.awt.Color(255, 255, 255));
        btnRefreshIoT.setText("Refresh");
        btnRefreshIoT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshIoTActionPerformed(evt);
            }
        });

        jtfSearchIoT.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jpnViewIoT.setBackground(new java.awt.Color(204, 204, 255));

        javax.swing.GroupLayout jpnViewIoTLayout = new javax.swing.GroupLayout(jpnViewIoT);
        jpnViewIoT.setLayout(jpnViewIoTLayout);
        jpnViewIoTLayout.setHorizontalGroup(
            jpnViewIoTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jpnViewIoTLayout.setVerticalGroup(
            jpnViewIoTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 583, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Arial", 3, 36)); // NOI18N
        jLabel1.setText("Khóa IoT");

        javax.swing.GroupLayout jpnRootLayout = new javax.swing.GroupLayout(jpnRoot);
        jpnRoot.setLayout(jpnRootLayout);
        jpnRootLayout.setHorizontalGroup(
            jpnRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnRootLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jpnRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jpnViewIoT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jpnRootLayout.createSequentialGroup()
                        .addComponent(jtfSearchIoT, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRefreshIoT, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(btnDeleteIoT, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(btnAddIoT, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
        );
        jpnRootLayout.setVerticalGroup(
            jpnRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnRootLayout.createSequentialGroup()
                .addGroup(jpnRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnRootLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jpnRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAddIoT, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDeleteIoT, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRefreshIoT, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfSearchIoT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jpnRootLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
                .addComponent(jpnViewIoT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnRoot, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnRoot, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnRefreshIoTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshIoTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRefreshIoTActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddIoT;
    private javax.swing.JButton btnDeleteIoT;
    private javax.swing.JButton btnRefreshIoT;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jpnRoot;
    private javax.swing.JPanel jpnViewIoT;
    private javax.swing.JTextField jtfSearchIoT;
    // End of variables declaration//GEN-END:variables
}
