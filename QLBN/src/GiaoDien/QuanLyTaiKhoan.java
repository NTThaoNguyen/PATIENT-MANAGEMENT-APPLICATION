package GiaoDien;

import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.logging.Logger;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import qlbn.QLBN;

public class QuanLyTaiKhoan extends javax.swing.JFrame {

    public QuanLyTaiKhoan() {
        initComponents();
        this.setLocationRelativeTo(null);
        setup();
        loadUser();
    }

    private void setup() {
        jTextField_TenDangNhap.setText("");
        jTextField_MatKhau.setText("");
        jLabel_TenDangNhap.setText(" ");
        jLabel_MatKhau.setText(" ");
    }

    private void loadUser() {
        DefaultTableModel dtm = (DefaultTableModel) jTable_DangNhap.getModel();
        dtm.setNumRows(0);
        Connection ketNoi = QLBN.layKetNoi();
        Vector vt;
        try {
            PreparedStatement ps = ketNoi.prepareStatement("SELECT NV.IDNV, HOVATEN = NV.HO + ' ' + NV.TEN FROM (SELECT TENDANGNHAP FROM DANGNHAP) DN, NHANVIEN NV WHERE NV.IDNV = DN.TENDANGNHAP");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                vt = new Vector();
                vt.add(rs.getInt("IDNV"));
                vt.add(rs.getString("HOVATEN"));
                dtm.addRow(vt);
            }
            jTable_DangNhap.setModel(dtm);
            rs.close();
            ps.close();
            ketNoi.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyTaiKhoan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String maHoaPass(String s) {
        String temp = "";
        Connection ketNoi = QLBN.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement("SELECT CONVERT(VARCHAR(32), HashBytes('MD5', '" + s + "'), 2) as md5");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                temp = rs.getString(1);
            }
            rs.close();
            ps.close();
            ketNoi.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyTaiKhoan.class.getName()).log(Level.SEVERE, null, ex);
        }
        return temp;
    }

    private void themUser(int tenDN, String matKhau) {
        matKhau = maHoaPass(matKhau);
        Connection ketNoi = QLBN.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement("INSERT INTO DANGNHAP VALUES (?,?)");
            ps.setInt(1, tenDN);
            ps.setString(2, matKhau);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyTaiKhoan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void xoaUser(int tenDN) {
        Connection ketNoi = QLBN.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement("DELETE FROM DANGNHAP WHERE TENDANGNHAP = ?");
            ps.setInt(1, tenDN);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyTaiKhoan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void suaUser(int tenDN, String matKhau) {
        matKhau = maHoaPass(matKhau);
        Connection ketNoi = QLBN.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement("UPDATE DANGNHAP SET MATKHAU = ? WHERE TENDANGNHAP = ?");
            ps.setString(1, matKhau);
            ps.setInt(2, tenDN);
            ps.executeUpdate();
            ps.close();
            ketNoi.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyTaiKhoan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_DangNhap = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jTextField_TenDangNhap = new javax.swing.JTextField();
        jLabel_TenDangNhap = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField_MatKhau = new javax.swing.JTextField();
        jLabel_MatKhau = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("QUẢN LÝ TÀI KHOẢN");

        jButton1.setBackground(new java.awt.Color(242, 112, 112));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setText("XÓA");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(105, 195, 105));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setText("THÊM");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(112, 220, 220));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton3.setText("SỬA");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton4.setText("RELOAD");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("QUẢN LÝ TÀI KHOẢN");

        jTable_DangNhap.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTable_DangNhap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "TÊN ĐĂNG NHẬP", "HỌ VÀ TÊN NHÂN VIÊN"
            }
        ));
        jTable_DangNhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_DangNhapMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_DangNhap);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Tên đăng nhập");

        jTextField_TenDangNhap.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel_TenDangNhap.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel_TenDangNhap.setForeground(new java.awt.Color(255, 0, 0));
        jLabel_TenDangNhap.setText("jLabel3");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Mật khẩu");

        jTextField_MatKhau.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel_MatKhau.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel_MatKhau.setForeground(new java.awt.Color(255, 0, 0));
        jLabel_MatKhau.setText("jLabel3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel_MatKhau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField_MatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel_TenDangNhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField_TenDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(217, 217, 217)
                        .addComponent(jLabel1)))
                .addContainerGap(132, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextField_TenDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel_TenDangNhap)
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jTextField_MatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel_MatKhau)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private boolean kiemTraUserDeXoa(String s) {
        boolean kt = false;
        Connection ketNoi = QLBN.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement("SELECT * FROM DANGNHAP WHERE TENDANGNHAP = " + s);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                kt = true;
            }
            rs.close();
            ps.close();
            ketNoi.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyTaiKhoan.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kt;
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String temp = jTextField_TenDangNhap.getText();

        boolean kt = kiemTraUserDeXoa(temp);

        if (kt == true) {
            int ret = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn xóa tài khoản này?", "Xác nhận", 0);
            if (ret == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (ret == JOptionPane.OK_OPTION) {
                int tenDN = Integer.parseInt(temp);
                xoaUser(tenDN);
                loadUser();
                setup();
                JOptionPane.showMessageDialog(this, "Xóa tài khoản thành công!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Tên đăng nhập không tồn tại!");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        setup();
    }//GEN-LAST:event_jButton4ActionPerformed

    private boolean checkTen(String s) {
        if (s.equals("")) {
            jLabel_TenDangNhap.setText("Tên đăng nhập không được để trống!");
            return false;
        }
        String mau = "[0-9]{1,}";
        if (s.matches(mau) == false) {
            jLabel_TenDangNhap.setText("Tên đăng nhập là mã nhân viên!");
            return false;
        } else {
            boolean kt = false;
            int temp;
            int t = Integer.parseInt(s);
            Connection ketNoi = QLBN.layKetNoi();
            try {
                PreparedStatement ps = ketNoi.prepareStatement("SELECT * FROM DANGNHAP WHERE TENDANGNHAP = " + s);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    kt = true;
                }
                rs.close();
                ps.close();
                ketNoi.close();
            } catch (SQLException ex) {
                Logger.getLogger(QuanLyTaiKhoan.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (kt == false) {
                jLabel_TenDangNhap.setText("Tên đăng nhập không tồn tại!");
                return false;
            }
            else{
                jLabel_TenDangNhap.setText(" ");
                return true;
            }
        }
    }

    private boolean checkTenDN(String s) {
        if (s.equals("")) {
            jLabel_TenDangNhap.setText("Tên đăng nhập không được để trống!");
            return false;
        }
        String mau = "[0-9]{1,}";
        if (s.matches(mau) == false) {
            jLabel_TenDangNhap.setText("Tên đăng nhập là mã nhân viên!");
            return false;
        } else {
            boolean kt = true;
            int temp;
            int t = Integer.parseInt(s);
            Connection ketNoi = QLBN.layKetNoi();
            try {
                PreparedStatement ps = ketNoi.prepareStatement("SELECT * FROM DANGNHAP");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    temp = rs.getInt("TENDANGNHAP");
                    if (temp == t) {
                        kt = false;
                        break;
                    }
                }
                rs.close();
                ps.close();
                ketNoi.close();
            } catch (SQLException ex) {
                Logger.getLogger(QuanLyTaiKhoan.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (kt == false) {
                jLabel_TenDangNhap.setText("Tên đăng nhập đã tồn tại!");
                return false;
            } else {
                boolean xet = false; //true -> có nhân viên mang mã số đó
                ketNoi = QLBN.layKetNoi();
                try {
                    PreparedStatement ps = ketNoi.prepareStatement("SELECT * FROM NHANVIEN");
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        temp = rs.getInt("IDNV");
                        if (temp == t) {
                            xet = true;
                            break;
                        }
                    }
                    rs.close();
                    ps.close();
                    ketNoi.close();
                } catch (SQLException ex) {
                    Logger.getLogger(QuanLyTaiKhoan.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (xet == false) {
                    jLabel_TenDangNhap.setText("Mã nhân viên không tồn tại!");
                    return false;
                } else {
                    jLabel_TenDangNhap.setText("");
                    return true;
                }
            }
        }
    }

    private boolean checkMatKhau(String s) {
        if (s.equals("")) {
            jLabel_MatKhau.setText("Mật khẩu không được để trống!");
            return false;
        }
        String mau = "[A-Za-z0-9]{5,15}";
        if (s.matches(mau) == false) {
            jLabel_MatKhau.setText("Mật khẩu từ 5 đến 15 kí tự bao gồm chữ và số!");
            return false;
        } else {
            jLabel_MatKhau.setText("");
            return true;
        }
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        boolean kt = true;
        int tenDN = 0;
        String temp = jTextField_TenDangNhap.getText();
        String matKhau = jTextField_MatKhau.getText();
        if (checkTenDN(temp) == false) {
            kt = false;
        } else {
            tenDN = Integer.parseInt(temp);
        }
        if (checkMatKhau(matKhau) == false) {
            kt = false;
        }

        if (kt == true) {
            themUser(tenDN, matKhau);
            setup();
            loadUser();
            JOptionPane.showMessageDialog(this, "Thêm tài khoản thành công!");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String temp = jTextField_TenDangNhap.getText();
        String matKhau = jTextField_MatKhau.getText();
        boolean kt = true;
        
        if(checkTen(temp) == false) kt = false;
        if (checkMatKhau(matKhau) == false) kt = false;

        if (kt == true) {
            int tenDN = Integer.parseInt(temp);
            suaUser(tenDN, matKhau);
            setup();
            loadUser();
            JOptionPane.showMessageDialog(this, "Sửa tài khoản thành công!");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTable_DangNhapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_DangNhapMouseClicked
//        int i = jTable_DangNhap.getSelectedRow();
//        TableModel model = jTable_DangNhap.getModel();
//        String tenDN = (String) model.getValueAt(i, 0);
//        jTextField_TenDangNhap.setText(tenDN);
//        jTextField_MatKhau.setText("");
    }//GEN-LAST:event_jTable_DangNhapMouseClicked

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLyTaiKhoan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel_MatKhau;
    private javax.swing.JLabel jLabel_TenDangNhap;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_DangNhap;
    private javax.swing.JTextField jTextField_MatKhau;
    private javax.swing.JTextField jTextField_TenDangNhap;
    // End of variables declaration//GEN-END:variables
}
