package CTDL;

import java.sql.Date;

public class PhieuXuatVien {
    private int idPXV;
    private Date ngayLap;
    private int idNV;
    private int idBA;

    public PhieuXuatVien() {
    }

    public PhieuXuatVien(int idPXV, Date ngayLap, int idNV, int idBA) {
        this.idPXV = idPXV;
        this.ngayLap = ngayLap;
        this.idNV = idNV;
        this.idBA = idBA;
    }

    public int getIdPXV() {
        return idPXV;
    }

    public void setIdPXV(int idPXV) {
        this.idPXV = idPXV;
    }

    public Date getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(Date ngayLap) {
        this.ngayLap = ngayLap;
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
