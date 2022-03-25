package CTDL;

import java.sql.Date;

public class KhamDieuTri {
    int iDNV;
    int iDBA;
    Date ngayKham;
    String ketQua;

    public KhamDieuTri() {
    }

    public KhamDieuTri(int iDNV, int iDBA, Date ngayKham, String ketQua) {
        this.iDNV = iDNV;
        this.iDBA = iDBA;
        this.ngayKham = ngayKham;
        this.ketQua = ketQua;
    }

    public int getiDNV() {
        return iDNV;
    }

    public int getiDBA() {
        return iDBA;
    }

    public Date getNgayKham() {
        return ngayKham;
    }

    public String getKetQua() {
        return ketQua;
    }

    public void setiDNV(int iDNV) {
        this.iDNV = iDNV;
    }

    public void setiDBA(int iDBA) {
        this.iDBA = iDBA;
    }

    public void setNgayKham(Date ngayKham) {
        this.ngayKham = ngayKham;
    }

    public void setKetQua(String ketQua) {
        this.ketQua = ketQua;
    }
    
}
