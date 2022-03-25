package GiaoDien;

import CTDL.BenhNhan;
import CTDL.NhanVien;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import qlbn.QLBN;

public class QuanLyBenhNhan extends javax.swing.JFrame {

    NhanVien nvDangTruc;
    Vector<BenhNhan> ds = new Vector<>();

    public QuanLyBenhNhan(NhanVien nvDangTruc) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.nvDangTruc = nvDangTruc;
        setup();
        loadBenhNhan();
    }

    public void setup() {
        jLabel_NV.setText("Nhân viên: " + nvDangTruc.getHo() + " " + nvDangTruc.getTen());
        jLabel_Ho.setText(" ");
        jLabel_Ten.setText(" ");
        jLabel_GioiTinh.setText(" ");
        jLabel_NgaySinh.setText(" ");
        jLabel_DiaChi.setText(" ");
        jLabel_BHYT.setText(" ");
        jLabel_CMND.setText(" ");
        jTextField_Ho.setText("");
        jTextField_Ten.setText("");
        jTextField_DiaChi.setText("");
        jTextField_BHYT.setText("");
        jTextField_CMND.setText("");
        jRadioButton_Nam.setSelected(true);
    }

    public void loadBenhNhan() {
        DefaultTableModel dtm = (DefaultTableModel) jTable_BenhNhan.getModel();
        dtm.setNumRows(0);
        Connection ketNoi = QLBN.layKetNoi();
        BenhNhan vt;
        Vector temp;
        ds = new Vector<>();
        try {
            PreparedStatement ps = ketNoi.prepareStatement("SELECT * FROM BENHNHAN");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                vt = new BenhNhan();
                temp = new Vector();
                vt.setHo(rs.getString("HO"));
                vt.setTen(rs.getString("TEN"));
                vt.setGioiTinh(rs.getString("GIOITINH"));
                vt.setNgaySinh(rs.getDate("NGAYSINH"));
                vt.setDiaChi(rs.getString("DIACHI"));
                vt.setcMND(rs.getString("CMND"));
                vt.setbHYT(rs.getString("BHYT"));
                temp.add(vt.getHo());
                temp.add(vt.getTen());
                temp.add(vt.getGioiTinh());
                temp.add(vt.getNgaySinh());
                temp.add(vt.getDiaChi());
                temp.add(vt.getcMND());
                temp.add(vt.getbHYT());
                ds.add(vt);
                dtm.addRow(temp);
            }
            jTable_BenhNhan.setModel(dtm);
            rs.close();
            ps.close();
            ketNoi.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyBenhNhan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean themBenhNhan(BenhNhan bn) {
        Connection ketNoi = QLBN.layKetNoi();
        System.out.println(bn.getHo() + " " + bn.getTen() + " " + bn.getGioiTinh() + " " + bn.getNgaySinh().toString() + " " + bn.getDiaChi() + " " + bn.getcMND() + " " + bn.getbHYT());
        try {
            PreparedStatement ps = ketNoi.prepareStatement("INSERT INTO BENHNHAN VALUES (?,?,?,?,?,?,?)");
            ps.setString(1, bn.getHo());
            ps.setString(2, bn.getTen());
            ps.setString(3, bn.getGioiTinh());
            ps.setString(4, bn.getNgaySinh().toString());
            ps.setString(5, bn.getDiaChi());
            ps.setString(6, bn.getcMND());
            ps.setString(7, bn.getbHYT());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyBenhNhan.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public boolean suaBenhNhan(BenhNhan bn) {
        Connection ketNoi = QLBN.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement("UPDATE BENHNHAN SET HO = ?, TEN = ?, GIOITINH = ?, NGAYSINH = ?, DIACHI = ?, BHYT = ? WHERE CMND = ?");
            ps.setString(1, bn.getHo());
            ps.setString(2, bn.getTen());
            ps.setString(3, bn.getGioiTinh());
            ps.setDate(4, bn.getNgaySinh());
            ps.setString(5, bn.getDiaChi());
            ps.setString(6, bn.getbHYT());
            ps.setString(7, bn.getcMND());
            ps.executeUpdate();
            ps.close();
            ketNoi.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyBenhNhan.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    public boolean xoaBenhNhan(String CMND) {
        Connection ketNoi = QLBN.layKetNoi();
        String sql = "delete from BENHNHAN where CMND = ?";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, CMND);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyBenhNhan.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public int lapBenhAn(String CMND) {
        int iDBN = -5;
        int iDBA = -5;
        Connection ketNoi = QLBN.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement("SELECT * FROM BENHNHAN WHERE CMND = N'" + CMND + "'");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                iDBN = rs.getInt("IDBN");
            }
            rs.close();
            ps.close();
            ketNoi.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyBenhNhan.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (iDBN > 0) {
            ketNoi = QLBN.layKetNoi();
            try {
                PreparedStatement ps = ketNoi.prepareStatement("INSERT INTO BENHAN VALUES (GETDATE(),?)");
                ps.setInt(1, iDBN);
                ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(QuanLyBenhNhan.class.getName()).log(Level.SEVERE, null, ex);
            }
            ketNoi = QLBN.layKetNoi();
            try {
                PreparedStatement ps = ketNoi.prepareStatement("SELECT * FROM BENHAN WHERE IDBN = " + iDBN);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    iDBA = rs.getInt("IDBA");
                }
                rs.close();
                ps.close();
                ketNoi.close();
            } catch (SQLException ex) {
                Logger.getLogger(QuanLyBenhNhan.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return iDBA;
    }

    public boolean checkLapBenhAn(String CMND) {
        int iDBN = -5;
        int dem = 0;
        int dem2 = -5;
        Connection ketNoi = QLBN.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement("SELECT * FROM BENHNHAN WHERE CMND = N'" + CMND + "'");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                iDBN = rs.getInt("IDBN");
            }
            rs.close();
            ps.close();
            ketNoi.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyBenhNhan.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (iDBN > 0) {
            ketNoi = QLBN.layKetNoi();
            try {
                PreparedStatement ps = ketNoi.prepareStatement("SELECT * FROM BENHAN WHERE IDBN = " + iDBN);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    dem++;
                }
                rs.close();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(QuanLyBenhNhan.class.getName()).log(Level.SEVERE, null, ex);
            }
            ketNoi = QLBN.layKetNoi();
            try {
                PreparedStatement ps = ketNoi.prepareStatement("SELECT COUNT(HD.IDBA) FROM HOADON HD, (SELECT IDBA FROM BENHAN WHERE IDBN = " + iDBN + ") T WHERE HD.IDBA = T.IDBA");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    dem2 = rs.getInt(1);
                }
                rs.close();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(QuanLyBenhNhan.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println(dem + " " + dem2);
        if (dem2 >= 0 && dem - dem2 == 0) {
            return true;
        } else {
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_BenhNhan = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField_Ho = new javax.swing.JTextField();
        jLabel_Ho = new javax.swing.JLabel();
        jTextField_Ten = new javax.swing.JTextField();
        jLabel_Ten = new javax.swing.JLabel();
        jRadioButton_Nam = new javax.swing.JRadioButton();
        jRadioButton_Nu = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel_GioiTinh = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jCalendar = new com.toedter.calendar.JDateChooser();
        jLabel_NgaySinh = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTextField_DiaChi = new javax.swing.JTextField();
        jLabel_DiaChi = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jTextField_CMND = new javax.swing.JTextField();
        jLabel_CMND = new javax.swing.JLabel();
        jTextField_BHYT = new javax.swing.JTextField();
        jLabel_BHYT = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField_Tim = new javax.swing.JTextField();
        jButton_Tim = new javax.swing.JButton();
        jButton_Them = new javax.swing.JButton();
        jButton_Sua = new javax.swing.JButton();
        jButton_Xoa = new javax.swing.JButton();
        jButton_Reload = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel_NV = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("QUẢN LÝ BỆNH NHÂN");
        setBackground(new java.awt.Color(255, 255, 255));

        jTable_BenhNhan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTable_BenhNhan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "HỌ", "TÊN", "GIỚI TÍNH", "NGÀY SINH", "ĐỊA CHỈ", "CMND", "BHYT"
            }
        ));
        jTable_BenhNhan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_BenhNhanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_BenhNhan);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("HỌ");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("TÊN");

        jTextField_Ho.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel_Ho.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel_Ho.setForeground(new java.awt.Color(255, 0, 0));
        jLabel_Ho.setText("jLabel3");

        jTextField_Ten.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel_Ten.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel_Ten.setForeground(new java.awt.Color(255, 0, 0));
        jLabel_Ten.setText("jLabel3");

        jRadioButton_Nam.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(jRadioButton_Nam);
        jRadioButton_Nam.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jRadioButton_Nam.setText("Nam");

        jRadioButton_Nu.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(jRadioButton_Nu);
        jRadioButton_Nu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jRadioButton_Nu.setText("Nữ");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("GIỚI TÍNH");

        jLabel_GioiTinh.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel_GioiTinh.setForeground(new java.awt.Color(255, 0, 0));
        jLabel_GioiTinh.setText("jLabel3");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("NGÀY SINH");

        jCalendar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel_NgaySinh.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel_NgaySinh.setForeground(new java.awt.Color(255, 0, 0));
        jLabel_NgaySinh.setText("jLabel3");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("ĐỊA CHỈ");

        jTextField_DiaChi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel_DiaChi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel_DiaChi.setForeground(new java.awt.Color(255, 0, 0));
        jLabel_DiaChi.setText("jLabel3");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("CMND");

        jTextField_CMND.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel_CMND.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel_CMND.setForeground(new java.awt.Color(255, 0, 0));
        jLabel_CMND.setText("jLabel3");

        jTextField_BHYT.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel_BHYT.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel_BHYT.setForeground(new java.awt.Color(255, 0, 0));
        jLabel_BHYT.setText("jLabel3");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("BHYT");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("TÌM BỆNH NHÂN");

        jButton_Tim.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton_Tim.setText("TÌM");
        jButton_Tim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_TimActionPerformed(evt);
            }
        });

        jButton_Them.setBackground(new java.awt.Color(105, 195, 105));
        jButton_Them.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton_Them.setText("THÊM");
        jButton_Them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ThemActionPerformed(evt);
            }
        });

        jButton_Sua.setBackground(new java.awt.Color(242, 112, 112));
        jButton_Sua.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton_Sua.setText("SỬA");
        jButton_Sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_SuaActionPerformed(evt);
            }
        });

        jButton_Xoa.setBackground(new java.awt.Color(112, 220, 220));
        jButton_Xoa.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton_Xoa.setText("XÓA");
        jButton_Xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_XoaActionPerformed(evt);
            }
        });

        jButton_Reload.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton_Reload.setText("RELOAD");
        jButton_Reload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ReloadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField_Tim, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_Tim, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(463, 471, Short.MAX_VALUE)
                        .addComponent(jButton_Them, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(jButton_Sua, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jButton_Xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jButton_Reload, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4))
                        .addGap(47, 47, 47)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jRadioButton_Nam, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(47, 47, 47)
                                .addComponent(jRadioButton_Nu, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jCalendar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel_GioiTinh, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel_Ten, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField_Ten, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel_Ho, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField_Ho, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel_NgaySinh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel13)
                            .addComponent(jLabel15))
                        .addGap(78, 78, 78)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel_BHYT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField_DiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_CMND)
                            .addComponent(jLabel_DiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_CMND, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField_BHYT, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(119, 119, 119))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(41, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jTextField_Ho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel_Ho)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField_Ten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel_Ten)
                                .addGap(17, 17, 17)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(jRadioButton_Nam)
                                    .addComponent(jRadioButton_Nu))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel_GioiTinh)
                                .addGap(18, 18, 18)
                                .addComponent(jCalendar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel9)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_DiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel_DiaChi)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jTextField_CMND, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel_CMND)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(jTextField_BHYT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel_BHYT)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_NgaySinh)
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_Tim, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jButton_Tim)
                    .addComponent(jButton_Sua, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Reload, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Them, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("QUẢN LÝ BỆNH NHÂN");

        jLabel_NV.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        jLabel_NV.setText("Nhân viên:");

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setText("KHÁM BAN ĐẦU");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jLabel_NV, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(524, 524, 524)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(17, 17, 17))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jLabel_NV)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public boolean checkTen(String s) {
        if (s.equals("") == true) {
            jLabel_Ten.setText("Tên không được bỏ trống!");
            return false;
        }
        String mau = "^\\p{L}+[\\p{L}\\p{Z}\\p{P}]{1,10}";
        if (s.matches(mau) == false) {
            jLabel_Ten.setText("Tên không hợp lệ!");
            return false;
        }
        jLabel_Ten.setText(" ");
        return true;
    }

    public boolean checkHo(String s) {
        if (s.equals("") == true) {
            jLabel_Ho.setText("Họ không được bỏ trống!");
            return false;
        }
        String mau = "^\\p{L}+[\\p{L}\\p{Z}\\p{P}]{1,30}";
        if (s.matches(mau) == false) {
            jLabel_Ho.setText("Họ không hợp lệ!");
            return false;
        }
        jLabel_Ho.setText(" ");
        return true;
    }

    public boolean checkNgaySinh(Date d) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            //Date date = df.parse(ngaySinh);
            String temp = df.format(d);
            jLabel_NgaySinh.setText(" ");
            return true;
        } catch (Exception e) {
            jLabel_NgaySinh.setText("Ngày sinh không hợp lệ!");
            return false;
        }
    }

    public boolean checkDiaChi(String s) {
        if (s.equals("") == true) {
            jLabel_DiaChi.setText("Địa chỉ không được để trống!");
            return false;
        }
        String mau = "[[0-9]+^\\p{L}+[\\p{L}\\p{Z}+[0-9/,.]]]{1,50}";
        if (s.matches(mau) == false) {
            jLabel_DiaChi.setText("Địa chỉ không hợp lệ");
            return false;
        }
        jLabel_DiaChi.setText(" ");
        return true;
    }

    public boolean checkCMND(String s) {
        if (s.equals("") == true) {
            jLabel_CMND.setText("CMND không được để trống!");
            return false;
        }
        String mau = "0[0-9]{9,11}";
        if (s.matches(mau) == false) {
            jLabel_CMND.setText("CMND gồm 10 - 12 số!");
            return false;
        }
        jLabel_CMND.setText(" ");
        return true;
    }

    public boolean checkBHYT(String s) {
        if (s.equals("") == true) {
            return true;
        }
        String mau = "[0-9]{13}";
        if (s.matches(mau) == false) {
            jLabel_BHYT.setText("BHYT bao gồm 13 số!");
            System.out.println("BHYT sai rồi " + s);
            return false;
        }
        jLabel_BHYT.setText(" ");
        return true;
    }

    private java.sql.Date doiDate(java.util.Date d) {
        String s = "";
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            s = df.format(d);
        } catch (Exception e) {
            System.out.println("Không chuyển ngày sinh được!");
        }
        java.sql.Date date = java.sql.Date.valueOf(s);
        return date;
    }

    private String chuanHoaHoTen(String hoTen) {
        hoTen = hoTen.trim();
        hoTen = hoTen.replaceAll("\\s+", " ");
        String[] temp = hoTen.split(" ");
        hoTen = "";
        for (int i = 0; i < temp.length; i++) {
            hoTen += temp[i].substring(0, 1).toUpperCase() + temp[i].substring(1).toLowerCase();
            if (i < temp.length - 1) {
                hoTen += " ";
            }
        }
        return hoTen;
    }

    private void jButton_ThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ThemActionPerformed
        BenhNhan bn = new BenhNhan();
        bn.setHo(jTextField_Ho.getText());
        bn.setTen(jTextField_Ten.getText());
        if (jRadioButton_Nam.isSelected() == true) {
            bn.setGioiTinh("Nam");
        } else {
            bn.setGioiTinh("Nữ");
        }
        //bn.setNgaySinh(doiDate(jCalendar.getDate()));
        Date temp = jCalendar.getDate();

        bn.setDiaChi(jTextField_DiaChi.getText());
        bn.setcMND(jTextField_CMND.getText());
        bn.setbHYT(jTextField_BHYT.getText());

        boolean kt = true;

        if (checkTen(bn.getTen()) == false) {
            kt = false;
        } else {
            bn.setTen(chuanHoaHoTen(bn.getTen()));
        }
        if (checkHo(bn.getHo()) == false) {
            kt = false;
        } else {
            bn.setHo(chuanHoaHoTen(bn.getHo()));
        }
        if (checkNgaySinh(temp) == false) {
            kt = false;
        } else {
            bn.setNgaySinh(doiDate(temp));
        }
        if (checkDiaChi(bn.getDiaChi()) == false) {
            kt = false;
        }
        if (checkCMND(bn.getcMND()) == false) {
            kt = false;
        }
        if (checkBHYT(bn.getbHYT()) == false) {
            kt = false;
        }

        if (kt == true) {
            if (themBenhNhan(bn) == true) {
                JOptionPane.showMessageDialog(this, "Thêm bệnh nhân thành công!");
            } else {
                JOptionPane.showMessageDialog(this, "Thêm bệnh nhân thất bại!");
            }
            setup();
            loadBenhNhan();
        }
    }//GEN-LAST:event_jButton_ThemActionPerformed

    private void jTable_BenhNhanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_BenhNhanMouseClicked
        int i = jTable_BenhNhan.getSelectedRow();
        BenhNhan bn = ds.get(i);
        jTextField_Ho.setText(bn.getHo());
        jTextField_Ten.setText(bn.getTen());
        if (bn.getGioiTinh().equals("Nam")) {
            jRadioButton_Nam.setSelected(true);
        } else {
            jRadioButton_Nu.setSelected(true);
        }
        jCalendar.setDate(bn.getNgaySinh());
        jTextField_DiaChi.setText(bn.getDiaChi());
        jTextField_CMND.setText(bn.getcMND());
        jTextField_BHYT.setText("");
        jTextField_BHYT.setText(bn.getbHYT());
    }//GEN-LAST:event_jTable_BenhNhanMouseClicked

    private void jButton_XoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_XoaActionPerformed
        String CMND = jTextField_CMND.getText();
        if (CMND.equals("") == false) {
            if (xoaBenhNhan(CMND) == true) {
                JOptionPane.showMessageDialog(this, "Xóa bệnh nhân thành công!");
            } else {
                JOptionPane.showMessageDialog(this, "Xóa bệnh nhân thất bại!");
            }
            setup();
            loadBenhNhan();
        }
    }//GEN-LAST:event_jButton_XoaActionPerformed

    private void jButton_ReloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ReloadActionPerformed
        setup();
        loadBenhNhan();
    }//GEN-LAST:event_jButton_ReloadActionPerformed

    private void jButton_SuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_SuaActionPerformed
        BenhNhan bn = new BenhNhan();
        bn.setHo(jTextField_Ho.getText());
        bn.setTen(jTextField_Ten.getText());
        if (jRadioButton_Nam.isSelected() == true) {
            bn.setGioiTinh("Nam");
        } else {
            bn.setGioiTinh("Nữ");
        }
        //bn.setNgaySinh(doiDate(jCalendar.getDate()));
        Date temp = jCalendar.getDate();

        bn.setDiaChi(jTextField_DiaChi.getText());
        bn.setcMND(jTextField_CMND.getText());
        bn.setbHYT(jTextField_BHYT.getText());

        boolean kt = true;

        if (checkTen(bn.getTen()) == false) {
            kt = false;
        } else {
            bn.setTen(chuanHoaHoTen(bn.getTen()));
        }
        if (checkHo(bn.getHo()) == false) {
            kt = false;
        } else {
            bn.setHo(chuanHoaHoTen(bn.getHo()));
        }
        if (checkNgaySinh(temp) == false) {
            kt = false;
        } else {
            bn.setNgaySinh(doiDate(temp));
        }
        if (checkDiaChi(bn.getDiaChi()) == false) {
            kt = false;
        }
        if (checkCMND(bn.getcMND()) == false) {
            kt = false;
        }
        if (checkBHYT(bn.getbHYT()) == false) {
            kt = false;
        }

        if (kt == true) {
            if (suaBenhNhan(bn) == true) {
                JOptionPane.showMessageDialog(this, "Sửa bệnh nhân thành công!");
            } else {
                JOptionPane.showMessageDialog(this, "Sửa bệnh nhân thất bại!");
            }
            setup();
            loadBenhNhan();
        }
    }//GEN-LAST:event_jButton_SuaActionPerformed

    private void jButton_TimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_TimActionPerformed
        loadBenhNhan();
        String CMND = jTextField_Tim.getText();
        if (CMND.equals("") == false) {
            Vector vt = null;
            BenhNhan bn = new BenhNhan();
            int n = ds.size();
            int i = 0;
            DefaultTableModel dtm = (DefaultTableModel) jTable_BenhNhan.getModel();
            dtm.setNumRows(0);
            while (i < n) {
                bn = ds.get(i);
                if (bn.getcMND().contains(CMND) == false) {
                    ds.remove(i);
                    n--;
                } else {
                    vt = new Vector();
                    vt.add(bn.getHo());
                    vt.add(bn.getTen());
                    vt.add(bn.getGioiTinh());
                    vt.add(bn.getNgaySinh());
                    vt.add(bn.getDiaChi());
                    vt.add(bn.getcMND());
                    vt.add(bn.getbHYT());
                    dtm.addRow(vt);
                    i++;
                }
            }
            jTable_BenhNhan.setModel(dtm);
        }
    }//GEN-LAST:event_jButton_TimActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String CMND = jTextField_CMND.getText();
        if (checkCMND(CMND) == true) {
            if (checkLapBenhAn(CMND) == true) {
                int i = lapBenhAn(CMND);
                if (i > 0) {
                    KhamBanDau kbd = new KhamBanDau(nvDangTruc, i);
                    kbd.setVisible(true);
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Bệnh nhân không tồn tại");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Bệnh nhân vẫn còn đang điều trị");
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new QuanLyBenhNhan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton_Reload;
    private javax.swing.JButton jButton_Sua;
    private javax.swing.JButton jButton_Them;
    private javax.swing.JButton jButton_Tim;
    private javax.swing.JButton jButton_Xoa;
    private com.toedter.calendar.JDateChooser jCalendar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel_BHYT;
    private javax.swing.JLabel jLabel_CMND;
    private javax.swing.JLabel jLabel_DiaChi;
    private javax.swing.JLabel jLabel_GioiTinh;
    private javax.swing.JLabel jLabel_Ho;
    private javax.swing.JLabel jLabel_NV;
    private javax.swing.JLabel jLabel_NgaySinh;
    private javax.swing.JLabel jLabel_Ten;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButton_Nam;
    private javax.swing.JRadioButton jRadioButton_Nu;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_BenhNhan;
    private javax.swing.JTextField jTextField_BHYT;
    private javax.swing.JTextField jTextField_CMND;
    private javax.swing.JTextField jTextField_DiaChi;
    private javax.swing.JTextField jTextField_Ho;
    private javax.swing.JTextField jTextField_Ten;
    private javax.swing.JTextField jTextField_Tim;
    // End of variables declaration//GEN-END:variables
}
