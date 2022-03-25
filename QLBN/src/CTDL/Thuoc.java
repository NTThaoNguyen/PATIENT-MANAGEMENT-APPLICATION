package CTDL;

public class Thuoc {
    private String maThuoc;
    private String tenThuoc;
    private String cachDung;
    private String tHYL;

    public Thuoc() {
    }

    public Thuoc(String maThuoc, String tenThuoc, String cachDung, String tHYL) {
        this.maThuoc = maThuoc;
        this.tenThuoc = tenThuoc;
        this.cachDung = cachDung;
        this.tHYL = tHYL;
    }

    public String getMaThuoc() {
        return maThuoc;
    }

    public void setMaThuoc(String maThuoc) {
        this.maThuoc = maThuoc;
    }

    public String getTenThuoc() {
        return tenThuoc;
    }

    public void setTenThuoc(String tenThuoc) {
        this.tenThuoc = tenThuoc;
    }

    public String getCachDung() {
        return cachDung;
    }

    public void setCachDung(String cachDung) {
        this.cachDung = cachDung;
    }

    public String gettHYL() {
        return tHYL;
    }

    public void settHYL(String tHYL) {
        this.tHYL = tHYL;
    }
    
}
