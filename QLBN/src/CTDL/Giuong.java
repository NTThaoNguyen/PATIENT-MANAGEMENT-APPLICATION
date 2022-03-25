package CTDL;

public class Giuong {
    private int idGiuong;
    private String trangThai;
    private String maPhong;

    public Giuong() {
    }

    public Giuong(int idGiuong, String trangThai, String maPhong) {
        this.idGiuong = idGiuong;
        this.trangThai = trangThai;
        this.maPhong = maPhong;
    }

    public int getIdGiuong() {
        return idGiuong;
    }

    public void setIdGiuong(int idGiuong) {
        this.idGiuong = idGiuong;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }
    
}
