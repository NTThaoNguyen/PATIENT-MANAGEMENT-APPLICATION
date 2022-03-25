package GiaoDien;

import CTDL.NhanVien;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import qlbn.QLBN;

public class QuanLyBenhAn extends javax.swing.JFrame {

    public boolean taiNha;
    NhanVien nvDangTruc;
    Vector ba = new Vector();
    private Vector ds = null;

    public QuanLyBenhAn(NhanVien nvDangTruc) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.nvDangTruc = nvDangTruc;
        
    }
    
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
    
    void layThongTinCT (int iDBA){
        taiNha = checkIDBA(iDBA);
        int iDBN = layIDBN(iDBA);
        lbl_tenBenhNhan.setText(lbl_tenBenhNhan.getText() + " " + layTenBN(iDBN));
        lbl_diaChi.setText(lbl_diaChi.getText() + " " + layDiaChi(iDBN));
        layDSDV(iDBA);
        layDSThuoc(iDBA);
        if (taiNha == false) {
                lbl_giuong.setText(lbl_giuong.getText() + " " + layIDGiuong(iDBA));
            }
            if (layBHYT(iDBN).equals("")) {
                lbl_BHYT.setText(lbl_BHYT.getText() + " " + "Không có");
            } else {
                lbl_BHYT.setText(lbl_BHYT.getText() + " " + layBHYT(iDBN));
            }
    }
    
    private String doiDatesangString(Date d) {
        String temp = "";
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            temp = df.format(d);
        } catch (Exception ex) {
            System.out.println("Không chuyển được");
        }
        return temp;
    }
    
    void layThongTinKhamBD(int iDBA){
        Date ngayKham = null;
        String ketQua = "", loai = "";
        Connection ketNoi = qlbn.QLBN.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement("SELECT * FROM KHAMBANDAU WHERE IDBA = " + iDBA);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ngayKham = rs.getDate("NGAYKHAM");
                ketQua = rs.getString("KETQUA");
                loai = rs.getString("LOAI");
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            Logger.getLogger(KhamBanDau.class.getName()).log(Level.SEVERE, null, e);
        }
        lbl_ngayKham.setText(lbl_ngayKham.getText() + doiDatesangString(ngayKham));
        lbl_ketQua.setText(lbl_ketQua.getText() + ketQua);
        lbl_loai.setText(lbl_loai.getText() + loai);
    }
    
    void layThongTinKhamDT(int iDBA){
        Connection ketNoi = qlbn.QLBN.layKetNoi();
        DefaultTableModel dtm = (DefaultTableModel) table_dieuTri.getModel();
        dtm.setNumRows(0);
        Vector vt;
        try {
            PreparedStatement ps = ketNoi.prepareStatement("SELECT * FROM KHAMDIEUTRI WHERE IDBA = " + iDBA);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                vt = new Vector();
                vt.add(rs.getDate("NGAYKHAM"));
                vt.add(rs.getString("KETQUA"));
                dtm.addRow(vt);
            }
            table_dieuTri.setModel(dtm);
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
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tfd_iDBA = new javax.swing.JTextField();
        btn_xacNhan = new javax.swing.JButton();
        lbl_tenBenhNhan = new javax.swing.JLabel();
        lbl_diaChi = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_dichVu = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        lbl_giuong = new javax.swing.JLabel();
        lbl_BHYT = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_thuoc = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        table_dieuTri = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        lbl_ngayKham = new javax.swing.JLabel();
        lbl_ketQua = new javax.swing.JLabel();
        lbl_loai = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("QUẢN LÝ BỆNH ÁN");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("THÔNG TIN BỆNH ÁN");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("CHI TIẾT THÔNG TIN");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel2.setText("Mã bệnh án:");

        btn_xacNhan.setText("Xác nhận");
        btn_xacNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xacNhanActionPerformed(evt);
            }
        });

        lbl_tenBenhNhan.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lbl_tenBenhNhan.setText("Tên bệnh nhân:");

        lbl_diaChi.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lbl_diaChi.setText("Địa chỉ:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel3.setText("Danh sách dịch vụ đã sử dụng:");

        table_dichVu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên dịch vụ", "Giá"
            }
        ));
        jScrollPane1.setViewportView(table_dichVu);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel6.setText("Danh sách thuốc:");

        lbl_giuong.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lbl_giuong.setText("Giường bệnh:");

        lbl_BHYT.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lbl_BHYT.setText("Bảo hiểm y tế:");

        table_thuoc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã toa thuốc", "Ngày lập", "Giá"
            }
        ));
        jScrollPane2.setViewportView(table_thuoc);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_giuong, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane1)
                        .addComponent(jLabel6)
                        .addComponent(jScrollPane2)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbl_BHYT, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 20, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(1, 1, 1)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addGap(18, 18, 18)
                            .addComponent(tfd_iDBA, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(btn_xacNhan))
                        .addComponent(lbl_diaChi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbl_tenBenhNhan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 122, Short.MAX_VALUE)
                .addComponent(lbl_giuong, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbl_BHYT)
                .addGap(26, 26, 26)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(44, 44, 44)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(tfd_iDBA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_xacNhan))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(lbl_tenBenhNhan)
                    .addGap(18, 18, 18)
                    .addComponent(lbl_diaChi)
                    .addContainerGap(412, Short.MAX_VALUE)))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("KHÁM ĐIỀU TRỊ");

        table_dieuTri.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Ngày khám", "Kết quả"
            }
        ));
        jScrollPane3.setViewportView(table_dieuTri);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("KHÁM BAN ĐẦU");

        lbl_ngayKham.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lbl_ngayKham.setText("Ngày khám: ");

        lbl_ketQua.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lbl_ketQua.setText("Kết quả: ");

        lbl_loai.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lbl_loai.setText("Loại: ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_ngayKham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_ketQua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_loai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_ngayKham)
                .addGap(30, 30, 30)
                .addComponent(lbl_ketQua)
                .addGap(32, 32, 32)
                .addComponent(lbl_loai)
                .addGap(0, 34, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_xacNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xacNhanActionPerformed
        if (tfd_iDBA.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Không được để trống mã bệnh án!");
        } else {
            int iDBA = Integer.parseInt(tfd_iDBA.getText());
            if (checkTonTaiBA(iDBA) == true) {
                tfd_iDBA.setEnabled(false);
                btn_xacNhan.setVisible(false);
                layThongTinCT(iDBA);
                layThongTinKhamBD(iDBA);
                layThongTinKhamDT(iDBA);
            } else {
                JOptionPane.showMessageDialog(this, "Mã bệnh án này không tồn tại!");
            }
        }
    }//GEN-LAST:event_btn_xacNhanActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new QuanLyBenhAn().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_xacNhan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbl_BHYT;
    private javax.swing.JLabel lbl_diaChi;
    private javax.swing.JLabel lbl_giuong;
    private javax.swing.JLabel lbl_ketQua;
    private javax.swing.JLabel lbl_loai;
    private javax.swing.JLabel lbl_ngayKham;
    private javax.swing.JLabel lbl_tenBenhNhan;
    private javax.swing.JTable table_dichVu;
    private javax.swing.JTable table_dieuTri;
    private javax.swing.JTable table_thuoc;
    private javax.swing.JTextField tfd_iDBA;
    // End of variables declaration//GEN-END:variables
}
