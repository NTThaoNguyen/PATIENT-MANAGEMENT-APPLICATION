package GiaoDien;

import java.sql.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import qlbn.QLBN;

public class ThongKeKhamBanDau extends javax.swing.JFrame {
    
    private Vector ds = null;
    
    public ThongKeKhamBanDau() {
        initComponents();
        this.setLocationRelativeTo(null);
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
    
    private void layDS(Date ngayBD, Date ngayKT) {
        Connection ketNoi = QLBN.layKetNoi();
        ds = new Vector();
        Vector e = null;
        int dem = 0;
        String d1 = doiDatesangString(ngayBD);
        String d2 = doiDatesangString(ngayKT);
        System.out.println(d1 + "\t" + d2);
        try {
            PreparedStatement ps = ketNoi.prepareStatement("SELECT BN.HOTEN, KBD.NGAYKHAM, KBD.KETQUA, KBD.LOAI\n"
                    + "FROM (SELECT IDBA, IDBN FROM BENHAN) BA, (SELECT HO + ' ' + TEN AS HOTEN, IDBN FROM BENHNHAN) BN,\n"
                    + "	 (SELECT * FROM KHAMBANDAU WHERE NGAYKHAM BETWEEN '" + d1 + "' AND '" + d2 + "') KBD\n"
                    + "WHERE KBD.IDBA = BA.IDBA AND BA.IDBN = BN.IDBN");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                e = new Vector();
                e.add(++dem);
                e.add(rs.getString("HOTEN"));
                e.add(rs.getString("NGAYKHAM"));
                e.add(rs.getString("KETQUA"));
                e.add(rs.getString("LOAI"));
                System.out.println(e.get(0) + " " + e.get(1) + " " + e.get(2) + " " + e.get(3) + " " + e.get(4));
                ds.add(e);
            }
            rs.close();
            ps.close();
            ketNoi.close();
        } catch (SQLException ex) {
            Logger.getLogger(ThongKeKhamBanDau.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jCalendar_ngayBD = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jCalendar_ngayKT = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Từ ngày");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Đến ngày");

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setText("IN THỐNG KÊ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCalendar_ngayBD, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCalendar_ngayKT, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(349, 349, 349)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jCalendar_ngayKT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCalendar_ngayBD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(45, 45, 45)
                .addComponent(jButton1)
                .addContainerGap(59, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("THỐNG KÊ KHÁM BAN ĐẦU");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 11, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Excel(Vector ds, String path) {
        Vector vt = null;
        int tong = 0;
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet();
        int rowNum = 0;

        // Create row
        Row row = sheet.createRow(rowNum++);
        // Create cells
        Cell cell = row.createCell(0);
        cell.setCellValue("STT");
        cell = row.createCell(1);
        cell.setCellValue("HỌ VÀ TÊN BỆNH NHÂN");
        cell = row.createCell(2);
        cell.setCellValue("NGÀY KHÁM");
        cell = row.createCell(3);
        cell.setCellValue("KẾT QUẢ");
        cell = row.createCell(4);
        cell.setCellValue("LOẠI");
        
        for (int i = 0; i < ds.size(); i++) {
            row = sheet.createRow(rowNum);
            vt = (Vector) ds.get(i);
            Cell cell1 = row.createCell(0);
            cell1.setCellValue((int) vt.get(0));
            Cell cell2 = row.createCell(1);
            cell2.setCellValue((String) vt.get(1));
            Cell cell3 = row.createCell(2);
            cell3.setCellValue((String) vt.get(2));
            Cell cell4 = row.createCell(3);
            cell4.setCellValue((String) vt.get(3));
            Cell cell5 = row.createCell(4);
            cell5.setCellValue((String) vt.get(4));
            rowNum++;
        }
//        row = sheet.createRow(rowNum);
//        cell = row.createCell(3);
//        cell.setCellValue("TỔNG");
//        cell = row.createCell(4);
//        cell.setCellValue(tong);
        
        try {
            FileOutputStream outputStream = new FileOutputStream(path);
            workbook.write(outputStream);
            workbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog(this, "Tạo File thành công ở đường dẫn:\n" + path);
    }
    
    private boolean checkNgay(Date ngayBD, Date ngayKT) {
        Date d = new Date();
//        if(ngayBD.compareTo(null) == 0){
//            JOptionPane.showMessageDialog(this, "Ngày bắt đầu không được để trống");
//            return false;
//        }
        if (ngayBD.compareTo(d) >= 0) {
            JOptionPane.showMessageDialog(this, "Ngày bắt đầu không hợp lệ");
            return false;
        }
        if (ngayKT.compareTo(ngayBD) <= 0) {
            JOptionPane.showMessageDialog(this, "Ngày bắt đầu phải nhỏ hơn ngày kết thúc");
            return false;
        }
        return true;
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Date ngayBD = jCalendar_ngayBD.getDate();
        Date ngayKT = jCalendar_ngayKT.getDate();
        String duongDan;
        boolean kt = true;
        if (checkNgay(ngayBD, ngayKT) == false) {
            kt = false;
        }
        String d1 = doiDatesangString(ngayBD);
        String d2 = doiDatesangString(ngayKT);
        if (kt == true) {
            layDS(ngayBD, ngayKT);
            duongDan = "E:/Thống kê khám ban đầu từ ngày " + d1 + " đến ngày " + d2 + ".xlsx";
            System.out.println(duongDan);
            Excel(ds, duongDan);
        }
    }//GEN-LAST:event_jButton1ActionPerformed
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ThongKeKhamBanDau().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private com.toedter.calendar.JDateChooser jCalendar_ngayBD;
    private com.toedter.calendar.JDateChooser jCalendar_ngayKT;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
