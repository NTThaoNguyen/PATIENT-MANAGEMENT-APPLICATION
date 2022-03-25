package CTDL;
import java.sql.Date;

public class PhieuNhapVien {
    private int idPNV;
    private String tenBenhDuDoan;
    private Date ngayLap;
    private int idNV;
    private int idBA;

    public PhieuNhapVien() {
    }

    public PhieuNhapVien(int idPNV, String tenBenhDuDoan, Date ngayLap, int idNV, int idBA) {
        this.idPNV = idPNV;
        this.tenBenhDuDoan = tenBenhDuDoan;
        this.ngayLap = ngayLap;
        this.idNV = idNV;
        this.idBA = idBA;
    }

    public int getIdPNV() {
        return idPNV;
    }

    public void setIdPNV(int idPNV) {
        this.idPNV = idPNV;
    }

    public String getTenBenhDuDoan() {
        return tenBenhDuDoan;
    }

    public void setTenBenhDuDoan(String tenBenhDuDoan) {
        this.tenBenhDuDoan = tenBenhDuDoan;
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
