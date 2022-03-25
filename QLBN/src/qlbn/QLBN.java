package qlbn;

import GiaoDien.DangNhap;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class QLBN {

    public static Connection layKetNoi(){
        Connection ketNoi = null;
        String uRL = "jdbc:sqlserver://;databaseName=QLBN";
        String userName = "sa";
        String password = "123";
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            ketNoi = DriverManager.getConnection(uRL, userName, password);
            System.out.println("Kết nối thành công rồi nha!");
        }
        catch(ClassNotFoundException | SQLException ex){
            System.out.println("Thôi toang rồi ông giáo ơi" + ex);
        }
        return ketNoi;
    }
    public static void main(String[] args) {
        GiaoDien.DangNhap dn = new DangNhap();
        dn.setVisible(true);
    }
}
