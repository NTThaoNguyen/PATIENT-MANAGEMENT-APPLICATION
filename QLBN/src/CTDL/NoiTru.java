package CTDL;

import java.sql.Date;

public class NoiTru {
    int iDBA;
    int iDGiuong;
    Date ngayDen;
    Date ngayDi;

    public void setiDBA(int iDBA) {
        this.iDBA = iDBA;
    }

    public void setiDGiuong(int iDGiuong) {
        this.iDGiuong = iDGiuong;
    }

    public void setNgayDen(Date ngayDen) {
        this.ngayDen = ngayDen;
    }

    public void setNgayDi(Date ngayDi) {
        this.ngayDi = ngayDi;
    }

    public int getiDBA() {
        return iDBA;
    }

    public int getiDGiuong() {
        return iDGiuong;
    }

    public Date getNgayDen() {
        return ngayDen;
    }

    public Date getNgayDi() {
        return ngayDi;
    }

    public NoiTru(int iDBA, int iDGiuong, Date ngayDen, Date ngayDi) {
        this.iDBA = iDBA;
        this.iDGiuong = iDGiuong;
        this.ngayDen = ngayDen;
        this.ngayDi = ngayDi;
    }

    public NoiTru() {
    }
}
