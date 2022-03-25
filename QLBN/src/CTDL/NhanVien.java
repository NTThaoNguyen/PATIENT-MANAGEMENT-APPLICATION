package CTDL;

public class NhanVien {
    private int idNV;
    private String ho;
    private String ten;
    private String loai;
    private String maKhoa;

    public NhanVien() {
    }

    public NhanVien(int idNV, String ho, String ten, String loai, String maKhoa) {
        this.idNV = idNV;
        this.ho = ho;
        this.ten = ten;
        this.loai = loai;
        this.maKhoa = maKhoa;
    }

    public int getIdNV() {
        return idNV;
    }

    public void setIdNV(int idNV) {
        this.idNV = idNV;
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

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public String getMaKhoa() {
        return maKhoa;
    }

    public void setMaKhoa(String maKhoa) {
        this.maKhoa = maKhoa;
    }
    
}
