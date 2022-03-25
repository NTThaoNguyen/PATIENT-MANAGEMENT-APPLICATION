package CTDL;

import java.sql.Date;

public class GiaDichVu {
    String maDV;
    Date ngay;
    int gia;

    public GiaDichVu() {
    }

    public GiaDichVu(String maDV, Date ngay, int gia) {
        this.maDV = maDV;
        this.ngay = ngay;
        this.gia = gia;
    }

    public String getMaDV() {
        return maDV;
    }

    public Date getNgay() {
        return ngay;
    }

    public int getGia() {
        return gia;
    }

    public void setMaDV(String maDV) {
        this.maDV = maDV;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }
    
}
