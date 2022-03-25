package CTDL;

public class PhieuTronVien {
    private int idPTV;
    private String ghiChu;
    private int idNV;
    private int idBA;

    public PhieuTronVien() {
    }

    public PhieuTronVien(int idPTV, String ghiChu, int idNV, int idBA) {
        this.idPTV = idPTV;
        this.ghiChu = ghiChu;
        this.idNV = idNV;
        this.idBA = idBA;
    }

    public int getIdPTV() {
        return idPTV;
    }

    public void setIdPTV(int idPTV) {
        this.idPTV = idPTV;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
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
