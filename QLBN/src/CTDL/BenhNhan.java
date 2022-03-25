package CTDL;

import java.sql.Date;

public class BenhNhan {
    private int idBN;
    private String ho;
    private String ten;
    private String gioiTinh;
    private Date ngaySinh;
    private String diaChi;
    private String cMND;
    private String bHYT;

    public BenhNhan() {
    }

    public BenhNhan(int idBN, String ho, String ten, String gioiTinh, Date ngaySinh, String diaChi, String cMND, String bHYT) {
        this.idBN = idBN;
        this.ho = ho;
        this.ten = ten;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.cMND = cMND;
        this.bHYT = bHYT;
    }

    public int getIdBN() {
        return idBN;
    }

    public void setIdBN(int idBN) {
        this.idBN = idBN;
    }

    public String getHo() {
        return ho;
    }

    public void setHo(String ho) {
        this.ho = ho;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getcMND() {
        return cMND;
    }

    public void setcMND(String cMND) {
        this.cMND = cMND;
    }

    public String getbHYT() {
        return bHYT;
    }

    public void setbHYT(String bHYT) {
        this.bHYT = bHYT;
    }
    
}
