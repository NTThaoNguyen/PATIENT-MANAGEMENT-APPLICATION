package CTDL;

public class HoaDon {
    int iDHD;
    int tienDV;
    int tienThuoc;
    int tienGiuong;
    int tienBHYT;
    int tongTien;
    int iDNV;
    int iDBA;

    public HoaDon() {
    }

    public HoaDon(int iDHD, int tienDV, int tienThuoc, int tienGiuong, int tienBHYT, int tongTien, int iDNV, int iDBA) {
        this.iDHD = iDHD;
        this.tienDV = tienDV;
        this.tienThuoc = tienThuoc;
        this.tienGiuong = tienGiuong;
        this.tienBHYT = tienBHYT;
        this.tongTien = tongTien;
        this.iDNV = iDNV;
        this.iDBA = iDBA;
    }

    public int getiDHD() {
        return iDHD;
    }

    public int getTienDV() {
        return tienDV;
    }

    public int getTienThuoc() {
        return tienThuoc;
    }

    public int getTienGiuong() {
        return tienGiuong;
    }

    public int getTienBHYT() {
        return tienBHYT;
    }

    public int getTongTien() {
        return tongTien;
    }

    public int getiDNV() {
        return iDNV;
    }

    public int getiDBA() {
        return iDBA;
    }

    public void setiDHD(int iDHD) {
        this.iDHD = iDHD;
    }

    public void setTienDV(int tienDV) {
        this.tienDV = tienDV;
    }

    public void setTienThuoc(int tienThuoc) {
        this.tienThuoc = tienThuoc;
    }

    public void setTienGiuong(int tienGiuong) {
        this.tienGiuong = tienGiuong;
    }

    public void setTienBHYT(int tienBHYT) {
        this.tienBHYT = tienBHYT;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    public void setiDNV(int iDNV) {
        this.iDNV = iDNV;
    }

    public void setiDBA(int iDBA) {
        this.iDBA = iDBA;
    }
    
}
