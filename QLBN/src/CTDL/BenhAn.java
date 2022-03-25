package CTDL;

import java.sql.Date;

public class BenhAn {
    private int idBA;
    private String tenBenh;
    private Date ngayLap;
    private int idBN;

    public BenhAn() {
    }

    public BenhAn(int idBA, String tenBenh, Date ngayLap, int idBN) {
        this.idBA = idBA;
        this.tenBenh = tenBenh;
        this.ngayLap = ngayLap;
        this.idBN = idBN;
    }

    public int getIdBA() {
        return idBA;
    }

    public void setIdBA(int idBA) {
        this.idBA = idBA;
    }

    public String getTenBenh() {
        return tenBenh;
    }

    public void setTenBenh(String tenBenh) {
        this.tenBenh = tenBenh;
    }

    public Date getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(Date ngayLap) {
        this.ngayLap = ngayLap;
    }

    public int getIdBN() {
        return idBN;
    }

    public void setIdBN(int idBN) {
        this.idBN = idBN;
    }
    
}
