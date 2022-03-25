package CTDL;

import java.sql.Date;

public class GiaPhong {
    String maPhong;
    Date ngay;
    int gia;

    public GiaPhong() {
    }

    public GiaPhong(String maPhong, Date ngay, int gia) {
        this.maPhong = maPhong;
        this.ngay = ngay;
        this.gia = gia;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public Date getNgay() {
        return ngay;
    }

    public int getGia() {
        return gia;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }
    
}
