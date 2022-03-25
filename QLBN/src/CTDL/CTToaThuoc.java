package CTDL;

public class CTToaThuoc {
    int iDTT;
    String maThuoc;
    int soLuong;

    public CTToaThuoc() {
    }

    public CTToaThuoc(int iDTT, String maThuoc, int soLuong) {
        this.iDTT = iDTT;
        this.maThuoc = maThuoc;
        this.soLuong = soLuong;
    }

    public int getiDTT() {
        return iDTT;
    }

    public String getMaThuoc() {
        return maThuoc;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setiDTT(int iDTT) {
        this.iDTT = iDTT;
    }

    public void setMaThuoc(String maThuoc) {
        this.maThuoc = maThuoc;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
    
}
