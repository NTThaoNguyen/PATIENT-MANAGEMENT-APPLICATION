/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GiaoDien;

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

/**
 *
 * @author Thu Hà
 */
public class HoaDon extends javax.swing.JFrame {

    public boolean taiNha;
    public NhanVien nvDangTruc;
    private long millis = System.currentTimeMillis();
    private Vector ds = null;
    private int tienDV = 0, tienThuoc = 0, tienGiuong = 0, tienBH = 0, tongChiPhi = 0, chiPhiSauCung = 0;
    private int maBenhAn = 0;

    public HoaDon(NhanVien nvDangTRuc, int iDBA) {
        initComponents();
        this.nvDangTruc = nvDangTRuc;
        this.setLocationRelativeTo(null);
        this.setTitle("Hóa đơn");
        if (iDBA > 0) {
            System.err.println(iDBA);
            this.maBenhAn = iDBA;
            tfd_iDBA.setEnabled(false);
            btn_xacNhan.setEnabled(false);
            btn_xacNhan.setVisible(false);
            tfd_iDBA.setText(String.valueOf(iDBA));
            taiNha = checkIDBA(iDBA);
            xuatHoaDon(iDBA, nvDangTRuc.getIdNV());
        } else {
            xuatHoaDon(iDBA, nvDangTRuc.getIdNV());
        }
    }

    private boolean checkIDBA(int iDBA) { // kiểm tra xem mã bệnh án có trong nội trú hay ko -> nếu có: tại nhà = false
        Connection ketNoi = qlbn.QLBN.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement("SELECT * FROM NOITRU WHERE IDBA = " + iDBA);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return false;
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            Logger.getLogger(KhamBanDau.class.getName()).log(Level.SEVERE, null, e);
        }
        return true;
    }

    private int layIDBN(int iDBA) {
        int id = 0;
        Connection ketNoi = qlbn.QLBN.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement("SELECT * FROM BENHAN WHERE IDBA = " + iDBA);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt("IDBN");
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            Logger.getLogger(KhamBanDau.class.getName()).log(Level.SEVERE, null, e);
        }
        return id;
    }

    private String layTenBN(int iDBN) {
        String tenBN = "";
        Connection ketNoi = qlbn.QLBN.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement("SELECT * FROM BENHNHAN WHERE IDBN = " + iDBN);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String ho = rs.getString("HO");
                String ten = rs.getString("TEN");
                tenBN = ho + " " + ten;
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            Logger.getLogger(KhamBanDau.class.getName()).log(Level.SEVERE, null, e);
        }
        return tenBN;
    }

    private String layDiaChi(int iDBN) {
        String diaChi = "";
        Connection ketNoi = qlbn.QLBN.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement("SELECT * FROM BENHNHAN WHERE IDBN = " + iDBN);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                diaChi = rs.getString("DIACHI");
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            Logger.getLogger(KhamBanDau.class.getName()).log(Level.SEVERE, null, e);
        }
        return diaChi;
    }

    private void tinhTienGiuong(int iDBA) {
        Connection ketNoi = qlbn.QLBN.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement("SELECT GP.GIA\n"
                    + "FROM GIAPHONG GP ,(SELECT G.MAPHONG\n"
                    + "FROM GIUONG G, (SELECT IDGIUONG FROM NOITRU WHERE IDBA = " + iDBA + ") NT\n"
                    + "WHERE G.IDGIUONG = NT.IDGIUONG) P\n"
                    + "WHERE GP.MAPHONG = P.MAPHONG");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tienGiuong = rs.getInt("GIA");
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            Logger.getLogger(KhamBanDau.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private String layIDGiuong(int iDBA) {
        int id = 0;
        Connection ketNoi = qlbn.QLBN.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement("SELECT * FROM NOITRU WHERE IDBA = " + iDBA);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt("IDGIUONG");
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            Logger.getLogger(KhamBanDau.class.getName()).log(Level.SEVERE, null, e);
        }
        if (id != 0) {
            return String.valueOf(id);
        } else {
            return "Không có";
        }
    }

    private String layBHYT(int iDBN) {
        String bhyt = "";
        Connection ketNoi = qlbn.QLBN.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement("SELECT * FROM BENHNHAN WHERE IDBN = " + iDBN);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                bhyt = rs.getString("BHYT");
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            Logger.getLogger(KhamBanDau.class.getName()).log(Level.SEVERE, null, e);
        }
        return bhyt;
    }

    private String layTenDV(int maDV) {
        String ten = "";
        Connection ketNoi = qlbn.QLBN.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement("SELECT * FROM DICHVU WHERE MADV = " + maDV);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ten = rs.getString("TENDV");
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            Logger.getLogger(KhamBanDau.class.getName()).log(Level.SEVERE, null, e);
        }
        return ten;
    }

    private int layGiaDV(int maDV) {
        int gia = 0;
        Connection ketNoi = qlbn.QLBN.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement("SELECT * FROM GIADICHVU WHERE MADV = " + maDV);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                gia = rs.getInt("GIA");
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            Logger.getLogger(KhamBanDau.class.getName()).log(Level.SEVERE, null, e);
        }
        return gia;
    }

    private void layDSDV(int iDBA) {
        DefaultTableModel dtm = (DefaultTableModel) table_dichVu.getModel();
        dtm.setNumRows(0);
        Connection ketNoi = qlbn.QLBN.layKetNoi();
        Vector vt;
        ds = new Vector();
        try {
            PreparedStatement ps = ketNoi.prepareStatement("SELECT * FROM CT_DICHVU WHERE IDBA = " + iDBA);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                vt = new Vector();
                String ten = layTenDV(rs.getInt("MADV"));
                vt.add(ten);
                int gia = layGiaDV(rs.getInt("MADV"));
                tienDV += gia;
                vt.add(gia);
                ds.add(vt);
                dtm.addRow(vt);
            }
            table_dichVu.setModel(dtm);
            rs.close();
            ps.close();
            ketNoi.close();
        } catch (SQLException ex) {
            Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void layDSThuoc(int iDBA) {
        DefaultTableModel dtm = (DefaultTableModel) table_thuoc.getModel();
        dtm.setNumRows(0);
        Connection ketNoi = qlbn.QLBN.layKetNoi();
        Vector vt;
        ds = new Vector();
        try {
            PreparedStatement ps = ketNoi.prepareStatement("SELECT * FROM TOATHUOC WHERE IDBA = " + iDBA);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                vt = new Vector();
                vt.add(rs.getInt("IDTT"));
                vt.add(rs.getDate("NGAYLAP"));
                int gia = rs.getInt("DONGIA");
                vt.add(gia);
                tienThuoc += gia;
                ds.add(vt);
                dtm.addRow(vt);
            }
            table_thuoc.setModel(dtm);
            rs.close();
            ps.close();
            ketNoi.close();
        } catch (SQLException ex) {
            Logger.getLogger(HoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void themVaoHoaDon(int tienDV, int tienThuoc, int tienGiuong, int tienBHYT, int tongTien, int iDNV, int iDBA) {
        Connection ketNoi = qlbn.QLBN.layKetNoi();
        System.err.println(iDBA);
        try {
            PreparedStatement ps = ketNoi.prepareStatement("INSERT INTO HOADON VALUES (?,?,?,?,?,?,?)");
            ps.setInt(1, tienDV);
            ps.setInt(2, tienThuoc);
            ps.setInt(3, tienGiuong);
            ps.setInt(4, tienBHYT);
            ps.setInt(5, tongTien);
            ps.setInt(6, iDNV);
            ps.setInt(7, iDBA);
            ps.executeUpdate();
            System.out.println("Them hoa don thanh cong");
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(KhamBanDau.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Them hoa don that bai");
        }
    }

    public void capNhatNoitru(int idBA) {
        Connection ketNoi = QLBN.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement("UPDATE NOITRU SET NGAYDI = GETDATE() WHERE IDBA = ?");
            ps.setInt(1, idBA);
            ps.executeUpdate();
            ps.close();
            ketNoi.close();
        } catch (SQLException ex) {
            Logger.getLogger(KhamDT.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void capNhatGiuong(int idBA) {
        int idGiuong = Integer.parseInt(layIDGiuong(idBA));
        Connection ketNoi = QLBN.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement("UPDATE GIUONG SET TRANGTHAI = N'trống' WHERE IDGIUONG = ?");
            ps.setInt(1, idGiuong);
            ps.executeUpdate();
            ps.close();
            ketNoi.close();
        } catch (SQLException ex) {
            Logger.getLogger(KhamDT.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    public void lapPhieuXuatVien(int idBA) {
//        Connection ketNoi = QLBN.layKetNoi();
//        try {
//            PreparedStatement ps = ketNoi.prepareStatement("INSERT INTO PHIEUXUATVIEN VALUES (getDate(),?,?)");
//            ps.setInt(1, idBA);
//            ps.setInt(2, nvDangTruc.getIdNV());
//            ps.executeUpdate();
//        } catch (SQLException ex) {
//            Logger.getLogger(KhamDT.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

    public Date layNgayDen(int idBA) {
        Date ngayDen = null;
        Connection ketNoi = qlbn.QLBN.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement("SELECT * FROM NOITRU WHERE IDBA = " + idBA);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ngayDen = rs.getDate("NGAYDEN");
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            Logger.getLogger(KhamBanDau.class.getName()).log(Level.SEVERE, null, e);
        }
        return ngayDen;
    }
    
    public int tinhTienDV(int iDBA){
        int gia = 0;
        Connection ketNoi = qlbn.QLBN.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement("SELECT SUM(G.GIA) FROM GIADICHVU G, (SELECT MADV FROM CT_DICHVU WHERE IDBA =" + iDBA + ") T WHERE T.MADV = G.MADV");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                gia = rs.getInt(1);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            Logger.getLogger(KhamBanDau.class.getName()).log(Level.SEVERE, null, e);
        }
        return gia;
    }

    public void xuatHoaDon(int iDBA, int iDNV) {

        if (iDBA > 0) {
            //Mã bệnh án
            tfd_iDBA.setText(String.valueOf(iDBA));
            maBenhAn = iDBA;
            //Tên bệnh nhân
            int iDBN = layIDBN(iDBA);
            lbl_tenBenhNhan.setText(lbl_tenBenhNhan.getText() + " " + layTenBN(iDBN));
            //Địa chỉ
            lbl_diaChi.setText(lbl_diaChi.getText() + " " + layDiaChi(iDBN));
            //Danh sách dịch vụ đã sử dụng
            layDSDV(iDBA);
            //Danh sách thuốc
            layDSThuoc(iDBA);
            //Giường bệnh
            if (taiNha == false) {
                tinhTienGiuong(iDBA);
                lbl_giuong.setText(lbl_giuong.getText() + " " + layIDGiuong(iDBA));
            }
            //Bảo hiểm y tế
            if (layBHYT(iDBN).equals("")) {
                lbl_BHYT.setText(lbl_BHYT.getText() + " " + "Không có");
            } else {
                lbl_BHYT.setText(lbl_BHYT.getText() + " " + layBHYT(iDBN));
            }
            //Tiền dịch vụ
            tienDV = tinhTienDV(iDBA);
            lbl_tienDV.setText(lbl_tienDV.getText() + " " + tienDV + " VND");
            //Tiền thuốc
            lbl_tienT.setText(lbl_tienT.getText() + " " + tienThuoc + " VND");
            //Tiền giường
            if (taiNha == false) {
                Date ngayDi = new java.sql.Date(millis);
                System.out.println(ngayDi);
                Date ngayDen = layNgayDen(maBenhAn);
                System.out.println(ngayDen);
                long tg = (ngayDi.getTime() - ngayDen.getTime()) / (86400000);
                System.out.println(tg);
                tienGiuong = tienGiuong * (int) (tg);
                lbl_tienG.setText(lbl_tienG.getText() + " " + tienGiuong + " VND");
            } else {
                tienGiuong = 0;
            }
            //Tổng chi phí
            tongChiPhi = (tienDV + tienGiuong + tienThuoc);
            lbl_tongChiPhi.setText("Tổng chi phí: " + tongChiPhi);
            //Tiền BHYT
            if (layBHYT(iDBN).equals("")) {
                tienBH = 0;
                lbl_tienBHYT.setText(lbl_tienBHYT.getText() + " " + tienBH + " VND");
            } else {
                tienBH = (int) ((double) tongChiPhi * 0.1);
                lbl_tienBHYT.setText(lbl_tienBHYT.getText() + " " + tienBH + " VND");
            }
            //Chi phí sau cùng
            chiPhiSauCung = (tienDV + tienGiuong + tienThuoc) - tienBH;
            lbl_chiPhiSauCung.setText(lbl_chiPhiSauCung.getText() + " " + chiPhiSauCung + " VND");
            //Ngày lập
            lbl_ngayLap.setText(lbl_ngayLap.getText() + " " + String.valueOf(new java.sql.Date(millis)));
            //Nhân viên lập
            lbl_NVLap.setText(lbl_NVLap.getText() + " " + nvDangTruc.getHo() + " " + nvDangTruc.getTen());
        }
    }

    boolean checkTonTai(int iDBA) { // kiem tra ton tai benh an trong hoa dơn
        boolean check = true;
        Connection ketNoi = qlbn.QLBN.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement("SELECT * FROM HOADON");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int t = rs.getInt("IDBA");
                if (t == iDBA) {
                    check = false;
                }
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            Logger.getLogger(KhamBanDau.class.getName()).log(Level.SEVERE, null, e);
        }
        return check;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lbl_tenBenhNhan = new javax.swing.JLabel();
        lbl_diaChi = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_dichVu = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_thuoc = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        lbl_giuong = new javax.swing.JLabel();
        lbl_BHYT = new javax.swing.JLabel();
        tfd_iDBA = new javax.swing.JTextField();
        btn_xacNhan = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        lbl_tienDV = new javax.swing.JLabel();
        lbl_tienT = new javax.swing.JLabel();
        lbl_tienG = new javax.swing.JLabel();
        lbl_tienBHYT = new javax.swing.JLabel();
        lbl_tongChiPhi = new javax.swing.JLabel();
        lbl_chiPhiSauCung = new javax.swing.JLabel();
        lbl_ngayLap = new javax.swing.JLabel();
        lbl_NVLap = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(153, 153, 153));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 28)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("HÓA ĐƠN");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel2.setText("Mã bệnh án:");

        lbl_tenBenhNhan.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lbl_tenBenhNhan.setText("Tên bệnh nhân:");

        lbl_diaChi.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lbl_diaChi.setText("Địa chỉ:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel3.setText("Danh sách dịch vụ đã sử dụng:");

        table_dichVu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Tên dịch vụ", "Giá"
            }
        ));
        jScrollPane1.setViewportView(table_dichVu);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel6.setText("Danh sách thuốc:");

        table_thuoc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã toa thuốc", "Ngày lập", "Giá"
            }
        ));
        jScrollPane2.setViewportView(table_thuoc);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setText("CHI TIẾT THÔNG TIN");

        lbl_giuong.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lbl_giuong.setText("Giường bệnh:");

        lbl_BHYT.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lbl_BHYT.setText("Bảo hiểm y tế:");

        btn_xacNhan.setText("Xác nhận");
        btn_xacNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xacNhanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(tfd_iDBA, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_xacNhan))
                            .addComponent(jScrollPane1)
                            .addComponent(jLabel6)
                            .addComponent(jScrollPane2)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_giuong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbl_BHYT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbl_diaChi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbl_tenBenhNhan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(131, 131, 131)
                        .addComponent(jLabel7)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel7)
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tfd_iDBA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_xacNhan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_tenBenhNhan)
                .addGap(18, 18, 18)
                .addComponent(lbl_diaChi)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_giuong)
                .addGap(18, 18, 18)
                .addComponent(lbl_BHYT)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setText("CHI PHÍ");

        lbl_tienDV.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lbl_tienDV.setText("Tiền dịch vụ:");

        lbl_tienT.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lbl_tienT.setText("Tiền thuốc:");

        lbl_tienG.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lbl_tienG.setText("Tiền giường:");

        lbl_tienBHYT.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lbl_tienBHYT.setText("Tiền bảo hiểm y tế (10% tổng chi phi):");

        lbl_tongChiPhi.setBackground(new java.awt.Color(204, 204, 204));
        lbl_tongChiPhi.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbl_tongChiPhi.setText("Tổng chi phí:");

        lbl_chiPhiSauCung.setBackground(new java.awt.Color(204, 204, 204));
        lbl_chiPhiSauCung.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbl_chiPhiSauCung.setText("Chi phí sau cùng:");

        lbl_ngayLap.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lbl_ngayLap.setText("Ngày lập hóa đơn:");

        lbl_NVLap.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lbl_NVLap.setText("Nhân viên lập:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(149, 149, 149))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lbl_ngayLap, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                            .addComponent(lbl_NVLap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_tienG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_tienT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_tienDV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_tongChiPhi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_tienBHYT, javax.swing.GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE)
                    .addComponent(lbl_chiPhiSauCung, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addComponent(lbl_tienDV)
                .addGap(18, 18, 18)
                .addComponent(lbl_tienT)
                .addGap(16, 16, 16)
                .addComponent(lbl_tienG)
                .addGap(44, 44, 44)
                .addComponent(lbl_tongChiPhi)
                .addGap(48, 48, 48)
                .addComponent(lbl_tienBHYT)
                .addGap(45, 45, 45)
                .addComponent(lbl_chiPhiSauCung)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl_ngayLap)
                .addGap(18, 18, 18)
                .addComponent(lbl_NVLap)
                .addContainerGap())
        );

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton1.setText("Xác nhận");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(390, 390, 390))
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                .addContainerGap())
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
        if (tfd_iDBA.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Không được để trống mã bệnh án!");
        } else {
            taiNha = checkIDBA(Integer.parseInt(tfd_iDBA.getText()));
            int iDBA = Integer.parseInt(tfd_iDBA.getText());
            if (checkTonTaiBA(iDBA) == true) {
                xuatHoaDon(iDBA, nvDangTruc.getIdNV());
                tfd_iDBA.setEnabled(false);
                btn_xacNhan.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(this, "Mã bệnh án này không tồn tại!");
            }
        }
    }//GEN-LAST:event_btn_xacNhanActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (checkTonTai(maBenhAn) == true) {
            themVaoHoaDon(tienDV, tienThuoc, tienGiuong, tienBH, chiPhiSauCung, nvDangTruc.getIdNV(), maBenhAn);
            //lapPhieuXuatVien(maBenhAn);
            if (taiNha == false) {
                capNhatGiuong(maBenhAn);
            }
            capNhatNoitru(maBenhAn);
            JOptionPane.showMessageDialog(this, "Lập hóa đơn thành công!");
            this.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(this, "Lỗi, Bệnh án này đã được lập hóa đơn rồi!");
        }
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
            java.util.logging.Logger.getLogger(HoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new HoaDon().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_xacNhan;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbl_BHYT;
    private javax.swing.JLabel lbl_NVLap;
    private javax.swing.JLabel lbl_chiPhiSauCung;
    private javax.swing.JLabel lbl_diaChi;
    private javax.swing.JLabel lbl_giuong;
    private javax.swing.JLabel lbl_ngayLap;
    private javax.swing.JLabel lbl_tenBenhNhan;
    private javax.swing.JLabel lbl_tienBHYT;
    private javax.swing.JLabel lbl_tienDV;
    private javax.swing.JLabel lbl_tienG;
    private javax.swing.JLabel lbl_tienT;
    private javax.swing.JLabel lbl_tongChiPhi;
    private javax.swing.JTable table_dichVu;
    private javax.swing.JTable table_thuoc;
    private javax.swing.JTextField tfd_iDBA;
    // End of variables declaration//GEN-END:variables
}
