package GiaoDien;

import CTDL.NhanVien;
import GiaoDien.HoaDon;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class KhamBanDau extends javax.swing.JFrame {

    public NhanVien nvDangTruc;
    long millis = System.currentTimeMillis();
    String maKhoa;
    String tenKhoa = "", maPhong = "";
    int maBenhAn = 0;

    public KhamBanDau(NhanVien nvDangTruc, int iDBA) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.nvDangTruc = nvDangTruc;
        lbl_nhanVienHienTai.setText("Nhân viên: " + nvDangTruc.getHo() + " " + nvDangTruc.getTen());
        lbl_ngayKham.setText("Ngày khám bệnh:      " + String.valueOf(new java.sql.Date(millis)));
        this.setTitle("Khám ban đầu");
        jDialog1.setTitle("Chọn phòng");
        lbl_loiKetQua.setVisible(false);
        lbl_loiKetQua.setText("Kết quả khám không được để trống!");
        jDialog1.setSize(450, 450);
        jDialog1.setLocationRelativeTo(null);
        btn_hoanTat.setEnabled(false);
        btn_suDungDV.setEnabled(false);
        btn_lapToaThuoc.setEnabled(false);
        if (iDBA > 0) {
            btn_xacNhan.setVisible(false);
            tfd_maBA.setText(iDBA + "");
            tfd_maBA.setEditable(false);
            maBenhAn = iDBA;
            btn_hoanTat.setEnabled(true);
            btn_suDungDV.setEnabled(true);
            btn_lapToaThuoc.setEnabled(true);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jDialog1 = new javax.swing.JDialog();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cbx_Khoa = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cbx_Phong = new javax.swing.JComboBox<>();
        cbx_Giuong = new javax.swing.JComboBox<>();
        btn_xacNhanPhong = new javax.swing.JButton();
        btn_xacNhanKhoa = new javax.swing.JButton();
        btn_xacNhanBoTri = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lbl_nhanVienHienTai = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tfd_maBA = new javax.swing.JTextField();
        lbl_ngayKham = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        rbtn_tainha = new javax.swing.JRadioButton();
        rbtn_nhapvien = new javax.swing.JRadioButton();
        btn_hoanTat = new javax.swing.JButton();
        btn_suDungDV = new javax.swing.JButton();
        btn_lapToaThuoc = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        text_kq = new javax.swing.JTextArea();
        lbl_loiKetQua = new javax.swing.JLabel();
        btn_xacNhan = new javax.swing.JButton();

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("BỐ TRÍ CHO BỆNH NHÂN NHẬP VIỆN");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Chọn khoa:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Chọn giường:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Chọn phòng:");

        btn_xacNhanPhong.setText("Xác nhận");
        btn_xacNhanPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xacNhanPhongActionPerformed(evt);
            }
        });

        btn_xacNhanKhoa.setText("Xác nhận");
        btn_xacNhanKhoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xacNhanKhoaActionPerformed(evt);
            }
        });

        btn_xacNhanBoTri.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_xacNhanBoTri.setText("Xác nhận");
        btn_xacNhanBoTri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xacNhanBoTriActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialog1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(53, 53, 53))
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialog1Layout.createSequentialGroup()
                        .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel7))
                        .addGap(22, 22, 22)
                        .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbx_Phong, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbx_Khoa, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_xacNhanPhong)
                            .addComponent(btn_xacNhanKhoa)))
                    .addGroup(jDialog1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbx_Giuong, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_xacNhanBoTri, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(61, 61, 61)
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cbx_Khoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_xacNhanKhoa))
                .addGap(30, 30, 30)
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(cbx_Phong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_xacNhanPhong))
                .addGap(30, 30, 30)
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cbx_Giuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                .addComponent(btn_xacNhanBoTri, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("KHÁM BAN ĐẦU");

        lbl_nhanVienHienTai.setFont(new java.awt.Font("Tahoma", 2, 15)); // NOI18N
        lbl_nhanVienHienTai.setText("Nhân viên đang làm việc ");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel2.setText("Nhập mã bệnh án:");

        tfd_maBA.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N

        lbl_ngayKham.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lbl_ngayKham.setText("Ngày khám");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel3.setText("Kết quả khám bệnh:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel4.setText("Hướng điều trị:");

        buttonGroup1.add(rbtn_tainha);
        rbtn_tainha.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        rbtn_tainha.setSelected(true);
        rbtn_tainha.setText("Điều trị tại nhà");

        buttonGroup1.add(rbtn_nhapvien);
        rbtn_nhapvien.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        rbtn_nhapvien.setText("Nhập viện");

        btn_hoanTat.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_hoanTat.setText("Hoàn tất");
        btn_hoanTat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hoanTatActionPerformed(evt);
            }
        });

        btn_suDungDV.setBackground(new java.awt.Color(112, 220, 220));
        btn_suDungDV.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_suDungDV.setText("Sử dụng dịch vụ");
        btn_suDungDV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_suDungDVActionPerformed(evt);
            }
        });

        btn_lapToaThuoc.setBackground(new java.awt.Color(112, 220, 220));
        btn_lapToaThuoc.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_lapToaThuoc.setText("Lập toa thuốc");
        btn_lapToaThuoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_lapToaThuocActionPerformed(evt);
            }
        });

        text_kq.setColumns(20);
        text_kq.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        text_kq.setRows(5);
        jScrollPane1.setViewportView(text_kq);

        lbl_loiKetQua.setForeground(new java.awt.Color(255, 51, 51));
        lbl_loiKetQua.setText("lỗi");

        btn_xacNhan.setText("Xác nhận");
        btn_xacNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xacNhanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(275, 275, 275)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbl_ngayKham, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(tfd_maBA, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_xacNhan))
                            .addComponent(jLabel3)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(160, 160, 160)
                                .addComponent(btn_suDungDV, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44)
                                .addComponent(btn_lapToaThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 740, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(rbtn_tainha)
                                .addGap(18, 18, 18)
                                .addComponent(rbtn_nhapvien)
                                .addGap(354, 354, 354))
                            .addComponent(lbl_loiKetQua, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(19, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btn_hoanTat, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(291, 291, 291))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lbl_nhanVienHienTai, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(4, 4, 4)
                .addComponent(lbl_nhanVienHienTai)
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tfd_maBA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_xacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lbl_ngayKham)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_loiKetQua)
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(rbtn_tainha)
                    .addComponent(rbtn_nhapvien))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_suDungDV, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_lapToaThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_hoanTat, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void themPhieuNhapVien(String tenBenh, Date ngayLap, int iDNV, String iDBAStr) {
        int iDBA = Integer.parseInt(iDBAStr);
        Connection ketNoi = qlbn.QLBN.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement("INSERT INTO PHIEUNHAPVIEN VALUES (?,?,?,?)");
            ps.setString(1, tenBenh);
            ps.setDate(2, ngayLap);
            ps.setInt(3, iDNV);
            ps.setInt(4, iDBA);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Thêm hồ sơ nhập viện thành công!");
            jDialog1.setVisible(true);
            load_Cbx_Khoa();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(KhamBanDau.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Thêm hồ sơ nhập viện thất bại!");
        }
    }

    private boolean themKhamBanDau(int iDNV, String maBAStr, Date ngay, String ketQua, String loai) {
        boolean kt = false;
        int iDBA = Integer.parseInt(maBAStr);
        Connection ketNoi = qlbn.QLBN.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement("INSERT INTO KHAMBANDAU VALUES (?,?,?,?,?)");
            ps.setInt(1, iDNV);
            ps.setInt(2, iDBA);
            ps.setDate(3, ngay);
            ps.setString(4, ketQua);
            ps.setString(5, loai);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Thêm hồ sơ khám ban đầu thành công!");
            kt = true;
            lbl_loiKetQua.setVisible(false);
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(KhamBanDau.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Thêm hồ sơ khám ban đầu thất bại!");
        }
        return kt;
    }

    private boolean checkIDBA(String ma) {
        Connection ketNoi = qlbn.QLBN.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement("SELECT * FROM BENHAN WHERE IDBA = " + ma);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("IDBA");
                if (id == Integer.parseInt(ma)) {
                    return true;
                }
            }
            rs.close();
            ps.close();
            ketNoi.close();
        } catch (SQLException e) {
            Logger.getLogger(KhamBanDau.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    private void load_Cbx_Khoa() {
        Connection ketNoi = qlbn.QLBN.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement("SELECT * FROM KHOA");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String temp = rs.getString("TENKHOA");
                cbx_Khoa.addItem(temp);
            }
        } catch (Exception e) {
            Logger.getLogger(KhamBanDau.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private String layMaKhoa(String tenKhoa) {
        Connection ketNoi = qlbn.QLBN.layKetNoi();
        String MK = "";
        try {
            PreparedStatement ps = ketNoi.prepareStatement("SELECT * FROM KHOA WHERE TENKHOA = N'" + tenKhoa + "'");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                MK = rs.getString("MAKHOA");
            }
        } catch (Exception e) {
            Logger.getLogger(KhamBanDau.class.getName()).log(Level.SEVERE, null, e);
        }
        return MK;
    }

    private void load_Cbx_Phong() {
        Connection ketNoi = qlbn.QLBN.layKetNoi();
        System.out.println(layMaKhoa(tenKhoa));
        try {
            PreparedStatement ps = ketNoi.prepareStatement("SELECT * FROM PHONG WHERE MAKHOA = '" + layMaKhoa(tenKhoa) + "'");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String temp = rs.getString("MAPHONG");
                cbx_Phong.addItem(temp);
            }
        } catch (Exception e) {
            Logger.getLogger(KhamBanDau.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    private void load_Cbx_Giuong() {
        Connection ketNoi = qlbn.QLBN.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement("SELECT * FROM GIUONG WHERE MAPHONG = '" + maPhong + "' AND TRANGTHAI = N'trống'");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int iDGiuong = rs.getInt("IDGIUONG");
                cbx_Giuong.addItem(String.valueOf(iDGiuong));
            }
        } catch (Exception e) {
            Logger.getLogger(KhamBanDau.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private boolean checkPhieuNhapVien(int ma) {
        boolean check = true;
        Connection ketNoi = qlbn.QLBN.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement("SELECT * FROM PHIEUNHAPVIEN WHERE IDBA = " + ma);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                check = false;
            }
            rs.close();
            ps.close();
            ketNoi.close();
        } catch (SQLException e) {
            Logger.getLogger(KhamBanDau.class.getName()).log(Level.SEVERE, null, e);
        }
        if (check == true) {
            return true;
        } else {
            return false;
        }
    }

    private void btn_hoanTatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hoanTatActionPerformed
        int iDNV = nvDangTruc.getIdNV();
        Date ngay = new java.sql.Date(millis);
        String ketQua = text_kq.getText();
        String loai = "";
        boolean kt = false;
        if (rbtn_tainha.isSelected()) {
            loai = rbtn_tainha.getText();
        } else {
            loai = rbtn_nhapvien.getText();
        }
        if (ketQua.equals("")) {
            lbl_loiKetQua.setVisible(true);
        } else if (checkIDBA(""+maBenhAn) == true) {
            kt = themKhamBanDau(iDNV, ""+maBenhAn, ngay, ketQua, loai);
            if (rbtn_nhapvien.isSelected() && kt == true) {
                if (checkPhieuNhapVien(Integer.parseInt(""+maBenhAn)) == true) {
                    themPhieuNhapVien(ketQua, ngay, iDNV, ""+maBenhAn);
                } else {
                    JOptionPane.showMessageDialog(this, "Không thể lập phiếu nhập viện do mã bệnh án này đã tồn tại!");
                }
            }
        }
        if (kt == true && rbtn_tainha.isSelected()) {
            HoaDon hd = new HoaDon(nvDangTruc, maBenhAn);
            hd.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_btn_hoanTatActionPerformed

    private void btn_xacNhanKhoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xacNhanKhoaActionPerformed
        tenKhoa = String.valueOf(cbx_Khoa.getSelectedItem());
        load_Cbx_Phong();
    }//GEN-LAST:event_btn_xacNhanKhoaActionPerformed

    private void btn_xacNhanPhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xacNhanPhongActionPerformed
        maPhong = String.valueOf(cbx_Phong.getSelectedItem());
        load_Cbx_Giuong();
    }//GEN-LAST:event_btn_xacNhanPhongActionPerformed

    private void themNoiTru(int iDBA, int iDGiuong, Date ngayDen) {
        Connection ketNoi = qlbn.QLBN.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement("INSERT INTO NOITRU VALUES (?,?,?,NULL)");
            ps.setInt(1, iDBA);
            ps.setInt(2, iDGiuong);
            ps.setDate(3, ngayDen);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(KhamBanDau.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Thêm hồ sơ nội trú đầu thất bại!");
        }
    }

    private void doiTrangThaiGiuong(int iDGiuong) {
        Connection ketNoi = qlbn.QLBN.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement("UPDATE GIUONG SET TRANGTHAI = N'đang dùng' WHERE IDGIUONG = " + iDGiuong);
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(jDialog1, "Bố trí giường cho bệnh nhân thành công!");
            jDialog1.setVisible(false);
        } catch (SQLException ex) {
            Logger.getLogger(KhamBanDau.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Đổi trạng thái giường thất bại!");
        }
    }

    private void btn_xacNhanBoTriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xacNhanBoTriActionPerformed
        //ghi thông tin vào nội trú và đổi trạng thái giường
        int iDBA = Integer.parseInt(tfd_maBA.getText());
        int iDGiuong = Integer.parseInt(String.valueOf(cbx_Giuong.getSelectedItem()));
        Date ngayDen = new java.sql.Date(millis);
        themNoiTru(iDBA, iDGiuong, ngayDen);
        doiTrangThaiGiuong(iDGiuong);
        this.dispose();
        System.out.println(iDBA + "    " + iDGiuong + "      " + String.valueOf(ngayDen));
    }//GEN-LAST:event_btn_xacNhanBoTriActionPerformed

    private void btn_lapToaThuocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_lapToaThuocActionPerformed
        String temp = tfd_maBA.getText();
        if (checkIDBA(temp) == true) {
            LapToaThuoc ltt = new LapToaThuoc(nvDangTruc, Integer.parseInt(temp));
            ltt.setVisible(true);
        }
    }//GEN-LAST:event_btn_lapToaThuocActionPerformed

    private void btn_suDungDVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_suDungDVActionPerformed
        SuDungDichVu dv = new SuDungDichVu(nvDangTruc, maBenhAn);
        dv.setVisible(true);
    }//GEN-LAST:event_btn_suDungDVActionPerformed

    private void btn_xacNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xacNhanActionPerformed
        if (tfd_maBA.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Mã bệnh không được bỏ trống!");
        } else if (!tfd_maBA.getText().equals("") && checkIDBA(tfd_maBA.getText()) == false) {
            JOptionPane.showMessageDialog(this, "Mã bệnh án không tồn tại!");
        } else {
            btn_xacNhan.setVisible(false);
            tfd_maBA.setEnabled(false);
            btn_hoanTat.setEnabled(true);
            btn_suDungDV.setEnabled(true);
            btn_lapToaThuoc.setEnabled(true);
            maBenhAn = Integer.parseInt(tfd_maBA.getText());
        }
    }//GEN-LAST:event_btn_xacNhanActionPerformed

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
            java.util.logging.Logger.getLogger(KhamBanDau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KhamBanDau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KhamBanDau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KhamBanDau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new KhamBanDau().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_hoanTat;
    private javax.swing.JButton btn_lapToaThuoc;
    private javax.swing.JButton btn_suDungDV;
    private javax.swing.JButton btn_xacNhan;
    private javax.swing.JButton btn_xacNhanBoTri;
    private javax.swing.JButton btn_xacNhanKhoa;
    private javax.swing.JButton btn_xacNhanPhong;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbx_Giuong;
    private javax.swing.JComboBox<String> cbx_Khoa;
    private javax.swing.JComboBox<String> cbx_Phong;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_loiKetQua;
    private javax.swing.JLabel lbl_ngayKham;
    private javax.swing.JLabel lbl_nhanVienHienTai;
    private javax.swing.JRadioButton rbtn_nhapvien;
    private javax.swing.JRadioButton rbtn_tainha;
    private javax.swing.JTextArea text_kq;
    private javax.swing.JTextField tfd_maBA;
    // End of variables declaration//GEN-END:variables
}
