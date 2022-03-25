package CTDL;

public class Phong {
    private String maPhong;
    private String maKhoa;

    public Phong() {
    }

    public Phong(String maPhong, String maKhoa) {
        this.maPhong = maPhong;
        this.maKhoa = maKhoa;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public String getMaKhoa() {
        return maKhoa;
    }

    public void setMaKhoa(String maKhoa) {
        this.maKhoa = maKhoa;
    }
    
}
