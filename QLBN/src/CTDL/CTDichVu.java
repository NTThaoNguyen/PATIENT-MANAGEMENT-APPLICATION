package CTDL;

import java.sql.Date;

public class CTDichVu {
    int iDBA;
    String maDV;
    Date ngaySuDung;

    public CTDichVu() {
    }

    public CTDichVu(int iDBA, String maDV, Date ngaySuDung) {
        this.iDBA = iDBA;
        this.maDV = maDV;
        this.ngaySuDung = ngaySuDung;
    }

    public int getiDBA() {
        return iDBA;
    }

    public String getMaDV() {
        return maDV;
    }

    public Date getNgaySuDung() {
        return ngaySuDung;
    }

    public void setiDBA(int iDBA) {
        this.iDBA = iDBA;
    }

    public void setMaDV(String maDV) {
        this.maDV = maDV;
    }

    public void setNgaySuDung(Date ngaySuDung) {
        this.ngaySuDung = ngaySuDung;
    }
    
}
