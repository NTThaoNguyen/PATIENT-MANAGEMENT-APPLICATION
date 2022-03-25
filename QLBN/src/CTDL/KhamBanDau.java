package CTDL;

import java.sql.Date;

public class KhamBanDau {
    int iDDV;
    int iDBA;
    Date ngayKham;
    String ketQua;
    String loai;

    public KhamBanDau() {
    }

    public KhamBanDau(int iDDV, int iDBA, Date ngayKham, String ketQua, String loai) {
        this.iDDV = iDDV;
        this.iDBA = iDBA;
        this.ngayKham = ngayKham;
        this.ketQua = ketQua;
        this.loai = loai;
    }

    public int getiDDV() {
        return iDDV;
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

    public String getLoai() {
        return loai;
    }

    public void setiDDV(int iDDV) {
        this.iDDV = iDDV;
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

    public void setLoai(String loai) {
        this.loai = loai;
    }
    
}
