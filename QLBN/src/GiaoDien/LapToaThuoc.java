package GiaoDien;

import CTDL.NhanVien;
import CTDL.Thuoc;
import CTDL.CTToaThuoc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import qlbn.QLBN;

public class LapToaThuoc extends javax.swing.JFrame {

    NhanVien nvDangTruc;
    int idBA = -5;
    Vector ds = new Vector();
    int index = 0;

    public LapToaThuoc(NhanVien nvDangTruc, int idBA) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.nvDangTruc = nvDangTruc;
        setup();
        loadThuoc();
        if (idBA > 0) {
            jTextField_BA.setText(idBA + "");
            jTextField_BA.setEditable(false);
            jLabel_BA.setText(" ");
            this.idBA = idBA;
        } else {
            jTextField_BA.setText("");
            jTextField_BA.setEditable(true);
            jLabel_BA.setText(" ");
        }
    }

    private void setup() {
        jLabel_SoLuong.setText(" ");
        jTextField_SoLuong.setText("");
        jLabel_DangChon.setText(" ");
        jLabel5.setText("Nhân viên: " + nvDangTruc.getTen());
        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
        dtm.setNumRows(0);
    }

    private void loadThuoc() {
        Thuoc t;
        Connection ketNoi = QLBN.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement("SELECT * FROM THUOC");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                t = new Thuoc();
                t.setMaThuoc(rs.getString("MATHUOC"));
                t.setTenThuoc(rs.getString("TENTHUOC"));
                t.setCachDung(rs.getString("CACHDUNG"));
                t.settHYL(rs.getString("THYL"));
                jComboBox_Thuoc.addItem(t.getTenThuoc());
            }
            rs.close();
            ps.close();
            ketNoi.close();
        } catch (SQLException ex) {
            Logger.getLogger(LapToaThuoc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private int tinhTien() {
        int tong = 0;
        int sl = 0;
        String temp = "";
        for (int i = 0; i < ds.size(); i++) {
            Vector vt = (Vector) ds.get(i);
            temp = (String) vt.get(0);
            sl = Integer.parseInt((String) vt.get(1));
            Connection ketNoi = QLBN.layKetNoi();
            try {
                PreparedStatement ps = ketNoi.prepareStatement("SELECT GIA FROM GIATHUOC G, (SELECT T.MATHUOC FROM THUOC T WHERE T.TENTHUOC = '" + temp + "') K WHERE K.MATHUOC = G.MATHUOC");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    tong += rs.getInt("GIA") * sl;
                }
                rs.close();
                ps.close();
                ketNoi.close();
            } catch (SQLException ex) {
                Logger.getLogger(QuanLyTaiKhoan.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return tong;
    }

    private void lapToaThuoc() {
        int tong = tinhTien();
        System.out.println(tong);
        Connection ketNoi = QLBN.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement("INSERT INTO TOATHUOC VALUES (getDate(),?,?,?)");
            ps.setInt(1, tong);
            ps.setInt(2, idBA);
            ps.setInt(3, nvDangTruc.getIdNV());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LapToaThuoc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void lapCT_ToaThuoc() {
        CTToaThuoc ct = new CTDL.CTToaThuoc();
        Connection ketNoi = QLBN.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement("SELECT T.IDTT, MAX(T.NGAYLAP) FROM TOATHUOC T GROUP BY T.IDTT");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ct.setiDTT(rs.getInt("IDTT"));
            }
            rs.close();
            ps.close();
            ketNoi.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyTaiKhoan.class.getName()).log(Level.SEVERE, null, ex);
        }
        String temp = "";
        for (int i = 0; i < ds.size(); i++) {
            Vector vt = (Vector) ds.get(i);
            temp = (String) vt.get(0);
            ketNoi = QLBN.layKetNoi();
            try {
                PreparedStatement ps = ketNoi.prepareStatement("SELECT T.MATHUOC FROM THUOC T WHERE T.TENTHUOC = '" + temp + "'");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    ct.setMaThuoc(rs.getString("MATHUOC"));
                    ct.setSoLuong(Integer.parseInt((String) vt.get(1)));
                }
                rs.close();
                ps.close();
                ketNoi.close();
            } catch (SQLException ex) {
                Logger.getLogger(QuanLyTaiKhoan.class.getName()).log(Level.SEVERE, null, ex);
            }

            ketNoi = QLBN.layKetNoi();
            try {
                PreparedStatement ps = ketNoi.prepareStatement("INSERT INTO CT_TOATHUOC VALUES (?,?,?)");
                ps.setInt(1, ct.getiDTT());
                ps.setString(2, ct.getMaThuoc());
                ps.setInt(3, ct.getSoLuong());
                ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(QuanLyTaiKhoan.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox_Thuoc = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jTextField_SoLuong = new javax.swing.JTextField();
        jLabel_SoLuong = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel_DangChon = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jTextField_BA = new javax.swing.JTextField();
        jLabel_BA = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("LẬP TOA THUỐC");

        jTable1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên thuốc", "Số lượng"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("LẬP TOA THUỐC");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Tên thuốc");

        jComboBox_Thuoc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Số lượng");

        jTextField_SoLuong.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel_SoLuong.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel_SoLuong.setForeground(new java.awt.Color(255, 0, 0));
        jLabel_SoLuong.setText("jLabel4");

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setText("THÊM");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setText("XÓA");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel_DangChon.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel_DangChon.setText("jLabel4");

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton3.setText("HOÀN TẤT");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Mã bệnh án");

        jTextField_BA.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel_BA.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel_BA.setForeground(new java.awt.Color(255, 0, 0));
        jLabel_BA.setText("jLabel4");

        jLabel5.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        jLabel5.setText("jLabel5");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2))
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42))
                    .addComponent(jLabel_SoLuong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel_DangChon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel_BA, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextField_BA, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jComboBox_Thuoc, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField_SoLuong))
                .addGap(65, 65, 65)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 523, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(459, 459, 459)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(440, 440, 440)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(256, 256, 256))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(7, 7, 7)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jTextField_BA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel_BA)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox_Thuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jTextField_SoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel_SoLuong)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel_DangChon)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(48, 48, 48)
                        .addComponent(jLabel2)
                        .addGap(35, 35, 35)
                        .addComponent(jLabel3))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private boolean checkSL(String s) {
        if (s.equals("")) {
            jLabel_SoLuong.setText("Số lượng không được để trống!");
            return false;
        }
        if (s.equals("0")) {
            jLabel_SoLuong.setText("Số lượng phải lớn hơn 0!");
            return false;
        }
        String mau = "[0-9]{1,}";
        if (s.matches(mau) == false) {
            jLabel_SoLuong.setText("Số lượng chỉ bao gồm chữ số!");
            return false;
        } else {
            jLabel_SoLuong.setText(" ");
            return true;
        }
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String tenThuoc = (String) jComboBox_Thuoc.getSelectedItem();
        String soLuong = jTextField_SoLuong.getText();
        boolean kt = true;

        if (checkSL(soLuong) == false) {
            kt = false;
        }

        if (kt == true) {
            Vector vt = new Vector();
            vt.add(tenThuoc);
            vt.add(soLuong);
            ds.add(vt);
            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.addRow(vt);
            jTable1.setModel(dtm);
            jLabel_SoLuong.setText(" ");
            jTextField_SoLuong.setText("");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        index = jTable1.getSelectedRow();
        Vector vt = (Vector) ds.get(index);
        jLabel_DangChon.setText("Thuốc: " + vt.get(0) + " và Số lượng: " + vt.get(1));
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
        dtm.removeRow(index);
        jLabel_DangChon.setText(" ");
        ds.remove(index);
    }//GEN-LAST:event_jButton2ActionPerformed

    private boolean checkBA(String s) {
        if (s.equals("")) {
            jLabel_BA.setText("Mã bệnh án không được bỏ trống!");
            return false;
        }
        String mau = "[0-9]{1,}";
        if (s.matches(mau) == false) {
            jLabel_BA.setText("Mã bệnh án không hợp lệ");
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
                jLabel_BA.setText("Mã bệnh án không tồn tại!");
                return false;
            } else {
                jLabel_BA.setText(" ");
                return true;
            }
        }
    }

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        boolean kt = true;
        if (idBA < 0) {
            String temp = jTextField_BA.getText();
            if (checkBA(temp) == false) {
                kt = false;
            }
        }

        if (jTable1.getRowCount() > 0 && kt == true) {
            lapToaThuoc();
            lapCT_ToaThuoc();
            JOptionPane.showMessageDialog(this, "Lập toa thuốc thành công!");
            this.dispose();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new LapToaThuoc().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox_Thuoc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel_BA;
    private javax.swing.JLabel jLabel_DangChon;
    private javax.swing.JLabel jLabel_SoLuong;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField_BA;
    private javax.swing.JTextField jTextField_SoLuong;
    // End of variables declaration//GEN-END:variables
}
