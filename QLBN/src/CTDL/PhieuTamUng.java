package CTDL;

import java.sql.Date;

public class PhieuTamUng {
    private int idPTU;
    private Date ngayUng;
    private int soTien;
    private int idNV;
    private int idBA;

    public PhieuTamUng() {
    }

    public PhieuTamUng(int idPTU, Date ngayUng, int soTien, int idNV, int idBA) {
        this.idPTU = idPTU;
        this.ngayUng = ngayUng;
        this.soTien = soTien;
        this.idNV = idNV;
        this.idBA = idBA;
    }

    public int getIdPTU() {
        return idPTU;
    }

    public void setIdPTU(int idPTU) {
        this.idPTU = idPTU;
    }

    public Date getNgayUng() {
        return ngayUng;
    }

    public void setNgayUng(Date ngayUng) {
        this.ngayUng = ngayUng;
    }

    public int getSoTien() {
        return soTien;
    }

    public void setSoTien(int soTien) {
        this.soTien = soTien;
    }

    public int getIdNV() {
        return idNV;
    }

    public void setIdNV(int idNV) {
        this.idNV = idNV;
    }

    public int getIdBA() {
        return idBA;
    }

    public void setIdBA(int idBA) {
        this.idBA = idBA;
    }
    
}
