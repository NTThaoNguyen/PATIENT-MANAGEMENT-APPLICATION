package GiaoDien;

import CTDL.NhanVien;
import CTDL.KhamDieuTri;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import qlbn.QLBN;

public class KhamDT extends javax.swing.JFrame {

    NhanVien nvDangTruc = new NhanVien();
    int iDBA = -5;

    public KhamDT(NhanVien nvDangTruc) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.nvDangTruc = nvDangTruc;
        setup();
    }

    public void setup() {
        jLabel_NhanVien.setText("Nhân viên: " + nvDangTruc.getHo() + " " + nvDangTruc.getTen());
        long millis = System.currentTimeMillis();
        jLabel_NgayKham.setText("Ngày khám:               " + new java.sql.Date(millis));
        jLabel_MaBA.setText(" ");
        jLabel_KetQua.setText(" ");
    }

    private void themKDT(KhamDieuTri dt) {
        Connection ketNoi = QLBN.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement("INSERT INTO KHAMDIEUTRI VALUES (?,?, getDate(), ?)");
            ps.setInt(1, dt.getiDNV());
            ps.setInt(2, dt.getiDBA());
            ps.setString(3, dt.getKetQua());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(KhamDT.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel_NhanVien = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField_MaBA = new javax.swing.JTextField();
        jLabel_MaBA = new javax.swing.JLabel();
        jLabel_NgayKham = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea_KetQua = new javax.swing.JTextArea();
        jButton_HT = new javax.swing.JButton();
        jLabel_KetQua = new javax.swing.JLabel();
        jButton_SDDV = new javax.swing.JButton();
        jButton_LTT = new javax.swing.JButton();
        jButton_LHD = new javax.swing.JButton();
        jButton_XacNhan = new javax.swing.JButton();

        jLabel4.setText("jLabel4");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("KHÁM ĐIỀU TRỊ");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("KHÁM ĐIỀU TRỊ");

        jLabel_NhanVien.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        jLabel_NhanVien.setText("jLabel2");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Mã bệnh án");

        jTextField_MaBA.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel_MaBA.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel_MaBA.setForeground(new java.awt.Color(255, 51, 51));
        jLabel_MaBA.setText("jLabel3");

        jLabel_NgayKham.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel_NgayKham.setText("jLabel2");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Kết quả");

        jTextArea_KetQua.setColumns(20);
        jTextArea_KetQua.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextArea_KetQua.setRows(5);
        jScrollPane1.setViewportView(jTextArea_KetQua);

        jButton_HT.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton_HT.setText("HOÀN TẤT");
        jButton_HT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_HTActionPerformed(evt);
            }
        });

        jLabel_KetQua.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel_KetQua.setForeground(new java.awt.Color(255, 51, 51));
        jLabel_KetQua.setText("jLabel3");

        jButton_SDDV.setBackground(new java.awt.Color(112, 220, 220));
        jButton_SDDV.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton_SDDV.setText("Sử dụng dịch vụ");
        jButton_SDDV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_SDDVActionPerformed(evt);
            }
        });

        jButton_LTT.setBackground(new java.awt.Color(112, 220, 220));
        jButton_LTT.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton_LTT.setText("Lập toa thuốc");
        jButton_LTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_LTTActionPerformed(evt);
            }
        });

        jButton_LHD.setBackground(new java.awt.Color(112, 220, 220));
        jButton_LHD.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton_LHD.setText("Lập hóa đơn");
        jButton_LHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_LHDActionPerformed(evt);
            }
        });

        jButton_XacNhan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton_XacNhan.setText("Xác nhận");
        jButton_XacNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_XacNhanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jButton_SDDV, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addComponent(jButton_LTT, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addComponent(jButton_LHD, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel_NhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(289, 289, 289)
                        .addComponent(jButton_HT, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_NgayKham, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addGap(81, 81, 81)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel_KetQua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addGap(53, 53, 53)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel_MaBA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jTextField_MaBA, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)))))
                        .addGap(18, 18, 18)
                        .addComponent(jButton_XacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(265, 265, 265)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_NhanVien)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField_MaBA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_XacNhan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_MaBA)
                .addGap(23, 23, 23)
                .addComponent(jLabel_NgayKham)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_KetQua)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_SDDV, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_LTT, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_LHD, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addComponent(jButton_HT, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private boolean ktBA(String s) {
        if (s.equals("")) {
            jLabel_MaBA.setText("Mã bệnh án không được để trống!");
            return false;
        } else {
            boolean kt = false;
            Connection ketNoi = QLBN.layKetNoi();
            try {
                PreparedStatement ps = ketNoi.prepareStatement("SELECT * FROM BENHAN WHERE IDBA = " + s);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    kt = true;
                }
                rs.close();
                ps.close();
                ketNoi.close();
            } catch (SQLException ex) {
                Logger.getLogger(KhamDT.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (kt == false) {
                jLabel_MaBA.setText("Mã bệnh án không tồn tại!");
                return false;
            } else {
                ketNoi = QLBN.layKetNoi();
                try {
                    PreparedStatement ps = ketNoi.prepareStatement("SELECT * FROM NOITRU WHERE IDBA = " + s);
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        kt = true;
                    }
                    rs.close();
                    ps.close();
                    ketNoi.close();
                } catch (SQLException ex) {
                    Logger.getLogger(KhamDT.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (kt == false) {
                    jLabel_MaBA.setText("Bệnh nhân này không có nhập viện!");
                    return false;
                } else {
                    jLabel_MaBA.setText(" ");
                    return true;
                }
            }
        }
    }

    private boolean ktKQ(String s) {
        if (s.equals("")) {
            jLabel_KetQua.setText("Kết quả không được để trống!");
            return false;
        } else {
            jLabel_KetQua.setText(" ");
            return true;
        }
    }

    public boolean checkNoiTru(String iDBA) {
        boolean kt = false;
        Connection ketNoi = QLBN.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement("SELECT * FROM NOITRU WHERE IDBA = " + iDBA);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                kt = true;
            }
            rs.close();
            ps.close();
            ketNoi.close();
        } catch (SQLException ex) {
            Logger.getLogger(KhamDT.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kt;
    }

    private void jButton_HTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_HTActionPerformed
        boolean kt = true;
        KhamDieuTri dt = new KhamDieuTri();
        dt.setiDNV(nvDangTruc.getIdNV());
        String temp;
        temp = jTextField_MaBA.getText();
        if (ktBA(temp) == false) {
            kt = false;
        } else {
            dt.setiDBA(Integer.parseInt(temp));
        }
        temp = jTextArea_KetQua.getText();
        if (ktKQ(temp) == false) {
            kt = false;
        } else {
            dt.setKetQua(temp);
        }

        if (kt == true) {
            themKDT(dt);
            JOptionPane.showMessageDialog(this, "Hoàn thành khám điều trị!");
            this.dispose();
        }

    }//GEN-LAST:event_jButton_HTActionPerformed

    private void jButton_LTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_LTTActionPerformed
        LapToaThuoc ltt = new LapToaThuoc(nvDangTruc, iDBA);
        ltt.setVisible(true);
    }//GEN-LAST:event_jButton_LTTActionPerformed

    private void jButton_LHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_LHDActionPerformed
        HoaDon hd = new HoaDon(nvDangTruc, iDBA);
        hd.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton_LHDActionPerformed

    private void jButton_SDDVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_SDDVActionPerformed
        SuDungDichVu dv = new SuDungDichVu(nvDangTruc, iDBA);
        dv.setVisible(true);
    }//GEN-LAST:event_jButton_SDDVActionPerformed

    private void jButton_XacNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_XacNhanActionPerformed
        String temp = jTextField_MaBA.getText();
        boolean kt1 = ktBA(temp);
        boolean kt2 = checkNoiTru(temp);
        if (kt1 == true && kt2 == true) {
            iDBA = Integer.parseInt(temp);
            jButton_XacNhan.setVisible(false);
            jTextField_MaBA.setEditable(false);
            jButton_HT.setEnabled(true);
            jButton_LTT.setEnabled(true);
            jButton_SDDV.setEnabled(true);
            jButton_LHD.setEnabled(true);
        } else if (kt1 == true && kt2 == false) {
            jButton_HT.setEnabled(false);
            jButton_LTT.setEnabled(false);
            jButton_SDDV.setEnabled(false);
            jButton_LHD.setEnabled(false);
            JOptionPane.showMessageDialog(this, "Bệnh nhân này được điều trị tại nhà!");
        } else {
            jButton_HT.setEnabled(false);
            jButton_LTT.setEnabled(false);
            jButton_SDDV.setEnabled(false);
            jButton_LHD.setEnabled(false);
            JOptionPane.showMessageDialog(this, "Mã bệnh án không tồn tại!");
        }
    }//GEN-LAST:event_jButton_XacNhanActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new KhamDieuTri().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_HT;
    private javax.swing.JButton jButton_LHD;
    private javax.swing.JButton jButton_LTT;
    private javax.swing.JButton jButton_SDDV;
    private javax.swing.JButton jButton_XacNhan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel_KetQua;
    private javax.swing.JLabel jLabel_MaBA;
    private javax.swing.JLabel jLabel_NgayKham;
    private javax.swing.JLabel jLabel_NhanVien;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea_KetQua;
    private javax.swing.JTextField jTextField_MaBA;
    // End of variables declaration//GEN-END:variables
}
