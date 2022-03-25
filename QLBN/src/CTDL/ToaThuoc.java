package CTDL;

import java.sql.Date;

public class ToaThuoc {
    int iDTT;
    Date ngayLap;
    int donGia;
    int iDBA;
    int iDNV;

    public ToaThuoc() {
    }

    public ToaThuoc(int iDTT, Date ngayLap, int donGia, int iDBA, int iDNV) {
        this.iDTT = iDTT;
        this.ngayLap = ngayLap;
        this.donGia = donGia;
        this.iDBA = iDBA;
        this.iDNV = iDNV;
    }

    public int getiDTT() {
        return iDTT;
    }

    public Date getNgayLap() {
        return ngayLap;
    }

    public int getDonGia() {
        return donGia;
    }

    public int getiDBA() {
        return iDBA;
    }

    public int getiDNV() {
        return iDNV;
    }

    public void setiDTT(int iDTT) {
        this.iDTT = iDTT;
    }

    public void setNgayLap(Date ngayLap) {
        this.ngayLap = ngayLap;
    }

    public void setDonGia(int donGia) {
        this.donGia = donGia;
    }

    public void setiDBA(int iDBA) {
        this.iDBA = iDBA;
    }

    public void setiDNV(int iDNV) {
        this.iDNV = iDNV;
    }
    
}
