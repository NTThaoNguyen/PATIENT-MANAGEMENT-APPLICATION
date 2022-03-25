package GiaoDien;

import CTDL.DichVu;
import CTDL.NhanVien;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import qlbn.QLBN;

public class SuDungDichVu extends javax.swing.JFrame {

    public NhanVien nvDangTruc;
    private int maBenhAn = 0;
    Vector ds = new Vector();
    int index = 0;
    private long millis = System.currentTimeMillis();

    public SuDungDichVu(NhanVien nvDangTRuc, int iDBA) {
        initComponents();
        this.nvDangTruc = nvDangTRuc;
        this.setLocationRelativeTo(null);
        this.setTitle("Dịch vụ");
        cbb_dichVu.setEnabled(false);
        loadCbbDichVu();
        btn_chonDV.setVisible(false);
        lblb_nhanVien.setText(lblb_nhanVien.getText() + " " + nvDangTRuc.getHo() + " " + nvDangTRuc.getTen());
        if (iDBA > 0) {
            this.maBenhAn = iDBA;
            txt_maBA.setEnabled(false);
            btn_xacNhan.setEnabled(false);
            btn_xacNhan.setVisible(false);
            txt_maBA.setText(String.valueOf(iDBA));
            cbb_dichVu.setEnabled(true);
            btn_chonDV.setVisible(true);
        } else {
            //chonDichVu(iDBA, nvDangTRuc.getIdNV());
        }
    }

    void loadCbbDichVu() {
        Connection ketNoi = qlbn.QLBN.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement("SELECT * FROM DICHVU");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String tenDV = rs.getString("TENDV");
                cbb_dichVu.addItem(tenDV);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            Logger.getLogger(KhamBanDau.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        lblb_nhanVien = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_maBA = new javax.swing.JTextField();
        btn_xacNhan = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        cbb_dichVu = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        btn_XacNhan = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_dichVu = new javax.swing.JTable();
        btn_chonDV = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 26)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("DỊCH VỤ");

        lblb_nhanVien.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        lblb_nhanVien.setText("Nhân viên: ");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel3.setText("Mã bệnh án:");

        txt_maBA.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        btn_xacNhan.setText("Xác nhận");
        btn_xacNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xacNhanActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel4.setText("Chọn dịch vụ:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel5.setText("Dịch vụ đã chọn:");

        btn_XacNhan.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btn_XacNhan.setText("XÁC NHẬN");
        btn_XacNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XacNhanActionPerformed(evt);
            }
        });

        table_dichVu.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        table_dichVu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã dịch vụ", "Tên dịch vụ"
            }
        ));
        table_dichVu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_dichVuMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table_dichVu);

        btn_chonDV.setText("Thêm");
        btn_chonDV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_chonDVActionPerformed(evt);
            }
        });

        jButton1.setText("Xóa");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txt_maBA, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbb_dichVu, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_xacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn_chonDV, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(0, 118, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblb_nhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 538, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(139, 139, 139))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btn_XacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(340, 340, 340))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblb_nhanVien)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_maBA, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_xacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cbb_dichVu, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_chonDV, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(btn_XacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    boolean checkTonTaiBA(int iDBA) { //kiem tra ton tai benh an trong benh an
        boolean check = false;
        Connection ketNoi = qlbn.QLBN.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement("SELECT * FROM BENHAN");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int t = rs.getInt("IDBA");
                if (t == iDBA) {
                    check = true;
                }
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            Logger.getLogger(KhamBanDau.class.getName()).log(Level.SEVERE, null, e);
        }
        return check;
    }

    private void btn_xacNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xacNhanActionPerformed
        if (txt_maBA.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Không được để trống mã bệnh án!");
        } else {
            int iDBA = Integer.parseInt(txt_maBA.getText());
            if (checkTonTaiBA(iDBA) == true) {
                //chonDichVu(iDBA, nvDangTruc.getIdNV());
                txt_maBA.setEnabled(false);
                btn_xacNhan.setVisible(false);
                cbb_dichVu.setEnabled(true);
                btn_chonDV.setVisible(true);
                maBenhAn = iDBA;
            } else {
                JOptionPane.showMessageDialog(this, "Mã bệnh án này không tồn tại!");
            }
        }
    }//GEN-LAST:event_btn_xacNhanActionPerformed

    String layMaDV(String tenDV) {
        String maDV = "";
        Connection ketNoi = qlbn.QLBN.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement("SELECT * FROM DICHVU WHERE TENDV = N'" + tenDV + "'");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                maDV = rs.getString("MADV");
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            Logger.getLogger(KhamBanDau.class.getName()).log(Level.SEVERE, null, e);
        }
        return maDV;
    }

    private void btn_chonDVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_chonDVActionPerformed
        String tenDV = String.valueOf(cbb_dichVu.getSelectedItem());
        String maDV = layMaDV(tenDV);
        Vector vt = new Vector();
        vt.add(maDV);
        vt.add(tenDV);
        ds.add(vt);
        DefaultTableModel dtm = (DefaultTableModel) table_dichVu.getModel();
        dtm.addRow(vt);
        table_dichVu.setModel(dtm);
    }//GEN-LAST:event_btn_chonDVActionPerformed

    boolean themCTDV(int iDBA, String maDV, Date ngaySD) {
        boolean check = true;
        Connection ketNoi = QLBN.layKetNoi();
        try {
            System.out.println(iDBA + " " + maDV + " " + ngaySD);
            PreparedStatement ps = ketNoi.prepareStatement("INSERT INTO CT_DICHVU VALUES (?,?,GETDATE())");
            ps.setInt(1, iDBA);
            ps.setString(2, maDV);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            check = false;
            Logger.getLogger(QuanLyTaiKhoan.class.getName()).log(Level.SEVERE, null, ex);
        }
        return check;
    }

    private void btn_XacNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XacNhanActionPerformed
        boolean check = true;
        Date ngaySD = new java.sql.Date(millis);
        for (int i = 0; i < ds.size(); i++) {
            Vector temp = (Vector) ds.get(i);
            if (themCTDV(maBenhAn, String.valueOf(temp.get(0)), ngaySD) == false) {
                check = false;
            }
        }
        if (check == true) {
            JOptionPane.showMessageDialog(this, "Thêm dịch vụ thành công!");
            this.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(this, "Thêm dịch vụ thất bại!");
        }
    }//GEN-LAST:event_btn_XacNhanActionPerformed

    private void table_dichVuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_dichVuMouseClicked
        index = table_dichVu.getSelectedRow();
        Vector vt = (Vector) ds.get(index);
    }//GEN-LAST:event_table_dichVuMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        DefaultTableModel dtm = (DefaultTableModel) table_dichVu.getModel();
        dtm.removeRow(index);
        ds.remove(index);
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SuDungDichVu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SuDungDichVu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SuDungDichVu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SuDungDichVu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new SuDungDichVu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_XacNhan;
    private javax.swing.JButton btn_chonDV;
    private javax.swing.JButton btn_xacNhan;
    private javax.swing.JComboBox<String> cbb_dichVu;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblb_nhanVien;
    private javax.swing.JTable table_dichVu;
    private javax.swing.JTextField txt_maBA;
    // End of variables declaration//GEN-END:variables
}
