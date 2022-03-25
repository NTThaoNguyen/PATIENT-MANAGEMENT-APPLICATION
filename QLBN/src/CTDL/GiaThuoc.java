package CTDL;

import java.sql.Date;

public class GiaThuoc {
    String maThuoc;
    Date ngay;
    int gia;

    public GiaThuoc() {
    }

    public GiaThuoc(String maThuoc, Date ngay, int gia) {
        this.maThuoc = maThuoc;
        this.ngay = ngay;
        this.gia = gia;
    }

    public String getMaThuoc() {
        return maThuoc;
    }

    public Date getNgay() {
        return ngay;
    }

    public int getGia() {
        return gia;
    }

    public void setMaThuoc(String maThuoc) {
        this.maThuoc = maThuoc;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }
    
}
